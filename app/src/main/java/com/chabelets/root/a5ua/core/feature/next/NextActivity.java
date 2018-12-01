package com.chabelets.root.a5ua.core.feature.next;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.chabelets.root.a5ua.R;
import com.chabelets.root.a5ua.core.utils.Starter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tvNext})
    void nextScreen() {
        Starter.startMapActivity(this);
    }

    @OnClick({R.id.tvBack})
    void backScreen() {
        Starter.startMainActivity(this);
    }
}
