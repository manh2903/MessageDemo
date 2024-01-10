package com.ndm.messagedemo.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;
import com.ndm.messagedemo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CallFragment extends Fragment {

    private EditText messageInput;
    private TextView receivedMessages;
    private Button sendButton;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    private static final String SERVER_IP = "192.168.1.139"; // Địa chỉ IP của server
    private static final int SERVER_PORT = 3000; // Cổng server của bạn

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        messageInput = view.findViewById(R.id.message_input);
        receivedMessages = view.findViewById(R.id.received_messages);
        sendButton = view.findViewById(R.id.send_button);

        new ConnectToServer().execute();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        return view;
    }

    private void sendMessage() {
        String message = messageInput.getText().toString();
        if (!message.isEmpty()) {
            new SendMessageTask().execute(message);
            messageInput.setText("");
        }
    }

    private class ConnectToServer extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                clientSocket = new Socket(SERVER_IP, SERVER_PORT);
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                startListening();
            } catch (IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "Failed to connect to the server.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return null;
        }
    }

    private void startListening() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String receivedMessage;
                    while ((receivedMessage = in.readLine()) != null) {
                        updateReceivedMessages(receivedMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void updateReceivedMessages(final String message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String timeStamp = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

                // Tách ID của client và tin nhắn từ chuỗi message
                String[] parts = message.split(": ", 2);
                if (parts.length >= 2) {
                    String clientID = parts[0];
                    String chatMessage = parts[1];

                    if (clientID.equals("1")) {
                        // Update the UI for messages received from Client 1 in Client 2's view
                        receivedMessages.append(timeStamp + " | Client 1: " + chatMessage + "\n");
                    } else {
                        // For messages received from other clients (excluding Client 1), update as usual
                        receivedMessages.append(timeStamp + " | Client " + clientID + ": " + chatMessage + "\n");
                    }
                } else {
                    receivedMessages.append(message + "\n");
                }
            }
        });
    }

    private class SendMessageTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            try {
                String message = strings[0];
                out.println(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}