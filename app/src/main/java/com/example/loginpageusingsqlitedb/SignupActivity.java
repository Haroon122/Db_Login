package com.example.loginpageusingsqlitedb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupActivity extends AppCompatActivity {

    EditText uname,uemail,uphone,upassword;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        uname=findViewById(R.id.userr);
        uemail=findViewById(R.id.emaill);
        uphone=findViewById(R.id.phonee);
        upassword=findViewById(R.id.pass);
        signin=findViewById(R.id.signupBTn);

        DbHelper Helper=new DbHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name, mail, phone,pass;
                name=uname.getText().toString();
                mail=uemail.getText().toString();
                phone=uphone.getText().toString();
                pass=upassword.getText().toString();

                if (name.isEmpty() || mail.isEmpty() || phone.isEmpty() || pass.isEmpty()){
                    Toast.makeText(SignupActivity.this,"All fields are required",Toast.LENGTH_SHORT).show();
                }else {
                    boolean i=Helper.checkEmails(mail);
                    if (i==false){
                        boolean insertfun=Helper.insert_record(name,mail,phone,pass);
                        if (insertfun==true){
                            Toast.makeText(SignupActivity.this,"Record Save Successfully",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(SignupActivity.this,"Failed to Saved Record ",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(SignupActivity.this,"Please Choose another Email",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    }
