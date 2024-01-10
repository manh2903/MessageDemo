package com.ndm.messagedemo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ndm.messagedemo.Item.Chat;
import com.ndm.messagedemo.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int ITEM_SELF = 1;
    private final int ITEM_OTHER = 2;

    private final DiffUtil.ItemCallback<Chat> diffCallback = new DiffUtil.ItemCallback<Chat>() {
        @Override
        public boolean areItemsTheSame(@NonNull Chat oldItem, @NonNull Chat newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Chat oldItem, @NonNull Chat newItem) {
            return oldItem.equals(newItem);
        }
    };

    private final AsyncListDiffer<Chat> differ = new AsyncListDiffer<>(this, diffCallback);

    public void submitChat(List<Chat> chats) {
        differ.submitList(chats);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == ITEM_SELF) {
            View selfChatView = inflater.inflate(R.layout.item_chat_self, parent, false);
            return new SelfChatItemViewHolder(selfChatView);
        } else {
            View otherChatView = inflater.inflate(R.layout.item_chat_other, parent, false);
            return new OtherChatItemViewHolder(otherChatView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chat chat = differ.getCurrentList().get(position);
        if (chat.isSelf()) {
            ((SelfChatItemViewHolder) holder).bind(chat);
        } else {
            ((OtherChatItemViewHolder) holder).bind(chat);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Chat chat = differ.getCurrentList().get(position);
        return chat.isSelf() ? ITEM_SELF : ITEM_OTHER;
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    static class OtherChatItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView msg;

        public OtherChatItemViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            msg = itemView.findViewById(R.id.msg);
        }

        public void bind(Chat chat) {
            name.setText(chat.getUsername());
            msg.setText(chat.getText());
        }
    }

    static class SelfChatItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView msg;

        public SelfChatItemViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            msg = itemView.findViewById(R.id.msg);
        }

        public void bind(Chat chat) {
            name.setText("You");
            msg.setText(chat.getText());
        }
    }
}
