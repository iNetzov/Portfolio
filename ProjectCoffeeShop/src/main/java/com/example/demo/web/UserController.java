package com.example.demo.web;

import com.example.demo.models.binding.ManageUsersBindingModel;
import com.example.demo.models.binding.ProfileEditBindingModel;
import com.example.demo.models.binding.UserRegisterBindingModel;
import com.example.demo.models.entity.UserEntity;
import com.example.demo.models.entity.enums.RoleEntityNameEnum;
import com.example.demo.models.service.UserServiceModel;
import com.example.demo.models.view.UserEditProfileViewModel;
import com.example.demo.models.view.UserManageViewModel;
import com.example.demo.models.view.UserProfileViewModel;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/register")
    public String registerUser(Model model) {
        if (!model.containsAttribute("usernameExist")) {
            model.addAttribute("usernameExist", false);
        }
        if (!model.containsAttribute("emailExist")) {
            model.addAttribute("emailExist", false);
        }
        return "register";
    }


    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @PostMapping("/register")
    public String registerUserConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }
        if (userService.findByUsername(userRegisterBindingModel.getUsername()) != null) {
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("usernameExist", true);
            return "redirect:register";

        }
        if (userService.findByEmail(userRegisterBindingModel.getEmail()) != null) {
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("emailExist", true);
            return "redirect:register";
        }


        UserServiceModel user = modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        userService.createUser(user);


        return "redirect:login";
    }

    @GetMapping("/login")
    public String loginUser(Model model) {
        if (!model.containsAttribute("username")) {
            model.addAttribute("username", "");
        }
        if (!model.containsAttribute("errorLogIn")) {
            model.addAttribute("errorLogIn", false);
        }


        return "login";
    }

    @PostMapping("/login-error")
    public String loginError(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username
            , RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("username", username)
                .addFlashAttribute("errorLogIn", true);

        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        UserEntity currentUser = userService.findByUsername(username);
        UserProfileViewModel userProfileViewModel = modelMapper.map(currentUser, UserProfileViewModel.class);
        model.addAttribute("profileInfo", userProfileViewModel);
        return "profile";
    }


    @GetMapping("/profile/edit")
    public String editProfile( @AuthenticationPrincipal UserDetails userDetails, Model model) {
        UserEntity currentUsername = userService.findByUsername(userDetails.getUsername());
        UserEditProfileViewModel userViewModel = new UserEditProfileViewModel();
        userViewModel.setUsername(currentUsername.getUsername());
        userViewModel.setId(currentUsername.getId());
        userViewModel.setEmail(currentUsername.getEmail());
        userViewModel.setFullName(currentUsername.getFullName());
        model.addAttribute("userEditProfileViewModel", userViewModel);

        return "profile-edit";
    }

    @ModelAttribute
    public ProfileEditBindingModel profileEditBindingModel() {
        return new ProfileEditBindingModel();
    }

    @PostMapping("/profile/edit")
    public String editProfileConfirm(@AuthenticationPrincipal UserDetails userDetails,@Valid ProfileEditBindingModel profileEditBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model) {

        if (bindingResult.hasErrors()) {

            redirectAttributes
                    .addFlashAttribute("profileEditBindingModel", profileEditBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.profileEditBindingModel", bindingResult);
            return "redirect:edit";
        }
        String username = userDetails.getUsername();
        UserEntity currentUser = userService.findByUsername(username);

        userService.updateUser(currentUser.getId(),profileEditBindingModel.getFullName());

        return "index";
    }

    @GetMapping("/manage-users")
    public String manageUser(Model model){
        List<UserManageViewModel> allUsers = userService.findAllUsers();
        model.addAttribute("allUsers",allUsers);

        return "manage-users";
    }

    @ModelAttribute
     public ManageUsersBindingModel manageUsersBindingModel(){
        return new ManageUsersBindingModel();
    }


    @PostMapping("/manage-users")
    public String manageUserConfirm(@Valid ManageUsersBindingModel manageUsersBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){

            redirectAttributes
                    .addFlashAttribute("manageUsersBindingModel",manageUsersBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.manageUsersBindingModel",bindingResult);

            return "redirect:manage-users";
        }
        String username = manageUsersBindingModel.getUsername();
        RoleEntityNameEnum newRole = manageUsersBindingModel.getRole();
        UserEntity user = userService.findByUsername(username);
        userService.updateUserRole(user,newRole);
        return "index-AdminPanel";

    }

}


