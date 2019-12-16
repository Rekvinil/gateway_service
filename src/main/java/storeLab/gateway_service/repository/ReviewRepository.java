package storeLab.gateway_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import storeLab.gateway_service.entity.Review;
import storeLab.gateway_service.entity.User;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByProductId(Integer productId);
    List<Review> findByUser(User user);
}
