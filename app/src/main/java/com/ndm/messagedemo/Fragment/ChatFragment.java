package com.ndm.messagedemo.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ndm.messagedemo.Adapter.MessageAdapter;
import com.ndm.messagedemo.Item.Message;
import com.ndm.messagedemo.R;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    private RecyclerView rcvMessage;
    private MessageAdapter messageAdapter;

    private List<Message> messageList;

    private EditText edt_messgage;
    private Button btnSend;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        edt_messgage = view.findViewById(R.id.edt_message);
        btnSend = view.findViewById(R.id.btn_send);
        rcvMessage = view.findViewById(R.id.rcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvMessage.setLayoutManager(linearLayoutManager);

        messageList = new ArrayList<>();

        messageAdapter = new MessageAdapter();
        messageAdapter.setData(messageList);
        rcvMessage.setAdapter(messageAdapter);

        btnSend.setOnClickListener(v -> send());
        return view;
    }

    private void send() {
        String strmessege = edt_messgage.getText().toString().trim();
        if (TextUtils.isEmpty(strmessege)) {
            return;
        }
        messageList.add(new Message(strmessege));

        messageAdapter.notifyDataSetChanged();
        rcvMessage.scrollToPosition(messageList.size() - 1);

        edt_messgage.setText("");
    }
}