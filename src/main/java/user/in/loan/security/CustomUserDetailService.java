package user.in.loan.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import user.in.loan.repository.PartnerAdminRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final PartnerAdminRepository partnerAdminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return partnerAdminRepository.findByEmail(email).orElseThrow(
                ()->new UsernameNotFoundException("user doest not exist")
        );
    }
}
