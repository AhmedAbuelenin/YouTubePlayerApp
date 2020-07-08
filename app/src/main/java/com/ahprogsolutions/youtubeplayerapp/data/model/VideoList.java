package com.ahprogsolutions.youtubeplayerapp.data.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class VideoList {

    public static LiveData<List<VideoInfo>> getVideos() {
        List<VideoInfo> list = new ArrayList<>();
        MutableLiveData<List<VideoInfo>> mutableLiveData = new MutableLiveData<>();
        list.add(new VideoInfo("xEA9vw8t4Ho", "Coding with Mitch",
                "img_codingwithmitch"));
        list.add(new VideoInfo("wKFJsrdiGS8", "Coding in Flow",
                "img_codinginflow"));
        list.add(new VideoInfo("WX1rSW74lzo", "HaiTHam Motovlog",
                "img_haitammotovlog"));
        list.add(new VideoInfo("2vn9PlLemwg", "Joma Tech",
                "img_jomatech"));
        list.add(new VideoInfo("4m7msadL5iA", "Microsoft for Startups",
                "img_microsoftforups"));
        mutableLiveData.setValue(list);
        System.out.println("VideoList size: " + list.size());
        return mutableLiveData;
    }

}
