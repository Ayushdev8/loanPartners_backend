package user.in.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequestDto {
    private String applicantId;
    private String applicantName;

    private String schemeId;

    private Long partnerId;
    private String matchedReason;
}
