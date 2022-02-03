/**
 * @author 60417
 * @date 2022/2/2
 * @time 13:21
 * @todo
 */
package FirthDay.Spittr.Bean;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SpitterForm {
    @NotNull
    @Size(min=4, max=20, message="{username.size}")
    private String username;

    @NotNull
    @Size(min=6, max=10, message="{password.size}")
    private String password;

    @NotNull
    @Size(min=1, max=20, message="{firstName.size}")
    private String firstName;

    @NotNull
    @Size(min=1, max=20, message="{lastName.size}")
    private String lastName;

    @NotNull
    @Email(message = "{email.valid}")
    private String email;

    private MultipartFile profilePicture;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MultipartFile getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(MultipartFile profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Spitter toSpitter() {
        return new Spitter(firstName,lastName,username, password,email);
    }

    @Override
    public String toString() {
        return "SpitterForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
