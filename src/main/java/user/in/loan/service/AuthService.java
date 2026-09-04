package user.in.loan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.in.loan.dto.PartnerResponseDto;
import user.in.loan.dto.PartnerSignupRequestDto;
import user.in.loan.entity.Partner;
import user.in.loan.entity.PartnerAdmin;
import user.in.loan.repository.PartnerAdminRepository;
import user.in.loan.repository.PartnerRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PartnerRepository partnerRepository;
    private final PasswordEncoder passwordEncoder;
    private final PartnerAdminRepository partnerAdminRepository;
    @Transactional
    public PartnerResponseDto registerPartner(PartnerSignupRequestDto request) {

        // 1. Check username
        if (partnerAdminRepository.existsByEmail(request.getPartnerEmail())) {
            throw new RuntimeException("user already exists");
        }
        Partner partner = partnerRepository.findByNameAndPartnerTypeAndDistrict(
                request.getPartnerName(),request.getPartnerType(),request.getDistrict()
        );
        if(partner != null){
            throw new RuntimeException("This organization has already exists");
        }

        // 2. Create Partner
        Partner newPartner = Partner.builder()
                .name(request.getPartnerName())
                .partnerType(request.getPartnerType())
                .state(request.getState())
                .district(request.getDistrict())
                .address(request.getPartnerAddress())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .email(request.getPartnerEmail())
                .phone(request.getPartnerPhone())
                .website(request.getWebsite())
                .createdAt(LocalDateTime.now())
                .build();


        partnerRepository.save(newPartner);

        // 3. Create PartnerAdmin
        PartnerAdmin partnerAdmin = PartnerAdmin.builder()
                .email(request.getPartnerEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .partner(newPartner)
                .username(request.getFullName())
                .role("PARTNER_ADMIN")
                .createdAt(LocalDateTime.now())
                .build();

        partnerAdminRepository.save(partnerAdmin);

        return new PartnerResponseDto(newPartner.getId(), newPartner.getName(), newPartner.getEmail(), newPartner.getPartnerType(),"you are Successfully registered ");
    }
}
