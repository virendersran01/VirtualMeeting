package com.virtualstudios.virtualmeeting.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.virtualstudios.virtualmeeting.R;
import com.virtualstudios.virtualmeeting.listeners.UserListener;
import com.virtualstudios.virtualmeeting.models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private List<User> users;
    private UserListener userListener;
    private List<User> selectedUsers;

    public UsersAdapter(List<User> users, UserListener userListener) {
        this.users = users;
        this.userListener = userListener;
        selectedUsers = new ArrayList<>();
    }

    public List<User> getSelectedUsers() {
        return selectedUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setUserData(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

      class ViewHolder extends RecyclerView.ViewHolder {

        TextView textFirstChar, textUsername, textEmail;
        ImageView imageAudioMeeting, imageVideoMeeting;
        ConstraintLayout userContainer;
        ImageView imageSelected;
    
         ViewHolder(@NonNull View itemView) {
            super(itemView);

            textFirstChar = itemView.findViewById(R.id.textFirstChar);
            textUsername = itemView.findViewById(R.id.textUsername);
            textEmail = itemView.findViewById(R.id.textEmail);
            imageAudioMeeting = itemView.findViewById(R.id.imageAudioMeeting);
            imageVideoMeeting = itemView.findViewById(R.id.imageVideoMeeting);
            userContainer = itemView.findViewById(R.id.userContainer);
            imageSelected = itemView.findViewById(R.id.imageSelected);

        }

        void setUserData(User user){
             textFirstChar.setText(user.firstName.substring(0,1));
             textUsername.setText(String.format("%s %s", user.firstName, user.lastName));
             textEmail.setText(user.email);
             imageAudioMeeting.setOnClickListener(view -> userListener.initiateAudioMeeting(user));
             imageVideoMeeting.setOnClickListener(view -> userListener.initiateVideoMeeting(user));

             userContainer.setOnLongClickListener(view -> {

                 if (imageSelected.getVisibility() != View.VISIBLE) {
                     selectedUsers.add(user);
                     imageSelected.setVisibility(View.VISIBLE);
                     imageVideoMeeting.setVisibility(View.GONE);
                     imageAudioMeeting.setVisibility(View.GONE);
                     userListener.onMultipleUsersAction(true);
                 }
                 return true;
             });

             userContainer.setOnClickListener(view -> {
                 if (imageSelected.getVisibility() == View.VISIBLE){
                     selectedUsers.remove(user);
                     imageSelected.setVisibility(View.GONE);
                     imageVideoMeeting.setVisibility(View.VISIBLE);
                     imageAudioMeeting.setVisibility(View.VISIBLE);
                     if (selectedUsers.size() == 0){
                         userListener.onMultipleUsersAction(false);
                     }
                 }else {
                     if (selectedUsers.size() > 0){
                         selectedUsers.add(user);
                         imageSelected.setVisibility(View.VISIBLE);
                         imageVideoMeeting.setVisibility(View.GONE);
                         imageAudioMeeting.setVisibility(View.GONE);
                     }
                 }
             });
        }
    }
}