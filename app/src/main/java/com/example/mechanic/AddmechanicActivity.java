package com.example.mechanic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mechanic.api.AbstractAPIListener;

public class AddmechanicActivity extends AppCompatActivity {
    EditText username,email,password1,password2,plocation,phone;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmechanic);
        //get spinner from the xml
        Spinner dropdown = findViewById(R.id.spinner1);
        //create  list of items for the spinner
        String[] items = new String[]{"Nakuru","Nairobi","Mombasa","Naivasha","Kericho"};
        //create an sdapter to describe how the items are displayed
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
       //Set the spinner adapter to the previously created
       // adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        dropdown.setAdapter(adapter);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password1 = findViewById(R.id.password1);
        password2 = findViewById(R.id.password2);
        plocation = findViewById(R.id.location);
        phone = findViewById(R.id.phone);

        submit = findViewById(R.id.submit);

        submit.setOnClickListener(view -> {
            String location = dropdown.getSelectedItem().toString();
            String usernamev = username.getText().toString();
            String password1v = password1.getText().toString();
            String password2v = password2.getText().toString();
            String emailv = email.getText().toString();
            String plocationv = plocation.getText().toString();
            String phonev = phone.getText().toString();

           if(!password1v.equals(password2v)){
               Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
           }else {
               final ProgressDialog progressDialog = new ProgressDialog(AddmechanicActivity.this);
               progressDialog.setMessage("Submitting...");
               progressDialog.show();
               final Model model = Model.getInstance(AddmechanicActivity.this.getApplication());
               model.addmechanic(usernamev,emailv,password1v,phonev,plocationv,location,new AbstractAPIListener() {
                           @Override
                           public void onaddmechanic(User user){
                               model.setUser(user);
                               if(user.getResponse().equals("success")){
                                   Toast.makeText(AddmechanicActivity.this,"Hostel added successfully",Toast.LENGTH_LONG).show();
                               }else{
                                   Toast.makeText(AddmechanicActivity.this, user.getError(), Toast.LENGTH_SHORT).show();
                               }
                               progressDialog.dismiss();
                           }
                       });


           }


        });


    }

}