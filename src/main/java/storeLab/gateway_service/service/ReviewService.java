package storeLab.gateway_service.service;

import org.springframework.stereotype.Service;
import storeLab.gateway_service.entity.Review;
import storeLab.gateway_service.entity.User;
import storeLab.gateway_service.repository.ReviewRepository;
import storeLab.gateway_service.repository.UserRepository;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public void addReview(String user, Integer productId, String text, String mark){
        reviewRepository.save(new Review(userRepository.findByUsername(user), productId, text, mark));
    }

    public void deleteReview(Integer id){
        reviewRepository.deleteById(id);
    }

    public List<Review> getReviewByProductId(Integer id){
        return reviewRepository.findByProductId(id);
    }

    public List<Review> getReviewByUser(User user){
        return reviewRepository.findByUser(user);
    }
}
