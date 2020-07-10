package com.ahprogsolutions.youtubeplayerapp.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
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
import com.google.android.youtube.player.YouTubePlayer;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class HomeActivity extends AppCompatActivity
        implements OnVideoPlayListener, VideoFragment.OnPlayerFullScreenListener {

    private static final int LANDSCAPE_VIDEO_PADDING_DP = 5;
    private static final long ANIMATION_DURATION_MILLIS = 300;
    @BindView(R.id.close_button)
    ImageButton closeButton;
    @BindView(R.id.video_box)
    LinearLayout videoBox;
    private VideoFragment videoFragment;
    private HomeFragment homeFragment;
    private boolean isFullscreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
        videoFragment =
                (VideoFragment) getFragmentManager().findFragmentById(R.id.video_fragment);
        videoFragment.setOnPlayerFullScreenListener(this);
        videoBox.setVisibility(View.INVISIBLE);
        System.out.println("HomeActivity: OnCreated is ON");

        layout();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

//        layout();
    }

    @Override
    public void onPlayerFullScreen(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        System.out.println("onPlayerFullScreenCallback: " + this.isFullscreen);

//        layout();
    }

    private void layout() {
        boolean isPortrait =
                getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        Objects.requireNonNull(homeFragment.getView())
                .setVisibility(isFullscreen ? View.GONE : View.VISIBLE);
        closeButton.setVisibility(isPortrait ? View.VISIBLE : View.GONE);

        if (isFullscreen) {
            System.out.println("Layout: FullScreen is working");
            videoBox.setTranslationY(0); // Reset any translation that was applied in portrait.
            setLayoutSize(Objects.requireNonNull(videoFragment.getView()), MATCH_PARENT, MATCH_PARENT);
            setLayoutSizeAndGravity(videoBox, MATCH_PARENT, MATCH_PARENT, Gravity.CENTER);
        } else {
            System.out.println("Layout: Portrait is working");
            setLayoutSize(homeFragment.getView(), MATCH_PARENT, MATCH_PARENT);
            setLayoutSize(Objects.requireNonNull(videoFragment.getView()), MATCH_PARENT, WRAP_CONTENT);
            setLayoutSizeAndGravity(videoBox, MATCH_PARENT, WRAP_CONTENT, Gravity.BOTTOM);
        }
//        else {
////            System.out.println("Layout: else statement is implemented");
//////            videoBox.setTranslationY(0); // Reset any translation that was applied in portrait.
//////            int screenWidth = dpToPx(getResources().getConfiguration().screenWidthDp);
//////            setLayoutSize(homeFragment.getView(), screenWidth / 4, MATCH_PARENT);
//////            int videoWidth = screenWidth - screenWidth / 4 - dpToPx(LANDSCAPE_VIDEO_PADDING_DP);
////            setLayoutSize(Objects.requireNonNull(videoFragment.getView()), MATCH_PARENT, MATCH_PARENT);
//////            setLayoutSizeAndGravity(videoBox, videoWidth, WRAP_CONTENT,
//////                    Gravity.END | Gravity.CENTER_VERTICAL);
////        }
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density + 0.5f);
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
