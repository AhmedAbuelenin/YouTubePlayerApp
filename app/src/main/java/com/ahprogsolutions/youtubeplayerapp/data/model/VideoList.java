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
                "img_codingwithmitch", "UCoNZZLhPuuRteu02rh7bzsw"));
        list.add(new VideoInfo("wKFJsrdiGS8", "Coding in Flow",
                "img_codinginflow", "UC_Fh8kvtkVPkeihBs42jGcA"));
        list.add(new VideoInfo("WX1rSW74lzo", "HaiTHam Motovlog",
                "img_haithammotovlog", "UCyV2Li3Thv7Xpfljr6fxFUg"));
        list.add(new VideoInfo("2vn9PlLemwg", "Joma Tech",
                "img_jomatech", "UCV0qA-eDDICsRR9rPcnG7tw"));
        list.add(new VideoInfo("4m7msadL5iA", "Microsoft for Startups",
                "img_microsoftforstartups", "microsoftventures"));
        mutableLiveData.setValue(list);
        System.out.println("VideoList size: " + list.get(0));
        return mutableLiveData;
    }

}
