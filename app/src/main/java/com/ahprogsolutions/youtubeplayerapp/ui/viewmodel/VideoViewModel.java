package com.ahprogsolutions.youtubeplayerapp.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.ahprogsolutions.youtubeplayerapp.data.model.VideoInfo;
import com.ahprogsolutions.youtubeplayerapp.data.model.VideoList;
import java.util.List;

public class VideoViewModel extends ViewModel {

    public LiveData<List<VideoInfo>> getAllVideos() {
        System.out.println("viewModel: called");
        return VideoList.getVideos();
    }

}
