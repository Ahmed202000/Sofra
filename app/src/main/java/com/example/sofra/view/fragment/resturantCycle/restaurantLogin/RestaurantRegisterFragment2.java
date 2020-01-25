package com.example.sofra.view.fragment.resturantCycle.restaurantLogin;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.sofra.R;
import com.example.sofra.view.fragment.untitledFolder.BaseFragment;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RestaurantRegisterFragment2 extends BaseFragment {


    private static final int PERMISSION_REQUEST = 0;
    private static final int RESULT_LOAD_IMAGE = 1;
    @BindView(R.id.restaurant_register2_fragment_et_phone)
    TextInputEditText restaurantRegister2FragmentEtPhone;
    @BindView(R.id.restaurant_register2_fragment_et_phone_whatsapp)
    TextInputEditText restaurantRegister2FragmentEtPhoneWhatsapp;
    @BindView(R.id.restaurant_register2_fragment_img_add_photo)
    ImageView restaurantRegister2FragmentImgAddPhoto;
    @BindView(R.id.restaurant_register2_fragment_btn_register)
    Button restaurantRegister2FragmentBtnRegister;

    public RestaurantRegisterFragment2() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_restaurnat_register2, container, false);
        ButterKnife.bind(this, view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
        }


        return view;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(baseActivity, "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(baseActivity, "Permission not granted", Toast.LENGTH_SHORT).show();
                    baseActivity.finish();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case RESULT_LOAD_IMAGE:
                    //data.getData return the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    // Get the cursor
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();
                    //Get the column index of MediaStore.Images.Media.DATA
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    //Gets the String value in the column
                    String imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();
                    // Set the Image in ImageView after decoding the String
                    restaurantRegister2FragmentImgAddPhoto.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                    break;

            }
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.restaurant_register2_fragment_img_add_photo, R.id.restaurant_register2_fragment_btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.restaurant_register2_fragment_img_add_photo:
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(gallery, "Select Image"), RESULT_LOAD_IMAGE);
                break;
            case R.id.restaurant_register2_fragment_btn_register:

                break;
        }
    }
}
