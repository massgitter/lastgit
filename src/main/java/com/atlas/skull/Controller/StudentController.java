package com.atlas.skull.Controller;

import com.atlas.skull.Dto.SchoolFeeResponse;
import com.atlas.skull.Dto.StudentRequest;
import com.atlas.skull.Dto.StudentResponse;
import com.atlas.skull.Exception.ResourceNotFound;
import com.atlas.skull.Service.StudentService;
import com.atlas.skull.SkullModel.Student;
import com.atlas.skull.SkullRepository.StudentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController

public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @PostMapping("/student")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER')")
    @Operation(summary = "you can Register new student here!")
    public String saveStudent(@RequestBody StudentRequest studentRequest){

        studentService.addStudent(studentRequest);
        return studentRequest.getFirstName() + " " + studentRequest.getMiddleName() + " successfully Registered";
    }

    @PutMapping("/student")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER')")
    @Operation(summary = "i will help you to edit student info")
    public String renameStudent(@RequestBody StudentRequest studentRequest, Long id){

        studentService.editStudentInfo(studentRequest, id);
        return "update success";
    }

    @DeleteMapping("/student")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER')")
    @Operation(summary = "I was your student, now am leaving please delete me?")
    public String removeStudent(Long id){

        studentService.deleteStudent(id);
        Student student2bDeleted = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFound(""));
        return student2bDeleted.getParty().getFirstName() + " " + student2bDeleted.getParty().getMiddleName() +
                " deleted";
    }

    @GetMapping("/student")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'ACTUSER')")
    @Operation(summary = "i am fetching 15 students  per page out of all from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully fetched"),

            @ApiResponse(responseCode = "404", description =  "student not available")
    })
    public List<StudentResponse> returnAllStudents(@RequestParam(defaultValue = "0") int pageNo,
                                                   @RequestParam(defaultValue = "15") int pageSize,
                                                   @RequestParam(defaultValue = "id") String sortBy){

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return studentService.getAllStudent(paging);
    }

    @GetMapping("/student/studentId")
    @Operation(summary = "you can search a student with his/her id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "404", description = "student not available yet")
    })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'ACTUSER')")
    public List<StudentResponse> returnStudentsByStudentId(String studentId){

        return studentService.getStudentsByStudentId(studentId);
    }

    @GetMapping("/student/phone")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'ACTUSER')")
    @Operation(summary = "find student by phone")
    public List<StudentResponse> returnAllStudentsByPhone(String phone){

        return studentService.getAllStudentByPhone(phone);
    }

    @GetMapping("/student/firstName")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'ACTUSER')")
    @Operation(summary = "search student by student's name")
    public List<StudentResponse> returnStudentsByFirstName(String firstName){

        return studentService.getAllStudentsByFirstName(firstName);
    }

    @GetMapping("/student/middleName")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'ACTUSER')")
    @Operation(summary = "find a student by father's name")
    public List<StudentResponse> returnAllStudentsByMiddleName(String middleName){

        return studentService.getAllStudentsByMiddleName(middleName);
    }

    @GetMapping("/student/lastName")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'ACTUSER')")
    @Operation(summary = "find a student by grand father's name")
    public List<StudentResponse> returnAllStudentsByLastName(String lastname){

        return studentService.getAllStudentsByLastName(lastname);
    }

    @GetMapping("/student/firstAndMiddleName")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'ACTUSER')")
    @Operation(summary = "find a student by student's and father's name")
    public List<StudentResponse>returnStudetsByFirstAndMiddleName(String firstName, String middleName){

        return studentService.getStudentsByFirstAndMiddleName(firstName, middleName);
    }

    @GetMapping("/student/fullName")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'ACTUSER')")
    @Operation(summary = "find a student by full name")
    public List<StudentResponse> returnStudentsByFullName(String firstName, String middleName, String lastName){

        return studentService.getStudentsByFullName(firstName, middleName, lastName);
    }

    @GetMapping("/student/byGrade")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'ACTUSER')")
    @Operation(summary = "find a student by grade")
    public List<StudentResponse> returnAllStudentsByGrade(Long id,
                                                          @RequestParam(defaultValue = "0") int pageNo,
                                                          @RequestParam(defaultValue = "15") int pageSize,
                                                          @RequestParam(defaultValue = "id") String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return studentService.getAllStudentsByGrade(id, paging);

    }

    @GetMapping("/student/byBranch")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'ACTUSER')")
    @Operation(summary = "find a student by Branch")
    public List<StudentResponse> returnStudentsByBranch(String branch,
                                                                @RequestParam(defaultValue = "0") int pageNo,
                                                                @RequestParam(defaultValue = "15") int pageSize,
                                                                @RequestParam(defaultValue = "id") String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return studentService.getAllStudentsByBranch(branch, paging);
    }

    @GetMapping("/student/byBranchAndGrade")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'ACTUSER')")
    @Operation(summary = "find a student by branch and grade")
    public List<StudentResponse> returnStudentsByGradeAndBranch(Long gradeId, String branch,
                                                                @RequestParam(defaultValue = "0") int pageNo,
                                                                @RequestParam(defaultValue = "15") int pageSize,
                                                                @RequestParam(defaultValue = "id") String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return studentService.getAllStudentsByGradeANdBranch(gradeId, branch, paging);
    }

    @GetMapping("/student/byAll")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'ACTUSER')")
    @Operation(summary = "find a student by students name grade and branch")
    public List<StudentResponse> returnStudentsByGradeBranchAndFirstName(Long gradeId, String branch, String firstName){

        return studentService.getAllStudentsByGradeBranchAndFirstName(gradeId, branch, firstName);
    }

    @GetMapping("/student/schoolFee")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'ACTUSER')")
    @Operation(summary = "you can see school fee info of a single student here ")
    public List<SchoolFeeResponse> returnSchoolFee(Long id){

        return studentService.getSchoolFee(id);
    }

}
