package com.example.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.adapter.CommentAdapter;
import com.example.adapter.RelateAdapter;
import com.example.androidnetwork.R;
import com.example.model.Comments;
import com.example.utils.JsonUtil;
import com.example.utils.StoreUtils;
import com.example.utils.StringUtil;
import com.example.utils.TimeUtils;
import com.example.adapter.InviteCodeAdaper;
import com.example.model.Marker;
import com.example.model.Press;
import com.example.utils.DialogUtils;
import com.example.utils.Loading;
import com.example.utils.NetUtils;
import com.example.utils.SharedNews;
import com.example.utils.StringUntils;
import com.example.view.MyGridView;
import com.example.view.TestListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2016/5/13.
 * 热点详情
 */
public class HotCareful extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private RelateAdapter adapter;
    // private CommentAdapter commentAdapter;
    private TestListView commentListview;
    private String url = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getnewsdetail";
    private String readurl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getrelatenews";
    private String commurl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getlogcomments";
    private String markerUrl = "http://192.168.1.134:8080/Jucaipen/jucaipen/getmarker";
    private String collectUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/star";

    private ImageButton hot_see;
    private Map<String, Object> map = new HashMap<>();
    private MyGridView grd;
    private ScrollView hot_scroll;
    private Context context = HotCareful.this;
    private List<Marker> list = new ArrayList<Marker>();
    private InviteCodeAdaper inviteCodeAdaper;
    private List<Comments> comms = new ArrayList<>();

    private Press press = new Press();
    private TextView tv_hottitle;
    private ImageView hot_image;
    private TextView tv_teachername;
    private TextView tv_inserttime;
    private ImageView iv_hotnews;
    private WebView tv_body;
    // private TestListView lv_see;
    // private AboutSee aboutSee;
    private TextView read_num;
    private TextView tv_zannum;
    private TextView tv_second;
    private int newsId;
    private int page = 1;
    private ImageView hot_headface;
    private TextView tv_markerNum;
    private ImageView iv_shared;
    private ImageButton share_qq;
    private ImageButton ibt_qqzone;
    private ImageButton ibt_weixin;
    private ImageButton ibt_wenxinzone;
    private ImageButton ibt_weibo;
    private Button btn_call;
    private String iscollectUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getisattention";
    private ProgressBar progress;


    // private String arry[] = {"分享到QQ", "分享到QQ空间", "分享到微信好友", "分享到微信朋友圈", "分享到新浪"};
    private ArrayList<String> imageList = new ArrayList<>();
    private Dialog dialog;
    private ArrayList<String> imagList = new ArrayList<>();
    private Animation animation;
    private int teacherId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotcareful);
        initwight();
    }

    private void initwight() {
        progress = (ProgressBar) findViewById(R.id.progress);
        animation = Loading.loadanntation(this, progress);

        Interpolator interpolator = new LinearInterpolator();
        progress.setInterpolator(interpolator);


        initDate();
        init();
        //全部评论
        // GetComm(0);
        //打赏接口
        Getmarker();

        tv_markerNum = (TextView) findViewById(R.id.tv_markerNum);
        tv_hottitle = (TextView) findViewById(R.id.tv_hottitle);
        hot_image = (ImageView) findViewById(R.id.hot_image);
        tv_teachername = (TextView) findViewById(R.id.tv_teachername);
        tv_inserttime = (TextView) findViewById(R.id.tv_inserttime);
        iv_hotnews = (ImageView) findViewById(R.id.iv_hotnews);
        tv_body = (WebView) findViewById(R.id.tv_body);
        read_num = (TextView) findViewById(R.id.read_num);
        tv_zannum = (TextView) findViewById(R.id.tv_zannum);
        tv_second = (TextView) findViewById(R.id.tv_second);
        hot_headface = (ImageView) findViewById(R.id.hot_headface);
        iv_shared = (ImageView) findViewById(R.id.iv_shared);
        dialog = DialogUtils.ShareDialog(context);

        share_qq = (ImageButton) dialog.findViewById(R.id.share_qq);
        share_qq.setOnClickListener(this);
        ibt_qqzone = (ImageButton) dialog.findViewById(R.id.ibt_qqzone);
        ibt_qqzone.setOnClickListener(this);
        ibt_weixin = (ImageButton) dialog.findViewById(R.id.ibt_weixin);
        ibt_weixin.setOnClickListener(this);
        ibt_wenxinzone = (ImageButton) dialog.findViewById(R.id.ibt_wenxinzone);
        ibt_wenxinzone.setOnClickListener(this);
        ibt_weibo = (ImageButton) dialog.findViewById(R.id.ibt_weibo);
        ibt_weibo.setOnClickListener(this);
        btn_call = (Button) dialog.findViewById(R.id.btn_call);
        btn_call.setOnClickListener(this);


        iv_shared.setOnClickListener(this);
        grd = (MyGridView) findViewById(R.id.grd);
        inviteCodeAdaper = new InviteCodeAdaper(list);
        grd.setAdapter(inviteCodeAdaper);


//        aboutSee = new AboutSee(this, relatedList);
//        lv_see = (TestListView) findViewById(R.id.lv_see);
//        lv_see.setAdapter(aboutSee);


    }

    private void Getmarker() {
        map.clear();
        map.put("logId", newsId);
        RequestParams params = NetUtils.sendHttpGet(markerUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                hot_scroll.setVisibility(View.VISIBLE);
                progress.clearAnimation();
                progress.setVisibility(View.GONE);
                if (result != null) {
                    try {
                        JSONArray array = new JSONArray(result);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            String face = object.getString("face");
                            int markerNum = object.getInt("markerNum");
                            Marker marker = new Marker();
                            marker.setFace(face);
                            marker.setMarkerNum(markerNum);
                            list.add(marker);
                        }
                        inviteCodeAdaper.notifyDataSetChanged();
                        tv_markerNum.setText(list.get(0).getMarkerNum() + "人赞赏");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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


    private void initDate() {
        teacherId = getIntent().getIntExtra("teacherId", -1);
        newsId = getIntent().getIntExtra("id", -1);
        map.clear();
        map.put("newsId", newsId);
        map.put("typeId", 1);

        RequestParams params = NetUtils.sendHttpGet(url, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                press = JsonUtil.gethotsee(result);
                tv_hottitle.setText(press.getTitle());
                tv_teachername.setText(press.getFrom());
                tv_inserttime.setText(TimeUtils.parseStrDate(press.getInsertDate(), "yyyy-MM-dd HH:mm"));
                tv_body.getSettings().setMinimumFontSize(39);
                tv_body.getSettings().setUseWideViewPort(true);
                tv_body.getSettings().setLoadWithOverviewMode(true);
                tv_body.loadDataWithBaseURL(null,press.getBody(),"text/html","UTF-8",null);
                tv_body.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return true;
                    }
                });
                read_num.setText("阅读数  " + press.getReadNum() + "");
                tv_zannum.setText(" " + press.getGoods() + "");
                tv_second.setText(press.getKeyWord());


                iv_hotnews.setVisibility(View.GONE);
                Glide.with(context).load(press.getTeacherFace())
                        .bitmapTransform(new CropCircleTransformation(context))
                        .placeholder(R.drawable.rentou)
                        .into(hot_headface);
                tv_teachername.setText(press.getTeacherName());


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

    private void GetComm(int parentId) {
        map.clear();
        map.put("id", newsId);
        map.put("commType", 0);
        map.put("parentId", parentId);
        map.put("page", 1);

        RequestParams params = NetUtils.sendHttpGet(commurl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                // Toast.makeText(HotCareful.this, ""+result, Toast.LENGTH_SHORT).show();

                if (result != null) {
                    comms = JsonUtil.getcomments(result);
                    //commentAdapter.setList(comms);
                }
                // commentAdapter.notifyDataSetChanged();
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

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });


    }


    private void init() {
//        adapter=new RelateAdapter(context);
//        lv_relatedNews.setAdapter(adapter);
        hot_see = (ImageButton) findViewById(R.id.hot_see);
        hot_see.setOnClickListener(this);
        hot_scroll = (ScrollView) findViewById(R.id.hot_scroll);
        hot_scroll.smoothScrollTo(0, 0);

        commentListview = (TestListView) findViewById(R.id.comment_xv);
        commentListview.setOnItemClickListener(this);
//        commentAdapter = new CommentAdapter(comms, this);
//        commentListview.setAdapter(commentAdapter);


        /*dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(this).inflate(R.layout.sharewindow, null);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();
        int width = d.getWidth();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        lp.width = width;*/


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hot_see:
                this.finish();
                break;


            case R.id.iv_shared:
                if (!dialog.isShowing()) {
                    dialog.show();
                }
                break;

            case R.id.share_qq:
                SharedNews.shareQQ(HotCareful.this, context, "脸子", "http://jcplicai.com");
                dialog.cancel();
                break;

            case R.id.ibt_qqzone:
                SharedNews.shareQz(this, context, "title", "body", "http://jcplicai.com", imagList);
                dialog.cancel();
                break;
            case R.id.ibt_weixin:
                SharedNews.shareWX(context, "title", "msg");
                dialog.cancel();
                break;
            case R.id.ibt_wenxinzone:
                SharedNews.shareWXz(context, "title", "msgzone");
                dialog.cancel();
                break;
            case R.id.ibt_weibo:
                Toast.makeText(HotCareful.this, "暂不支持分享", Toast.LENGTH_SHORT).show();
                dialog.cancel();
                break;
            case R.id.btn_call:
                dialog.cancel();
                break;


            //分享
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                View view = LayoutInflater.from(this).inflate(R.layout.sharewindow, null);
//                builder.setView(view);
//                builder.show();
               /* builder.setSingleChoiceItems(arry, 0, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                String wh = arry[which];
                                //{"分享到QQ","分享到微信","分享到新浪"}
                                if (wh.equals("分享到QQ")) {
                                    share();
                                } else if (wh.equals("分享到QQ空间")) {
                                    shareQz();
                                } else if (wh.equals("分享到微信好友")) {
                                    shareWX();
                                } else if (wh.equals("分享到微信朋友圈")) {
                                    shareWXz();
                                }
                            }
                        }*/

//                Dialog dialog = builder.create();
//                dialog.show();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(this, QuestionAnswer.class);
        startActivity(intent);

    }


 /*   //QQ分享
    public void share() {
        Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, "要分享的标题");
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://jcplicai.com");
        Tencent mTencent = QQLoginUtil.getInstance().getTencenInstance(this);
        mTencent.shareToQQ(this, bundle, new IUiListener() {
            @Override
            public void onComplete(Object o) {
                Toast.makeText(HotCareful.this, "分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(UiError uiError) {
                Toast.makeText(HotCareful.this, "error:" + uiError.errorDetail, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(HotCareful.this, "cancel", Toast.LENGTH_SHORT).show();

            }
        });
    }*/

    /*// 分享到QQ空间
    public void shareQz() {
        Bundle bubdle = new Bundle();
        bubdle.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        bubdle.putString(QzoneShare.SHARE_TO_QQ_TITLE, "标题");//必填
        bubdle.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "摘要");//选填
        bubdle.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "http://jcplicai.com");//必填
        imageList.add("http://img.1.png");
        bubdle.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageList);
        Toast.makeText(HotCareful.this, "share", Toast.LENGTH_SHORT).show();
        Tencent mTencent = QQLoginUtil.getInstance().getTencenInstance(this);
        mTencent.shareToQzone(this, bubdle, new IUiListener() {
            @Override
            public void onComplete(Object o) {
                Toast.makeText(HotCareful.this, "success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(UiError uiError) {
                Toast.makeText(HotCareful.this, "error:" + uiError.errorCode, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancel() {
                Toast.makeText(HotCareful.this, "cancel", Toast.LENGTH_SHORT).show();

            }
        });
    }*/


    /*//分享给微信好友
    public void shareWX() {
        WXTextObject object = new WXTextObject();
        object.text = "我要分享";

        WXMediaMessage wxm = new WXMediaMessage();
        wxm.description = "分享描述";
        wxm.mediaObject = object;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "text";
        req.message = wxm;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        IWXAPI api = WeiXinLoginUtils.getApi(this);
        api.sendReq(req);
    }

    //分享到微信朋友圈
    public void shareWXz() {
        WXTextObject object = new WXTextObject();
        object.text = "我要分享";

        WXMediaMessage wxm = new WXMediaMessage();
        wxm.description = "分享描述";
        wxm.mediaObject = object;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "text";
        req.message = wxm;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        IWXAPI api = WeiXinLoginUtils.getApi(this);
        api.sendReq(req);
    }*/


}
