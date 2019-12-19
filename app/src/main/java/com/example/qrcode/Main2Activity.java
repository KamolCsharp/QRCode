package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Main2Activity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView;
    DatabaseHelper sqlhelper;
    SQLiteDatabase db;
    Cursor usercursor;
    String ip="10.1.1.117";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        sqlhelper = new DatabaseHelper(this);
        db = sqlhelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(DatabaseHelper.COLUMN_FAMILIYASI, "Eshpolatov");
//        cv.put(DatabaseHelper.COLUMN_ISMI, "Kamol");
//        cv.put(DatabaseHelper.COLUMN_SHARIFI, "Ikrom og'li");
//        cv.put(DatabaseHelper.COLUMN_EMAIL, "kamoluzmu@mail.ru");
//        db.insert(DatabaseHelper.TABLE, null, cv);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void handleResult(Result result) {


        usercursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(result.getText())});
        if (usercursor != null && usercursor.moveToFirst()) {
            usercursor.moveToFirst();
            MainActivity.tv.setText("Familiyasi: " + usercursor.getString(1) + "\n" +
                    "Ismi: " + usercursor.getString(2) + "\n" +
                    "Sharifi: " + usercursor.getString(3) + "\n" +
                    "Email: " + usercursor.getString(4));
            usercursor.close();
            onBackPressed();
        } else {
            MainActivity.tv.setText("Bunday Malumot yoq!!!");
            usercursor.close();
            onBackPressed();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();

    }
}
