package storeLab.gateway_service.service;

import org.springframework.stereotype.Service;
import storeLab.gateway_service.entity.User;
import storeLab.gateway_service.entity.WishList;
import storeLab.gateway_service.repository.WishListRepository;

import java.util.List;

@Service
public class WishListService {
    private final WishListRepository wishListRepository;


    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public void addToWishList(User user, Integer productId){
        for(WishList wishList : getWishList(user)){
            if(wishList.getProductId().equals(productId)){
                return;
            }
        }
        wishListRepository.save(new WishList(user, productId));
    }

    public void deleteFromWishList(Integer id){
        wishListRepository.deleteById(id);
    }

    public List<WishList> getWishList(User user){
        return wishListRepository.findByUser(user);
    }
}
