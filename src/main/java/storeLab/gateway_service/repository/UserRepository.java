package storeLab.gateway_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import storeLab.gateway_service.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByActivationCode(String code);
}
