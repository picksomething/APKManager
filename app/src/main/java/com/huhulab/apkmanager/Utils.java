package com.huhulab.apkmanager;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Utils {

    public static Dialog createLoadingDialog(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.layout_dialog, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.progress_layout);
        ImageView progress_image = (ImageView) v.findViewById(R.id.progress_image);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
        progress_image.startAnimation(animation);
        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);
        //loadingDialog.setCancelable(false);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        return loadingDialog;
    }
}
