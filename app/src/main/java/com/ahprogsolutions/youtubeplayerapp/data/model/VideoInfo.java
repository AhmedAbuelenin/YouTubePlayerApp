package com.ahprogsolutions.youtubeplayerapp.data.model;

import android.content.Context;

public class VideoInfo {

    private String videoId;
    private String videoAuthor;
    private String videoIconRes;
    private String channelId;

        public VideoInfo(String videoId, String videoAuthor, String videoIconRes, String channelId) {
        this.videoId = videoId;
        this.videoAuthor = videoAuthor;
        this.videoIconRes = videoIconRes;
        this.channelId = channelId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoAuthor() {
        return videoAuthor;
    }

    public void setVideoAuthor(String videoAuthor) {
        this.videoAuthor = videoAuthor;
    }

    public String getVideoIconResString() {
        return videoIconRes;
    }

    public void setVideoIconRes(String videoIconRes) {
        this.videoIconRes = videoIconRes;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public int getVideoIconResId(Context context, String videoIconRes) {
        return context.getResources().getIdentifier(videoIconRes, "drawable",
                context.getPackageName());
    }
}
