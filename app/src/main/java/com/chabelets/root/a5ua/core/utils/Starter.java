package com.chabelets.root.a5ua.core.utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.chabelets.root.a5ua.core.feature.main.MainActivity;
import com.chabelets.root.a5ua.core.feature.map.MapActivity;
import com.chabelets.root.a5ua.core.feature.next.NextActivity;

public class Starter {

    public static void startNextActivity(@NonNull Context context) {
        Intent intent = new Intent(context, NextActivity.class).addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void startMainActivity(@NonNull Context context) {
        Intent intent = new Intent(context, MainActivity.class).addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void startMapActivity(@NonNull Context context) {
        Intent intent = new Intent(context, MapActivity.class);
        context.startActivity(intent);
    }

    public static void startMapWithFlagActivity(@NonNull Context context) {
        Intent intent = new Intent(context, MapActivity.class).addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}