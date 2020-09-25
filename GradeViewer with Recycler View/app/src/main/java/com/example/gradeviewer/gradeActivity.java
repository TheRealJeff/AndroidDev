package com.example.gradeviewer;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;



public class gradeActivity extends AppCompatActivity {

    protected Toolbar toolbar_gradeActivity;
    protected ActionBar actionbar;
    protected ArrayList<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_viewer);


        setupUI();

        //create course recycler list and link to adapter
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_courses);
        courses = Course.createCourseList();

        CoursesAdapter adapter = new CoursesAdapter(courses);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    //show appbar icons or menus options
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    //setup UI element from layout
    protected void setupUI(){
        toolbar_gradeActivity = (Toolbar) findViewById(R.id.toolbar_gradeActivity);
        setSupportActionBar(toolbar_gradeActivity);
        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    //do actions if menu item is selected
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item_SwitchGrades:
                //User chose to switch grade viewing type
                return true;

            default:
                //User action not recognized
                return super.onOptionsItemSelected(item);
        }
    }
}