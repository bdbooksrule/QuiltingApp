package com.example.quiltingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.quiltingapp.ui.main.ProjectListAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.quiltingapp.extra.message";
    public static final int TEXT_REQUEST = 1;
//    public ArrayList<ProjectModel> projectModelArrayList;
    public ListView pList;
    private ProjectListAdapter projectListAdapter;

    private ProjectViewModel mProjectViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);*/

/*        this.projectModelArrayList = new ArrayList<>();

        //to be removed after testing is done:
        ProjectModel exampleProject = new ProjectModel(0);
        exampleProject.setNotes("Notes go in here");
        exampleProject.setDesigner("optional space to fill with a designer name.");
        exampleProject.setStatus("Quilting Completed");
        exampleProject.setTitle("ExampleProjectTitle");
        this.projectModelArrayList.add(exampleProject);

        ArrayList<String> nameList = new ArrayList<>();
        int i;
        for(i = 0; i < this.projectModelArrayList.size(); i++) {
            String name = projectModelArrayList.get(i).getTitle();
            nameList.add(name);
        }
        String[] nameArray = new String[nameList.size()];
        for(i = 0; i < nameList.size(); i++) {
            nameArray[i] = nameList.get(i);
        }*/
        pList = (ListView) findViewById(R.id.listforprojects);
        projectListAdapter = new ProjectListAdapter(getApplicationContext());
        //set list here
        pList.setAdapter(projectListAdapter);

        mProjectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);
        mProjectViewModel.getAllProjects().observe(this, new Observer<List<ProjectModel>>() {
            @Override
            public void onChanged(List<ProjectModel> projectModels) {
                projectListAdapter.setmProjects(projectModels);
            }
        });

        pList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int intId = (int) id;
                launchProjectA(intId);
            }
        });

        Button addButton = (Button) findViewById(R.id.addbutton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newProject();
            }
        });
    }

    public void launchProjectA(int id) {
        Intent intent = new Intent(this, ProjectActivity.class);
        int pos = findProject(id);
        String message = mProjectViewModel.getAllProjects().getValue().get(pos).toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivityForResult(intent, TEXT_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TEXT_REQUEST) {
            if(resultCode == RESULT_OK) {
                String reply = data.getStringExtra(ProjectActivity.EXTRA_REPLY);
                String[] replySegs = reply.split(";\n", 5);
                int checkId = Integer.parseInt(replySegs[3]);

                ProjectModel project = new ProjectModel(checkId);
                project.setTitle(replySegs[0]);
                project.setStatus(replySegs[1]);
                project.setDesigner(replySegs[2]);
                project.setNotes(replySegs[4]);

/*                if (reply == "del") {
                    mProjectViewModel.delete(project);
                    return;
                }*/

                int pos = findProject(checkId);
                if(pos != -1) {
                    mProjectViewModel.update(project);
                } else {
                    mProjectViewModel.insert(project);
                }
            }
        }

    }

/*    public void updateList() { //deprecated by use of SQLite
        ArrayList<String> nameList = new ArrayList<>();
        int i;
        for(i = 0; i < this.projectModelArrayList.size(); i++) {
            String name = projectModelArrayList.get(i).getTitle();
            nameList.add(name);
        }
        String[] nameArray = new String[nameList.size()];
        for(i = 0; i < nameList.size(); i++) {
            nameArray[i] = nameList.get(i);
        }

        projectListAdapter.setNameList(nameArray);
    }*/

    public int findProject (int id) {
        List<ProjectModel> searchSpace = mProjectViewModel.getAllProjects().getValue();
        int j;
        int size;
        try {
            size = searchSpace.size();
        } catch (NullPointerException e){
            return -1;
        }
        if(size <= 0) {
            return -1;
        }
        for (j = 0; j < size; j++) {
            if(searchSpace.get(j).getId() == id) {
                return j;
            }
        }
        return -1;
    }

    private int newId(int startingPoint) {
        if(findProject(startingPoint) == -1) {
            return startingPoint;
        } else {
            return newId(startingPoint + 1);
        }
    }

    public void newProject() {
        int size;
        int id;
        try{
            size = mProjectViewModel.getAllProjects().getValue().size();
        } catch (NullPointerException e) {
            size = 0;
        }
        id = newId(size);

        ProjectModel newProj = new ProjectModel(id);
        newProj.setTitle("NewProjectTitle");
        newProj.setStatus("Just Started");

        mProjectViewModel.insert(newProj);

    }
}