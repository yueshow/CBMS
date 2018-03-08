package com.awesome.consumer.cbms.db.services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.awesome.consumer.cbms.beans.City;
import com.awesome.consumer.cbms.db.AddressDBOpenhelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Create: 05/03/18 , 下午3:07
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 05/03/18)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：
 * -----------------------------------------------------------------
 */
public class AddressService {
    private AddressDBOpenhelper dbOpenHelper;
    private List<City> result = new ArrayList<>();
    public AddressService(Context context){
        dbOpenHelper = new AddressDBOpenhelper(context);
    }

    public List getCity(Integer key){
        result.clear();
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from address where parentId = ?",
                new String[]{String.valueOf(key)});
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int parentId = cursor.getInt(cursor.getColumnIndex("parentId"));

            String zh = cursor.getString(cursor.getColumnIndex("zh"));
            String tw = cursor.getString(cursor.getColumnIndex("tw"));
//            String en = cursor.getString(cursor.getColumnIndex("en"));
//            String kh = cursor.getString(cursor.getColumnIndex("kh"));

            City bean = new City(id, parentId, zh, tw, "", "");
            result.add(bean);
        }
        cursor.close();
        db.close();
        return result;
    }
}
