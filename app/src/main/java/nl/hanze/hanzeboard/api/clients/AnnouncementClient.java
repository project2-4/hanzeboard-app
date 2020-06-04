package nl.hanze.hanzeboard.api.clients;

import nl.hanze.hanzeboard.api.responses.announcement.AnnouncementMessageResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AnnouncementClient {

    @GET("courses/{id}/announcements")
    Call<AnnouncementMessageResponse> getAnnouncements(@Path("id") String id);
}
