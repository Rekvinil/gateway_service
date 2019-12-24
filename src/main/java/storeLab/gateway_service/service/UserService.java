package storeLab.gateway_service.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import storeLab.gateway_service.entity.Role;
import storeLab.gateway_service.entity.User;
import storeLab.gateway_service.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final MailSender mailSender;

    public UserService(UserRepository userRepository, MailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
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

    public boolean addUser(User user){
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb!=null){
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPoints(0);
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);

        if(!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "%s, Спасибо что зарегестрировались\n Пожалуйста подтвердите свою учетную запись для входа\n" +
                            "http://localhost:8080/activate/%s", user.getFirstName(), user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
        }
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
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

    public void changeProints(String username, Integer points){
        User user = userRepository.findByUsername(username);
        Integer userPoints = user.getPoints();
        userPoints += points/100*10;
        user.setPoints(userPoints);
        userRepository.save(user);
    }

    public void desceasePoints(String username, Integer points){
        User user = userRepository.findByUsername(username);
        Integer userPoints = user.getPoints();
        userPoints -= points;
        user.setPoints(userPoints);
        userRepository.save(user);
    }

    public User getUserById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if(user==null){
            return false;
        }
        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }
}
