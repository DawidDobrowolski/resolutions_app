package pl.resolutions.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_resolution")
public class UserResolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "weekly_plan")
    @Min(1)
    private Integer weeklyPlan;

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Column(columnDefinition = "TEXT")
    private String description;


    private boolean active = true;

    @Transient
    private int lastActivitiesUnits;

    @Column(name = "email_reminder")
    private boolean emailReminder = false;

    @ManyToOne
    private User user;

    @ManyToOne
    private Resolution resolution;

    @OneToMany(mappedBy = "userResolution", cascade = CascadeType.REMOVE)
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (active){
            this.endDate = null;
        }else{
            this.endDate = new Date();
        }
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getLastActivitiesUnits() {
        return lastActivitiesUnits;
    }

    public void setLastActivitiesUnits(int lastActivitiesUnits) {
        this.lastActivitiesUnits = lastActivitiesUnits;
    }

    //    public int getLastActivitiesUnits() {
//        Date now = new Date();
//        Date sevenDaysAgo = new Date(now.getTime() - TimeUnit.DAYS.toMillis(7));
//        return activities.stream()
//                .filter(a -> a.getDate().after(sevenDaysAgo))
//                .mapToInt(a -> a.getUnitsOfActivity())
//                .sum();
//    }
//
//    public int getForDaysActivitiesUnits(int days) {
//        Date now = new Date();
//        Date setDaysAgo = new Date(now.getTime() - TimeUnit.DAYS.toMillis(days));
//        return activities.stream()
//                .filter(a -> a.getDate().after(setDaysAgo))
//                .mapToInt(a -> a.getUnitsOfActivity())
//                .sum();
//    }

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
