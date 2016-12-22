package com.xiaoziqianbao.rxbindingabc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText et_input;
    private Button btn_enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         textView = (TextView) findViewById(R.id.tv_1);
        et_input = (EditText) findViewById(R.id.et_input);
        btn_enter = (Button) findViewById(R.id.btn_enter);
        bindingEvent();
    }

    /**
     * 绑定事件
     */
    private void bindingEvent() {
        /**点击事件*/
        RxView.clicks(btn_enter).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Toast.makeText(MainActivity.this, "11111", Toast.LENGTH_SHORT).show();     
            }
        });
        /**隐藏或者显示*/
        RxView.visibility(btn_enter).call(true);
        /**设置是否可点击*/
        RxView.clickable(btn_enter).call(true);
        /**长按*/
        RxView.longClicks(btn_enter).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Toast.makeText(MainActivity.this, "长按", Toast.LENGTH_SHORT).show();
            }
        });
        /**延迟400毫秒 取最后一个值*/
        RxTextView.textChanges(et_input).debounce(400, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                textView.setText(charSequence.toString());
            }
        });
        /**相当于setText*/
        RxTextView.text(et_input).call("老司机带带我");

    }
}
