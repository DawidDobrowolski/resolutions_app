package pl.resolutions.entity;


import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
public class Activity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date date;

    @NotNull
    @Min(1)
    @Column(name = "units_of_activity")
    private Integer unitsOfActivity;

    @NotBlank
    private String note;

    @ManyToOne
    private UserResolution userResolution;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUnitsOfActivity() {
        return unitsOfActivity;
    }

    public void setUnitsOfActivity(Integer unitsOfActivity) {
        this.unitsOfActivity = unitsOfActivity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public UserResolution getUserResolution() {
        return userResolution;
    }

    public void setUserResolution(UserResolution userResolution) {
        this.userResolution = userResolution;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", date=" + date +
                ", unitsOfActivity=" + unitsOfActivity +
                ", note='" + note + '\'' +
                '}';
    }
}
