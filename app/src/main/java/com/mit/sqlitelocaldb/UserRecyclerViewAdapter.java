package com.mit.sqlitelocaldb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mit.sqlitelocaldb.model.User;
import java.util.List;
public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.MyViewHolder> {
    List<User> userList;
    Context context;

    public UserRecyclerViewAdapter(List<User> userListItem, Context context) {
        this.userList = userListItem;
        this.context = context;
  }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        alt + enter
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }
/*alt+enter*/
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final User user = userList.get(position);

        holder.txtName.setText(user.getFirstname()+" "+user.getSecondname());
        holder.txtPhone.setText(user.getPhone());
        holder.txtEmail.setText(user.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,UserDetailsActivity.class);
                intent.putExtra("id",""+user.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtPhone,txtEmail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           txtName = itemView.findViewById(R.id.txtName);
           txtPhone = itemView.findViewById(R.id.txtPhone);
           txtEmail = itemView.findViewById(R.id.txtEmail);

        }
    }
}
