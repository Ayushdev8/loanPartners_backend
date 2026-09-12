package user.in.loan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.in.loan.dto.*;
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

    @GetMapping("/get-partners")
    public ResponseEntity<List<PartnerResponseDto>> getAllPartners(){
        List<PartnerResponseDto> response = partnerService.getPartners();
        return ResponseEntity.ok(response);

    }
    @PostMapping("/addApplication")
    public ResponseEntity<String> addApplication(@RequestBody ApplicationRequestDto request){
        System.out.println("add application");
        String response = partnerService.addApplications(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("get-applications/{partnerId}")
    public ResponseEntity<List<ApplicationResponseDto>> getAllApplications(@PathVariable Long partnerId){
        System.out.println("get applications");
        List<ApplicationResponseDto> response = partnerService.getApplications(partnerId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
        public ResponseEntity<String> health(){
        return ResponseEntity.ok("ok");
        }

}
