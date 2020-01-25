package com.example.sofra.helper;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import com.example.sofra.R;

import butterknife.ButterKnife;

public class CustomDialog extends Dialog {

    private Activity activity;
    private Context context;
    private boolean Cancelable;

    public CustomDialog(Context context, Activity activity, boolean Cancelable) {
        super(context);
        this.activity = activity;
        this.context = context;
        this.Cancelable = Cancelable;
        onCreate();
    }

    public void onCreate() {
        try {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialog_rating);
            ButterKnife.bind(this);

            CustomDialog.this.setCancelable(Cancelable);

            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

            show();
        } catch (Exception e) {

        }

    }


}