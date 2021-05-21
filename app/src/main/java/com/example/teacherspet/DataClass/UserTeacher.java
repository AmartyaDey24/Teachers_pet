package com.example.teacherspet.DataClass;

public class UserTeacher {
    String uidT,teacherName,teacherEmail,dep,course,language;

    public UserTeacher(String uidT, String teacherName, String teacherEmail, String dep, String course, String language) {
        this.uidT = uidT;
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
        this.dep = dep;
        this.course = course;
        this.language = language;
    }

    public UserTeacher() {
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
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
