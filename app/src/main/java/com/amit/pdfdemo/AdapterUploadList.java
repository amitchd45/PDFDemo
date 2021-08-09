package com.amit.pdfdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterUploadList extends RecyclerView.Adapter<AdapterUploadList.MyViewHolder> {
    private Context context;
    private List<Upload>userList;
    private Select select;
    public interface Select{
        void onClick(String title,String url);
    }

    public AdapterUploadList(Context context, List<Upload> userList, Select select) {
        this.context = context;
        this.userList = userList;
        this.select = select;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(userList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select.onClick(userList.get(position).name,userList.get(position).url);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView userImage;
        private TextView title,userEmailTv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title);
        }
    }
}
