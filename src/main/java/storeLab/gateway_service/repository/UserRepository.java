package storeLab.gateway_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import storeLab.gateway_service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
