package com.atlas.skull.Mapper;

import com.atlas.skull.Dto.SchoolFeeResponse;
import com.atlas.skull.Dto.StudentRequest;
import com.atlas.skull.Dto.StudentResponse;
import com.atlas.skull.SkullModel.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "verificationStatus", expression = "java(1)")
    @Mapping(target = "unPenalizable", expression = "java(0)")
    @Mapping(target = "studentCategory", source = "studentCategory")
    @Mapping(target = "section", source = "section")
    @Mapping(target = "party", source = "party")
    @Mapping(target = "is_active", expression = "java(1)")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "customerBankName", source = "branch.name")
    @Mapping(target = "customerBankAcc", source = "studentRequest.studentId")
    Student registerStudent(StudentRequest studentRequest, Party party, Section section, Branch branch,
                            StudentCategory studentCategory);

    @Mapping(target = "phone", source = "student.party.address.phone")
    @Mapping(target = "middleName", source = "party.middleName")
    @Mapping(target = "lastName", source = "party.lastName")
    @Mapping(target = "grade", source = "section.grade.grade")
    @Mapping(target = "firstName", source = "party.firstName")
    @Mapping(target = "section", source = "section.section")
    StudentResponse getStudent(Student student);

    @Mapping(target = "status", source = "periodicPaymentStatus.name")
    @Mapping(target = "amount", source = "studentPeriodicPayments.studentPaymentCategory.price")
    @Mapping(target = "academicYear", source = "studentPeriodicPayments.studentPaymentCategory.paymentCategory.academicYear.name")
    @Mapping(target = "paymentPeriod", source = "paymentPeriod.name")
    SchoolFeeResponse getSchoolFee(PeriodicPayment periodicPayment);
}
