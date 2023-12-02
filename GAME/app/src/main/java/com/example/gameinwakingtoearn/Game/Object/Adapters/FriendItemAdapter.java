package com.example.gameinwakingtoearn.Game.Object.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gameinwakingtoearn.Game.Object.MainUI.ItemClickedListener;
import com.example.gameinwakingtoearn.Game.Object.Models.Item;
import com.example.gameinwakingtoearn.R;

import org.w3c.dom.Text;

import java.util.List;

public class FriendItemAdapter extends RecyclerView.Adapter<FriendItemAdapter.FriendItemViewHolder> {

    private List<Item> itemList;

    public ItemClickedListener itemClickedListener;

    public void setClickedListener(ItemClickedListener itemClickedListener) {
        this.itemClickedListener = itemClickedListener;
    }

    public FriendItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public FriendItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new FriendItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendItemViewHolder holder, int position) {

        Item item = itemList.get(position);

        holder.avatar.setImageResource(item.getAvatar());
        holder.username.setText(item.getUsername());
        holder.level.setText(item.getLevel());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class FriendItemViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        ImageView avatar;
        TextView username;
        TextView level;
        public FriendItemViewHolder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.avatar);
            username = itemView.findViewById(R.id.username);
            level = itemView.findViewById(R.id.level);

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
