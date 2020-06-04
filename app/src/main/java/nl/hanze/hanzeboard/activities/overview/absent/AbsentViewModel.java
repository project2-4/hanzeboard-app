package nl.hanze.hanzeboard.activities.overview.absent;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import nl.hanze.hanzeboard.api.API;
import nl.hanze.hanzeboard.api.clients.StaffClient;
import nl.hanze.hanzeboard.api.responses.staff.StaffMessageResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbsentViewModel extends ViewModel {

    private StaffClient staffClient;
    private MutableLiveData<StaffMessageResponse> mStaffData = new MutableLiveData<>();

    public void init(Context context) {
        staffClient = API.createService(context, StaffClient.class);

        Call<StaffMessageResponse> courseCall = staffClient.getStaffMessage();
        courseCall.enqueue(new Callback<StaffMessageResponse>() {
            @Override
            public void onResponse(Call<StaffMessageResponse> call, Response<StaffMessageResponse> response) {
                if (response.isSuccessful()) {
                    mStaffData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<StaffMessageResponse> call, Throwable t) {
                Log.v("ERROR: ", t.getMessage());
            }
        });
    }

    /**
     * Getter for the mStaffData property.
     *
     * @return the corresponding JsonMessageResponse, mStaffData.
     */
    public LiveData<StaffMessageResponse> getStaffData() {
        return mStaffData;
    }
}
