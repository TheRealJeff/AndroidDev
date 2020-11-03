package com.example.courseviewer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.courseviewer.Database.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class assignmentActivity extends AppCompatActivity {

    protected FloatingActionButton buttonAddAssignment;
    protected Button buttonDeleteCurrentCourse;
    protected TextView textCourseName;
    protected TextView textCourseCode;
    protected ListView listViewAssignments;
    protected Course course;
    private static final String TAG = "assignmentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        setupUI();

        //get course name form the mainActivity
        Intent intent = getIntent();
        final String courseName = intent.getExtras().getString("courseName");

        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        course = databaseHelper.getCourseByName(courseName);

        textCourseName.setText(course.getCourseName());
        textCourseCode.setText(course.getCourseCode());


        //button to add a course by calling a fragment. sends the course selected to the fragment for the new assignment
        buttonAddAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertAssignmentDialogFragment dialogFragment = new InsertAssignmentDialogFragment();

                Bundle courseSelected = new Bundle();
                courseSelected.putString("course", course.getCourseName());
                dialogFragment.setArguments(courseSelected);

                dialogFragment.show(getSupportFragmentManager(), "Insert Assignment Fragment");
            }
        });

        //delete the current course and returns to the mainActivity
        buttonDeleteCurrentCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.deleteCourse(course.getCourseID());
                goToCourseViewer();
            }
        });

        loadListView(course.getCourseID());
    }

    @Override
    protected void onResume(){
        super.onResume();

        loadListView(course.getCourseID());
    }
//Test comment
    /* Alternative loadListView with if statement as an assignment filter
    public void loadListView(){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<Assignment> assignments = databaseHelper.getAllAssignments();


        ArrayList<String> assignmentsListText = new ArrayList<>();

        for (int i = 0; i <assignments.size(); i++){
            if (assignments.get(i).getAssignmentCourseID() == course.getCourseID()) {
                String temp = "";
                temp += assignments.get(i).getAssignmentName() + "\n";
                temp += assignments.get(i).getAssignmentGrade();

                assignmentsListText.add(temp);
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, assignmentsListText);
        listViewAssignments.setAdapter(arrayAdapter);
    }
    */

    //loads the list view with the assignments for the selected course
    public void loadListView(int courseID){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<Assignment> assignments = databaseHelper.getAssignmentsByCourseId(courseID);
        List<Integer> grades = new ArrayList<>();

        ArrayList<String> assignmentsListText = new ArrayList<>();

        for (int i = 0; i <assignments.size(); i++){
            grades.add(assignments.get(i).getAssignmentGrade());

            String temp = "";
            temp += assignments.get(i).getAssignmentName() + "\n";
            temp += assignments.get(i).getAssignmentGrade() + "%";

            assignmentsListText.add(temp);
        }


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, assignmentsListText);
        listViewAssignments.setAdapter(arrayAdapter);
    }

    //method to return to the mainActivity
    private void goToCourseViewer() {
        Intent intent = new Intent(assignmentActivity.this, MainActivity.class);
        startActivity(intent);
    }

    //setup for UI elements
    private void setupUI() {
        buttonAddAssignment = findViewById(R.id.buttonAddAssignment);
        buttonDeleteCurrentCourse = findViewById(R.id.buttonDeleteCurrentCourse);

        textCourseName = findViewById(R.id.textCourseName);
        textCourseCode = findViewById(R.id.textCourseCode);

        listViewAssignments = findViewById(R.id.listViewAssignments);
    }

}