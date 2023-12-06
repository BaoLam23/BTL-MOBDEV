package com.example.gameinwakingtoearn.Game.Object.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gameinwakingtoearn.Game.Object.MainUI.ItemClickedListener;
import com.example.gameinwakingtoearn.Game.Object.Models.FriendReqItem;
import com.example.gameinwakingtoearn.Game.Object.Models.Item;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.FireBaseMangament;
import com.example.gameinwakingtoearn.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.WriteBatch;

import java.util.List;

public class FriendReqItemAdapter extends
        RecyclerView.Adapter<FriendReqItemAdapter.FriendReqItemViewHolder> {

    private List<FriendReqItem> itemList;

    public ItemClickedListener itemClickedListener;

    public void setClickedListener(ItemClickedListener itemClickedListener) {
        this.itemClickedListener = itemClickedListener;
    }

    public FriendReqItemAdapter(List<FriendReqItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public FriendReqItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friend_req_item_layout, parent, false);
        return new FriendReqItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendReqItemViewHolder holder, int position) {
        FriendReqItem item = itemList.get(position);

        holder.avatar.setImageResource(item.getAvatar());
        holder.username.setText(item.getUsername());
        holder.iconXp.setImageResource(item.getXpIcon());
        holder.level.setText(item.getLevel());
        holder.acceptIcon.setImageResource(item.getAcceptIcon());
        holder.declineIcon.setImageResource(item.getDeclineIcon());

        holder.acceptIcon.setOnClickListener(view -> {
            Log.e("accept Icon clicked ","ok");
            if(itemList.size() > position){
                FireBaseMangament.acceptRequest(itemList.get(position).getId());
                itemList.remove(position);
                this.notifyDataSetChanged();

            }
        });

        holder.declineIcon.setOnClickListener(view -> {
            Log.e("decline Icon clicked ","ok");
            FireBaseMangament.declineRequest(itemList.get(position).getId());
            itemList.remove(position);
            this.notifyDataSetChanged();
        });
    }




    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class FriendReqItemViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        ImageView avatar;
        ImageView acceptIcon;
        ImageView declineIcon;
        TextView username;
        ImageView iconXp;
        TextView level;
        public FriendReqItemViewHolder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.avatar);
            username = itemView.findViewById(R.id.username);
            acceptIcon = itemView.findViewById(R.id.acceptIcon);
            declineIcon = itemView.findViewById(R.id.declineIcon);
            iconXp = itemView.findViewById(R.id.xpIconOtherUsers);
            level =  itemView.findViewById(R.id.levelOtherUsers);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickedListener != null) {
                itemClickedListener.onClick(v, getAdapterPosition());
            }
        }
    }

}
