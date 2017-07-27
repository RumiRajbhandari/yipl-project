package com.example.root.yiplchallenge;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.yiplchallenge.model.User;

import java.util.List;

/**
 * Created by root on 7/27/17.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    List<User> userList;

    public UserAdapter(List<User> userList){
        this.userList=userList;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView userId, id, title, body;
        public UserViewHolder(View itemView) {
            super(itemView);
            userId=(TextView)itemView.findViewById(R.id.user_id);
            id=(TextView)itemView.findViewById(R.id.id);
            title=(TextView)itemView.findViewById(R.id.title);
            body=(TextView)itemView.findViewById(R.id.body);
        }
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_single_row,parent,false);
        return new UserViewHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user=userList.get(position);
        holder.userId.setText(String.valueOf(user.getUserId()));
        holder.id.setText(String.valueOf(user.getId()));
        holder.title.setText(user.getTitle());
        holder.body.setText(user.getBody());
    }



    @Override
    public int getItemCount() {
        return userList.size();
    }
}
