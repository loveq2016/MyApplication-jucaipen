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
/*public class SchoolAdapter extends BaseAdapter implements View.OnClickListener {
    private final List<SchoolKnowledge> knowledges;
    private final Context context;

    public SchoolAdapter(List<SchoolKnowledge> knowledges, Context context) {
        this.knowledges = knowledges;
        this.context = context;
    }

    @Override
    public int getCount() {
        return knowledges.size();
    }

    @Override
    public Object getItem(int position) {
        return knowledges.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ui_knowtype, null);
        }
        TextView tv_bgType = (TextView) convertView.findViewById(R.id.tv_bgType);
        LinearLayout ll_lay = (LinearLayout) convertView.findViewById(R.id.ll_lay);

        SchoolKnowledge know = knowledges.get(position);
        List<SmallType> strs = know.getSmallType();
        int bId = know.getBigId();
        int sId = know.getSmallId();


        String type = know.getBigType();
        tv_bgType.setText(type);
        tv_bgType.setBackgroundColor(know.getColor());
        if (strs.size() <= 0) {
            TextView tv_item = new TextView(context);
            tv_item.setText("暂无二级分类");
            ll_lay.addView(tv_item);
        } else {
            for (int i = 0; i < strs.size(); i++) {
                TextView tv_item = new TextView(context);
                tv_item.setText(strs.get(i).smallType);
                ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tv_item.setLayoutParams(lp);
                tv_item.setId(i);
                tv_item.setId(strs.get(i).xxId);
                tv_item.setOnClickListener(this);
                ll_lay.addView(tv_item);
            }
            ll_lay.measure(0, 0);
            int h = ll_lay.getMeasuredHeight();
            tv_bgType.setHeight(h);
        }

        return convertView;
    }

    @Override
    public void onClick(View v) {
        int nId = v.getId();
        Intent intent = new Intent();
        intent.setClass(context, KnowListActivity.class);
        intent.putExtra("nId", nId);
        context.startActivity(intent);

    }
}*/
public class SchoolAdapter extends BaseExpandableListAdapter implements View.OnClickListener {
    private List<SchoolKnowledge> parents;
    private List<List<SmallType>> child;
    private Context context;
   /* private final List<SchoolKnowledge> knowledges;
    private final Context context;*/

    /*public SchoolAdapter(List<SchoolKnowledge> knowledges, Context context) {
        this.knowledges = knowledges;
        this.context = context;
    }*/

    public SchoolAdapter(List<SchoolKnowledge> parents, List<List<SmallType>> child, Context context) {
        this.parents = parents;
        this.child = child;
        this.context = context;

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