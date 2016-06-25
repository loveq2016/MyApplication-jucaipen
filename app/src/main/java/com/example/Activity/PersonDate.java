package com.example.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.utils.JsonUtil;
import com.example.utils.StoreUtils;
import com.example.model.Person;
import com.example.utils.DialogUtils;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2016/5/16.
 * <p/>
 * 我的 编辑资料
 */
public class PersonDate extends Activity implements View.OnClickListener {
    private Context context = PersonDate.this;
    private TimePickerView pickerView;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private LayoutInflater inflater;
    private PopupWindow popupWindow;
    private Dialog dialog;
    private LinearLayout edt_liner;
    private boolean isshow = true;
    private ImageView head_face;
    private ImageView person_back;
    private RadioButton sex_man;
    private RadioButton sex_woman;
    private int sex;
    private int index;
    private ImageButton sex_sure;
    private WindowManager m;
    private Display d;
    private int width;
    private TextView brithday;
    private TextView tv_style;
    private RadioButton rbt_wenjian;
    private RadioButton rbt_jijin;
    private String myInfoUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getuserinfo";
    private String commUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/completeinfo";
    private ImageButton style_sure;
    private TextView tv_finish;
    private TextView tv_persondate;
    private Map<String, Object> map = new HashMap<>();
    private EditText edt_nickName;//昵称
    private TextView edt_account;//账号
    private EditText date_trueName;//姓名
    private RelativeLayout person_birth;//出生日期
    private RelativeLayout person_sex;//性别
    private RelativeLayout person_style;//投资风格
    private RelativeLayout person_address;//所在地
    private RelativeLayout person_date;//个人简介
    private TextView tv_sex;
    boolean isClick;
    private TextView tv_where;
    private String proice;
    private int proiceId;
    private String city;
    private int cityId;
    private String town;
    private int townId;
    private int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persondate);
        init();

        //获取个人信息
        GetMyDate();
    }

    private void GetMyDate() {
        map.clear();
        map.put("userId", userId);
        RequestParams params = NetUtils.sendHttpGet(myInfoUrl, map);
        params.setCacheMaxAge(0);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    // Toast.makeText(PersonDate.this, ""+result, Toast.LENGTH_SHORT).show();
                    Person person = JsonUtil.getMyInfo(result);
                    Glide.with(context).load(person.getFaceImage())
                            .bitmapTransform(new CropCircleTransformation(context))
                            .placeholder(R.drawable.rentou)
                            .into(head_face);
                    edt_nickName.setText(person.getNickName());
                    edt_account.setText(person.getAccount());
                    date_trueName.setText(person.getTrueName());
                    tv_sex.setText(person.getSex());
                    brithday.setText(person.getBirthday());
                    tv_where.setText(person.getLocalCity());
                    tv_style.setText(person.getInvestType());
                    tv_persondate.setText(person.getDescript());

                    //{"nickName":"6663","account":"13761129031","sex":"男",
                    // "trueName":"袁强强","birthday":"2016-02-26 00:00:00.0","localCity":"","investType":null,
                    // "descript":"Big face, small pig \n","faceImage":"http://img.penU16549.jpg"}

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

    private void init() {

        userId = StoreUtils.getUserInfo(this);
        proice = getIntent().getStringExtra("proice");
        proiceId = getIntent().getIntExtra("proiceId", -1);
        city = getIntent().getStringExtra("city");
        cityId = getIntent().getIntExtra("cityId", -1);
        town = getIntent().getStringExtra("town");
        townId = getIntent().getIntExtra("townId", -1);


        inflater = getLayoutInflater();
        edt_liner = (LinearLayout) findViewById(R.id.edt_liner);
        person_birth = (RelativeLayout) findViewById(R.id.person_birth);
        person_birth.setOnClickListener(this);
        person_sex = (RelativeLayout) findViewById(R.id.person_sex);
        person_sex.setOnClickListener(this);
        head_face = (ImageView) findViewById(R.id.head_face);
        head_face.setOnClickListener(this);
        person_back = (ImageView) findViewById(R.id.person_back);
        person_back.setOnClickListener(this);
        person_style = (RelativeLayout) findViewById(R.id.person_style);
        person_style.setOnClickListener(this);
        person_date = (RelativeLayout) findViewById(R.id.person_date);
        person_date.setOnClickListener(this);
        person_address = (RelativeLayout) findViewById(R.id.person_address);
        person_address.setOnClickListener(this);
        brithday = (TextView) findViewById(R.id.brithday);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_style = (TextView) findViewById(R.id.tv_style);
        tv_finish = (TextView) findViewById(R.id.tv_finish);
        tv_finish.setOnClickListener(this);
        tv_finish.setText("编辑");

        tv_persondate = (TextView) findViewById(R.id.tv_persondate);
        edt_nickName = (EditText) findViewById(R.id.edt_nickName);
        edt_account = (TextView) findViewById(R.id.edt_account);
        date_trueName = (EditText) findViewById(R.id.date_trueName);
        tv_where = (TextView) findViewById(R.id.tv_where);

        tv_where.setText(proice + "-" + city + "-" + town);
        NoCliclk(false);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        m = getWindowManager();
        d = m.getDefaultDisplay();
        width = d.getWidth();


        pickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        pickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                brithday.setText(sdf.format(date));
            }
        });

    }

    private void NoCliclk(boolean isClick) {

        head_face.setClickable(isClick);
        edt_nickName.setEnabled(isClick);
        date_trueName.setEnabled(isClick);
        person_sex.setClickable(isClick);
        person_birth.setClickable(isClick);
        person_address.setClickable(isClick);
        person_style.setClickable(isClick);
        person_date.setClickable(isClick);


    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.person_birth:
                pickerView.show();
                break;
            case R.id.person_sex:
                dialog.setContentView(R.layout.dialog_sex);
                dialog = DialogUtils.Loading(dialog, width);
                if (dialog != null && !dialog.isShowing()) {
                    dialog.show();
                }
                sex_man = (RadioButton) dialog.findViewById(R.id.sex_man);
                sex_man.setOnClickListener(this);
                sex_woman = (RadioButton) dialog.findViewById(R.id.sex_woman);
                sex_woman.setOnClickListener(this);
                sex_sure = (ImageButton) dialog.findViewById(R.id.sex_sure);
                sex_sure.setOnClickListener(this);
                break;


//            case R.id.person_number:
//                Intent intent = new Intent();
//                intent.setClass(this, Otheraccount.class);
//                startActivity(intent);
//                break;
            case R.id.head_face:
                Toast.makeText(PersonDate.this, "相机，相册", Toast.LENGTH_SHORT).show();

//                dialog.setContentView(R.layout.headimage);
//                dialog = DialogUtils.Loading(dialog, width);
//                if (dialog != null && !dialog.isShowing()) {
//                    dialog.show();
//                }


                break;
            case R.id.person_back:
                this.finish();
                break;
            case R.id.sex_man:
                sex = 1;
                break;
            case R.id.sex_woman:
                sex = 2;
                break;

            case R.id.sex_sure:
                if (sex == 1) {
                    tv_sex.setText("男");
                    dialog.cancel();
                } else if (sex == 2) {
                    tv_sex.setText("女");
                    dialog.cancel();
                } else {
                    Toast.makeText(PersonDate.this, "请您选择性别", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.tv_finish:
                if (tv_finish.getText().equals("编辑")) {
                    tv_finish.setText("完成");
                    isClick = true;
                    NoCliclk(isClick);


                } else {
                    tv_finish.setText("编辑");
                    isClick = false;
                    NoCliclk(isClick);
                    //修改信息 提交给服务器
                    GetPersonDate();
                    Toast.makeText(PersonDate.this, "提交服务器", Toast.LENGTH_SHORT).show();
                }


                break;

            //投资风格
            case R.id.person_style:
                dialog.setContentView(R.layout.person_styles);
                dialog = DialogUtils.Loading(dialog, width);
                if (dialog != null && !dialog.isShowing()) {
                    dialog.show();
                }
                rbt_wenjian = (RadioButton) dialog.findViewById(R.id.rbt_wenjian);
                rbt_wenjian.setOnClickListener(this);
                rbt_jijin = (RadioButton) dialog.findViewById(R.id.rbt_jijin);
                rbt_jijin.setOnClickListener(this);
                style_sure = (ImageButton) dialog.findViewById(R.id.style_sure);
                style_sure.setOnClickListener(this);
                break;
            case R.id.rbt_wenjian:
                index = 1;
                break;
            case R.id.rbt_jijin:
                index = 2;
                break;
            case R.id.style_sure:
                if (index == 1) {
                    tv_style.setText("稳健型");
                    dialog.cancel();
                } else if (index == 2) {
                    tv_style.setText("激进型");
                    dialog.cancel();
                } else {
                    Toast.makeText(PersonDate.this, "请您选择投资风格", Toast.LENGTH_SHORT).show();
                }
                break;


            //所在地
            case R.id.person_address:
                //省  市  区
                intent.setClass(this, Province.class);
                startActivity(intent);
                break;

            //个人简介
            case R.id.person_date:
                intent.setClass(this, Introduce.class);
                startActivityForResult(intent, 100);
                break;
            default:
                break;
        }
    }

    private void GetPersonDate() {
//        nickName   昵称
//        trueName   姓名
//        sex        性别
//        birth      出生日期
//        cityId        所在城市id  （int 类型）
//        investId      投资风格id   （int 类型）
//        plain        个人简介
        String nickName = edt_nickName.getText().toString().trim();
        String trueName = date_trueName.getText().toString().trim();
        String birth = brithday.getText().toString();
        String sex = tv_sex.getText().toString();
        tv_style.getText().toString();
        String plain = tv_persondate.getText().toString();

        RequestParams params = NetUtils.sendHttpGet(commUrl, map);
        map.clear();
        map.put("nickName", nickName);
        map.put("trueName", trueName);
        map.put("sex", sex);
        map.put("birth", birth);
        map.put("cityId", "2");
        map.put("investId", "1");
        map.put("plain", plain);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            if (data != null) {
                String value = data.getStringExtra("value");
                if (value != null && value.length() > 0) {
                    tv_persondate.setText(value);
                }
            } else {
                tv_persondate.setText("暂无简介");
            }

        }


    }
}
