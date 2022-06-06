package com.atlas.skull.Controller;

import com.atlas.skull.Dto.*;
import com.atlas.skull.Service.PaymentService;
import com.atlas.skull.SkullModel.StudentCategory;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController

public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/academicYear")
    @PreAuthorize("hasAuthority('ACTUSER')")
    @Operation(summary = "add new academic year")
    public String saveAcademicYear(@RequestBody AcademicYearRequest academicYearRequest){

        paymentService.addAcademicYear(academicYearRequest);
        return "Success!";
    }

    @PutMapping("/academicYear")
    @PreAuthorize("hasAuthority('ACTUSER')")
    @Operation(summary = "edit academic year")
    public String updateAcademicYear(@RequestBody AcademicYearRequest academicYearRequest, Long id){

        paymentService.editAcademicYear(academicYearRequest, id);
        return "update Success";
    }

    @GetMapping("/academicYear")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'ACTUSER')")
    @Operation(summary = "get all academic years")
    public List<AcademicYearResponse> returnAllAcademicYears(){

        return paymentService.getAllAcademicYear();
    }

    @PostMapping("/paymentCategory")
    @PreAuthorize("hasAuthority('ACTUSER')")
    @Operation(summary = "add payment category, it is a category that a particular grade students pay")
    public String savePaymentCategory(@RequestBody PaymentCategoryRequest paymentCategoryRequest){

        paymentService.addPaymentCategory(paymentCategoryRequest);
        return "success";
    }

    @PutMapping("/paymentCategory")
    @PreAuthorize("hasAuthority('ACTUSER')")
    @Operation(summary = "edit payment category")
    public String updatePaymentCategory(@RequestBody PaymentCategoryRequest paymentCategoryRequest, Long id){

        paymentService.editPaymentCategory(paymentCategoryRequest, id);
        return "update Success";
    }

    @GetMapping("/paymentCategory")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'ACTUSER')")
    @Operation(summary = "get all payment categories")
    public List<PaymentCategoryResponse> returnAllPaymentCategories(){

        return paymentService.getAllPaymentCategories();
    }

    @PostMapping("/paymentPeriod")
    @PreAuthorize("hasAuthority('ACTUSER')")
    @Operation(summary = "add payment period, such as september, october...")
    public String savePaymentPeriod(@RequestBody PaymentPeriodRequest paymentPeriodRequest){

        paymentService.addPaymentPeriod(paymentPeriodRequest);
        return "Success";
    }

    @PutMapping("/paymentPeriod")
    @PreAuthorize("hasAuthority('ACTUSER')")
    @Operation(summary = "edit payment period")
    public String updatePaymentPeriod(@RequestBody PaymentPeriodRequest paymentPeriodRequest, Long id){

        paymentService.editPaymentPeriod(paymentPeriodRequest, id);
        return "update success!";
    }

    @GetMapping("/paymentPeriod")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'ACTUSER')")
    @Operation(summary = "get all payment periods")
    public List<PaymentPeriodResponse> returnAllPaymentPeriods(){

        return paymentService.getAllPaymentPeriod();
    }

    @PostMapping("studentCategory")
    @PreAuthorize("hasAuthority('ACTUSER')")
    @Operation(summary =  "add student category, it is to implement if students has discount")
    public String saveStudentCategory(@RequestBody StudentCategoryRequest studentCategoryRequest){

        paymentService.addStudentCategory(studentCategoryRequest);
        return "Success";
    }

    @PutMapping("/studentCategory")
    @PreAuthorize("hasAuthority('ACTUSER')")
    @Operation(summary = "edit student category")
    public String updateStudentCategory(@RequestBody StudentCategoryRequest studentCategoryRequest, Long id){

        paymentService.editStudentCategory(studentCategoryRequest, id);
        return "update success";
    }

    @GetMapping("studentCategory")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'ACTUSER')")
    @Operation(summary = "get all student categories")
    public List<StudentCategoryResponse> getAllStudentCategories(){

        return paymentService.getAllStudentCategories();
    }

    @PostMapping("/studentPaymentCategory")
    @PreAuthorize("hasAuthority('ACTUSER')")
    @Operation(summary = "add new student payment categories")
    public String saveStudentPaymentCategory(@RequestBody StudentPaymentCategoryRequest categoryRequest){

        paymentService.addStudentPaymentCategory(categoryRequest);
        return "Success";
    }

    @PutMapping("/studentPaymentCategory")
    @PreAuthorize("hasAuthority('ACTUSER')")
    @Operation(summary = "edit student payment category")
    public String updateStudentPaymentCategory(@RequestBody StudentPaymentCategoryRequest categoryRequest, Long id){

        paymentService.editStudentPaymentCategory(categoryRequest, id);
        return "update success";
    }

    @GetMapping("/studentPaymentCategory")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'ACTUSER')")
    @Operation(summary = "get all student payment categories")
    public List<StudentPaymentCategoryResponse> getAllStudentPaymentCategories(){

        return paymentService.getAllStudentPaymentCategories();
    }

}
