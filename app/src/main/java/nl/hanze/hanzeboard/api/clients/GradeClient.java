package nl.hanze.hanzeboard.api.clients;

import nl.hanze.hanzeboard.api.responses.grade.GradeMessageResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GradeClient {

    @GET("grades")
    Call<GradeMessageResponse> getGrades();
}
