package com.example.courseviewer.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.courseviewer.Assignment;
import com.example.courseviewer.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String TAG = "DatabaseHelper";

    //class that define the methods to manage the database

    //constructor
    public DatabaseHelper(Context context){
        super(context, Config.DATABASE_NAME, null, Config.DATABASE_VERSION);
        this.context = context;
    }

    //contains the queries to create the database tables
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE_COURSE = "CREATE TABLE " + Config.COURSE_TABLE_NAME + " (" + Config.COLUMN_COURSE_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + Config.COLUMN_COURSE_NAME + " TEXT NOT NULL, " + Config.COLUMN_COURSE_CODE +
                " TEXT NOT NULL)";

        Log.d(TAG, CREATE_TABLE_COURSE);

        sqLiteDatabase.execSQL(CREATE_TABLE_COURSE);

        Log.d(TAG, "Course Table Created");

        String CREATE_TABLE_ASSIGNMENT = "CREATE TABLE " + Config.ASSIGNMENT_TABLE_NAME + " (" + Config.COLUMN_ASSIGNMENT_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + Config.COLUMN_ASSIGNMENT_COURSE_ID + " INTEGER, " + Config.COLUMN_ASSIGNMENT_NAME +
                " TEXT NOT NULL, " + Config.COLUMN_ASSIGNMENT_GRADE + " INTEGER)";

        Log.d(TAG, CREATE_TABLE_ASSIGNMENT);

        sqLiteDatabase.execSQL(CREATE_TABLE_ASSIGNMENT);

        Log.d(TAG, "Assignment Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //method to insert a course to the courses table
    public long insertCourse(Course course){

        long id = -1;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Config.COLUMN_COURSE_NAME, course.getCourseName());
        contentValues.put(Config.COLUMN_COURSE_CODE, course.getCourseCode());

        try{
            id = db.insertOrThrow(Config.COURSE_TABLE_NAME, null, contentValues);
        }
        catch (SQLException e){
            Log.d(TAG, "EXCEPTION: " + e);
            Toast.makeText(context, "Operation Failed!: " + e, Toast.LENGTH_LONG).show();
        }
        finally {
            db.close();
        }
        return id;

    }

    //method to insert an assignment to the assignments table
    public long insertAssignment(Assignment assignment){

        long id = -1;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Config.COLUMN_ASSIGNMENT_COURSE_ID, assignment.getAssignmentCourseID());
        contentValues.put(Config.COLUMN_ASSIGNMENT_NAME, assignment.getAssignmentName());
        contentValues.put(Config.COLUMN_ASSIGNMENT_GRADE, assignment.getAssignmentGrade());

        try{
            id = db.insertOrThrow(Config.ASSIGNMENT_TABLE_NAME, null, contentValues);
        }
        catch (SQLException e){
            Toast.makeText(context, "Operation Failed!: " + e, Toast.LENGTH_LONG).show();
        }
        finally {
            db.close();
        }
        return id;

    }

    //returns a list of all the courses in the database
    public List<Course> getAllCourses(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        try {
            cursor = db.query(Config.COURSE_TABLE_NAME, null, null, null, null, null, null);

            if (cursor != null){
                if (cursor.moveToFirst()){
                    List<Course> courses = new ArrayList<>();

                    do {
                        int courseID = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_COURSE_ID));
                        String courseName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_NAME));
                        String courseCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_CODE));

                        courses.add(new Course(courseID, courseName, courseCode));
                    }
                    while (cursor.moveToNext());

                    return courses;
                }
            }
        }
        catch (SQLException e){
            Log.d(TAG, "EXCEPTION: " + e);
            Toast.makeText(context, "Operation Failed!: " + e, Toast.LENGTH_LONG).show();
        }
        finally {
            if (cursor != null)
                cursor.close();

            db.close();
        }
        return Collections.emptyList();
    }

    //returns a list of all the assignments in the database
    public List<Assignment> getAllAssignments(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        try {
            cursor = db.query(Config.ASSIGNMENT_TABLE_NAME, null, null, null, null, null, null);

            if (cursor != null){
                if (cursor.moveToFirst()){
                    List<Assignment> assignments = new ArrayList<>();

                    do {
                        int assignmentID = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_ID));
                        int assignmentCourseID = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_COURSE_ID));
                        String assignmentName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_NAME));
                        int assignmentGrade = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_GRADE));

                        assignments.add(new Assignment(assignmentID, assignmentCourseID, assignmentName, assignmentGrade));
                    }
                    while (cursor.moveToNext());

                    return assignments;
                }
            }
        }
        catch (SQLException e){
            Log.d(TAG, "EXCEPTION: " + e);
            Toast.makeText(context, "Operation Failed!: " + e, Toast.LENGTH_LONG).show();
        }
        finally {
            if (cursor != null)
                cursor.close();

            db.close();
        }
        return Collections.emptyList();
    }

    //returns a course from the database selected by its name
    public Course getCourseByName(String courseName){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        try {
            cursor = db.query(Config.COURSE_TABLE_NAME, null, Config.COLUMN_COURSE_NAME + "= ?", new String[]{courseName}, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()){

                    int courseID = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_COURSE_ID));
                    String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_NAME));
                    String code = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_CODE));

                    return new Course(courseID, name, code);
                }
            }
        }
        catch (SQLException e){
            Log.d(TAG, "EXCEPTION: " + e);
            Toast.makeText(context, "Operation Failed!: " + e, Toast.LENGTH_LONG).show();
        }
        finally {
            if (cursor != null)
                cursor.close();

            db.close();
        }
        return null;
    }

    //returns a course from the database selected by its ID
    public List<Assignment> getAssignmentsByCourseId(int courseId){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        //Log.d(TAG, "In getAssignmentByCOURSEID");

        try {
            cursor = db.query(Config.ASSIGNMENT_TABLE_NAME, null, Config.COLUMN_ASSIGNMENT_COURSE_ID + "= ?", new String[]{String.valueOf(courseId)}, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()){
                    List<Assignment> assignments = new ArrayList<>();


                    do {
                        int assignmentID = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_ID));
                        int assignmentCourseID = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_COURSE_ID));
                        String assignmentName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_NAME));
                        int assignmentGrade = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_GRADE));

                        assignments.add(new Assignment(assignmentID, assignmentCourseID, assignmentName, assignmentGrade));
                    }
                    while (cursor.moveToNext());

                    return assignments;
                }
            }
        }
        catch (SQLException e){
            Log.d(TAG, "EXCEPTION: " + e);
            Toast.makeText(context, "Operation Failed!: " + e, Toast.LENGTH_LONG).show();
        }
        finally {
            if (cursor != null)
                cursor.close();

            db.close();
        }
        return Collections.emptyList();
    }

    //delete a course and the associated assignments
    public void deleteCourse(int courseID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Config.COURSE_TABLE_NAME, Config.COLUMN_COURSE_ID + " = ?", new String[]{String.valueOf(courseID)});
        db.delete(Config.ASSIGNMENT_TABLE_NAME, Config.COLUMN_ASSIGNMENT_COURSE_ID + " = ? ", new String[]{String.valueOf(courseID)});
        Toast.makeText(context, "Course Deleted", Toast.LENGTH_SHORT).show();
    }

}
