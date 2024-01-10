package com.ndm.messagedemo.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ndm.messagedemo.Adapter.ChatAdapter;
import com.ndm.messagedemo.Item.Chat;
import com.ndm.messagedemo.MainActivity;
import com.ndm.messagedemo.R;
import com.ndm.messagedemo.Socket.SocketHandler;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatFragment1 extends Fragment {

    private ChatAdapter chatAdapter;
    private RecyclerView rvChat;

    private final List<Chat> chatList = new ArrayList<>();
    private String userName = "";

    public static final String USERNAME = "username";
    public static final int Img = 1;

    private CircleImageView img;



    int a;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_layout, container, false);

        img = view.findViewById(R.id.user_image);

        Bundle arguments = getArguments();
        if (arguments != null) {
            userName = arguments.getString(USERNAME);
            a = Integer.parseInt(arguments.getString(String.valueOf(Img)));
            img.setImageResource(a);
        }
        if (userName == null || userName.isEmpty()) {
            requireActivity().finish();
            return view;
        }

        chatAdapter = new ChatAdapter();
        rvChat = view.findViewById(R.id.rvChat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        rvChat.setLayoutManager(layoutManager);
        rvChat.setAdapter(chatAdapter);


        SocketHandler socketHandler = new SocketHandler();
        if (socketHandler == null)
        {

        }


       view.findViewById(R.id.btnSend).setOnClickListener(v -> {
            String message = ((EditText) view.findViewById(R.id.etMsg)).getText().toString().trim();
            if (!message.isEmpty()) {
                Chat chat = new Chat(userName, message);
                socketHandler.emitChat(chat);
                ((EditText) view.findViewById(R.id.etMsg)).setText("");
            }
        });

        socketHandler._onNewChat().observe(getViewLifecycleOwner(), chat -> {
            Chat updatedChat = new Chat(chat.getUsername(), chat.getText());
            updatedChat.setSelf(chat.getUsername().equals(userName));
            chatList.add(updatedChat);
            chatAdapter.submitChat(chatList);
            rvChat.scrollToPosition(chatList.size() - 1);
        });

        return view;
    }

}