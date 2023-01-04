package LTM_Final.TCP;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String code,gpaLetter;
    private float gpa;
    private static final long serialVersionUID = 20151107;

    public Student() {
    }

    public Student(float gpa) {
        this.id = id;
        this.code = code;
        this.gpaLetter = gpaLetter;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGpaLetter() {
        return gpaLetter;
    }

    public void setGpaLetter(String gpaLetter) {
        this.gpaLetter = gpaLetter;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }
}
