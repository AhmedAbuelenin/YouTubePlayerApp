package com.ahprogsolutions.youtubeplayerapp.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ahprogsolutions.youtubeplayerapp.R;
import com.ahprogsolutions.youtubeplayerapp.ui.fragment.HomeFragment;
import com.ahprogsolutions.youtubeplayerapp.ui.fragment.VideoFragment;

public class HomeActivity extends AppCompatActivity
        implements OnVideoPlayListener {

    private VideoFragment videoFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.activity_container);
        if (fragment == null) {
            fragment = new HomeFragment(this);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onPlay(String videoId) {
        Toast.makeText(this, "Hello OnPlay", Toast.LENGTH_SHORT).show();
        if (videoFragment == null) {
            videoFragment = (VideoFragment) getFragmentManager().
                    findFragmentById(R.id.video_fragment_container);
            videoFragment.setVideoId(videoId);
            System.out.println("home activity: video fragment instatiated" + videoId);
            return;
        }
        System.out.println("home activity: video fragment already instatiated" + videoId);
        videoFragment.setVideoId(videoId);
    }

}
