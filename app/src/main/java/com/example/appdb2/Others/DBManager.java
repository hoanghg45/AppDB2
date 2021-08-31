package com.example.appdb2.Others;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appdb2.Model.People;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PeopleManger";
    private static final int VERSION = 1;
    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLQuery = "CREATE TABLE People (ID INTEGER primary key AUTOINCREMENT,NAME VARCHAR(255),ADDRESS VARCHAR(255),SEX VARCHAR(255),CONTRACT VARCHAR(255),PHONE INTEGER)";
        db.execSQL(SQLQuery);    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public ArrayList<People> getAllPeople() {

        ArrayList<People> PeopleList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from People", null);

        //Đến dòng đầu của tập dữ liệu
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int PeopleId = cursor.getInt(0);
            String PeopleName = cursor.getString(1);
            String PeopleAddress = cursor.getString(2);
            String PeopleSex = cursor.getString(3);
            String PeopleContract = cursor.getString(4);
            int PeoplePhone = cursor.getInt(5);


            PeopleList.add(new People(PeopleId,PeopleName,PeopleAddress,PeopleSex,PeopleContract,PeoplePhone));
            cursor.moveToNext();
        }

        cursor.close();

        return PeopleList;
    }public People getPeopleByID(int ID) {
        People People = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from People where id = ?", new String[]{ID + ""});

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int PeopleId = cursor.getInt(0);
            String PeopleName = cursor.getString(1);
            String PeopleAddress = cursor.getString(2);
            String PeopleSex = cursor.getString(3);
            String PeopleContract = cursor.getString(4);
            int PeoplePhone = cursor.getInt(5);

            People = new People(PeopleId,PeopleName,PeopleAddress,PeopleSex,PeopleContract,PeoplePhone);
        }
        cursor.close();
        return People;
    }

    //cập nhật
    public void updatePeople(People people) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE People SET name=?, address = ?, sex = ?,contract =?,phone =? where id = ?",
                new String[]{people.getName(),people.getAdd(),people.getSex(), people.getContract(),people.getPhone()+ "",people.getID()+""});
    }

    //thêm mới
    public void insertPeople(People people) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO People ( name, address, sex,contract,phone ) VALUES (?,?,?,?,?)",
                new String[]{people.getName(),people.getAdd(),people.getSex(), people.getContract(),people.getPhone()+ ""});
    }

    //Xoá SV bằng ID
    public void deletePeopleByID(int PeopleId) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM People where id = ?", new String[]{String.valueOf(PeopleId)});
    }


}
