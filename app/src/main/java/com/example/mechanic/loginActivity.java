package com.example.mechanic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mechanic.api.AbstractAPIListener;

public class loginActivity extends AppCompatActivity {
    EditText usernamee,passwordd;
    Button login,resetpass,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernamee = findViewById(R.id.username);
        passwordd= findViewById(R.id.password);
        resetpass= findViewById(R.id.resetpass);

        signup= findViewById(R.id.signup);
        login = findViewById(R.id.login);

        resetpass.setOnClickListener(v->{
            Intent intent = new Intent(this, resetpasswordActivity.class);
            startActivity(intent);

        });

        login.setOnClickListener(v -> {

            String username =usernamee.getText().toString();
            String password =  passwordd.getText().toString();
            if(username.length()   > 1){
                final ProgressDialog progressDialog = new ProgressDialog(loginActivity.this);
                progressDialog.setMessage("Logging in...");
                progressDialog.show();
                //final Model model = Model.getInstance(requireActivity().getApplication());
                final Model model = Model.getInstance(loginActivity.this.getApplication());
                model.login(username,password, new AbstractAPIListener() {
                    @Override
                    public void onLogin(User user){
                        model.setUser(user);
                        switch (user.getPermission()) {
                            case "user": {
                                Intent intent = new Intent(loginActivity.this, mechanicActivity.class);
                                startActivity(intent);
                                progressDialog.dismiss();
                                break;
                            }
                            case "admin": {
                                Intent intent = new Intent(loginActivity.this, AdminActivity.class);
                                startActivity(intent);
                                progressDialog.dismiss();
                                break;
                            }

                            default:
                                Toast.makeText(loginActivity.this, user.getError(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                break;
                        }


                    }
                });


            }

            else if(username.length() == 0){
                Toast.makeText(loginActivity.this, "Enter values", Toast.LENGTH_SHORT).show();


            }

        });

    }
}