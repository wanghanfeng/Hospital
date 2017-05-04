package model;

import java.util.Date;

/**
 * Created by lp on 2017/5/4.
 */
public class Conexamne {
    private int c_id;
    private String name;
    private String sex;
    private String unit;
    private String approve;
    private String suggest;
    private String situation;
    private Date time;

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Conexamne{" +
                "c_id=" + c_id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", unit='" + unit + '\'' +
                ", approve='" + approve + '\'' +
                ", suggest='" + suggest + '\'' +
                ", situation='" + situation + '\'' +
                ", time=" + time +
                '}';
    }
}
