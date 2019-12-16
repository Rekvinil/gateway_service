package storeLab.gateway_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import storeLab.gateway_service.entity.User;
import storeLab.gateway_service.entity.WishList;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Integer> {
    List<WishList> findByUser(User user);
}
