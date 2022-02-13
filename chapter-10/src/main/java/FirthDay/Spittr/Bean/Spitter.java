/**
 * @author 60417
 * @date 2022/1/24
 * @time 20:22
 * @todo
 */
package FirthDay.Spittr.Bean;

import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 该spitter应用的用户
 * 包括基本信息：
 * 性，名、用户名、密码、对应uid
 * id  firstname lastname username password
 */
//@PropertySource(value = {"classpath:ValidationMessages.properties"})
public class Spitter {
    //id是数据库工具生成的
    private Long id;//spitter的id
    @NotNull
    @Size(min = 1,max = 20,message = "{firstName.size}")
    private String firstName;
    @NotNull
    @Size(min = 1,max = 20,message = "{lastName.size}")
    private String lastName;
    @NotNull
    @Size(min = 4,max = 20,message = "{username.size}")
    private String username;
    @NotNull
    @Size(min = 6,max = 10,message = "{password.size}")
    private String password;
    @NotNull
    @Email(message = "{email.valid}")
    private String email;



    //    个人照片的信息
    private String profilePicture_name;
    //对应的数据：
    private byte[] profilePicture;//图片数据


    public String getProfilePicture_name() {
        return profilePicture_name;
    }

    public void setProfilePicture_name(String profilePicture_name) {
        this.profilePicture_name = profilePicture_name;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Spitter() {}//默认的构造器

    public Spitter(String firstName, String lastName, String username,
                   String password,String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public Spitter(Long id, String firstName, String lastName, String username, String password,String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
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
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    @Override
    public String toString() {
        return "Spitter{" +
                "id=" + id +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
