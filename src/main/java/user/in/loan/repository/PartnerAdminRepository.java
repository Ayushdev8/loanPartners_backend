package user.in.loan.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import user.in.loan.entity.Partner;
import user.in.loan.entity.PartnerAdmin;

import java.util.Optional;

public interface PartnerAdminRepository extends JpaRepository<PartnerAdmin, Long> {
    boolean existsByEmail(String email);
    Optional<PartnerAdmin> findByEmail(String email);
    PartnerAdmin findByPartner(Partner partner);
}
