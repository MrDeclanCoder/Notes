package com.dch.notes.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.dch.notes.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.iv_first)
    ImageView ivHome;
    @BindView(R.id.bt_first)
    Button btHome;
    @BindView(R.id.iv_second)
    ImageView ivSecond;
    @BindView(R.id.bt_second)
    Button btSecond;
    @BindView(R.id.iv_third)
    ImageView ivThird;
    @BindView(R.id.bt_third)
    Button btThird;
    @BindView(R.id.iv_forth)
    ImageView ivForth;
    @BindView(R.id.bt_forth)
    Button btForth;

    @OnClick(R.id.bt_first)
    public void homeButtonClick() {
        startScaleAnim(ivHome);
    }

    @OnClick(R.id.bt_second)
    public void secondButtonClick() {
        startScaleAnim(ivSecond);
    }

    @OnClick(R.id.bt_third)
    public void thirdeButtonClick() {
        startScaleAnim(ivThird);
    }

    @OnClick(R.id.bt_forth)
    public void forthButtonClick() {
        startScaleAnim(ivForth);
    }

    private void startScaleAnim(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.8f,  1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.8f,  1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400).setInterpolator(new AnticipateOvershootInterpolator());
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);


    }
}
