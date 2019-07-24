package pl.resolutions.entity;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Resolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String unit;

    private Integer unitMin;


    @OneToMany(mappedBy = "resolution")
    private List<UserResolution> userResolutions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getUnitMin() {
        return unitMin;
    }

    public void setUnitMin(Integer unitMin) {
        this.unitMin = unitMin;
    }

    public List<UserResolution> getUserResolutions() {
        return userResolutions;
    }

    public void setUserResolutions(List<UserResolution> userResolutions) {
        this.userResolutions = userResolutions;
    }

    @Override
    public String toString() {
        return "Resolution{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", unitMin=" + unitMin +
                '}';
    }


}
