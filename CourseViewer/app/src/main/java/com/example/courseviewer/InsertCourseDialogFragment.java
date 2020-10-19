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

public class InsertCourseDialogFragment extends DialogFragment {

    protected EditText editTextCourseName;
    protected EditText editTextCourseCode;
    protected Button buttonSaveCourse;
    protected Button buttonCancelCourse;

    //Code that obtains the new course name and code and puts it in the course table

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_insert_course, container, false);

        editTextCourseName = view.findViewById(R.id.editTextCourseName);
        editTextCourseCode = view.findViewById(R.id.editTextCourseCode);
        buttonSaveCourse = view.findViewById(R.id.buttonSaveCourse);
        buttonCancelCourse = view.findViewById(R.id.buttonCancelCourse);

        buttonSaveCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextCourseName.getText().toString();
                String code = editTextCourseCode.getText().toString();

                DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
                if (!(name.equals("") || code.equals(""))) {
                    databaseHelper.insertCourse(new Course(name, code));
                    ((MainActivity)getActivity()).loadListView();
                    Toast.makeText(getActivity(), "Course is saved", Toast.LENGTH_SHORT).show();
                    getDialog().dismiss();
                }

            }
        });

        buttonCancelCourse.setOnClickListener(new View.OnClickListener() {
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
