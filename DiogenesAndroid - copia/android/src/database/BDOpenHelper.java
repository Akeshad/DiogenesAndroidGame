package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDOpenHelper extends SQLiteOpenHelper {

    public BDOpenHelper(Context c, int version){
        super(c, "explotingCookies", null, version);
    }

        @Override
        public void onCreate (SQLiteDatabase db){
        db.execSQL("create table score(puntuation int(5))");
    }

        @Override
        public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){

    }


}
