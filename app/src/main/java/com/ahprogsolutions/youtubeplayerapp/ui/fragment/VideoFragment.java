package com.ahprogsolutions.youtubeplayerapp.ui.fragment;

import android.os.Bundle;

import com.ahprogsolutions.youtubeplayerapp.data.api.YouTubeApi;
import com.ahprogsolutions.youtubeplayerapp.ui.activity.OnVideoPlayListener;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class VideoFragment extends YouTubePlayerFragment
        implements YouTubePlayer.OnInitializedListener,
        YouTubePlayer.OnFullscreenListener {

    private YouTubePlayer player;
    private String videoId;
    private OnPlayerFullScreenListener onPlayerFullScreenListener;

    public VideoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialize(YouTubeApi.getApiKey(), this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean restored) {
        this.player = player;
        System.out.println("video Fragment: Initializated Successful");
        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        if (!restored && videoId != null) {
            player.cueVideo(videoId);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        System.out.println("video Fragment: Failure Initializing");

    }

    @Override
    public void onFullscreen(boolean status) {
        System.out.println("onFullScreenListener: " + status);
        onPlayerFullScreenListener.onPlayerFullScreen(status);
    }

    public void setVideoId(String videoId) {
        if (videoId != null && !videoId.equals(this.videoId)) {
            this.videoId = videoId;
            if (player != null) {
                System.out.println("Video is ready to show");
                player.cueVideo(videoId);
            }
        }
    }

    public void pause() {
        if (player != null) {
            player.pause();
        }
    }

    @Override
    public void onDestroy() {
        if (player != null) {
            player.release();
        }
        super.onDestroy();
    }

    public void setOnPlayerFullScreenListener(OnPlayerFullScreenListener onPlayerFullScreenListener) {
        this.onPlayerFullScreenListener = onPlayerFullScreenListener;
    }

    public interface OnPlayerFullScreenListener {
        void onPlayerFullScreen(boolean status);
    }


}
