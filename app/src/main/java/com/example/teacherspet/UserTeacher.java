package com.example.teacherspet;

public class UserTeacher {
    String uidT,teacherName,teacherEmail;

    public UserTeacher(String uidT, String teacherName, String teacherEmail) {
        this.uidT = uidT;
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
    }

    public UserTeacher() {
    }

    public String getUidT() {
        return uidT;
    }

    public void setUidT(String uidT) {
        this.uidT = uidT;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }
}
