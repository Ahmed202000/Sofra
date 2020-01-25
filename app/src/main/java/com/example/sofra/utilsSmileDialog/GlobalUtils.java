package com.example.sofra.utilsSmileDialog;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.sofra.R;
import com.hsalf.smilerating.SmileRating;

import butterknife.BindView;

import static android.content.ContentValues.TAG;

public class GlobalUtils {
    public static String rating = "Not given yet";
    @BindView(R.id.smile_rating)
    SmileRating smileRating;
    @BindView(R.id.dialog_btn_add_review)
    Button dialogBtnAddReview;

    public static void showDiallog(Context context, final DialogCallback dialogCallback) {
        //create the dialog
        final CustomDialog dialog = new CustomDialog(context, R.style.customDialogTheme);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dialog_rating, null);

        dialog.setContentView(v);

        Button btn_done = (Button) dialog.findViewById(R.id.dialog_btn_add_review);
        SmileRating smileRating = (SmileRating) dialog.findViewById(R.id.smile_rating);

        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                switch (smiley) {
                    case SmileRating.BAD:
                        Log.i(TAG, "Bad");
                        rating = "bad";
                        break;
                    case SmileRating.GOOD:
                        Log.i(TAG, "Good");
                        rating = "Good";
                        break;
                    case SmileRating.GREAT:
                        Log.i(TAG, "Great");
                        rating = "Great";
                        break;
                    case SmileRating.OKAY:
                        Log.i(TAG, "Okay");
                        rating = "Okay";
                        break;
                    case SmileRating.TERRIBLE:
                        Log.i(TAG, "Terrible");
                        rating = "Terrible";
                        break;
                }
            }
        });
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogCallback != null) {
                    dialogCallback.callback(rating);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}

