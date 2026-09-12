package user.in.loan.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import user.in.loan.entity.type.ApplicationStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "applicant_id")
    private String applicantId;
    @Column(name = "applicant_name")
    private String applicantName;


    @Column(length = 100)
    private String schemeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @Column(name = "matched_reason", columnDefinition = "TEXT")
    private String matchedReason;

//    @Column(name = "principal_amount", precision = 12, scale = 2)
//    private BigDecimal principalAmount;
//
//    @Column(name = "tenure_months")
//    private Integer tenureMonths;
//
//    @Column(name = "monthly_emi", precision = 12, scale = 2)
//    private BigDecimal monthlyEmi;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ApplicationStatus status = ApplicationStatus.RECOMMENDED;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
