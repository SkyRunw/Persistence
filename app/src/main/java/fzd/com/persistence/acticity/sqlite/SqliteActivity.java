package fzd.com.persistence.acticity.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import fzd.com.persistence.R;
import fzd.com.persistence.base.BaseActivity;

/**
 * Created by Administrator on 2016/5/17.
 * 1. SQLiteOpenHelper 帮助类
 * 2. getReadableDatabase() 和getWritableDatabase()
 * 3.数据库文件会存放在/data/data/<package name>/databases/目录下
 */
public class SqliteActivity extends BaseActivity{
    private MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void loadData() {

    }

    //创建或升级数据库
    private void createOrUpgrade(){
        myDatabaseHelper.getWritableDatabase();
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.acitivity_sqlite);
        Button create_database = (Button) findViewById(R.id.create_database);
        myDatabaseHelper = new MyDatabaseHelper(this, "BookStrore.db", null, 3);
        create_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createOrUpgrade();
            }
        });

        Button upgrage_database = (Button) findViewById(R.id.upgrage_database);
        upgrage_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createOrUpgrade();
            }
        });

        Button add_data = (Button) findViewById(R.id.add_data);
        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              add();
            }
        });
        Button update_data = (Button) findViewById(R.id.update_data);
        update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db =  myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 10.99);
                db.update("book",values,"name = ?", new String[]{"The Da Vinci Code"});
            }
        });
        Button delete_data = (Button) findViewById(R.id.delete_data);
        delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              delete();
            }
        });

        Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               query();
            }
            });

        Button replace_data = (Button) findViewById(R.id.replace_data);
        replace_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transcation();
            }
        });

    }

    private void transcation() {

        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        try{
            db.beginTransaction();
            db.delete("Book",null, null);
//            if(true){
//                throw new NullPointerException();
//            }
            ContentValues values = new ContentValues();
            values.put("name", "Game of Thrones");
            values.put("author", "George Martin");
            values.put("pages", 720);
            values.put("price", 20.85);
            db.insert("Book", null, values);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

    private void query() {
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        //  查询Book 表中所有的数据
        Cursor cursor = db.query("Book", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                //  遍历Cursor 对象，取出数据并打印
                String name = cursor.getString(cursor.
                        getColumnIndex("name"));
                String author = cursor.getString(cursor.
                        getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex
                        ("pages"));
                double price = cursor.getDouble(cursor.
                        getColumnIndex("price"));
                Log.d("SqliteActivity", "book name is " + name);
                Log.d("SqliteActivity", "book author is " + author);
                Log.d("SqliteActivity", "book pages is " + pages);
                Log.d("SqliteActivity", "book price is " + price);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void delete() {
        SQLiteDatabase db =  myDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("price", 10.99);
        db.delete("Book", "pages > ?", new String[] { "500" });
    }

    private void add() {
        SQLiteDatabase db =  myDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //  开始组装第一条数据
        values.put("name", "The Da Vinci Code");
        values.put("author", "Dan Brown");
        values.put("pages", 454);
        values.put("price", 16.96);
        /**
         * 参数：表名，列默认值，ContentValue
         */
        db.insert("Book", null, values); //  插入第一条数据
        values.clear();
        //  开始组装第二条数据
        values.put("name", "The Lost Symbol");
        values.put("author", "Dan Brown");
        values.put("pages", 510);
        values.put("price", 19.95);
        db.insert("Book", null, values); //插入第二条数据
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
