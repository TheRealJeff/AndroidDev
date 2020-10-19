package com.example.courseviewer.Database;

public class Config {

    //Config file with all the database table and column names

    public static final String DATABASE_NAME = "courses_DB";
    public static final int DATABASE_VERSION = 1;

    public static final String COURSE_TABLE_NAME = "courses";

    public static final String COLUMN_COURSE_ID = "_idCourse";
    public static final String COLUMN_COURSE_NAME = "courseName";
    public static final String COLUMN_COURSE_CODE = "courseCode";

    public static final String ASSIGNMENT_TABLE_NAME = "assignments";

    public static final String COLUMN_ASSIGNMENT_ID = "_idAssignment";
    public static final String COLUMN_ASSIGNMENT_COURSE_ID = "_idAssignmentCourse";
    public static final String COLUMN_ASSIGNMENT_NAME = "assignmentName";
    public static final String COLUMN_ASSIGNMENT_GRADE = "assignmentGrade";

}
