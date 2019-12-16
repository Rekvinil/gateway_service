package storeLab.gateway_service.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import storeLab.gateway_service.entity.Role;
import storeLab.gateway_service.service.UserService;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUserList(Model model){
        model.addAttribute("users", userService.getUsers());
        return "userList";
    }

    @GetMapping("/user/{username}")
    public String userEdit(@PathVariable String username, Model model){
        model.addAttribute("user", userService.getUserByUsername(username));
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping("/user")
    public String userSave(@RequestParam String username, @RequestParam Map<String,String> form){
        userService.changeRoleOfUser(username,form);
        return "redirect:/user";
    }
}
