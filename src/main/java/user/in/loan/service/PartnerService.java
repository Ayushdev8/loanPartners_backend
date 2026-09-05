package user.in.loan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import user.in.loan.dto.PartnerResponseDto;
import user.in.loan.dto.PartnerSchemeResponse;
import user.in.loan.dto.SchemeRequestDto;

import user.in.loan.entity.Partner;
import user.in.loan.entity.PartnerAdmin;
import user.in.loan.entity.PartnerScheme;
import user.in.loan.repository.PartnerAdminRepository;
import user.in.loan.repository.PartnerRepository;
import user.in.loan.repository.PartnerSchemeRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerService {
    private final PartnerRepository partnerRepository;
    private final PartnerAdminRepository partnerAdminRepository;
    private final PartnerSchemeRepository partnerSchemeRepository;
    public String addScheme(SchemeRequestDto request,Long partnerId){
        Partner partner = partnerRepository.findById(partnerId).orElseThrow(
                ()->new RuntimeException("partner not found")
        );
        PartnerAdmin partnerAdmin = partnerAdminRepository.findByPartner(partner);
        if(partnerAdmin == null){
            throw new RuntimeException("partner Admin not found");
        }

        PartnerScheme partnerScheme = PartnerScheme.builder()
                .partner(partner)
                .schemeId(request.getSchemeId())
                .avgTurnaroundDays(request.getAvgTurnaroundDays())
                .currentLoad(request.getLoadLevel())
                .updatedBy(partnerAdmin)
                .build();

        partnerSchemeRepository.save(partnerScheme);
        return "Scheme added successfully";
    }

    public List<PartnerSchemeResponse> getScheme(Long partnerId){
        Partner partner = partnerRepository.findById(partnerId).orElseThrow(
                ()->new RuntimeException("organization not found ")
        );
        List<PartnerScheme> partnerSchemes = partnerSchemeRepository.findByPartnerId(partner.getId());
        if(partnerSchemes == null){
            throw new RuntimeException(" schemes not found");
        }

        return partnerSchemes.stream()
                .map(partnerScheme -> {
                    PartnerSchemeResponse dto = new PartnerSchemeResponse();
                    dto.setPartnerSchemeId(partnerScheme.getId());


                    dto.setSchemeId(partnerScheme.getSchemeId());
                    dto.setLoadLevel(partnerScheme.getCurrentLoad());
                    dto.setAvgTurnaroundDays(partnerScheme.getAvgTurnaroundDays());
                    dto.setUpdatedBy(partnerScheme.getUpdatedBy().getUsername());


                    return dto;
                })
                .toList();
    }

    public List<PartnerResponseDto> getAllPartners(String schemeId){
        List<PartnerScheme> partnerSchemes = partnerSchemeRepository.findBySchemeId(schemeId);
        if(partnerSchemes.isEmpty()){
            throw new RuntimeException("For this scheme there is not any partner");
        }

        return partnerSchemes.stream()
                .map(partnerScheme -> {
                    PartnerResponseDto dto = new PartnerResponseDto(
                            partnerScheme.getPartner().getId(),
                            partnerScheme.getPartner().getName(),
                            partnerScheme.getPartner().getEmail(),
                            partnerScheme.getPartner().getPartnerType(),
                            partnerScheme.getPartner().getPhone(),
                            partnerScheme.getPartner().getState(),
                            partnerScheme.getPartner().getDistrict(),
                            partnerScheme.getPartner().getLatitude(),
                            partnerScheme.getPartner().getLongitude(),
                            partnerScheme.getPartner().getWebsite(),
                            partnerScheme.getPartner().getAddress(),
                            partnerScheme.getPartner().getIsVerified(),
                            partnerScheme.getPartner().getCreatedAt()


                    );
                    return dto;
                })
                .toList();

    }
}
