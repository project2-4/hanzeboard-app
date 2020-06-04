package nl.hanze.hanzeboard.api.clients;

import nl.hanze.hanzeboard.api.responses.course.CourseMessageResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CourseClient {

    @GET("courses")
    Call<CourseMessageResponse> getCourseMessage();
}
