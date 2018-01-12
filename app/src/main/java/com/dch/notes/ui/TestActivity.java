package com.dch.notes.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.dch.notes.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.ll_test1)
    LinearLayout llTest1;
    @BindView(R.id.ll_test2)
    LinearLayout llTest2;
    @BindView(R.id.ll_test3)
    LinearLayout llTest3;
    @BindView(R.id.ll_test4)
    LinearLayout llTest4;
    @BindView(R.id.ll_test5)
    LinearLayout llTest5;

    @OnClick(R.id.ll_test1)
    public void onClick1(){

    }

    @OnClick(R.id.ll_test2)
    public void onClick2(){

    }

    @OnClick(R.id.ll_test3)
    public void onClick3(){

    }

    @OnClick(R.id.ll_test4)
    public void onClick4(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }
}
