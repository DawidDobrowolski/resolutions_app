package pl.resolutions.model;

import pl.resolutions.entity.User;

public class UserResolutionRanking {

    private User user;
    private Integer sumUnits;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getSumUnits() {
        return sumUnits;
    }

    public void setSumUnits(Integer sumUnits) {
        this.sumUnits = sumUnits;
    }
}
