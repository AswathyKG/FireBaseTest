package com.example.firebasetest;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    EditText uname, password, cpassword, place, email;
    Button submit;
    RadioButton male, female, other;
    RadioGroup radioGroup;
    Date date_time;
    String uid;
    String name, mailid, places, pswd, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findElements();
        findValue();
        myRef = database.getReference("userinfo" );
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uid=email.getText().toString().trim();
                name = uname.getText().toString().trim();
                mailid = email.getText().toString().trim();
                pswd = password.getText().toString().trim();
                places = place.getText().toString().trim();


                if(password.getText().toString().equals(cpassword.getText().toString())){
                    writeNewUser();
                    Toast.makeText(getApplicationContext(),"User Created & Data Uploaded Successfully",Toast.LENGTH_SHORT).show();

                }
                else {
                    cpassword.setError("password do not match");
                }


            }
        });

    }



    private void findValue() {
       /* uid=email.getText().toString().trim();
        name = uname.getText().toString().trim();
        mailid = email.getText().toString().trim();
        pswd = password.getText().toString().trim();
        places = place.getText().toString().trim();
*/
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.male:
                        gender = "male";
                        break;
                    case R.id.female:
                        gender = "female";
                        break;
                    case R.id.other:
                        gender = "other";
                        break;
                }

            }
        });

    }

    private void findElements() {
        uname = findViewById(R.id.uname);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);
        place = findViewById(R.id.place);
        email = findViewById(R.id.email);
        radioGroup = findViewById(R.id.groupradio);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        other = findViewById(R.id.other);
        date_time = Calendar.getInstance().getTime();
        submit=findViewById(R.id.submit);
    }

    private void writeNewUser() {

        myRef.child(mailid).child("mailid").setValue(mailid);
        myRef.child(mailid).child("name").setValue(name);
        myRef.child(mailid).child("pswd").setValue(pswd);
        myRef.child(mailid).child("place").setValue(places);
        myRef.child(mailid).child("gender").setValue(gender);
        myRef.child(mailid).child("date_time").setValue(date_time);

    }
}