package com.atlas.skull.SkullRepository;

import com.atlas.skull.Dto.StudentResponse;
import com.atlas.skull.SkullModel.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByStudentId(String studentId);

    List<Student> findAllByCustomerBankName(String customerBankName, Pageable pageable);

    List<Student> findAllByParty_FirstName(String party_firstName);

    List<Student> findAllByParty_Address_Phone(String party_address_phone);

    List<Student> findAllByParty_MiddleName(String party_middleName);

    List<Student> findAllByParty_LastName(String party_lastName);

    List<Student> findAllByParty_FirstNameAndParty_MiddleName(String party_firstName, String party_middleName);

    List<Student> findAllByParty_FirstNameAndParty_MiddleNameAndParty_LastName(String party_firstName,
                                                                      String party_middleName, String party_lastName);

    List<Student> findAllBySection_Grade_Id(Long section_grade_id, Pageable pageable);

    List<Student> findAllBySection_Grade_IdAndCustomerBankName(Long section_grade_id, String customerBankName,
                                                               Pageable pageable);

    List<Student> findAllBySection_Grade_IdAndCustomerBankNameAndParty_FirstName(Long section_grade_id,
                                                                                 String customerBankName, String party_firstName);
}
