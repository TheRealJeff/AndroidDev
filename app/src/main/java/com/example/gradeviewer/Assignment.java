package com.example.gradeviewer;

import java.util.Random;

public class Assignment {

    private static int assID = 0;  //static ID increments with every new assignment created
    private String assignmentTitle; //title of assignment
    private int assignmentGrade;    //grade of assignment

    private Assignment (String title, int grade){
        assignmentTitle = title;
        assignmentGrade = grade;
        assID++;
    }

    static public Assignment generateRandomAssignment(){
        Random rnd = new Random();
        String tempTitle = "Assignment" + assID;
        int tempGrade = rnd.nextInt(100) +1;

        //TODO: create switch cases to assign letter grade

        return new Assignment(tempTitle,tempGrade);
    }

    //get methods
    public String getAssignmentTitle() {return assignmentTitle;}
    public int getAssignmentGrade() {return assignmentGrade;}
}
