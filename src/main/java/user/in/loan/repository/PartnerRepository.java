package user.in.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import user.in.loan.entity.Partner;
import user.in.loan.entity.type.PartnerType;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    Partner findByNameAndPartnerTypeAndDistrict(String name, PartnerType partnerType, String district);
}
