package com.example.rolli.ghid_gradina_botanica;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Plants.db";
    private static final String TABLE_FLOWERS = "Flowers";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_FLOWERNAME = "flowername";
    private static final String COLUMN_FLOWERIMG = "img";
    private static final String COLUMN_FLOWERTEXT = "text";
    private static final String COLUMN_FLOWERCATEGORY = "category";
    ArrayList<ItemModel> list = new ArrayList<>();



    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_FLOWERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FLOWERNAME + " TEXT, " +
                COLUMN_FLOWERIMG + " TEXT, "+
                COLUMN_FLOWERTEXT + " TEXT, " +
                COLUMN_FLOWERCATEGORY + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLOWERS);
        onCreate(db);
    }


    public void addProduct(ItemModel item) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FLOWERNAME, item.getItemName());
        values.put(COLUMN_FLOWERIMG, item.getItemImg());
        values.put(COLUMN_FLOWERTEXT, item.getItemText());
        values.put(COLUMN_FLOWERCATEGORY, item.getItemCategory());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_FLOWERS, null, values);
        db.close();
    }


    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_FLOWERS + " WHERE 1";
        int i = 0;


        Cursor recordSet = db.rawQuery(query, null);

        recordSet.moveToFirst();


        while (!recordSet.isAfterLast()) {
            ItemModel model = new ItemModel();
            if (recordSet.getString(recordSet.getColumnIndex("flowername")) != null) {
                dbString += "Item Name:";
                dbString += recordSet.getString(recordSet.getColumnIndex("flowername"));
                model.setItemName(recordSet.getString(recordSet.getColumnIndex("flowername")));
                dbString += " Image:";
                dbString += recordSet.getString(recordSet.getColumnIndex("img"));
                model.setItemImg(recordSet.getString(recordSet.getColumnIndex("img")));
                dbString += " Text:";
                dbString += recordSet.getString(recordSet.getColumnIndex("text"));
                model.setItemText(recordSet.getString(recordSet.getColumnIndex("text")));
                dbString += " Category:";
                dbString += recordSet.getString(recordSet.getColumnIndex("category"));
                model.setItemCategory(recordSet.getString(recordSet.getColumnIndex("category")));
                dbString += "\n";
            }
            list.add(i, model);
            i++;
            recordSet.moveToNext();
        }

        db.close();
        return dbString;
    }

    public void createList() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_FLOWERS + " WHERE 1";
        int i = 0;


        Cursor recordSet = db.rawQuery(query, null);

        recordSet.moveToFirst();


        while (!recordSet.isAfterLast()) {
            ItemModel model = new ItemModel();
            if (recordSet.getString(recordSet.getColumnIndex("flowername")) != null) {
                model.setItemName(recordSet.getString(recordSet.getColumnIndex("flowername")));
                model.setItemImg(recordSet.getString(recordSet.getColumnIndex("img")));
                model.setItemText(recordSet.getString(recordSet.getColumnIndex("text")));
                model.setItemCategory(recordSet.getString(recordSet.getColumnIndex("category")));
            }

            list.add(i, model);
            i++;

            recordSet.moveToNext();
        }
        Log.i("tag", Integer.toString(list.size()));
        db.close();
    }

    public void deleteElements() {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_FLOWERS);

    }

    public ArrayList<ItemModel> getItemModel() {

        return list;
    }
}
