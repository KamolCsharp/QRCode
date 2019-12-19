package com.example.qrcode;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "usermalumot.db";
    private static final int SCHEMA = 1;
    static final String TABLE = "users";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FAMILIYASI = "Familiyasi";
    public static final String COLUMN_ISMI = "Ismi";
    public static final String COLUMN_SHARIFI = "Sharifi";
    public static final String COLUMN_EMAIL = "Email";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FAMILIYASI + " TEXT, " +
                COLUMN_ISMI + " TEXT, " + COLUMN_SHARIFI + " TEXT, " +
                COLUMN_EMAIL + " TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
