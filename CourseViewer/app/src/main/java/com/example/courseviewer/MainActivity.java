package com.example.courseviewer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.courseviewer.Database.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    protected FloatingActionButton buttonAddCourse;
    protected TextView textValueAllAssignmentAverage;
    protected ListView listViewCourses;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();

        loadListView();

        //adds a course by calling the course fragment
        buttonAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertCourseDialogFragment dialogFragment = new InsertCourseDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "Insert Course Fragment");
            }
        });

        //finds the course that was selected and goes to the assignmentActivity to display the course assignments
        listViewCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String courseName = (String) adapterView.getItemAtPosition(i);

                Scanner s = new Scanner(courseName);
                courseName = s.nextLine();
                s.close();

                goToAssignmentViewer(courseName);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        loadListView();
    }

    //loads the list view of the courses, calculates each course's average and the total average from all courses
    public void loadListView(){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<Course> courses = databaseHelper.getAllCourses();

        float allAssignmentAverageValue = 0;
        int averageItems = courses.size();

        ArrayList<String> coursesListText = new ArrayList<>();

        for (int i = 0; i <courses.size(); i++){
            String temp = "";
            temp += courses.get(i).getCourseName() + "\n";
            temp += courses.get(i).getCourseCode();
            if(courseAverage(courses.get(i)) != 0) {
                temp += "\n" + "Assignment Average: " + String.format("%.2f", courseAverage(courses.get(i))) + "%";
                allAssignmentAverageValue += courseAverage(courses.get(i));
            }
            else{
                averageItems -= 1;
                temp += "\n" + "Assignment Average: NA";
            }

            coursesListText.add(temp);
        }

        if (allAssignmentAverageValue != 0){
            allAssignmentAverageValue = allAssignmentAverageValue / averageItems;
            textValueAllAssignmentAverage.setText(String.format("%.2f",allAssignmentAverageValue) + "%");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coursesListText);
        listViewCourses.setAdapter(arrayAdapter);
    }

    //goes to assignmentActivity
    private void goToAssignmentViewer(String courseName) {
        Intent intent = new Intent(MainActivity.this, assignmentActivity.class);
        intent.putExtra("courseName", courseName);
        startActivity(intent);
    }

    //setup UI elements
    private void setupUI() {
        buttonAddCourse = findViewById(R.id.buttonAddCourse);

        textValueAllAssignmentAverage = findViewById(R.id.textValueAverageAllAssignments);

        listViewCourses = findViewById(R.id.listViewCourses);

    }

    //method to get a course average
    private float courseAverage(Course course){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<Assignment> assignments = databaseHelper.getAssignmentsByCourseId(course.getCourseID());
        float average = 0;

        for (int i = 0; i< assignments.size(); i++){
            average += assignments.get(i).getAssignmentGrade();
        }

        if(average != 0){
            average = average / assignments.size();
            return average;
        }
        return 0;
    }
}