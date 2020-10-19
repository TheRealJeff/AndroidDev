package com.example.courseviewer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.courseviewer.Database.DatabaseHelper;

public class InsertAssignmentDialogFragment extends DialogFragment {

    protected EditText editTextAssignmentName;
    protected EditText editTextAssignmentGrade;
    protected Button buttonSaveAssignment;
    protected Button buttonCancelAssignment;
    protected String courseName;
    protected static final String TAG = "AssFrag";

    //Code that obtains the new assignment course ID, name and grade and puts it in the assignment table

    //obtains the selected course from assignmentActivity
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        courseName = getArguments().getString("course");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_insert_assignment, container, false);

        editTextAssignmentName = view.findViewById(R.id.editTextAssignmentName);
        editTextAssignmentGrade = view.findViewById(R.id.editTextAssignmentGrade);
        buttonSaveAssignment = view.findViewById(R.id.buttonSaveAssignment);
        buttonCancelAssignment = view.findViewById(R.id.buttonCancelAssignment);

        buttonSaveAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextAssignmentName.getText().toString();
                int grade = Integer.parseInt(editTextAssignmentGrade.getText().toString());


                DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
                Course course = databaseHelper.getCourseByName(courseName);

                if (!(name.equals("") || editTextAssignmentGrade.getText().equals(""))){
                    databaseHelper.insertAssignment(new Assignment(course.getCourseID(), name, grade));
                    ((assignmentActivity)getActivity()).loadListView(course.getCourseID());
                    Toast.makeText(getActivity(), "Assignment is saved", Toast.LENGTH_SHORT).show();
                    getDialog().dismiss();
                }
            }
        });

        buttonCancelAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        getDialog().getWindow().setLayout(1000, 1400);

    }

}
