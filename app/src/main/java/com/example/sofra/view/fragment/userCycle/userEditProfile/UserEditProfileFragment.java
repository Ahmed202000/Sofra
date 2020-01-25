package com.example.sofra.view.fragment.userCycle.userEditProfile;

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
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.sofra.R;
import com.example.sofra.adapter.SpinnerAdapter;
import com.example.sofra.data.GeneralRequest;
import com.example.sofra.data.model.listOfCity.ListOfCityData;
import com.example.sofra.view.activity.UserCycleActivity;
import com.example.sofra.view.fragment.untitledFolder.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.sofra.data.api.ApiClient.getClient;


public class UserEditProfileFragment extends BaseFragment {

    @BindView(R.id.user_edit_profile_fragment_add_photo)
    CircleImageView editProfileFragmentAddPhoto;
    @BindView(R.id.user_edit_profile_fragment_sp_city)
    Spinner userEditProfileFragmentSpCity;
    @BindView(R.id.user_edit_profile_fragment_sp_town)
    Spinner userEditProfileFragmentSpTown;

    private static final int PERMISSION_REQUEST = 0;
    private static final int RESULT_LOAD_IMAGE = 1;
    UserCycleActivity userCycleActivity = new UserCycleActivity();
    Uri imageUri;

    private SpinnerAdapter cityAdapter, townAdapter;
    private ListOfCityData listOfCityData;


    public UserEditProfileFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_user_edit_profile, container, false);
        ButterKnife.bind(this, view);
        userCycleActivity = (UserCycleActivity) getActivity();

        cityAdapter = new SpinnerAdapter(getActivity());
        townAdapter = new SpinnerAdapter(getActivity());

        GeneralRequest.getSpinnerData(getClient().getCity(), cityAdapter
                , userEditProfileFragmentSpCity, "City");
//        GeneralRequest.getSpinnerDataTown(getClient().getTown(townAdapter.selectedId), townAdapter
//                , userEditProfileFragmentSpTown, "Town");

        initGallery();
        return view;
    }

    private void initGallery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
        }

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
//         Result code is RESULT_OK only if the user selects an Image
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
                    editProfileFragmentAddPhoto.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                    break;

            }
    }

    @OnClick({R.id.user_edit_profile_fragment_add_photo, R.id.user_edit_profile_fragment_et_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_edit_profile_fragment_add_photo:
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(gallery, "Select Image"), RESULT_LOAD_IMAGE);
                break;
            case R.id.user_edit_profile_fragment_et_name:
                break;
        }
    }

    @Override
    public void onBack() {
        super.onBack();
        userCycleActivity.setSelection(R.id.nav_home);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
