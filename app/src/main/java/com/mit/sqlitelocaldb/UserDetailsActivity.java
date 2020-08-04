package com.mit.sqlitelocaldb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mit.sqlitelocaldb.db.MyLocalDb;
import com.mit.sqlitelocaldb.model.User;

public class UserDetailsActivity extends AppCompatActivity {

    private TextView txtName,txtPhone,txtEmail;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        final String id = this.getIntent().getStringExtra("id");
        context = UserDetailsActivity.this;

        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtName = findViewById(R.id.txtName);

        getUserDetailsById(Integer.parseInt(id));

        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Building Alert Dialog -
                //Builder Partner
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                alertBuilder.setTitle("Delete User");
                alertBuilder.setMessage("Are you sure you want to delete this user?");
                alertBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //deletion
                        deleteUser(Integer.parseInt(id));
                    }
                });
                alertBuilder.setNegativeButton("NO",null);

                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.show();


            }
        });

        //Alerts


    }
    /*
    * method to get users by Id
    * */
    private void getUserDetailsById(int id){
        MyLocalDb.getInstance(this)
                .getUserDao()
                .getUserById(id)
                .observe(this, new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        if (user!=null){
                            txtName.setText(user.getFirstname()+" "+user.getSecondname());
                            txtEmail.setText(user.getEmail());
                            txtPhone.setText(user.getPhone());
                        }

                    }
                });
    }

    private void deleteUser(final int id){

        new Thread(new Runnable() {
            @Override
            public void run() {
                MyLocalDb.getInstance(context)
                        .getUserDao()
                        .deleteUser(id);
            }
        }).start();

        Toast.makeText(context,"User Deleted successfully",Toast.LENGTH_SHORT).show();
        finish();

    }
}