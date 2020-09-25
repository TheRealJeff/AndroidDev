package com.example.gradeviewer;

import java.util.ArrayList;
import java.util.Random;

public class Course {
    private static int courseID = 1;    //static ID increments with every new course created
    private String courseTitle;
    private static ArrayList<Assignment> assignments;

    private Course(String title, ArrayList<Assignment> assns){
        courseTitle = title;
        assignments = assns;
        courseID++;
    }

    //returns a course instance with random assignment values
    static public Course generateRandomCourse(){
        Random rnd = new Random();
        int assignmentNo = rnd.nextInt(5);
        ArrayList<Assignment> tempAssns = new ArrayList<Assignment>();

        for(int i=0;i<assignmentNo;i++)
            tempAssns.add(Assignment.generateRandomAssignment());

        return new Course("Course " + courseID, tempAssns);
    }


    //creates course list and returns ArrayList
    public static ArrayList<Course> createCourseList(){
        Random rnd = new Random();
        int numCourses = rnd.nextInt(5);

        courseID = 1;

        ArrayList<Course> courses = new ArrayList<>();

        for(int j=0; j<numCourses; j++){
            Course course = Course.generateRandomCourse();
            courses.add(course);
            ArrayList<Assignment> assignments = course.getAssignments();
            System.out.println(course.getCourseTitle());
            for(int i = 0; i<assignments.size(); i++){
                System.out.println(assignments.get(i).getAssignmentTitle()+"    "+assignments.get(i).getAssignmentGrade());
            }
        }

        return courses;
    }

    //get methods
    public String getCourseTitle() {return courseTitle;}
    public static ArrayList<Assignment> getAssignments() {return assignments;}
}
