package com.ahprogsolutions.youtubeplayerapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ahprogsolutions.youtubeplayerapp.R;
import com.ahprogsolutions.youtubeplayerapp.api.YouTubeApi;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class HomeActivity extends AppCompatActivity
        implements YouTubePlayer.OnInitializedListener, View.OnClickListener {

    private YouTubePlayerFragment mYouTubePlayerFragment;
    private Button playBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        System.out.println("on create: ok");
        playBtn = findViewById(R.id.play_btn);
        playBtn.setOnClickListener(this);
        mYouTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager()
                .findFragmentById(R.id.youtube_fragment);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer youTubePlayer, boolean b) {
        System.out.println("on success: ok");
        youTubePlayer.loadPlaylist("PLgCYzUzKIBE8M2SMSIEZ6jJepG6qD0ugX");

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {
        System.out.println("on failure: something wrong");

    }

    @Override
    public void onClick(View v) {
        mYouTubePlayerFragment.initialize(YouTubeApi.getApiKey(), this);
    }
}