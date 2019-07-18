package pl.resolutions.support;

import pl.resolutions.entity.UserResolution;

public class UserResolutionReport {

    private String name;
    private String resolutionType;
    private String resolutionUnit;
    private long numberOfDays;
    private double planForSetDays;
    private int unitsInActions;
    private double resolutionRealization;
    private double toGo;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResolutionType() {
        return resolutionType;
    }

    public void setResolutionType(String resolutionType) {
        this.resolutionType = resolutionType;
    }

    public String getResolutionUnit() {
        return resolutionUnit;
    }

    public void setResolutionUnit(String resolutionUnit) {
        this.resolutionUnit = resolutionUnit;
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

    public int getUnitsInActions() {
        return unitsInActions;
    }

    public void setUnitsInActions(int unitsInActions) {
        this.unitsInActions = unitsInActions;
    }

    public double getResolutionRealization() {
        return resolutionRealization;
    }

    public void setResolutionRealization(double resolutionRealization) {
        this.resolutionRealization = resolutionRealization;
    }

    public double getToGo() {
        return toGo;
    }

    public void setToGo(double toGo) {
        this.toGo = toGo;
    }
}