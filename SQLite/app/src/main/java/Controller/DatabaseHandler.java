package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.Person;
import Utils.Utils;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PEOPLE_TABLE = "CREATE TABLE "+Utils.TABLE_NAME
                                    +" ("
                                    + Utils.KEY_NAME + " TEXT,"+ Utils.KEY_LNAME + " TEXT,"
                                    + Utils.KEY_ADDRESS + " TEXT,"+ Utils.KEY_AGE + " TEXT)";
        db.execSQL(CREATE_PEOPLE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ Utils.TABLE_NAME);
        onCreate(db);
    }
    public void addPerson(Person person){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.KEY_NAME,person.getName());
        contentValues.put(Utils.KEY_LNAME,person.getLname());
        contentValues.put(Utils.KEY_ADDRESS,person.getAddress());
        contentValues.put(Utils.KEY_AGE,String.valueOf(person.getAge()));
        database.insert(Utils.TABLE_NAME,null, contentValues);
        database.close();
    }
    public Person getPerson(String name){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(Utils.TABLE_NAME,
                new String[]{Utils.KEY_NAME,Utils.KEY_LNAME,Utils.KEY_ADDRESS,Utils.KEY_AGE}, Utils.KEY_NAME+"=?",
                new String[]{name}, null,null,null,null);
            cursor.moveToFirst();
        //Log.d("Cursor", cursor.getString(0));
        //Log.d("Cursor", cursor.getString(1));
        //Log.d("Cursor", cursor.getString(2));
        //Log.d("Cursor", cursor.getString(3));


        return new Person(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                Integer.parseInt(cursor.getString(3)));

    }
    public List<Person> getPeople(){
        SQLiteDatabase database = this.getReadableDatabase();
        List<Person> personList = new ArrayList<>();
        String getAll = "SELECT * FROM "+Utils.TABLE_NAME;
        Cursor cursor = database.rawQuery(getAll,null);
        cursor.moveToFirst();
            do {
                Person person = new Person();
                person.setName(cursor.getString(0));
                person.setLname(cursor.getString(1));
                person.setAddress(cursor.getString(2));
                person.setAge(Integer.parseInt(cursor.getString(3)));
                personList.add(person);
            }while (cursor.moveToNext());

        return personList;

    }

}
