package com.example.quiltingapp.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quiltingapp.ProjectModel;
import com.example.quiltingapp.R;

import java.util.List;

public class ProjectListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<ProjectModel> mProjects;

    public ProjectListAdapter(Context appContext) {
        this.context = appContext;
        this.inflater = (LayoutInflater.from(appContext));
    }

    public void setmProjects(List<ProjectModel> projects) {
        mProjects = projects;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mProjects != null) {
            return mProjects.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return mProjects.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.tab1listitem, null);
        TextView projNameView = (TextView) view.findViewById(R.id.pNameText);
        projNameView.setText(mProjects.get(i).getTitle());
        return view;
    }
}
