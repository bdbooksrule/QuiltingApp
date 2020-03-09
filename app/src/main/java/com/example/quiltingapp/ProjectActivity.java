package com.example.quiltingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class ProjectActivity extends AppCompatActivity {

    public ProjectModel projectModel;
    private EditText pTitle;
    public EditText pStatus;
    public EditText pDesigner;
    public EditText pNotes;

    public static final String EXTRA_REPLY = "com.example.quiltingapp.extra.REPLY";
    public static final String TAG = "ProjectActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String[] messageSegs = message.split(";\n", 5);
        projectModel = new ProjectModel(Integer.parseInt(messageSegs[3]));
        Log.d(TAG, messageSegs[0]);
        Log.d(TAG, messageSegs[1]);
        Log.d(TAG, messageSegs[2]);
        Log.d(TAG, messageSegs[3]);
        Log.d(TAG, messageSegs[4]);


        this.pTitle = findViewById(R.id.editTitle);
        this.pStatus = findViewById(R.id.editStat);
        this.pDesigner = findViewById(R.id.editDes);
        this.pNotes = findViewById(R.id.editNotes);

        if(messageSegs.length == 5) {
            projectModel.setTitle(messageSegs[0]);
            this.pTitle.setText(messageSegs[0]);
            projectModel.setStatus(messageSegs[1]);
            this.pStatus.setText(messageSegs[1]);
            projectModel.setDesigner(messageSegs[2]);
            this.pDesigner.setText(messageSegs[2]);
            projectModel.setNotes(messageSegs[4]);
            this.pNotes.setText(messageSegs[4]);
        }
    }

    public void saveButtonHandler(android.view.View view) {
        projectModel.setTitle(pTitle.getText().toString());
        projectModel.setDesigner(pDesigner.getText().toString());
        projectModel.setStatus(pStatus.getText().toString());
        projectModel.setNotes(pNotes.getText().toString());

        Intent saveProjectIntent = new Intent();
        saveProjectIntent.putExtra(EXTRA_REPLY, projectModel.toString());
        setResult(RESULT_OK, saveProjectIntent);
        finish();
    }

/*    public void deleteButtonHandler(android.view.View view) {
        Intent deleteProjectIntent = new Intent();
        deleteProjectIntent.putExtra(EXTRA_REPLY, "del");
        setResult(RESULT_OK, deleteProjectIntent);
        finish();
    }*/


}
