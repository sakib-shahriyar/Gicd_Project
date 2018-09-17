package com.example.shahe.gicdproject;

import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper ap_RecDB;
    Typeface robotoFont;
    Button btnSave, btnShow;
    EditText userName, emailUser, phoneNumber;
    TextView name, email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        robotoFont = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");
        ap_RecDB = new DatabaseHelper(this);
        userName = (EditText) findViewById(R.id.userName);
        emailUser = (EditText) findViewById(R.id.emailUser);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnShow = (Button) findViewById(R.id.btnShow);


        name = (TextView)  findViewById(R.id.name);
        email = (TextView)  findViewById(R.id.email);
        phone = (TextView)  findViewById(R.id.phone);


        name.setTypeface(robotoFont);
        email.setTypeface(robotoFont);
        phone.setTypeface(robotoFont);

        userName.setTypeface(robotoFont);
        emailUser.setTypeface(robotoFont);
        phoneNumber.setTypeface(robotoFont);

        btnShow.setTypeface(robotoFont);
        btnSave.setTypeface(robotoFont);

        insertData();
        viewData();
     }

     public void insertData(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String email = emailUser.getText().toString();
                String phone = phoneNumber.getText().toString();

                boolean inData = ap_RecDB.addData(name, email, phone);

                if(inData == true){
                    Toast.makeText(MainActivity.this, "Congratulation!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Try Again!", Toast.LENGTH_LONG).show();
                }


                userName.setText("");
                emailUser.setText("");
                phoneNumber.setText("");
            }

        });
     }

     public void viewData(){
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = ap_RecDB.showData();

                if(data.getCount() == 0){
                    displayData("Error", "No Data Found.");
                }
                StringBuffer buffer = new StringBuffer();

                while(data.moveToNext()){
                    buffer.append("ID: " + data.getString(0) + "\n");
                    buffer.append("Name: " + data.getString(1) + "\n");
                    buffer.append("Email: " + data.getString(2) + "\n");
                    buffer.append("Phone: " + data.getString(3) + "\n");
                    buffer.append("\n");
                }
                displayData("Data", buffer.toString());
            }
        });
     }

     public void displayData(String title, String message){
         AlertDialog.Builder builder = new Builder(this);
         builder.setCancelable(true);
         builder.setTitle(title);
         builder.setMessage(message);
         builder.show();
     }

}
