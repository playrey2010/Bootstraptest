package com.example.demo.Controller;

import com.example.demo.Role;
import com.example.demo.RoleRepository;
import com.example.demo.User;
import com.example.demo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;



    @RequestMapping("/secure")
    public String secure(Principal principal, Model model){
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "secure";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user,
                                          BindingResult result, Model model){
        if (result.hasErrors()){
            user.clearPassword();
            model.addAttribute("user", user);
            return "register";
        }
        else if (!userRepository.existsByUsername(user.getUsername())) {
            model.addAttribute("user", user);
            model.addAttribute("message", "New user account created");
            user.setEnabled(true);
            userRepository.save(user);

            Role role = new Role(user.getUsername(), "ROLE_USER");
            roleRepository.save(role);
            return "index";
        }
        else {
            model.addAttribute("message", "Username already taken");
            return "register";
        }
    }

}
