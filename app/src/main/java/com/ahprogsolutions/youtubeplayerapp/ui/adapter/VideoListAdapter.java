package com.ahprogsolutions.youtubeplayerapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahprogsolutions.youtubeplayerapp.R;
import com.ahprogsolutions.youtubeplayerapp.data.model.VideoInfo;
import com.ahprogsolutions.youtubeplayerapp.data.api.YouTubeApi;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;
import java.util.Map;

public class VideoListAdapter extends BaseAdapter {
    private Context context;
    private final List<VideoInfo> videoInfoList;
    private Map<YouTubeThumbnailView, YouTubeThumbnailLoader> thumbnailViewToLoaderMap;
    private final LayoutInflater inflater;
    private final ThumbnailListener thumbnailListener;

    public VideoListAdapter(Context context, List<VideoInfo> videoInfoList) {
        this.context = context;
        this.videoInfoList = videoInfoList;
        inflater = LayoutInflater.from(context);
        thumbnailListener = new ThumbnailListener();
    }

    @Override
    public int getCount() {
        return videoInfoList.size();
    }

    @Override
    public VideoInfo getItem(int position) {
        return videoInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        VideoInfo info = videoInfoList.get(position);
        // There are three cases here
        if (view == null) {
            view = inflater.inflate(R.layout.item_video, parent, false);
            YouTubeThumbnailView thumbnail = (YouTubeThumbnailView) view.findViewById(R.id.thumbnailView);
            thumbnail.setTag(info.getVideoId());
            thumbnail.initialize(YouTubeApi.getApiKey(), thumbnailListener);
        } else {
            YouTubeThumbnailView thumbnail = (YouTubeThumbnailView) view.findViewById(R.id.thumbnailView);
            thumbnailViewToLoaderMap = thumbnailListener.getThumbnailViewToLoaderMap();
            YouTubeThumbnailLoader loader = thumbnailViewToLoaderMap.get(thumbnail);
            if (loader == null) {
                thumbnail.setTag(info.getVideoId());
            } else {
                thumbnail.setImageResource(R.drawable.loading_thumbnail);
                loader.setVideo(info.getVideoId());
            }
        }
        TextView channelName = (TextView) view.findViewById(R.id.channel_name);
        channelName.setText(info.getVideoAuthor());
        ImageView channelIcon = (ImageView) view.findViewById(R.id.channel_icon);
        channelIcon.setImageResource(info.getVideoIconResId(context, info.getVideoIconResString()));
        Button subscBtn = (Button) view.findViewById(R.id.subscribe_btn);
        subscBtn.setOnClickListener(v -> {
            Intent intent;
            String channelId = info.getChannelId();
            if (channelId.equals("microsoftventures")) {
                intent = YouTubeIntents.createUserIntent(context, channelId);
            } else{
                intent = YouTubeIntents.createChannelIntent(context, channelId);
            }
            System.out.println("channelId: " + channelId);
            context.startActivity(intent);
        });
        return view;
    }

    public void releaseLoaders() {
        for (YouTubeThumbnailLoader loader : thumbnailViewToLoaderMap.values()) {
            loader.release();
        }
    }

}
