package com.chabelets.root.a5ua.core.feature.next;

import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.chabelets.root.a5ua.R;
import com.chabelets.root.a5ua.core.utils.Starter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.chabelets.root.a5ua.core.feature.map.MapActivity.PLACE_NAME;

public class PlaceFragment extends DialogFragment {

    @BindView(R.id.tvAboutPlace)
    TextView tvAboutPlace;

    private Unbinder unbinder;
    private static final double WIDTH_DIALOG = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(obtainLayoutResId(), container, false);
        this.unbinder = ButterKnife.bind(this, rootView);
        decorateDialog();
        getBundleArgs();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().setOnKeyListener((dialog, keyCode, event) -> {
            if ((keyCode == KeyEvent.KEYCODE_BACK)) {
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
                return true;
            } else
                return false;
        });
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null)
            unbinder.unbind();
        super.onDestroyView();
    }

    @OnClick(R.id.btClose)
    public void closeFragment(){
        Starter.startMapWithFlagActivity(requireActivity());
    }

    protected int obtainLayoutResId() {
        return R.layout.dialog_place;
    }

    protected void decorateDialog() {
        Window window = getDialog().getWindow();
        Point size = new Point();
        if (window != null) {
            Display display = window.getWindowManager().getDefaultDisplay();
            display.getSize(size);
            window.setLayout((int) (size.x * WIDTH_DIALOG), WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
            window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorTransparent)));
        }
    }

    void getBundleArgs(){
        if (getArguments() != null) {
            String placeName = getArguments().getString(PLACE_NAME);
            tvAboutPlace.setText(placeName);
        } else {
            tvAboutPlace.setText(R.string.unknown_place);
        }
    }
}
