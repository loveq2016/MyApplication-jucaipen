package com.example.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/6/3.
 * <p/>
 * 充值界面
 */
public class PayMoneny extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private ImageButton pay_back;
    private ImageButton pay_sure;
    private RadioGroup money_grp1;
    private RadioGroup money_grp2;
    private TextView money_num;
    private RadioButton payone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymoneny);

        init();
    }

    private void init() {
        pay_back = (ImageButton) findViewById(R.id.pay_back);
        pay_back.setOnClickListener(this);
        pay_sure = (ImageButton) findViewById(R.id.pay_sure);
        pay_sure.setOnClickListener(this);
        money_num = (TextView) findViewById(R.id.money_num);
        payone = (RadioButton) findViewById(R.id.payone);


        money_grp1 = (RadioGroup) findViewById(R.id.money_grp1);
        money_grp1.setOnCheckedChangeListener(this);

        money_grp2 = (RadioGroup) findViewById(R.id.money_grp2);
        money_grp2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case 1:
                        money_num.setText("3000元");
                        selected1();
                        Toast.makeText(PayMoneny.this, "3000", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        money_num.setText("8000元");
                        selected1();
                        Toast.makeText(PayMoneny.this, "8000", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        money_num.setText("10000元");
                        selected1();
                        Toast.makeText(PayMoneny.this, "1万", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


    }

    private void selected2() {
        for (int i = 0; i < money_grp2.getChildCount(); i++) {
            RadioButton button = (RadioButton) money_grp2.getChildAt(i);
            button.setChecked(false);
        }
    }

    private void selected1() {
        for (int i = 0; i < money_grp1.getChildCount(); i++) {
            RadioButton button = (RadioButton) money_grp1.getChildAt(i);
            button.setChecked(false);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.pay_back:
                this.finish();
                break;
            case R.id.pay_sure:
                Toast.makeText(PayMoneny.this, "" + money_num.getText().toString(), Toast.LENGTH_SHORT).show();
                String money = money_num.getText().toString();
                if (money != null && money.length() > 0) {
                    intent.putExtra("money", money);
                    intent.setClass(this, PayType.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(PayMoneny.this, "请选择充值数量", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.payone:
                money_num.setText("3000元");
                selected1();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case 0:
                money_num.setText("100元");
                selected2();
                break;
            case 2:
                money_num.setText("1000元");
                selected2();
                break;
            case 3:
                money_num.setText("1500元");
                selected2();
                break;

        }


    }


}
