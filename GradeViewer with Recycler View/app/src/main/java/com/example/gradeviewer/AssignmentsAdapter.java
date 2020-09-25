package com.example.gradeviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AssignmentsAdapter extends RecyclerView.Adapter<AssignmentsAdapter.myViewHolder> {

    //define the ViewHolder class with constructor
    public class myViewHolder extends RecyclerView.ViewHolder{

        public TextView assignmentGradeTextView;
        public TextView assignmentTitleTextView;

        public myViewHolder(View itemView){
            super(itemView);

            assignmentGradeTextView = (TextView) itemView.findViewById(R.id.text_assignmentTitle);
            assignmentTitleTextView = (TextView) itemView.findViewById(R.id.text_assignmentGrade);

        }
    }

    private List<Assignment> mAssignments;

    public AssignmentsAdapter(List<Assignment> assignments) {
        mAssignments = assignments;
    }

    //Create each view
    @Override
    public AssignmentsAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View assignmentView = inflater.inflate(R.layout.assignment_item, parent, false);

        myViewHolder viewHolder = new myViewHolder(assignmentView);
        return viewHolder;
    }

    //Bind the data to each view
    @Override
    public void onBindViewHolder(myViewHolder holder, int position){
        Assignment assignment = mAssignments.get(position);

        TextView textView = holder.assignmentTitleTextView;
        textView.setText(assignment.getAssignmentTitle());

        TextView textView1 = holder.assignmentGradeTextView;
        textView1.setText(assignment.getAssignmentGrade());
    }

    //get number of items
    @Override
    public int getItemCount() { return mAssignments.size();}

}
