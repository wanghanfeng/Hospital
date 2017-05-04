package model;

/**
 * Created by AceCream on 2017/4/14.
 * 护理记录
 */
public class NurseRecords {
    private int n_id;
    //患者姓名
    private String patient;
    //药名
    private String drug;

    private String sex;
    private String thecode;
    private int age;
    private String ins;
    private String thetime;
    private String nurse;
    private String note;

    public int getN_id() {
        return n_id;
    }

    public void setN_id(int n_id) {
        this.n_id = n_id;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getThecode() {
        return thecode;
    }

    public void setThecode(String thecode) {
        this.thecode = thecode;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIns() {
        return ins;
    }

    public void setIns(String ins) {
        this.ins = ins;
    }

    public String getThetime() {
        return thetime;
    }

    public void setThetime(String thetime) {
        this.thetime = thetime;
    }

    public String getNurse() {
        return nurse;
    }

    public void setNurse(String nurse) {
        this.nurse = nurse;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
