package user.in.loan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import user.in.loan.dto.*;

import user.in.loan.entity.Application;
import user.in.loan.entity.Partner;
import user.in.loan.entity.PartnerAdmin;
import user.in.loan.entity.PartnerScheme;
import user.in.loan.entity.type.ApplicationStatus;
import user.in.loan.repository.ApplicationRepository;
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
    private final ApplicationRepository applicationRepository;
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

    public List<PartnerResponseDto> getPartners(){
        List<Partner> partners = partnerRepository.findAll();
        if(partners.isEmpty()){
            throw new RuntimeException(" no partners found");

        }
        return partners.stream()
                .map(partner ->{
                    PartnerResponseDto dto = new PartnerResponseDto(
                            partner.getId(),
                            partner.getName(),
                            partner.getEmail(),
                            partner.getPartnerType(),
                            partner.getPhone(),
                            partner.getState(),
                            partner.getDistrict(),
                            partner.getLatitude(),
                            partner.getLongitude(),
                            partner.getWebsite(),
                            partner.getAddress(),
                            partner.getIsVerified(),
                            partner.getCreatedAt()
                    );
                            return dto;
                })
                .toList();
    }

    public String addApplications(ApplicationRequestDto request){
        Partner partner = partnerRepository.findById(request.getPartnerId()).orElseThrow(
                ()->new RuntimeException("organization not found ")
        );
        Application application = Application.builder()
                .applicantId(request.getApplicantId())
                .applicantName(request.getApplicantName())
                .schemeId(request.getSchemeId())
                .partner(partner)
                .matchedReason(request.getMatchedReason())
                .status(ApplicationStatus.APPLIED)
                .createdAt(LocalDateTime.now())
                .build();

        applicationRepository.save(application);
        return "Application added successfully";
    }

    public List<ApplicationResponseDto> getApplications(Long partnerId){
        Partner partner = partnerRepository.findById(partnerId).orElseThrow(
                ()->new RuntimeException("organization not found ")
        );

        List<Application> applications = applicationRepository.findByPartnerId(partner.getId());
        if(applications.isEmpty()){
            throw new RuntimeException("applications not generated yet");
        }

        return  applications.stream()
                .map(application -> {
                    ApplicationResponseDto dto = new ApplicationResponseDto(
                            application.getApplicantId(),
                            application.getApplicantName(),
                            application.getSchemeId(),
                            application.getMatchedReason(),
                            application.getStatus(),
                            application.getCreatedAt()

                    );
                        return dto;
                })
                .toList();
    }

}
