package com.dds.core.ui.setting;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dds.core.socket.SocketManager;
import com.dds.core.voip.CallMultiActivity;
import com.dds.core.ui.user.UserListFragment;
import com.dds.core.voip.CallSingleActivity;
import com.dds.core.voip.TmapSetting;
import com.dds.webrtc.R;

import java.util.UUID;


public class SettingFragment extends Fragment {

    private SettingViewModel notificationsViewModel;
    private Button button;
    private String userId;
    private String avatar;
    private String nickName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(requireActivity()).get(SettingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
//        final TextView textView = root.findViewById(R.id.text_notifications);
//        button = root.findViewById(R.id.exit);
        Button fastin = root.findViewById(R.id.intoroom);
        Button demo = root.findViewById(R.id.demo);


        VideoView videoView = root.findViewById(R.id.video);
        MediaController mediaController = new MediaController(this.getContext());
        mediaController.setMediaPlayer(videoView);
//        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+R.raw.blackbox));

        demo.setOnClickListener(view -> {
            videoView.start();

        });


        fastin.setOnClickListener(view -> {
            CallSingleActivity.openActivity(getContext(), "menti", true, getNickName(), false, false);
        });

//        tmap.setOnClickListener(view ->{
////            Intent gogo = new Intent(this, BlackBoxSetting.class);
//            Intent intent = new Intent(getActivity().getApplicationContext(), TmapSetting.class);
//            startActivity(intent);
//        });

//        button.setOnClickListener(view -> {
//            SocketManager.getInstance().unConnect();
//        });
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


    private void createRoom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("바로 룸을 만들고 룸에 들어갑니다.");
        builder.setPositiveButton("확인", (dialog, which) -> {
            String room = "testing";
            // 방을 만들고 들어갑니다.
            CallMultiActivity.openActivity(getActivity(),
                    "room-" + room, false);
            //랜덤 방 코드 생성
//            Toast.makeText(getContext(), room, Toast.LENGTH_LONG).show();

        }).setNegativeButton("취소", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public String getNickName() {
        if (TextUtils.isEmpty(nickName)) {
            return userId;
        }
        return nickName;
    }


//    private void joinRoom(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setMessage("바로 룸을 만들고 룸에 들어갑니다.");
//        builder.setPositiveButton("확인", (dialog, which) -> {
//            String room = "testing";
//            // 방을 만들고 들어갑니다.
//            CallMultiActivity.openActivity(getActivity(),
//                    "room-" + room, false);
//            //랜덤 방 코드 생성
////            Toast.makeText(getContext(), room, Toast.LENGTH_LONG).show();
//
//        }).setNegativeButton("취소", (dialog, which) -> dialog.dismiss());
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }

}