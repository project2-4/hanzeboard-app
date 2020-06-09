package nl.hanze.hanzeboard.activities.overview.announcements;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import nl.hanze.hanzeboard.api.API;
import nl.hanze.hanzeboard.api.clients.AnnouncementClient;
import nl.hanze.hanzeboard.api.responses.announcement.AnnouncementMessageResponse;
import nl.hanze.hanzeboard.api.responses.course.CourseResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnouncementsViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private AnnouncementClient announcementClient;
    private MutableLiveData<AnnouncementMessageResponse> mAnnouncementsData = new MutableLiveData<>();

    public void init(Context context, CourseResponse course){
        announcementClient = API.createService(context, AnnouncementClient.class);

        String id = course.getId();
        Call<AnnouncementMessageResponse> courseCall = announcementClient.getAnnouncements(id);
        courseCall.enqueue(new Callback<AnnouncementMessageResponse>() {
            @Override
            public void onResponse(Call<AnnouncementMessageResponse> call, Response<AnnouncementMessageResponse> response) {
                if (response.isSuccessful()) {
                    mAnnouncementsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AnnouncementMessageResponse> call, Throwable t) {
                Log.v("ERROR: ", t.getMessage());
            }
        });
    }

    public LiveData<AnnouncementMessageResponse> getAnnouncementsData() {
        return mAnnouncementsData;
    }
}
