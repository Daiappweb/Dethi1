package com.example.chuade1;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<person>data;
    personAdapter adapter;
    TranVanDai_SQLite sqLite;
    Button addPerson;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lvShow);
        sqLite = new TranVanDai_SQLite(MainActivity.this,"DBContact",null,1);
        data = new ArrayList<>();
        addPerson = findViewById(R.id.btnAdd);

        //db chỉ chạy 1 lần rồi comment lại ko sẽ trùng data
//        sqLite.addInfo(new person(1,"Trần Văn Đạt","098755123"));
//        sqLite.addInfo(new person(2,"Trần Văn Long","098755123"));
//        sqLite.addInfo(new person(3,"Trần Văn Dương","098755123"));
//        sqLite.addInfo(new person(4,"Trần Văn Thành","098755123"));
//        sqLite.addInfo(new person(5,"Trần Văn Công","098755123"));
//        sqLite.addInfo(new person(6,"Trần Văn Vũ","098755123"));
//
//        sqLite.addInfo(new person(7,"Bùi thị thu hiền","0978556323"));
        data = sqLite.getAllInfo();
//        data.sort(Comparator.naturalOrder());

        adapter = new personAdapter(MainActivity.this,data);
        listView.setAdapter(adapter);

        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,addPerson.class);
                startActivityForResult(intent,100);
            }
        });

        sortName();
    }

    private void sortName() {
        Collections.sort(data, new Comparator<person>() {
            @Override
            public int compare(person o1, person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data1) {
        super.onActivityResult(requestCode, resultCode, data1);
        Bundle bundle = data1.getExtras();
        int id = bundle.getInt("Id");
        String name = bundle.getString("Name");
        String phone = bundle.getString("Phone");
        if(requestCode==100 && resultCode ==200){
            sqLite.addInfo(new person(id,name,phone));
            data = sqLite.getAllInfo();
            adapter = new personAdapter(MainActivity.this,data);
            listView.setAdapter(adapter);
            sortName();

        }
    }
}