package nl.hanze.hanzeboard.activities.overview.profile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import nl.hanze.hanzeboard.GlideApp;
import nl.hanze.hanzeboard.R;
import nl.hanze.hanzeboard.activities.overview.OverviewActivity;
import nl.hanze.hanzeboard.activities.overview.OverviewViewModel;
import nl.hanze.hanzeboard.activities.overview.absent.AbsentViewModel;
import nl.hanze.hanzeboard.activities.overview.announcements.AnnouncementsFragment;
import nl.hanze.hanzeboard.activities.overview.courses.CoursesAdapter;
import nl.hanze.hanzeboard.api.API;
import nl.hanze.hanzeboard.api.responses.UserResponse;
import nl.hanze.hanzeboard.api.responses.course.CourseResponse;

public class ProfileFragment extends Fragment {

    private LinearLayout profileView;
    private ImageView profileImage;

    private int PROFILE_IMAGE_RESULT = 22;
    private ProfileViewModel mViewModel;
    public String currentPhotoPath;

    protected OverviewViewModel overviewViewModel;

    /**
     * Lifecycle method onCreateView. Sets the parameters and actions when the view is going to
     * be created.
     *
     * @param inflater used to 'inflate' the XML layout.
     * @param container the viewGroup which this fragment belongs to.
     * @param savedInstanceState used to pass data between activites.
     * @return a parsed/ 'inflated' view.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    /**
     * Lifecycle method onViewCreated. Used to retrieve the view from the XML layout after the view
     * has been created.
     *
     * @param view the corresponding view of this class.
     * @param savedInstanceState a bundle used to pass data between activities.
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileImage = view.findViewById(R.id.profileImage);
        profileView = view.findViewById(R.id.profileView);
    }

    /**
     * Lifecycle method onActivityCreated. Used to bind the corresponding adapter to the retrieved
     * view after the activity has been created.
     *
     * @param savedInstanceState a bundle used to pass data between activities.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        mViewModel.init(getContext());

        overviewViewModel = OverviewActivity.mViewModel;


        String avatarUrl = "https://greendestinations.org/wp-content/uploads/2019/05/avatar-exemple.jpg";

        if(overviewViewModel.getUser().getValue() != null) {
            avatarUrl = API.STORAGE_URL + overviewViewModel.getUser().getValue().getAvatarUrl();
        }

        GlideApp.with(profileView)
                .load(avatarUrl)
                .circleCrop()
                .into(profileImage);

        profileImage.setOnClickListener(this::newUserImage);
    }

    /**
     * @param view
     */
    private void newUserImage(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "nl.hanze.hanzeboard.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, PROFILE_IMAGE_RESULT);
            }
        }
    }

    /**
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PROFILE_IMAGE_RESULT && resultCode == -1) {
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File avatar = new File(currentPhotoPath);
            Uri selectedImage = Uri.fromFile(avatar);
            mediaScanIntent.setData(selectedImage);

            mViewModel.setProfileUrl(selectedImage, avatar, this, overviewViewModel);
        }
    }

    private File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();

        return image;
    }

//    private void galleryAddPic() {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        File f = new File(currentPhotoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        this.getActivity().sendBroadcast(mediaScanIntent);
//    }

    public void setAvatar(String avatarUrl) {
        Glide.with(profileView)
                .load(avatarUrl)
                .circleCrop()
                .into(profileImage);
    }
}
