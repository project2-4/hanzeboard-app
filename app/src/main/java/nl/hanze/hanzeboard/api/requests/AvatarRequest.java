package nl.hanze.hanzeboard.api.requests;

import java.io.File;

public class AvatarRequest {
    public File avatar;

    public AvatarRequest(File avatar) {
        this.avatar = avatar;
    }
}
