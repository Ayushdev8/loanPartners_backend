package user.in.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import user.in.loan.entity.type.ApplicationStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponseDto {
    private String applicantId;
    private String applicantName;
    private String schemeId;
    private String matchedReason;
    private ApplicationStatus status;
    private LocalDateTime createdAt;

}
