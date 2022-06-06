package com.atlas.skull.Mapper;

import com.atlas.skull.Dto.*;
import com.atlas.skull.SkullModel.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface PaymentMapper {

    @Mapping(target = "updatedOn", expression = "java(java.time.Instant.now())")
    @Mapping(target = "id", ignore = true)
    AcademicYear saveIntoAcademicYear(AcademicYearRequest academicYearRequest);

    AcademicYearResponse getFromAcademicYear(AcademicYear academicYear);

    @Mapping(target = "updatedOn", expression = "java(java.time.Instant.now())")
    @Mapping(target = "id", ignore = true)
    StudentCategory saveIntoStudentCategory(StudentCategoryRequest studentCategoryRequest);

    StudentCategoryResponse getFromStudentCategory(StudentCategory studentCategory);

    @Mapping(target = "updatedOn", expression = "java(java.time.Instant.now())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "academicYear", source = "academicYear")
    @Mapping(target = "name", source = "paymentCategoryRequest.name")
    @Mapping(target = "description", source = "paymentCategoryRequest.description")
    PaymentCategory saveIntoPaymentCategory(PaymentCategoryRequest paymentCategoryRequest,
                                            AcademicYear academicYear);

    @Mapping(target = "academicYear", source = "academicYear.name")
    PaymentCategoryResponse getFromPaymentCategory(PaymentCategory paymentCategory);

    @Mapping(target = "studentCategory", source = "studentCategory")
    @Mapping(target = "paymentCategory", source = "paymentCategory")
    @Mapping(target = "id", ignore = true)
    StudentPaymentCategory saveIntoStudentPaymentCategory(StudentPaymentCategoryRequest
                 studentPaymentCategoryRequest, PaymentCategory paymentCategory, StudentCategory studentCategory);

    @Mapping(target = "paymentCategory", source = "paymentCategory.name")
    @Mapping(target = "studentCategory", source = "studentCategory.name")
    StudentPaymentCategoryResponse getFromStudentPaymentCategory(StudentPaymentCategory studentPaymentCategory);

    @Mapping(target = "name", source = "paymentPeriodRequest.name")
    @Mapping(target = "description", source = "paymentPeriodRequest.description")
    @Mapping(target = "startDate", source = "paymentPeriodRequest.startDate")
    @Mapping(target = "endDate", source = "paymentPeriodRequest.endDate")
    @Mapping(target = "academicYear", source = "academicYear")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedOn", expression = "java(java.time.Instant.now())")
    PaymentPeriod saveIntoPaymentPeriod(PaymentPeriodRequest paymentPeriodRequest,
                                        AcademicYear academicYear);

    @Mapping(target = "academicYear", source = "academicYear.name")
    PaymentPeriodResponse getFromPaymentPeriod(PaymentPeriod paymentPeriod);

}
