package storeLab.gateway_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import storeLab.gateway_service.entity.Cart;
import storeLab.gateway_service.entity.User;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserAndProductId(User user, Integer productId);
    List<Cart> findAllByUser(User user);
    void deleteAllByUser(User user);
}
