package user.in.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import user.in.loan.entity.type.LoadLevel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchemeRequestDto {
    private String schemeId;
    private Integer avgTurnaroundDays;
    private LoadLevel loadLevel;

}
