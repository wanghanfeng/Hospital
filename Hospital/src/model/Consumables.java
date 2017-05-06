package model;

/**
 * Created by AceCream on 2017/4/15.
 * 外诊报销
 */
public class Consumables {

    private int c_id;
    private String thename;
    private String unit;
    private String hos;
    private String reason;
    private String auditor;
    private double totalPrice;
    private double reducePrice;
    private int days;
    private String doctor;
    private String thetime;

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getThename() {
        return thename;
    }

    public void setThename(String thename) {
        this.thename = thename;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getHos() {
        return hos;
    }

    public void setHos(String hos) {
        this.hos = hos;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getReducePrice() {
        return reducePrice;
    }

    public void setReducePrice(double reducePrice) {
        this.reducePrice = reducePrice;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getThetime() {
        return thetime;
    }

    public void setThetime(String thetime) {
        this.thetime = thetime;
    }
}
