package com.ahprogsolutions.youtubeplayerapp.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ahprogsolutions.youtubeplayerapp.R;
import com.ahprogsolutions.youtubeplayerapp.ui.fragment.HomeFragment;
import com.ahprogsolutions.youtubeplayerapp.ui.fragment.VideoFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class HomeActivity extends AppCompatActivity
        implements OnVideoPlayListener {

    @BindView(R.id.close_button)
    ImageButton closeButton;
    @BindView(R.id.video_box)
    LinearLayout videoBox;
    private VideoFragment videoFragment;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
        videoFragment =
                (VideoFragment) getFragmentManager().findFragmentById(R.id.video_fragment);
        videoBox.setVisibility(View.INVISIBLE);
//        System.out.println("HomeActivity: OnCreated is ON");

        layout();
    }

    private void layout() {
        boolean isPortrait =
                getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        closeButton.setVisibility(isPortrait ? View.VISIBLE : View.GONE);

        if (isPortrait) {
            System.out.println("Layout: Portrait is working");
            setLayoutSize(Objects.requireNonNull(homeFragment.getView()), MATCH_PARENT, MATCH_PARENT);
            setLayoutSize(Objects.requireNonNull(videoFragment.getView()), MATCH_PARENT, WRAP_CONTENT);
            setLayoutSizeAndGravity(videoBox, MATCH_PARENT, WRAP_CONTENT, Gravity.BOTTOM);
        }
    }

       private static void setLayoutSize(View view, int width, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }

    private static void setLayoutSizeAndGravity(View view, int width, int height, int gravity) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }

    @Override
    public void onPlay(String videoId) {
        Toast.makeText(this, "OnPlay", Toast.LENGTH_SHORT).show();
        if (videoBox.getVisibility() == View.INVISIBLE) {
            System.out.println("home activity: videoBox is visible now");
            videoBox.setVisibility(View.VISIBLE);
        }
        videoFragment.setVideoId(videoId);
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        if (fragment instanceof HomeFragment) {
            HomeFragment homeFragment = (HomeFragment) fragment;
            homeFragment.setOnVideoPlayListener(this);
        }
    }

    public void onClickClose(View view) {
        homeFragment.getListView().clearChoices();
        homeFragment.getListView().requestLayout();
        videoFragment.pause();
        videoBox.setVisibility(View.INVISIBLE);
    }

    //    @TargetApi(16)
    private void runOnAnimationEnd(ViewPropertyAnimator animator, final Runnable runnable) {
//        if (Build.VERSION.SDK_INT >= 16) {
//            animator.withEndAction(runnable);
//        }
        if (Build.VERSION.SDK_INT >= 21) {
            animator.setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    runnable.run();
                }
            });
        }
    }


}
