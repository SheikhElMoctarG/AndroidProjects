package Controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Data;
import Utils.Utils;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(@Nullable Context context) {
        super(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+Utils.TABLE_NAME+" ("
                +Utils.KEY_ID + " TEXT,"+ Utils.KEY_NAME+" TEXT,"
                +Utils.KEY_AGE + " TEXT," +Utils.KEY_ADDRESS +" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+Utils.TABLE_NAME);
        onCreate(db);
    }
    public long addData(Data data){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.KEY_ID,data.getId());
        contentValues.put(Utils.KEY_NAME,data.getName());
        contentValues.put(Utils.KEY_AGE,data.getAge());
        contentValues.put(Utils.KEY_ADDRESS,data.getAddress());
        long result = database.insert(Utils.TABLE_NAME,null,contentValues);
        database.close();
        return result;
    }
    public long updateData(Data data){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.KEY_ID, data.getId());
        contentValues.put(Utils.KEY_NAME, data.getName());
        contentValues.put(Utils.KEY_AGE, data.getAge());
        contentValues.put(Utils.KEY_ADDRESS, data.getAddress());
        long result = database.update(Utils.TABLE_NAME,contentValues,Utils.KEY_ID
                                        +"=?",new String[]{String.valueOf(data.getId())});
        database.close();
        return result;
    }
    public void deleteData(Data data){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(Utils.TABLE_NAME,Utils.KEY_ID
                        +"=?",new String[]{String.valueOf(data.getId())});
        database.close();
    }
    public Data getData(int id){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(Utils.TABLE_NAME,
                new String[]{Utils.KEY_ID,Utils.KEY_NAME,
                        Utils.KEY_AGE,Utils.KEY_ADDRESS},
                Utils.KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();
        Data data = new Data(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),
                cursor.getString(3)
        );
        return data;
    }
    @SuppressLint("Range")
    public List<Data> getAllData(){
        List<Data> allData = new ArrayList<>();
        String query = "SELECT * FROM "+Utils.TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query,null);
        if (cursor.moveToFirst())
            do {
                Data data = new Data();
                data.setId(cursor.getInt(cursor.getColumnIndex(Utils.KEY_ID)));
                data.setName(cursor.getString(cursor.getColumnIndex(Utils.KEY_NAME)));
                data.setAge(cursor.getInt(cursor.getColumnIndex(Utils.KEY_AGE)));
                data.setAddress(cursor.getString(cursor.getColumnIndex(Utils.KEY_ADDRESS)));
                allData.add(data);
            }while (cursor.moveToNext());

        database.close();
        return allData;
    }
    public int getDataCount(){
        String query = "SELECT * FROM "+Utils.TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query,null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}
