package nl.hanze.hanzeboard.api.clients;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AnnouncementClient {

    @GET("courses/{id}/announcements")
    Call<Object> getAnnouncements(@Path("id") String id);
}
