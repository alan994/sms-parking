package hr.alanjagar.smsparing.model;

/**
 * Created by ajagar on 20.10.2018..
 */

public class Zone {
    private String name;
    private String smsNumber;
    private String comment;

    public Zone() {
    }

    public Zone(String name, String smsNumber, String comment) {
        this.setComment(comment);
        this.setName(name);
        this.setSmsNumber(smsNumber);
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
}
