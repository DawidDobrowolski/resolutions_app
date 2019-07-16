package pl.resolutions.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user_resolution")
public class UserResolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "weekly_plan")
    private Integer weeklyPlan;

    @NotNull
    @Column(name = "start_date")
    @DateTimeFormat
    private Date startDate;

    @Column(columnDefinition = "TEXT")
    private String description;


    private boolean active = true;


    @Column(name = "email_reminder")
    private boolean emailReminder = false;

    @ManyToOne
    private User user;

    @ManyToOne
    private Resolution resolution;

    @OneToMany(mappedBy = "userResolution",fetch = FetchType.EAGER)
    private List<Activity> activities = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWeeklyPlan() {
        return weeklyPlan;
    }

    public void setWeeklyPlan(Integer weeklyPlan) {
        this.weeklyPlan = weeklyPlan;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isEmailReminder() {
        return emailReminder;
    }

    public void setEmailReminder(boolean emailReminder) {
        this.emailReminder = emailReminder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Activity> getLastActivities() {
        return activities.stream()
                .filter(a -> a.getDate().isAfter(LocalDateTime.now().minusDays(7)))
                .collect(Collectors.toList());
    }

    public int getLastActivitiesUnits() {
        return activities.stream()
                .filter(a -> a.getDate().isAfter(LocalDateTime.now().minusDays(7)))
                .mapToInt(a -> a.getUnitsOfActivity())
                .sum();
    }

    @Override
    public String toString() {
        return "UserResolution{" +
                "id=" + id +
                ", weeklyPlan=" + weeklyPlan +
                ", startDate=" + startDate +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", emailReminder=" + emailReminder +
                '}';
    }
}
