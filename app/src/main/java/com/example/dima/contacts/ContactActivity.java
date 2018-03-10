package com.example.dima.contacts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.Manifest.permission.CALL_PHONE;

public class ContactActivity extends AppCompatActivity {

    String LOG_ARGS = "test";
    EditText name, number, birthday;
    Button save, close, call;
    boolean newContact = true;
    private static int REQUEST_READ_ACCESS_FINE = 10001, countFragments = 0;
    private static final String[] READ_ACCESS_FINE = new String[]{CALL_PHONE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        final Intent intent = getIntent();
        newContact = true;

        final ContactsHelper ch = new ContactsHelper(getApplicationContext()); //инициализация нашего помошника управления контактами в базе данных



        name = findViewById(R.id.contactNameEditText);
        number = findViewById(R.id.contactNumberEditText);
        birthday = findViewById(R.id.contactBirthdayEditText);
        save = findViewById(R.id.saveContactButton);
        close = findViewById(R.id.closeContactButton);
       call = findViewById(R.id.callContactButton);

        if (intent.getStringExtra("name")!=null || intent.getStringExtra("number")!=null || intent.getStringExtra("birthday")!=null){
            newContact = false;
            name.setText(intent.getStringExtra("name"));
            number.setText(intent.getStringExtra("number"));
            birthday.setText(intent.getStringExtra("birthday"));
        }

        call.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                if (isPermissionGranted(CALL_PHONE)) {
                    if (number.getText().toString().length() != 0) {
                    Intent i = new Intent(Intent.ACTION_CALL);
                    Log.d(LOG_ARGS, "Intent '"+number.getText().toString()+"'");
                    i.setData(Uri.parse("tel:" + number.getText().toString()));
                    startActivity(i);
                    }
                    else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Введите номер", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                } else { //иначе запрашиваем разрешение
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(READ_ACCESS_FINE, REQUEST_READ_ACCESS_FINE);
                    }
                }




            }
        });


        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (newContact){
                    ch.insert(name.getText().toString(),number.getText().toString(), birthday.getText().toString());
                    Log.d(LOG_ARGS,ch.getAll().toString());
                    startActivity(new Intent(ContactActivity.this,MainActivity.class));
                    Log.d(LOG_ARGS, "Нажатие: save");
                }
                else {
                    ch.updateContact(intent.getStringExtra("id"),name.getText().toString(),number.getText().toString(), birthday.getText().toString());
                    Log.d(LOG_ARGS,ch.getAll().toString());
                    startActivity(new Intent(ContactActivity.this,MainActivity.class));
                    Log.d(LOG_ARGS, "Нажатие: save, обновление");
                }


            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_ARGS, "Нажатие: close");
                startActivity(new Intent(ContactActivity.this,MainActivity.class));

            }
        });



}

    private boolean isPermissionGranted(String permission) {
        int permissionCheck = ActivityCompat.checkSelfPermission(this, permission);
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }                   // проверяем разрешение
    private void requestPermission(String permission, int requestCode) {
        ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
    }
}
