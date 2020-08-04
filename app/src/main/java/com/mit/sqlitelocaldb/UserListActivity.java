package com.mit.sqlitelocaldb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mit.sqlitelocaldb.db.MyLocalDb;
import com.mit.sqlitelocaldb.model.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        context = UserListActivity.this;

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
               startActivity(new Intent(context,AddUserActivity.class));
            }
        });
        getUsers();
    }

    private void getUsers(){
        //our logic

        //fetch list of users from db

        MyLocalDb.getInstance(context)
                .getUserDao()
                .getAllUser()
                .observe(this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        //set
                        UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(users,context);
                        recyclerView.setAdapter(adapter);
                    }
                });

    }
}