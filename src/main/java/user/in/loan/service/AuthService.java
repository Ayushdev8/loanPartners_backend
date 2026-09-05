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
            throw new RuntimeException("A user with this email already exists");
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
                .email(request.getPartnerEmail())
                .phone(request.getPartnerPhone())
                .website(request.getWebsite())
                .createdAt(LocalDateTime.now())
                .build();

        String state = request.getState();

        if (state != null) {

            state = state.trim().toLowerCase();

            if (state.equals("bihar")) {

                newPartner.setLatitude(25.58931160506634);
                newPartner.setLongitude(85.1157013495663);

            } else if (state.equals("uttarpradesh") ||
                    state.equals("up")|| state.equals("uttar pradesh")) {

                newPartner.setLatitude(26.850434383091088);
                newPartner.setLongitude(80.90610152983272);

            } else if (state.equals("rajasthan")) {

                newPartner.setLatitude(26.968254636556335);
                newPartner.setLongitude(75.96878790146295);

            } else if (state.equals("gujarat")) {

                newPartner.setLatitude(23.01536887971253);
                newPartner.setLongitude(72.63289417309376);
            } else if (state.equals("delhi")){
                newPartner.setLatitude(28.600319070114047);
                newPartner.setLongitude(77.20722191200949);

            }
        }
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

        return new PartnerResponseDto(
                newPartner.getId(),
                newPartner.getName(),
                newPartner.getEmail(),
                newPartner.getPartnerType(),
                newPartner.getPhone(),
                newPartner.getState(),
                newPartner.getDistrict(),
                newPartner.getLatitude(),
                newPartner.getLongitude(),
                newPartner.getWebsite(),
                newPartner.getAddress());
    }
}
