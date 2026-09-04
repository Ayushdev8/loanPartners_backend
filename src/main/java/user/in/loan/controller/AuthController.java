package user.in.loan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.in.loan.dto.PartnerResponseDto;
import user.in.loan.dto.PartnerSignupRequestDto;
import user.in.loan.service.AuthService;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<PartnerResponseDto> registerPartnerAndAdmin(@RequestBody PartnerSignupRequestDto request){
        System.out.println("Authcontroller started");
        PartnerResponseDto response = authService.registerPartner(request);
        return ResponseEntity.ok(response);
    }
}
