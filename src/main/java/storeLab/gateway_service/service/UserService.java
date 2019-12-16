package storeLab.gateway_service.service;

import org.springframework.stereotype.Service;
import storeLab.gateway_service.entity.Role;
import storeLab.gateway_service.entity.User;
import storeLab.gateway_service.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void userEditing(User user){
        User user1 = userRepository.findByUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setCity(user.getCity());
        user1.setEmail(user.getEmail());
        user1.setAdress(user.getAdress());
        userRepository.save(user1);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void changeRoleOfUser(String username, Map<String,String> form){
        User user = userRepository.findByUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for(String key : form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }
}
