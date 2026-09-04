package user.in.loan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import user.in.loan.entity.type.LoadLevel;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "partner_scheme")

public class PartnerScheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id", nullable = false)
    private Partner partner;

    private String schemeId;

//    @Column(name = "accepting_applications")
//    private Boolean acceptingApplications = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_load", length = 10)
    private LoadLevel currentLoad = LoadLevel.LOW;

    @Column(name = "avg_turnaround_days")
    private Integer avgTurnaroundDays;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private PartnerAdmin updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}
