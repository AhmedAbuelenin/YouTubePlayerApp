package com.ahprogsolutions.youtubeplayerapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ahprogsolutions.youtubeplayerapp.R;
import com.ahprogsolutions.youtubeplayerapp.ui.adapter.VideoListAdapter;
import com.ahprogsolutions.youtubeplayerapp.ui.viewmodel.VideoViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {

    @BindView(R.id.video_list_view)
    ListView videoListView;
    private Unbinder unbinder;
    private VideoViewModel videoViewModel;
    private VideoListAdapter videoListAdapter;

    public HomeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
        System.out.println("onCreate: started");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,
                container, false);
        unbinder = ButterKnife.bind(this, view);
        System.out.println("onCreateView: started");
        initVideoList();
        return view;
    }

    private void initVideoList() {
        videoViewModel.getAllVideos().observe(this, videoInfos -> {
            videoListAdapter = new VideoListAdapter(getActivity(), videoInfos);
            videoListView.setAdapter(videoListAdapter);
            System.out.println("initVideoList: started");
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        videoListAdapter.releaseLoaders();
        unbinder.unbind();
    }

}
