package tacos.entity;

import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class RegistrationForm {

    @Size (min = 2, message = "Username is required and must be at least {min} characters long")
    private String username;
    @Size (min = 6, message = "Password is required and must be at least {min} characters long")
    private String password;
    @Size (min = 6, message = "Confirm password is required and must be at least {min} characters long")
    private String confirmPassword;
    private String fullName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password), fullName, street, city, state, zip, phone);
    }
}
