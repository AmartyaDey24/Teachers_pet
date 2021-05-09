package com.example.teacherspet;

public class UserStudent {
    private String uidS,studentName,studentUsn,studentEmail;

    public UserStudent(String uidS, String studentName, String studentUsn, String studentEmail) {
        this.uidS = uidS;
        this.studentName = studentName;
        this.studentUsn = studentUsn;
        this.studentEmail = studentEmail;
    }

    public UserStudent() {
    }

    public String getUidS() {
        return uidS;
    }

    public void setUid(String uidS) {
        this.uidS = uidS;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentUsn() {
        return studentUsn;
    }

    public void setStudentUsn(String studentUsn) {
        this.studentUsn = studentUsn;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
}
