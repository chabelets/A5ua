package com.chabelets.root.a5ua.core.feature.main;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpView;
import com.bumptech.glide.Glide;
import com.chabelets.root.a5ua.R;
import com.chabelets.root.a5ua.core.utils.Starter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpAppCompatActivity implements MvpView {

    @BindView(R.id.tvNext)
    TextView tvNext;
    @BindView(R.id.ivLogo)
    ImageView ivLogo;

    private static final String IMG_URL = "http://a5.ua/sites/default/files/newsite_logo_1.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setImageLogo();
    }

    private void setImageLogo() {
        Glide.with(this).load(IMG_URL).into(ivLogo);
    }

    @OnClick({R.id.tvNext})
    void nextScreen() {
        Starter.startNextActivity(this);
    }
}
