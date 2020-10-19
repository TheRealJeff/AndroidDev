package com.example.gradeviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.myViewHolder> {

    //define the ViewHolder class with constructor
    public class myViewHolder extends RecyclerView.ViewHolder{

        public TextView courseTextView;
        public ArrayList<Assignment> assignments;

        //TODO create element for course average

        public myViewHolder(View itemView){
            super(itemView);

            courseTextView = (TextView) itemView.findViewById(R.id.text_courseID);

            //define recycler list for the assignments
            //TODO send assignment list to adapter

            RecyclerView recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView_assignments);

            assignments = Course.getAssignments();

            //AssignmentsAdapter adapter = new AssignmentsAdapter(assignments);

            //recyclerView.setAdapter(adapter);
            //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    private List<Course> mCourses;


    public CoursesAdapter(List<Course> courses){
        mCourses = courses;
    }

    //create View for the data
    @Override
    public CoursesAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View courseView = inflater.inflate(R.layout.course_item, parent, false);

        myViewHolder viewHolder = new myViewHolder(courseView);
        return viewHolder;
    }

    //Bind the data to each view
    @Override
    public void onBindViewHolder(myViewHolder holder, int position){
        Course course = mCourses.get(position);

        //TODO set options for if number of courses is 0

        TextView textView = holder.courseTextView;
        textView.setText(course.getCourseTitle());

    }

    //get number of items
    @Override
    public int getItemCount(){
        return mCourses.size();
    }
}
