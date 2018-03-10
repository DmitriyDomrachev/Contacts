package com.example.dima.contacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aleksej
 * on 03.03.2018.
 */

//класс для инициализации нашей базы данных, а так же её обновления в случаии добавления новых полей
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "contactDB.db";
    public static final String TABLE_NAME = "contacts";
    public static int DATABASE_VERSION = 1;

    //имена для колонок таблицы контактов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_BIRTHDAY = "birthday";

    //индексы расположения этих колонок в таблице
    public static final int NUM_COLUMN_ID = 0;
    public static final int NUM_COLUMN_NAME = 1;
    public static final int NUM_COLUMN_PHONE = 2;
    public static final int NUM_COLUMN_BIRTHDAY = 3;

    //конструктор, куда передаётся контекст, имя базы и её версия
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //при помомощи DDL выполняем иницицализацию первой таблицы в нашей базе данных
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_BIRTHDAY + " TEXT); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
