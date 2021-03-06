package pt.ipp.estg.agendadigital;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sofia on 14/12/2017.
 */

public class DbHelper extends SQLiteOpenHelper{
    public static final String TAG = DbHelper.class.getSimpleName();
    public static final String DB_NAME="login.db";
    public static final int DB_VERSION=1;
    public static final String USER_TABLE = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASS = "password";

    /**
     * Cria tabela Users(
     * id integer primary key autoincrement
     * username text
     * password text);
     */
    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " TEXT," + COLUMN_PASS + " TEXT);";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    /**
     +     * Storing user details in database
     +     * */

    public void addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

       ContentValues values = new ContentValues();
       values.put(COLUMN_USERNAME, username);
       values.put(COLUMN_PASS, password);

               long id = db.insert(USER_TABLE, null, values);
            db.close();

           Log.d(TAG, "Registado com Sucesso!" + id);
       }

<<<<<<< HEAD
  public boolean getUser(String username, String pass){
     //HashMap<String, String> user = new HashMap<String, String>();
      String selectQuery = "select * from  " + USER_TABLE + " where " +
              COLUMN_USERNAME + " = " + "'"+ username +"'" + " and " + COLUMN_PASS + " = " + "'"+pass+"'";
=======
  public boolean getUser(String pass, String mail){
     //HashMap<String, String> user = new HashMap<String, String>();
      String selectQuery = "select * from  " + USER_TABLE + " where " +
              COLUMN_EMAIL + " = " + "'"+ mail +"'" + " and " + COLUMN_PASS + " = " + "'"+pass+"'";
>>>>>>> 982b2760bcf216628ab147bbd20463c3b9a73332

      SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursor = db.rawQuery(selectQuery, null);

      // Move to first row
       cursor.moveToFirst();
                if (cursor.getCount() > 0) {
                             return true;
                   }
                cursor.close();
              db.close();

      return false;
           }

    public boolean compareEmail(String mail){
        String selectQueryEmail = "select * from  " + USER_TABLE + " where " + COLUMN_EMAIL + " = " + "'"+ mail +"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQueryEmail, null);
        cursor.moveToFirst();
      if(cursor!=null){
          return false;
      }else{
          return true;
      }

    }
}


