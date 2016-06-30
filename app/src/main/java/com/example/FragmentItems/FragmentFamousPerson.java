package com.example.FragmentItems;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidnetwork.R;
import com.example.utils.JsonUtil;
import com.example.Activity.FamousPlain;
import com.example.adapter.PersonAdapter;
import com.example.model.Famous;
import com.example.utils.NetUtils;
import com.example.utils.StoreUtils;
import com.example.utils.StringUntils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jucaipen on 16/5/6.
 * <p/>
 * 主页 －－－ 名家模块
 */
public class FragmentFamousPerson extends Fragment implements View.OnClickListener, AbsListView.OnScrollListener {
    private View view;
    private RelativeLayout px;
    private RelativeLayout isLiving;
    private Map<String, Object> map = new HashMap<>();
    private XRecyclerView lv_famous;
    private String personUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getfamous";
    private PopupWindow window;
    private PersonAdapter personAdapter;
    private TextView comm;
    private List<Famous> famousList = new ArrayList<>();
    private TextView moth;
    private TextView totle;
    private TextView week;
    private LinearLayout type;
    private boolean scrollFlag;
    private int lastVisibleItemPosition;
    private int page = 1;
    private PersonAdapter.OnRecyclerViewItemClickListener mOnItemClickListener;
    private ProgressBar famous_progress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.famousperson, container, false);

        initView();
        GetPerson(page);

        return view;


    }

    private void initView() {
        personAdapter = new PersonAdapter(getActivity(), famousList);
        px = (RelativeLayout) view.findViewById(R.id.px);
        isLiving = (RelativeLayout) view.findViewById(R.id.isLiving);
        lv_famous = (XRecyclerView) view.findViewById(R.id.lv_famous);
        type = (LinearLayout) view.findViewById(R.id.type);
        lv_famous.setAdapter(personAdapter);
        personAdapter.setmOnItemClickListener(new PersonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), FamousPlain.class);
                intent.putExtra("teacherId", famousList.get(position).getId());
                startActivity(intent);
            }
        });

        famous_progress = (ProgressBar) view.findViewById(R.id.famous_progress);

        // lv_famous.setOnScrollListener(this);
        // lv_famous.setOnItemClickListener(this);
        isLiving.setOnClickListener(this);
        px.setOnClickListener(this);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.ui_type, null);
        window = new PopupWindow(v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setOutsideTouchable(true);
        v.setFocusableInTouchMode(false);
        comm = (TextView) v.findViewById(R.id.comm);
        moth = (TextView) v.findViewById(R.id.moth);
        totle = (TextView) v.findViewById(R.id.totle);
        week = (TextView) v.findViewById(R.id.week);
        week.setOnClickListener(this);
        moth.setOnClickListener(this);
        totle.setOnClickListener(this);
        comm.setOnClickListener(this);


        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        lv_famous.setLayoutManager(manager);
        lv_famous.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        lv_famous.setPullRefreshEnabled(false);
        lv_famous.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

                if (famousList != null && famousList.size() > 0) {
                    int totlePage = famousList.get(0).getTotpager();
                    if (totlePage >= page) {
                        int p = page++;
                        GetPerson(p);
                    }

                }


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lv_famous.loadMoreComplete();
                    }
                }, 3000);

            }
        });


    }

    public void setOnItemClickListener(PersonAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    private void GetPerson(int pages) {
        map.clear();
        map.put("isIndex", 1);
        map.put("page", pages);
        map.put("userId", StoreUtils.getUserInfo(getActivity()) + "");
        RequestParams params = NetUtils.sendHttpGet(personUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                famous_progress.setVisibility(View.GONE);
                lv_famous.setVisibility(View.VISIBLE);

                if (result != null && result.length() > 0) {
                    famousList = JsonUtil.getFamous(result, famousList);
                    personAdapter.setList(famousList);
                    personAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.px:
                //综合排序
                window.showAsDropDown(px, 0, 0);
                break;
            case R.id.isLiving:
                //正在直播
                break;
            case R.id.notLive:
                //暂未直播
                break;
            case R.id.week:
                window.dismiss();
                Toast.makeText(getActivity(), "周人气", Toast.LENGTH_SHORT).show();
                break;
            case R.id.moth:
                window.dismiss();
                Toast.makeText(getActivity(), "月人气", Toast.LENGTH_SHORT).show();
                break;
            case R.id.totle:
                window.dismiss();
                Toast.makeText(getActivity(), "总人气", Toast.LENGTH_SHORT).show();
                break;
            case R.id.comm:
                window.dismiss();
                Toast.makeText(getActivity(), "推荐", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;

        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //判断是否滑动
       /* if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            scrollFlag = true;
        } else {
            scrollFlag = false;
        }*/

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        /*if (scrollFlag) {
            if (firstVisibleItem > lastVisibleItemPosition) {
                //上滑
                type.setVisibility(View.GONE);
            } else if (firstVisibleItem < lastVisibleItemPosition) {
                //下滑
                type.setVisibility(View.VISIBLE);
            } else {
                return;
            }
        }
        lastVisibleItemPosition = firstVisibleItem;*/

    }

  /*  @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent();
        switch (parent.getId()) {
            case R.id.lv_famous:
                intent.setClass(getActivity(), FamousPlain.class);
                intent.putExtra("teacherId", famousList.get(position).getId());
                break;
        }
        startActivity(intent);

    }*/
}
