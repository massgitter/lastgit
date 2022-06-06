package com.atlas.skull.Service;

import com.atlas.skull.Dto.*;
import com.atlas.skull.Exception.ResourceNotFound;
import com.atlas.skull.Mapper.PaymentMapper;
import com.atlas.skull.SkullModel.*;
import com.atlas.skull.SkullRepository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service

public class PaymentService {

    private final StudentPaymentCategoryRepository studentPaymentCategoryRepository;
    private final PaymentCategoryRepository paymentCategoryRepository;
    private final StudentCategoryRepository studentCategoryRepository;
    private final AcademicYearRepository academicYearRepository;
    private final PaymentPeriodRepository paymentPeriodRepository;
    private final PaymentMapper paymentMapper;

    public void addAcademicYear(AcademicYearRequest academicYearRequest){

        academicYearRepository.save(paymentMapper.saveIntoAcademicYear(academicYearRequest));
    }

    public void editAcademicYear(AcademicYearRequest academicYearRequest, Long id){

        AcademicYear academicYear2bUpdated = academicYearRepository.findById(id).orElseThrow(()-> new
                ResourceNotFound("academicYear not found!"));
        academicYear2bUpdated.setName(academicYearRequest.getName());
        academicYear2bUpdated.setDescription(academicYearRequest.getDescription());
        academicYear2bUpdated.setStartDate(academicYearRequest.getStartDate());
        academicYear2bUpdated.setEndDate(academicYearRequest.getEndDate());
        academicYear2bUpdated.setUpdatedOn(java.time.Instant.now());
        academicYearRepository.save(academicYear2bUpdated);
    }

    public List<AcademicYearResponse> getAllAcademicYear(){

        return academicYearRepository.findAll().stream().map(paymentMapper::getFromAcademicYear)
                .collect(Collectors.toList());
    }

    public void addPaymentCategory(PaymentCategoryRequest paymentCategoryRequest){

        AcademicYear academicYear = academicYearRepository.findById(paymentCategoryRequest.getAcademicYearId())
                .orElseThrow(()-> new ResourceNotFound("academicYear not found"));
        paymentCategoryRepository.save(paymentMapper.saveIntoPaymentCategory(paymentCategoryRequest, academicYear));
    }

    public void editPaymentCategory(PaymentCategoryRequest paymentCategoryRequest, Long id){

        PaymentCategory category2bUpdated = paymentCategoryRepository.findById(id).orElseThrow(()-> new
                ResourceNotFound("Payment category not found"));

        AcademicYear academicYear = academicYearRepository.findById(paymentCategoryRequest.getAcademicYearId())
                .orElseThrow(()-> new ResourceNotFound("Academic year not found"));

        category2bUpdated.setName(paymentCategoryRequest.getName());
        category2bUpdated.setDescription(paymentCategoryRequest.getDescription());
        category2bUpdated.setUpdatedOn(java.time.Instant.now());
        category2bUpdated.setAcademicYear(academicYear);
        paymentCategoryRepository.save(category2bUpdated);

    }

    public List<PaymentCategoryResponse> getAllPaymentCategories(){

        return paymentCategoryRepository.findAll().stream().map(paymentMapper::getFromPaymentCategory)
                .collect(Collectors.toList());
    }

    public void addPaymentPeriod(PaymentPeriodRequest paymentPeriodRequest){

        AcademicYear academicYear = academicYearRepository.findById(paymentPeriodRequest.getAcademicYearId())
                .orElseThrow(()-> new ResourceNotFound("Academic year not found"));

        paymentPeriodRepository.save(paymentMapper.saveIntoPaymentPeriod(paymentPeriodRequest, academicYear));
    }

    public void editPaymentPeriod(PaymentPeriodRequest paymentPeriodRequest, Long id){

        PaymentPeriod period2bUpdated = paymentPeriodRepository.findById(id).orElseThrow(()-> new
                ResourceNotFound("not found"));

        AcademicYear academicYear = academicYearRepository.findById(paymentPeriodRequest.getAcademicYearId())
                .orElseThrow(()-> new ResourceNotFound("Academic year not found"));

        period2bUpdated.setName(paymentPeriodRequest.getName());
        period2bUpdated.setDescription(paymentPeriodRequest.getDescription());
        period2bUpdated.setUpdatedOn(java.time.Instant.now());
        period2bUpdated.setStartDate(paymentPeriodRequest.getStartDate());
        period2bUpdated.setEndDate(paymentPeriodRequest.getEndDate());
        period2bUpdated.setAcademicYear(academicYear);
        paymentPeriodRepository.save(period2bUpdated);
    }

    public List<PaymentPeriodResponse> getAllPaymentPeriod(){

        return paymentPeriodRepository.findAll().stream().map(paymentMapper::getFromPaymentPeriod)
                .collect(Collectors.toList());
    }

    public void addStudentCategory(StudentCategoryRequest studentCategoryRequest){

        studentCategoryRepository.save(paymentMapper.saveIntoStudentCategory(studentCategoryRequest));
    }

    public void  editStudentCategory(StudentCategoryRequest studentCategoryRequest, Long id){

        StudentCategory category2bUpdated = studentCategoryRepository.findById(id).orElseThrow(()->
                new ResourceNotFound(""));
        category2bUpdated.setName(studentCategoryRequest.getName());
        category2bUpdated.setDescription(studentCategoryRequest.getDescription());
        category2bUpdated.setUpdatedOn(java.time.Instant.now());
        studentCategoryRepository.save(category2bUpdated);
    }

    public List<StudentCategoryResponse> getAllStudentCategories(){

        return studentCategoryRepository.findAll().stream().map(paymentMapper::getFromStudentCategory).
                collect(Collectors.toList());
    }

    public void addStudentPaymentCategory(StudentPaymentCategoryRequest studentPaymentCategoryRequest){

        PaymentCategory paymentCategory = paymentCategoryRepository.findById(studentPaymentCategoryRequest.
                getPaymentCategoryId()).orElseThrow(()-> new ResourceNotFound(""));

        StudentCategory studentCategory = studentCategoryRepository.findById(studentPaymentCategoryRequest.
                getStudentCategoryId()).orElseThrow(()-> new ResourceNotFound(""));

        studentPaymentCategoryRepository.save(paymentMapper.saveIntoStudentPaymentCategory
                (studentPaymentCategoryRequest, paymentCategory, studentCategory));
    }

    public void editStudentPaymentCategory(StudentPaymentCategoryRequest studentPaymentCategoryRequest, Long id){

        StudentPaymentCategory category2bUpdated = studentPaymentCategoryRepository.findById(id).
                orElseThrow(()-> new ResourceNotFound(""));

        PaymentCategory paymentCategory = paymentCategoryRepository.findById(studentPaymentCategoryRequest.
                getPaymentCategoryId()).orElseThrow(()-> new ResourceNotFound(""));

        StudentCategory studentCategory = studentCategoryRepository.findById(studentPaymentCategoryRequest.
                getStudentCategoryId()).orElseThrow(()-> new ResourceNotFound(""));

        category2bUpdated.setPaymentCategory(paymentCategory);
        category2bUpdated.setStudentCategory(studentCategory);
        category2bUpdated.setPrice(studentPaymentCategoryRequest.getPrice());
        studentPaymentCategoryRepository.save(category2bUpdated);
    }

    public List<StudentPaymentCategoryResponse> getAllStudentPaymentCategories(){

        return studentPaymentCategoryRepository.findAll().stream().map(paymentMapper::getFromStudentPaymentCategory)
                .collect(Collectors.toList());
    }

}
