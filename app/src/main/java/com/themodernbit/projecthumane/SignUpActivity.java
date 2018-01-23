package com.themodernbit.projecthumane;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.themodernbit.projecthumane.User.UserClass;
import com.themodernbit.projecthumane.User.UserDBHandler;

public class SignUpActivity extends AppCompatActivity {

    private EditText UserName;
    private EditText PhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        UserName = (EditText) findViewById(R.id.editTextName);
        PhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);



    }


    public void onSignupClick(View view){
        UserDBHandler dbHandler = new UserDBHandler(this, null, null, 1);

        String name = UserName.getText().toString();
        String phone = PhoneNumber.getText().toString();
        UserClass user = new UserClass(name,phone,"en");


        dbHandler.addHandler(user);
        PhoneNumber.setText("");
        UserName.setText("");

        Intent theIntent = new Intent(this,MainActivity.class);
       // theIntent.putExtra("key",dbHandler);
        startActivity(theIntent);

    }
}
