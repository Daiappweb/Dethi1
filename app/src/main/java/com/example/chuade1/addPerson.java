package com.example.chuade1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addPerson extends AppCompatActivity {

    EditText edtId;
    EditText edtName;
    EditText edtPhone;
    Button btnSubmit;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        btnSubmit = findViewById(R.id.btnSubmit);
        back = findViewById(R.id.btnBack);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(edtId.getText().toString().trim());
                String name = edtName.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("Id",id);
                bundle.putString("Name",name);
                bundle.putString("Phone",phone);

                intent.putExtras(bundle);
                setResult(200,intent);
//                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addPerson.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}