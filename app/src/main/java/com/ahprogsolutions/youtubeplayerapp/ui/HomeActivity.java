package com.ahprogsolutions.youtubeplayerapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ahprogsolutions.youtubeplayerapp.R;
import com.ahprogsolutions.youtubeplayerapp.api.YouTubeApi;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

public class HomeActivity extends AppCompatActivity
        implements YouTubeThumbnailView.OnInitializedListener, View.OnClickListener {

    private YouTubeThumbnailView mYouTubeThumbnailView;
    private Button subscBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        System.out.println("on create: ok");
        subscBtn = findViewById(R.id.subscribe_btn);
        subscBtn.setOnClickListener(this);

        mYouTubeThumbnailView = findViewById(R.id.thumbnailView);
        mYouTubeThumbnailView.initialize(YouTubeApi.getApiKey(), this);
    }

    @Override
    public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView,
                                        YouTubeThumbnailLoader youTubeThumbnailLoader) {
        System.out.println("on success: ok");
        youTubeThumbnailLoader.setVideo("AcUauzCn7RE");
        // youTubeThumbnailLoader should be released in onDestroyView()
    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView,
                                        YouTubeInitializationResult youTubeInitializationResult) {
        System.out.println("on failure: something is wrong");
    }

    @Override
    public void onClick(View v) {
        //for the subscribtion button
    }


}