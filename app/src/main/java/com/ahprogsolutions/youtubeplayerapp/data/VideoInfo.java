package com.ahprogsolutions.youtubeplayerapp.data;

import android.content.Context;

public class VideoInfo {

    private String videoId;
    private String videoAuthor;
    private String videoIconRes;

    public VideoInfo() {
    }

    public VideoInfo(String videoId, String videoAuthor, String videoIconRes) {
        this.videoId = videoId;
        this.videoAuthor = videoAuthor;
        this.videoIconRes = videoIconRes;
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

    public String getVideoIconRes() {
        return videoIconRes;
    }

    public void setVideoIconRes(String videoIconRes) {
        this.videoIconRes = videoIconRes;
    }

    public int getVideoIconResource(Context context, String videoIconRes) {
        return context.getResources().getIdentifier(videoIconRes, "drawable",
                context.getPackageName());
    }
}
