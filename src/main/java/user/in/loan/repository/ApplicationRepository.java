package user.in.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import user.in.loan.entity.Application;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application,Long> {
    List<Application> findByPartnerId(Long partnerId);

}
