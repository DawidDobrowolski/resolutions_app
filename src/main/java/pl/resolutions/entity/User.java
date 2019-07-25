package pl.resolutions.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import pl.resolutions.validation.EditPassword;
import pl.resolutions.validation.EditUser;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(groups = {EditUser.class, Default.class})
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(groups = {EditUser.class, Default.class})
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(groups = {EditUser.class, Default.class})
    @Email(groups = {EditUser.class, Default.class})
    @Column(unique = true)
    private String email;

    @NotBlank(groups = {EditPassword.class, Default.class})
    @Size(min = 8,groups = {EditPassword.class, Default.class})
    private String password;


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserResolution> userResolutions = new ArrayList<>();

    private boolean admin = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserResolution> getUserResolutions() {
        return userResolutions;
    }

    public void setUserResolutions(List<UserResolution> userResolutions) {
        this.userResolutions = userResolutions;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getName() {
        return firstName + " " + lastName;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
