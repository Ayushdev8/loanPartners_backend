package user.in.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import user.in.loan.entity.type.LoadLevel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerSchemeResponse {
    private Long PartnerSchemeId;
    private String schemeId;
    private Integer avgTurnaroundDays;
    private LoadLevel loadLevel;
    private String updatedBy;

}
