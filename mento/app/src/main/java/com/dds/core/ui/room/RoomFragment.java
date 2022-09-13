package com.dds.core.ui.room;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.dds.core.voip.CallMultiActivity;
import com.dds.webrtc.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class RoomFragment extends Fragment {

    private RoomViewModel roomViewModel;
    private RecyclerView list;
    private List<RoomInfo> datas;
    private RoomAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private TextView no_data;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        roomViewModel = new ViewModelProvider(requireActivity()).get(RoomViewModel.class);
        View root = inflater.inflate(R.layout.fragment_room, container, false);
        initView(root);
        initData();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initView(View root) {
        setHasOptionsMenu(true);
        list = root.findViewById(R.id.list);
        refreshLayout = root.findViewById(R.id.swipe);
        no_data = root.findViewById(R.id.no_data);
    }

    private void initData() {
        adapter = new RoomAdapter();
        datas = new ArrayList<>();
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getContext()));

        roomViewModel.getRoomList().observe(getViewLifecycleOwner(), roomInfos -> {
            if (getActivity() == null) return;
            if (getActivity().isFinishing()) {
                return;
            }
            if (roomInfos.size() > 0) {
                no_data.setVisibility(View.GONE);
            } else {
                no_data.setVisibility(View.VISIBLE);
            }
            datas.clear();
            datas.addAll(roomInfos);
            adapter.notifyDataSetChanged();
            refreshLayout.setRefreshing(false);
        });

        refreshLayout.setOnRefreshListener(() -> roomViewModel.loadRooms());
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_room, menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_create) {
            createRoom();
            return true;
        }
        return super.onOptionsItemSelected(item);



    }


    // 방 만들기
    private void createRoom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("자동으로 룸을 만들고 룸에 들어갑니다.");
        builder.setPositiveButton("확인", (dialog, which) -> {
            String room = UUID.randomUUID().toString().substring(0, 16);
            // 방을 만들고 들어갑니다.
            CallMultiActivity.openActivity(getActivity(),
                    "room-" + room, true);
                                //랜덤 방 코드 생성
            Toast.makeText(getContext(), room, Toast.LENGTH_LONG).show();

        }).setNegativeButton("취소", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    private class RoomAdapter extends RecyclerView.Adapter<RoomFragment.Holder> {

        @NonNull
        @Override
        public RoomFragment.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_rooms, parent, false);
            return new RoomFragment.Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RoomFragment.Holder holder, int position) {
            RoomInfo roomInfo = datas.get(position);
            holder.text.setText(roomInfo.getRoomId());
            holder.item_join_room.setOnClickListener(v -> {
                CallMultiActivity.openActivity(getActivity(), roomInfo.getRoomId(), false);
            });
        }


        @Override
        public int getItemCount() {
            return datas.size();
        }


    }

    private static class Holder extends RecyclerView.ViewHolder {

        private final TextView text;
        private final Button item_join_room;

        Holder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.item_user_name);
            item_join_room = itemView.findViewById(R.id.item_join_room);
        }
    }


}