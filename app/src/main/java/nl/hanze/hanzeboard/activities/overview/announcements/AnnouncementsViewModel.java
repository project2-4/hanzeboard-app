package nl.hanze.hanzeboard.activities.overview.announcements;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import nl.hanze.hanzeboard.api.API;
import nl.hanze.hanzeboard.api.clients.AnnouncementClient;
import nl.hanze.hanzeboard.api.responses.announcement.AnnouncementMessageResponse;
import nl.hanze.hanzeboard.api.responses.course.CourseResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnouncementsViewModel extends ViewModel {

    private AnnouncementClient announcementClient;
    private MutableLiveData<AnnouncementMessageResponse> mAnnouncementsData = new MutableLiveData<>();

    public void init(Context context, CourseResponse course){
        announcementClient = API.createService(context, AnnouncementClient.class);

        String id = course.getId();
        Call<AnnouncementMessageResponse> courseCall = announcementClient.getAnnouncements(id);
        courseCall.enqueue(new Callback<AnnouncementMessageResponse>() {

            @Override
            public void onResponse(Call<AnnouncementMessageResponse> call, Response<AnnouncementMessageResponse> response) {
                Log.v("ERROR: ", String.valueOf(response.errorBody()));
                if (response.isSuccessful()) {
                    Log.v("BODDDYYY", String.valueOf(response.body().getObjectList()));
                    mAnnouncementsData.setValue(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                       Log.v("ERROR BODY:", jObjError.getJSONObject("error").getString("message"));
                    } catch (Exception e) {
                        Log.v("EXCEPTION BODY:", e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<AnnouncementMessageResponse> call, Throwable t) {
                Log.v("ERROR: ", t.getMessage());
                Log.v("ERRORRRRRRR",t.getLocalizedMessage());
                t.printStackTrace();
                Log.v("ERROR1: ", String.valueOf(t.getStackTrace()[0]));
                Log.v("ERROR2: ", String.valueOf(mAnnouncementsData.getValue()));
            }
        });
    }

    public LiveData<AnnouncementMessageResponse> getAnnouncementsData() {
        return mAnnouncementsData;
    }
}
