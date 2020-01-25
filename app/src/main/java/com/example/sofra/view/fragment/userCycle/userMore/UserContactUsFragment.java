package com.example.sofra.view.fragment.userCycle.userMore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sofra.R;
import com.example.sofra.view.fragment.untitledFolder.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserContactUsFragment extends BaseFragment {

    @BindView(R.id.user_contact_us_cb_complaint)
    RadioButton userContactUsCbComplaint;
    @BindView(R.id.user_contact_us_cb_suggestion)
    RadioButton userContactUsCbSuggestion;
    @BindView(R.id.user_contact_us_cb_enquiry)
    RadioButton userContactUsCbEnquiry;
    @BindView(R.id.user_contact_us_radio_group)
    RadioGroup userContactUsRadioGroup;

    public UserContactUsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_user_contact_us, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

//    public void onRadioButtonClicked(View view) {
//        // Is the button now checked?
//        boolean checked = ((RadioButton) view).isChecked();
//        // Check which radio button was clicked
//        switch (view.getId()) {
//            case R.id.user_contact_us_cb_complaint:
//                if (checked)
//                    userContactUsCbSuggestion.setChecked(false);
//                    userContactUsCbEnquiry.setChecked(false);
//                    break;
//            case R.id.user_contact_us_cb_enquiry:
//                if (checked)
//                    userContactUsCbComplaint.setChecked(false);
//                    userContactUsCbSuggestion.setChecked(false);
//                break;
//            case R.id.user_contact_us_cb_suggestion:
//                if (checked)
//                    userContactUsCbComplaint.setChecked(false);
//                    userContactUsCbEnquiry.setChecked(false);
//                break;
//        }
//    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
