package com.dds.core.voip;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;

import com.dds.webrtc.R;

import java.util.ArrayList;

public class BlackBoxSetting extends Activity {
    ListView listView1;
    ArrayAdapter<String> adapter;
    ArrayList<String> listItem;
    VideoView videoView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blackboxsetting);

        videoView = findViewById(R.id.blackbox_play);
        MediaController mediaController = new MediaController(this);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);



//        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.blackbox_example));

        listItem = new ArrayList<String>();
        listItem.add("2022-09-02");
        listItem.add("2022-09-03");
        listItem.add("2022-09-04");
        listItem.add("2022-09-06");
        listItem.add("2022-09-07");

        listItem.add("2022-09-08");
        listItem.add("2022-09-09");

        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_single_choice,listItem);
        listView1 = findViewById(R.id.listView1);
        listView1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView1.setAdapter(adapter);


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // 콜백매개변수는 순서대로 어댑터뷰, 해당 아이템의 뷰, 클릭한 순번, 항목의 아이디
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String choice = listItem.get(i).toString();
                Toast.makeText(getApplicationContext(),choice,Toast.LENGTH_SHORT).show();
                if(choice=="2022-09-02"){
                    videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.blackbox1));

                }else if(choice=="2022-09-04"){
                    videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.blackbox2));
                }else if(choice == "2022-09-06"){
                    videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.blackbox3));
                }else if(choice == "2022-09-07"){
                    videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.blackbox4));
                }else if(choice == "2022-09-08" || choice == "2022-09-03"){
                    videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.blackbox5));
                }else if(choice == "2022-09-09"){
                    videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.blackbox_example));
                }
            }
        });
    }
}
