package com.example.mye_commerceapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private Button createAccount;
    private EditText userName,phoneNumber,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        createAccount=this.findViewById(R.id.register_btn);
        userName=this.findViewById(R.id.register_username_input);
        phoneNumber=this.findViewById(R.id.register_phone_number_input);
        password=this.findViewById(R.id.register_password_input);

    }
}
