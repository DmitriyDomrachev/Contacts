package com.example.dima.contacts;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.BLUETOOTH_ADMIN;
import static android.Manifest.permission.CALL_PHONE;


public class MainActivity extends AppCompatActivity {




    RecyclerView rv;
    List<Contact> contacts = new ArrayList<>();
    static ArrayList<Contact> updateContacts = new ArrayList<>();
    static int contContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContactsHelper ch = new ContactsHelper(getApplicationContext()); //инициализация нашего помошника управления контактами в базе данных

        updateContacts = ch.getAll();
        contContacts = updateContacts.size();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ContactActivity.class));

            }
        });


        load();

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this)); // устанавливаем разметку для списка.
        rv.setItemAnimator(new DefaultItemAnimator()); //устанавливаем класс, отвечающий за анимации в списке
        rv.setAdapter(new RVAdapter(contacts, this)); //устанавливаем наш адаптер



    }



    public void load() {

        for (int i = 0; i < contContacts; i++) {
            contacts.add(new Contact(updateContacts.get(i).id, updateContacts.get(i).name, updateContacts.get(i).phone, updateContacts.get(i).birthday));

        }
    }
    public void update() {

        for (int i = 0; i < contContacts; i++) {
            contacts.add(new Contact(updateContacts.get(i).id, updateContacts.get(i).name, updateContacts.get(i).phone, updateContacts.get(i).birthday));
            rv.setAdapter(new RVAdapter(contacts, this)); //устанавливаем наш адаптер

        }
    }


         // запрашиваем разрешение



}
