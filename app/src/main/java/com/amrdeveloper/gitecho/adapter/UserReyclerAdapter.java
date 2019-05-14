package com.amrdeveloper.gitecho.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.databinding.UserListItemBinding;
import com.amrdeveloper.gitecho.object.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserReyclerAdapter extends RecyclerView.Adapter<UserReyclerAdapter.UserViewHolder>{

    private Context context;
    private List<User> userList;

    public UserReyclerAdapter(Context context){
        this.context = context;
        this.userList = new ArrayList<>();
    }

    public UserReyclerAdapter(Context context,List<User> userList){
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        int layoutID = R.layout.user_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean shouldAttachToParentImmediately = false;
        UserListItemBinding binding = DataBindingUtil.inflate(inflater,layoutID,parent,shouldAttachToParentImmediately);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
         User currentUser = userList.get(i);
         userViewHolder.bingUser(currentUser);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void updateRecyclerData(List<User> userList){
         if(userList != null){
             this.userList = userList;
             notifyDataSetChanged();
         }
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        private UserListItemBinding binding;

        private UserViewHolder(@NonNull UserListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bingUser(User user){
            binding.userNameTxt.setText(user.getName());
            binding.userLoginTxt.setText(user.getUsername());
            binding.userBioTxt.setText(user.getBiography());
            binding.userLocationTxt.setText(user.getLocation());
            Picasso.get().load(user.getAvatarUrl()).into(binding.userAvatarImg);
        }
    }
}