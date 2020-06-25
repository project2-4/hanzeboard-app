package nl.hanze.hanzeboard.activities.overview;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

import nl.hanze.hanzeboard.R;
import nl.hanze.hanzeboard.activities.LoginActivity;
import nl.hanze.hanzeboard.api.API;
import nl.hanze.hanzeboard.api.responses.UserResponse;
import nl.hanze.hanzeboard.api.responses.course.CourseResponse;

public class OverviewActivity extends AppCompatActivity {

    protected OverviewViewModel mViewModel;

    private AppBarConfiguration mAppBarConfiguration;
    private List<CourseResponse> courseList;
    private int courseId;

    /**
     * Lifecycle method onCreate, sets the contentView of this class and initiates the init method.
     *
     * @param savedInstanceState a bundle used to pass data between activities.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        courseList = new ArrayList<>();
        init();
    }

    /**
     * Method to bind the onSupportNavigateUp event.
     *
     * @return true or false whether this element should be navigated up or down.
     */
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**
     * The init method of this class. Here everything will be declared and initialized in order for
     * this class to function properly.
     */
    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        View menuView = navigationView.getHeaderView(0);

        TextView textViewUsername = menuView.findViewById(R.id.headerUsername);
        TextView textViewEmail = menuView.findViewById(R.id.headerEmail);
        ImageView imageViewAvatar = menuView.findViewById(R.id.headerAvatar);

        mViewModel = new ViewModelProvider(this).get(OverviewViewModel.class);
        mViewModel.init(this);

        mViewModel.getUser().observe(this, user -> {
            textViewEmail.setText(user.getEmail());
            textViewUsername.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));

            String avatarUrl = "https://greendestinations.org/wp-content/uploads/2019/05/avatar-exemple.jpg";

            if (user.getAvatarUrl() != null) {
                avatarUrl = API.STORAGE_URL + user.getAvatarUrl();
            }

            Glide.with(menuView)
                    .load(avatarUrl)
                    .circleCrop()
                    .into(imageViewAvatar);
        });

        mViewModel.getCourses().observe(this, courseMessage -> {
            courseList.addAll(courseMessage.getObjectList());
        });

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_grades, R.id.nav_courses, R.id.nav_absent)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public List<CourseResponse> getCourseList() {
        return courseList;
    }

    public void setCurrentCourse(int id){
        this.courseId = id;
    }

    public CourseResponse getCurrentCourse(){
        return this.courseList.get(courseId);
    }

    public OverviewViewModel getViewModel() {
        return mViewModel;
    }
}
