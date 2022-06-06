package com.atlas.skull.SkullModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "paymentCategory")

public class PaymentCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Instant updatedOn;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch =  FetchType.EAGER)
    @JoinColumn(name = "academicYear_id")
    private AcademicYear academicYear;
}
