package user.in.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import user.in.loan.entity.PartnerScheme;

import java.util.List;

public interface PartnerSchemeRepository extends JpaRepository<PartnerScheme, Long> {
    List<PartnerScheme> findByPartnerId(Long partnerId);
    List<PartnerScheme> findBySchemeId(String schemeId);
}
