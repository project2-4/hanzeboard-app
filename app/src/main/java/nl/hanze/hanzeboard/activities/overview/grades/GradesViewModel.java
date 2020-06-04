package nl.hanze.hanzeboard.activities.overview.grades;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import nl.hanze.hanzeboard.api.API;
import nl.hanze.hanzeboard.api.clients.GradeClient;
import nl.hanze.hanzeboard.api.clients.StaffClient;
import nl.hanze.hanzeboard.api.responses.grade.GradeMessageResponse;
import nl.hanze.hanzeboard.api.responses.staff.StaffMessageResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GradesViewModel extends ViewModel {

    private GradeClient gradeClient;
    private MutableLiveData<GradeMessageResponse> mGrades = new MutableLiveData<>();

    public void init(Context context) {
        gradeClient = API.createService(context, GradeClient.class);

        Call<GradeMessageResponse> courseCall = gradeClient.getGrades();
        courseCall.enqueue(new Callback<GradeMessageResponse>() {
            @Override
            public void onResponse(Call<GradeMessageResponse> call, Response<GradeMessageResponse> response) {
                if (response.isSuccessful()) {
                    mGrades.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GradeMessageResponse> call, Throwable t) {
                Log.v("ERROR: ", t.getMessage());
            }
        });
    }

    public MutableLiveData<GradeMessageResponse> getGrades() {
        return mGrades;
    }
}
