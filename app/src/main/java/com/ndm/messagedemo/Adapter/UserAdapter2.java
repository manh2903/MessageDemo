package com.ndm.messagedemo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ndm.messagedemo.Item.UserItem;
import com.ndm.messagedemo.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter2 extends RecyclerView.Adapter<UserAdapter2.UserViewHolder2> {

    List<UserItem> mUserItem;
    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public void Setdata(List<UserItem> list) {
        mUserItem = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user2,parent,false);
        return new  UserViewHolder2(view);
    }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder2 holder, int position){
            int currentPosition = holder.getAdapterPosition();
            UserItem user = mUserItem.get(currentPosition);
            if (user == null) {
                return;
            }
            holder.circleImageView.setImageResource(user.getResouceId());
            holder.tv_user.setText(user.getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(mUserItem.get(currentPosition));
                    }
                }
            });
        }




    @Override
    public int getItemCount() {
        if (mUserItem != null) {
            return mUserItem.size();
        }
        return 0;
    }

    public class UserViewHolder2 extends RecyclerView.ViewHolder {
        private CircleImageView circleImageView;

        private TextView tv_user;

        public UserViewHolder2(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.user_image);
            tv_user = itemView.findViewById(R.id.tv_name);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(UserItem item);
    }

}
