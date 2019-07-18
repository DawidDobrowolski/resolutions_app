package pl.resolutions.support;

import pl.resolutions.entity.UserResolution;

public class UserResolutionReport {

    private UserResolution userResolution;
    private long numberOfDays;
    private double planForSetDays;
    private double unitsInActions;
    private double resolutionRealization;

    public UserResolution getUserResolution() {
        return userResolution;
    }

    public void setUserResolution(UserResolution userResolution) {
        this.userResolution = userResolution;
    }

    public long getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(long numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public double getPlanForSetDays() {
        return planForSetDays;
    }

    public void setPlanForSetDays(double planForSetDays) {
        this.planForSetDays = planForSetDays;
    }

    public double getUnitsInActions() {
        return unitsInActions;
    }

    public void setUnitsInActions(double unitsInActions) {
        this.unitsInActions = unitsInActions;
    }

    public double getResolutionRealization() {
        return resolutionRealization;
    }

    public void setResolutionRealization(double resolutionRealization) {
        this.resolutionRealization = resolutionRealization;
    }
}
