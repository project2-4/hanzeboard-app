package nl.hanze.hanzeboard.activities.overview;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import nl.hanze.hanzeboard.api.API;
import nl.hanze.hanzeboard.api.clients.CourseClient;
import nl.hanze.hanzeboard.api.clients.UserClient;
import nl.hanze.hanzeboard.api.responses.course.CourseResponse;
import nl.hanze.hanzeboard.api.responses.course.CourseMessageResponse;
import nl.hanze.hanzeboard.api.responses.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverviewViewModel extends ViewModel {

    private MutableLiveData<UserResponse> mUser = new MutableLiveData<>();
    private MutableLiveData<CourseMessageResponse> mUserCourses = new MutableLiveData<>();
    private UserClient userClient;
    private CourseClient courseClient;

    /**
     * The init method of this class. Here everything will be declared and initialized in order for
     * this class to function properly.
     */
    public void init(Context context) {
        userClient = API.createService(context, UserClient.class);
        courseClient = API.createService(context, CourseClient.class);
        mUserCourses = new MutableLiveData<>();

        Call<UserResponse> userCall = userClient.me();
        userCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    mUser.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.v("ERROR: ", t.getMessage());
            }
        });

        Call<CourseMessageResponse> courseCall = courseClient.getCourseMessage();
        courseCall.enqueue(new Callback<CourseMessageResponse>() {
            @Override
            public void onResponse(Call<CourseMessageResponse> call, Response<CourseMessageResponse> response) {
                if (response.isSuccessful()){
                    mUserCourses.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CourseMessageResponse> call, Throwable t) {
                Log.v("ERROR: ", t.getMessage());
            }
        });
    }

    /**
     * Getter for the mUser property.
     *
     * @return the corresponding UserClient, mUser.
     */
    public LiveData<UserResponse> getUser() {
        return mUser;
    }

    /**
     * Getter for the mUserCourses property.
     *
     * @return the corresponding JsonMessageResponse, mUserCourses.
     */
    public LiveData<CourseMessageResponse> getCourses() {
        return mUserCourses;
    }
}
