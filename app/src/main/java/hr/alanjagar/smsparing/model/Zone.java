package hr.alanjagar.smsparing.model;

/**
 * Created by ajagar on 20.10.2018..
 */

public class Zone {
    private String name;
    private String smsNumber;
    private String comment;


    private String halfHourSuffix;
    private String halfHourPrefix;
    private boolean supportsHalfHour;

    public Zone() {
    }

    public Zone(String name, String smsNumber, String comment) {
        this.setComment(comment);
        this.setName(name);
        this.setSmsNumber(smsNumber);
    }

    public Zone(String name, String smsNumber, String comment, String halfHourSuffix, String halfHourPrefix, boolean supportsHalfHour) {
        this.name = name;
        this.smsNumber = smsNumber;
        this.comment = comment;
        this.halfHourSuffix = halfHourSuffix;
        this.halfHourPrefix = halfHourPrefix;
        this.supportsHalfHour = supportsHalfHour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmsNumber() {
        return smsNumber;
    }

    public void setSmsNumber(String smsNumber) {
        this.smsNumber = smsNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getHalfHourSuffix() {
        return halfHourSuffix;
    }

    public void setHalfHourSuffix(String halfHourSuffix) {
        this.halfHourSuffix = halfHourSuffix;
    }

    public String getHalfHourPrefix() {
        return halfHourPrefix;
    }

    public void setHalfHourPrefix(String halfHourPrefix) {
        this.halfHourPrefix = halfHourPrefix;
    }

    public boolean isSupportsHalfHour() {
        return supportsHalfHour;
    }

    public void setSupportsHalfHour(boolean supportsHalfHour) {
        this.supportsHalfHour = supportsHalfHour;
    }
}
