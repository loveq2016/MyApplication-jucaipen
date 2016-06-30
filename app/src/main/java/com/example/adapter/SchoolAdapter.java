package com.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Activity.KnowListActivity;
import com.example.androidnetwork.R;
import com.example.model.SmallType;
import com.example.model.SchoolKnowledge;

import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public class SchoolAdapter extends BaseExpandableListAdapter implements View.OnClickListener {
    private List<SchoolKnowledge> parents;
    private List<List<SmallType>> child;
    private Context context;


    public SchoolAdapter(List<SchoolKnowledge> parents, List<List<SmallType>> child, Context context) {
        this.parents = parents;
        this.child = child;
        this.context = context;

    }

    public void setParents(List<SchoolKnowledge> parents) {
        this.parents = parents;
    }

    public void setChild(List<List<SmallType>> child) {
        this.child = child;
    }

    @Override
    public int getGroupCount() {
        return parents.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parents.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ui_knowtype, null);
        }
        TextView tv_bgType = (TextView) convertView.findViewById(R.id.tv_bgType);
        tv_bgType.setText(parents.get(groupPosition).getBigType());
        ImageView type_iv= (ImageView) convertView.findViewById(R.id.type_iv);
        if(isExpanded){
            type_iv.setImageResource(R.drawable.top_jian);
        }else {
            type_iv.setImageResource(R.drawable.under_jian);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.childentype, null);
        }
        TextView childen_bgType = (TextView) convertView.findViewById(R.id.childen_bgType);
        convertView.setOnClickListener(this);
        convertView.setId(child.get(groupPosition).get(childPosition).xxId);
        childen_bgType.setText(child.get(groupPosition).get(childPosition).smallType);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void onClick(View v) {
        int nId = v.getId();
        Intent intent = new Intent();
        intent.setClass(context, KnowListActivity.class);
        intent.putExtra("nId", nId);
        context.startActivity(intent);
    }


}