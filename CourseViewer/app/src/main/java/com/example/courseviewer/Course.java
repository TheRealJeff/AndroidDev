package com.example.courseviewer;

public class Course {

    private Integer courseID;
    private String courseName;
    private String courseCode;

    //class that define the information needed for a course in the database, with getters and setters

    public Course(Integer courseID, String courseName, String courseCode) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    public Course(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

}
