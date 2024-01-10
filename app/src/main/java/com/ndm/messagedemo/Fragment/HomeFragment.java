package com.ndm.messagedemo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ndm.messagedemo.Adapter.UserAdapter;
import com.ndm.messagedemo.Adapter.UserAdapter2;
import com.ndm.messagedemo.Item.UserItem;
import com.ndm.messagedemo.R;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rcv, rcv2;

    private ImageView img;
    private EditText edt_sreach;
    private List<UserItem> mUserItem;

    private UserAdapter userAdapter;
    private UserAdapter2 userAdapter2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rcv = view.findViewById(R.id.rcvUser1);
        rcv2 = view.findViewById(R.id.rcvUser2);
        img = view.findViewById(R.id.menu1);
        edt_sreach = view.findViewById(R.id.timkiem);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(linearLayoutManager);
        rcv2.setLayoutManager(linearLayoutManager2);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();
        initView();

        userAdapter2.setOnItemClickListener(new UserAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(UserItem item) {
                String username = item.getName(); // Giả sử bạn có một phương thức để lấy tên người dùng từ UserItem
                int img = item.getResouceId();
                if (username != null && !username.isEmpty()) {
                    ChatFragment1 chatFragment1 = new ChatFragment1();


                    Bundle bundle = new Bundle();
                    bundle.putString(ChatFragment1.USERNAME, username);
                    bundle.putString(String.valueOf(ChatFragment1.Img), String.valueOf(img));
                    chatFragment1.setArguments(bundle);

                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.containerProductDetail, chatFragment1);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    // Ẩn các thành phần trong HomeFragment
                    hideComponents();


                }
            }
        });
    }

    private void initData() {

        mUserItem = new ArrayList<>();
        mUserItem.add(new UserItem("manh 1", R.drawable.anh));
        mUserItem.add(new UserItem("manh 2", R.drawable.abcd));
        mUserItem.add(new UserItem("manh 3", R.drawable.anh));
        mUserItem.add(new UserItem("manh 4", R.drawable.abcd));
        mUserItem.add(new UserItem("manh 5", R.drawable.anh));
        mUserItem.add(new UserItem("manh 6", R.drawable.abcd));
    }

    private void initView() {
        userAdapter = new UserAdapter();
        userAdapter.Setdata(mUserItem);

        userAdapter2 = new UserAdapter2();
        userAdapter2.Setdata(mUserItem);

        rcv.setAdapter(userAdapter);
        rcv2.setAdapter(userAdapter2);


    }

    private void hideComponents() {
        BottomNavigationView bottomNav = requireActivity().findViewById(R.id.bottom_nav);
        bottomNav.setVisibility(View.GONE);

        rcv.setVisibility(View.GONE);
        rcv2.setVisibility(View.GONE);
        img.setVisibility(View.GONE);
        edt_sreach.setVisibility(View.GONE);
    }


    public void showHiddenComponents() {
        BottomNavigationView bottomNav = getActivity().findViewById(R.id.bottom_nav);
        bottomNav.setVisibility(View.VISIBLE);

        // Hiển thị các view đã bị ẩn
        rcv.setVisibility(View.VISIBLE);
        rcv2.setVisibility(View.VISIBLE);
        img.setVisibility(View.VISIBLE);
        edt_sreach.setVisibility(View.VISIBLE);
    }

}