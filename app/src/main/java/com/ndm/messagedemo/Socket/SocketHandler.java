package com.ndm.messagedemo.Socket;


import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.gson.Gson;
import com.ndm.messagedemo.Item.Chat;

import io.socket.client.IO;
import io.socket.client.Socket;
import java.net.URISyntaxException;

public class SocketHandler {

    private Socket socket = null;

    private MutableLiveData<Chat> _onNewChat = new MutableLiveData<>();
    public LiveData<Chat> _onNewChat() {
        return _onNewChat;
    }

    public SocketHandler() {
        try {
            socket = IO.socket(SOCKET_URL);
            socket.connect();
            registerOnNewChat();

        } catch (URISyntaxException e) {
                Log.d("hi","abccc");
            e.printStackTrace();
        }
    }

    private void registerOnNewChat() {
        socket.on(CHAT_KEYS.BROADCAST, args -> {
            if (args != null && args.length > 0) {
                Object data = args[0];
                Log.d("DATADEBUG", data.toString());
                if (!data.toString().isEmpty()) {
                    Chat chat = new Gson().fromJson(data.toString(), Chat.class);
                    _onNewChat.postValue(chat);
                }
            }

        });
    }

    public void disconnectSocket() {
        socket.disconnect();
        socket.off();
    }

    public void emitChat(Chat chat) {
        String jsonStr = new Gson().toJson(chat, Chat.class);
        socket.emit(CHAT_KEYS.NEW_MESSAGE, jsonStr);
    }

    private static class CHAT_KEYS {
        public static final String NEW_MESSAGE = "new_message";
        public static final String BROADCAST = "broadcast";
    }

    private static final String SOCKET_URL = "http://192.168.1.139:3000/";
}
