package user.in.loan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.in.loan.dto.PartnerSchemeResponse;
import user.in.loan.dto.PartnerSignupRequestDto;
import user.in.loan.dto.PartnerResponseDto;
import user.in.loan.dto.SchemeRequestDto;
import user.in.loan.service.PartnerService;

import java.util.List;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PartnerController {
    private final PartnerService partnerService;
    @PostMapping("/add/scheme/{partnerId}")
    public ResponseEntity<String> addSchemes(@PathVariable Long partnerId,@RequestBody SchemeRequestDto request){
        String response = partnerService.addScheme(request,partnerId);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/get/partnerSchemes/{partnerId}")
    public ResponseEntity<List<PartnerSchemeResponse>> getSchemes(@PathVariable Long partnerId){
        System.out.println("get partner schemes");
        List<PartnerSchemeResponse> response = partnerService.getScheme(partnerId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/partners/{schemeId}")
    public ResponseEntity<List<PartnerResponseDto>> getPartners(@PathVariable String schemeId){
        System.out.println("get partner");
        List<PartnerResponseDto> response = partnerService.getAllPartners(schemeId);
        return ResponseEntity.ok(response);

    }

}
