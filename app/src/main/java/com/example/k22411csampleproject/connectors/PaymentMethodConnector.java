package com.example.k22411csampleproject.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.k22411csampleproject.models.PaymentMethod;

import java.util.ArrayList;

public class PaymentMethodConnector {
    public ArrayList<PaymentMethod> getAll(SQLiteDatabase database) {
        ArrayList<PaymentMethod> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM PaymentMethod", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            list.add(new PaymentMethod(id, name, description));
        }
        cursor.close();
        return list;
    }
}
