package com.virtualstudios.virtualmeeting.listeners;

import com.virtualstudios.virtualmeeting.models.User;

public interface UserListener {

    void initiateVideoMeeting(User user);

    void initiateAudioMeeting(User user);

    void onMultipleUsersAction(boolean isMultipleUsersSelected);
}
