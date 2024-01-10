package com.ndm.messagedemo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ndm.messagedemo.Item.UserItem;
import com.ndm.messagedemo.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    List<UserItem> mUserItem;

    public void Setdata(List<UserItem> list) {
        mUserItem = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user1,parent,false);
        return new  UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

         UserItem userItem = mUserItem.get(position);
       if(userItem == null )
        {
            return;
        }
        holder.circleImageView.setImageResource(userItem.getResouceId());
        holder.tv_user.setText(userItem.getName());
    }

    @Override
    public int getItemCount() {
        if (mUserItem != null) {
            return mUserItem.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView circleImageView;

        private TextView tv_user;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.user_image);
            tv_user = itemView.findViewById(R.id.tv_name);
        }
    }
}
