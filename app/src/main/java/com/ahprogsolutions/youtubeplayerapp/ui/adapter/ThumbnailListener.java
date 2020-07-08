package com.ahprogsolutions.youtubeplayerapp.ui.adapter;

import com.ahprogsolutions.youtubeplayerapp.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import java.util.HashMap;
import java.util.Map;

public class ThumbnailListener implements YouTubeThumbnailView.OnInitializedListener,
        YouTubeThumbnailLoader.OnThumbnailLoadedListener {

    private Map<YouTubeThumbnailView, YouTubeThumbnailLoader> thumbnailViewToLoaderMap;

    public ThumbnailListener() {
        this.thumbnailViewToLoaderMap = new HashMap<>();
    }

    @Override
    public void onInitializationSuccess(YouTubeThumbnailView view,
                                        YouTubeThumbnailLoader loader) {
        loader.setOnThumbnailLoadedListener(this);
        thumbnailViewToLoaderMap.put(view, loader);
        view.setImageResource(R.drawable.loading_thumbnail);
        String videoId = (String) view.getTag();
        loader.setVideo(videoId);
        System.out.println("onInitializationSuccess: started");
    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView,
                                        YouTubeInitializationResult youTubeInitializationResult) {
        for (YouTubeThumbnailLoader loader : thumbnailViewToLoaderMap.values()) {
            loader.release();
        }
        System.out.println("onInitializationFailure: something wrong");
    }

    @Override
    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {

    }

    @Override
    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView,
                                 YouTubeThumbnailLoader.ErrorReason errorReason) {

    }

    public HashMap<YouTubeThumbnailView, YouTubeThumbnailLoader> getThumbnailViewToLoaderMap() {
        return new HashMap<>(thumbnailViewToLoaderMap);
    }
}
