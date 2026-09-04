package user.in.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import user.in.loan.entity.type.PartnerType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerResponseDto {
    private Long partnerId;
    private String partnerName;
    private String partnerEmail;
    private PartnerType partnerType;
    private String message;
//    private String partnerEmail;
//    private String partnerPhone;
//    private String state;
//    private String district;
//    private Double latitude;
//    private Double longitude;
//    private String website;
//    private String partnerAddress;
}
