package com.example.taskmanagerapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.taskmanagerapp.entity.Task;


public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DB_NAME = "TasksDatabase.db";
    public static final int DB_Version = 1;

    public static final String TABLE_NAME = "tbl_contacts";

    public static final String COL_ID = "id";
    public static final String COL_TASKTITLE = "task_title";
    public static final String COL_TASKDESCRIPTION = "task_description";
    public static final String COL_TIME = "time";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_Version);
        this.context = context;
    }

    //Creates table with columns in db for tasks
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_TASKTITLE + " TEXT, " +
                COL_TASKDESCRIPTION + " TEXT, " +
                COL_TIME + " TEXT);";
        db.execSQL(query);
    }
    //Updates sqlitedatabase when available
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public void createTask (Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_TASKTITLE, task.gettaskTitle());
        cv.put(COL_TASKDESCRIPTION, task.gettaskDescription());
        cv.put(COL_TIME, task.gettaskTime());

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1)
            Toast.makeText(context, " Failed to Insert", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, task.gettaskTitle() + " saved successfully", Toast.LENGTH_SHORT).show();


    }
    public Cursor readAllTask(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = null;
        if (db != null) cursor = db.rawQuery(query, null);

        return cursor;

    }

    public Cursor readTaskById (String id) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = " + id;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db == null) Toast.makeText(context, "NO DATA", Toast.LENGTH_SHORT).show();
        else cursor = db.rawQuery(query, null);

        return cursor;

    }

    public long updateTaskbyId(Task task) {
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues cv = new ContentValues();

         cv.put(COL_TASKTITLE, task.gettaskTitle());
         cv.put(COL_TASKDESCRIPTION, task.gettaskDescription());
         cv.put(COL_TIME, task.gettaskTime());

         return db.update(TABLE_NAME, cv, "id=?", new String[]{task.getID()+""});
    }

    public long deleteTaskbyID (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id=?", new String[]{id});
    }
}