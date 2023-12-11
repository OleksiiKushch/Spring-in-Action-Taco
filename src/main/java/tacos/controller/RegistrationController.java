package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.entity.RegistrationForm;
import tacos.entity.User;
import tacos.service.UserService;

import javax.validation.Valid;
import java.util.Objects;

import static tacos.constants.AppConstants.CONFIRMATION_PASSWORD;
import static tacos.constants.AppConstants.USERNAME;

@Slf4j
@Controller
@RequestMapping ("/registration")
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute ("registrationForm")
    public RegistrationForm createRegistrationForm() {
        return new RegistrationForm();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(@Valid RegistrationForm form, Errors errors) {
        if (!errors.hasFieldErrors(USERNAME)) {
            advancedValidationUsernameField(form, errors);
        }
        if (!errors.hasFieldErrors(CONFIRMATION_PASSWORD)) {
            advancedValidationConfirmPasswordField(form, errors);
        }
        if (errors.hasErrors()) {
            return "registration";
        }

        User user = form.toUser(passwordEncoder);
        user = userService.create(user);
        log.info("New user successfully saved: {}", user);
        return "redirect:/login";
    }

    private void advancedValidationUsernameField(RegistrationForm form, Errors errors) {
        User existingUser = userService.findUserByUsername(form.getUsername());
        if (Objects.nonNull(existingUser)) {
            errors.rejectValue(USERNAME, "user.already.exist",
                               "User with this username already exist");
        }
    }

    private void advancedValidationConfirmPasswordField(RegistrationForm form, Errors errors) {
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            errors.rejectValue(CONFIRMATION_PASSWORD, "password.confirmPassword.not.match",
                               "Password and confirmation password must match");
        }
    }

}
