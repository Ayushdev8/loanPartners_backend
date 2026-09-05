package user.in.loan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import user.in.loan.entity.type.PartnerType;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "partner")
public class Partner {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 150)
        private String name;

        @Enumerated(EnumType.STRING)
        @Column(name = "partner_type", nullable = false, length = 20)
        private PartnerType partnerType;

        @Column(length = 50)
        private String state;

        @Column(length = 50)
        private String district;

        @Column(columnDefinition = "TEXT")
        private String address;

        private Double latitude;
        private Double longitude;

        @Column(length = 20)
        private String phone;

        @Column(length = 100)
        private String email;

        @Column(length = 255)
        private String website;

        @Column(name = "npa_flag")
        private Boolean npaFlag = false;

        @Column(name = "is_verified")
        private Boolean isVerified = true;

        @Column(name = "created_at", updatable = false)
        private LocalDateTime createdAt;

        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

        @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<PartnerAdmin> admins;
        @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<PartnerScheme> partnerSchemes;
}

