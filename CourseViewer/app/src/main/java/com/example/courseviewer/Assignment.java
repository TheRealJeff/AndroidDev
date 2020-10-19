package com.example.courseviewer;

public class Assignment {

    private Integer assignmentID;
    private Integer assignmentCourseID;
    private String assignmentName;
    private Integer assignmentGrade;

    //class that define the information needed for an assignment in the database, with getters and setters

    public Assignment(Integer assignmentID, Integer assignmentCourseID, String assignmentName, Integer assignmentGrade) {
        this.assignmentID = assignmentID;
        this.assignmentCourseID = assignmentCourseID;
        this.assignmentName = assignmentName;
        this.assignmentGrade = assignmentGrade;
    }

    public Assignment(Integer assignmentCourseID, String assignmentName, Integer assignmentGrade) {
        this.assignmentCourseID = assignmentCourseID;
        this.assignmentName = assignmentName;
        this.assignmentGrade = assignmentGrade;
    }

    public Integer getAssignmentCourseID() {
        return assignmentCourseID;
    }

    public void setAssignmentCourseID(Integer assignmentCourseID) {
        this.assignmentCourseID = assignmentCourseID;
    }

    public Integer getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(Integer assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public Integer getAssignmentGrade() {
        return assignmentGrade;
    }

    public void setAssignmentGrade(Integer assignmentGrade) {
        this.assignmentGrade = assignmentGrade;
    }
}
