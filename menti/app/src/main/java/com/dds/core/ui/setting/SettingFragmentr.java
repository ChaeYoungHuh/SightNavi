package com.dds.core.ui.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dds.webrtc.R;

public class SettingFragmentr extends Fragment {
    private Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settingr, container, false);
//        final TextView textView = root.findViewById(R.id.text_notifications);
//        button = root.findViewById(R.id.setting);

        return root;


    }
}

