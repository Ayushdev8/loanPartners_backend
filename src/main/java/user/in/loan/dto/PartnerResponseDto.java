package user.in.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import user.in.loan.entity.type.PartnerType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerResponseDto {
    private Long partnerId;
    private String partnerName;
    private String partnerEmail;
    private PartnerType partnerType;
    private String partnerPhone;
    private String state;
    private String district;
    private Double latitude;
    private Double longitude;
    private String website;
    private String partnerAddress;
    private boolean isVerified;
    private LocalDateTime createdAt;
}
