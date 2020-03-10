package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseGame implements Database {

    private BDOpenHelper openHelper;

    public DatabaseGame(Context c){
        openHelper=new BDOpenHelper(c,1);
    }

    /**
     * This function is going to return an int that represents the score of the player
     * @return
     */
    @Override
    public int loadScore() {
        SQLiteDatabase db=openHelper.getWritableDatabase();
        Cursor c=db.query("score",
                null,null,null,
                null,null,null);
        if(c.moveToFirst()){
            return c.getInt(c.getColumnIndex("puntuation"));
        }else{

            return 0;
        }
    }

    /**
     *Add the new score to the database
     * @param newScore
     */
    @Override
    public void saveScore(int newScore) {
        SQLiteDatabase db=openHelper.getWritableDatabase();
        Cursor c=db.query("score",
                null,null,null,
                null,null,null);

        ContentValues cv=new ContentValues();
        cv.put("puntuation",newScore);

        if(c.moveToFirst()){
            db.update("score",cv,null,
                    null);
        }else{
            //Caso en que la tabla esté vacía
            db.insert("score",null,cv);
        }
        c.close();
        db.close();
    }


}
