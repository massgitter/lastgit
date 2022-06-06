package com.atlas.skull.Service;

import com.atlas.skull.Dto.SchoolFeeResponse;
import com.atlas.skull.Dto.StudentRequest;
import com.atlas.skull.Dto.StudentResponse;
import com.atlas.skull.Exception.ResourceNotFound;
import com.atlas.skull.Mapper.StudentMapper;
import com.atlas.skull.SkullModel.*;
import com.atlas.skull.SkullRepository.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service

public class StudentService {

    private final PeriodicPaymentRepository periodicPaymentRepository;
    private final StudentCategoryRepository studentCategoryRepository;
    private final StudentRepository studentRepository;
    private final AddressRepository addressRepository;
    private final PartyRepository partyRepository;
    private final GradeRepository gradeRepository;
    private final SectionRepository sectionRepository;
    private final BranchRepository branchRepository;
    private StudentMapper studentMapper;

    public void  addStudent(StudentRequest studentRequest){

        Address address = new Address();
        address.setPhone(studentRequest.getPhone());
        addressRepository.save(address);

        Party party = new Party();
        party.setFirstName(studentRequest.getFirstName());
        party.setMiddleName(studentRequest.getMiddleName());
        party.setLastName(studentRequest.getLastName());
        party.setAddress(address);
        partyRepository.save(party);

        Grade grade = gradeRepository.findById(studentRequest.getGradeId()).orElseThrow(()-> new
                ResourceNotFound("grade not found"));

        Section section = sectionRepository.findBySectionAndGrade_Id(studentRequest.getSection(), grade.getId())
                .orElseThrow(()-> new ResourceNotFound("section not found"));

        Branch branch = branchRepository.findById(studentRequest.getBranchId()).orElseThrow(()-> new
                ResourceNotFound("branch not found"));

        StudentCategory studentCategory = studentCategoryRepository.findById(studentRequest.getStudentCategoryId())
                .orElseThrow(()-> new ResourceNotFound("student category not found"));
        studentRepository.save(studentMapper.registerStudent(studentRequest, party, section, branch, studentCategory));

    }

    public void editStudentInfo(StudentRequest studentRequest, Long id){

        Student student2bUpdated = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFound(""));
        student2bUpdated.getParty().setFirstName(studentRequest.getFirstName());
        student2bUpdated.getParty().setMiddleName(studentRequest.getMiddleName());
        student2bUpdated.getParty().setLastName(studentRequest.getLastName());
        student2bUpdated.getParty().getAddress().setPhone(studentRequest.getPhone());
        student2bUpdated.setStudentId(studentRequest.getStudentId());
        student2bUpdated.setCustomerBankAcc(studentRequest.getStudentId());

        Grade grade = gradeRepository.findById(studentRequest.getGradeId()).orElseThrow(()->
                new ResourceNotFound("grade"));
        student2bUpdated.getSection().setGrade(grade);

        Section section = sectionRepository.findBySectionAndGrade_Id(studentRequest.getSection(),
                studentRequest.getGradeId()).orElseThrow(()-> new ResourceNotFound("section not found"));
        student2bUpdated.setSection(section);

        Branch branch = branchRepository.findById(studentRequest.getBranchId()).orElseThrow(()->
                new ResourceNotFound(""));
        student2bUpdated.setCustomerBankName(branch.getName());

        StudentCategory studentCategory = studentCategoryRepository.findById(studentRequest.getStudentCategoryId())
                .orElseThrow(()-> new ResourceNotFound("Student category"));
        student2bUpdated.setStudentCategory(studentCategory);
        studentRepository.save(student2bUpdated);
    }

    public String deleteStudent(Long id){

        Student student2bDeleted = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFound(""));
        studentRepository.deleteById(id);
        return student2bDeleted.getParty().getFirstName() + " " + student2bDeleted.getParty().getMiddleName()
                + " deleted";
    }

    public List<StudentResponse> getAllStudent(Pageable pageable){

        return studentRepository.findAll(pageable).stream().map(studentMapper::getStudent).collect(Collectors.toList());
    }

    public List<StudentResponse> getStudentsByStudentId(String studentId){

        return studentRepository.findByStudentId(studentId).stream().map(studentMapper::getStudent)
                .collect(Collectors.toList());
    }

    public List<StudentResponse> getAllStudentsByFirstName(String firstName){

        return studentRepository.findAllByParty_FirstName(firstName).stream().map(studentMapper::getStudent)
                .collect(Collectors.toList());
    }

    public List<StudentResponse> getAllStudentByPhone(String phone){

        return studentRepository.findAllByParty_Address_Phone(phone).stream().map(studentMapper::getStudent)
                .collect(Collectors.toList());
    }

    public List<StudentResponse> getAllStudentsByMiddleName(String middleName){

        return studentRepository.findAllByParty_MiddleName(middleName).stream().map(studentMapper::getStudent)
                .collect(Collectors.toList());
    }

    public List<StudentResponse> getAllStudentsByLastName(String lastName){

        return studentRepository.findAllByParty_LastName(lastName).stream().map(studentMapper::getStudent)
                .collect(Collectors.toList());
    }

    public List<StudentResponse> getStudentsByFirstAndMiddleName(String firstName, String middle_name){

        return studentRepository.findAllByParty_FirstNameAndParty_MiddleName(firstName, middle_name).stream()
                .map(studentMapper::getStudent).collect(Collectors.toList());
    }

    public List<StudentResponse> getStudentsByFullName(String firstName, String middleName, String lastName){

        return studentRepository.findAllByParty_FirstNameAndParty_MiddleNameAndParty_LastName(firstName, middleName,
                lastName).stream().map(studentMapper::getStudent).collect(Collectors.toList());
    }

    public List<StudentResponse> getAllStudentsByGrade(Long gradeId, Pageable pageable){

        return studentRepository.findAllBySection_Grade_Id(gradeId, pageable).stream().map(studentMapper::getStudent)
                .collect(Collectors.toList());
    }

    public List<StudentResponse> getAllStudentsByBranch(String branch, Pageable pageable){

        return studentRepository.findAllByCustomerBankName(branch, pageable).stream().map(studentMapper::getStudent)
                .collect(Collectors.toList());
    }

    public List<StudentResponse> getAllStudentsByGradeANdBranch(Long gradeId, String branch, Pageable pageable){

        return studentRepository.findAllBySection_Grade_IdAndCustomerBankName(gradeId, branch, pageable).stream()
                .map(studentMapper::getStudent).collect(Collectors.toList());
    }

    public List<SchoolFeeResponse> getSchoolFee(Long id){

        return periodicPaymentRepository.findAllByStudentPeriodicPayments_Student_Id(id).stream()
                .map(studentMapper::getSchoolFee).collect(Collectors.toList());
    }

    public List<StudentResponse> getAllStudentsByGradeBranchAndFirstName(Long id, String branch, String firstName){

        return studentRepository.findAllBySection_Grade_IdAndCustomerBankNameAndParty_FirstName(id, branch, firstName)
                .stream().map(studentMapper::getStudent).collect(Collectors.toList());
    }

}
