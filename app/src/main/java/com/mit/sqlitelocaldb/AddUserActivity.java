package com.mit.sqlitelocaldb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mit.sqlitelocaldb.db.MyLocalDb;
import com.mit.sqlitelocaldb.model.User;

import java.util.List;

public class AddUserActivity extends AppCompatActivity {

    private EditText edt_first_name,edt_second_name,edt_email,edt_phone,edt_age;
    private TextView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_first_name = findViewById(R.id.edt_first_name);
        edt_second_name = findViewById(R.id.edt_second_name);
        edt_email = findViewById(R.id.edt_email);
        edt_phone = findViewById(R.id.edt_phone);
        edt_age = findViewById(R.id.edt_age);

        data = findViewById(R.id.data);

        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validation
                saveUser(edt_first_name.getText().toString(),
                        edt_second_name.getText().toString(),
                        edt_email.getText().toString(),
                        edt_phone.getText().toString(),
                        Integer.parseInt(edt_age.getText().toString()));
            }
        });

        Button btnShowData = findViewById(R.id.btn_show_data);
        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getUserData();
            }
        });
    }

    private void saveUser(final String first_name, final String second_name, final String email, final String phone, final int age){
        //insert our table within our database
        //how we access our datatabse
        //create a user object
        final User user = new User(0,first_name,second_name,email,phone,age);
        new Thread(new Runnable() {
            @Override
            public void run() {

                MyLocalDb.getInstance(AddUserActivity.this)
                        .getUserDao()
                        .insertUser(user);
            }
        }).start();
        Toast.makeText(this,"User "+first_name+" saved successfully",Toast.LENGTH_SHORT).show();
        finish();//it stops the activity
    }

    //Threads
    //Main UI
    //ANR

    private void getUserData(){
        //
        final String[] userdata = {""};
        MyLocalDb.getInstance(this)
                .getUserDao()
                .getAllUser()
                .observe(this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        for (User person:users
                             ) {
                            userdata[0] +="Name: "+person.getFirstname()+" Phone: "+person.getPhone()+"\n------------\n";
                        }
                        data.setText(userdata[0]);
                    }
                });
    }
}