package fzd.com.persistence.acticity.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/5/17.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper{
    public static final String CREATE_BOOK = "create table Book ("
                                +"id integer primary key autoincrement,"
                                +"author text,"
                                +"price real,"
                                +"pages integer,"
                                +"name text)";
    public static final String CREATE_CATEGORY = "create table Category ("
                                +"id integer primary key autoincrement,"
                                +"category_name text,"
                                +"category_code)";
    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    /**
     * 创建数据库,只有第一次运行时自动调用
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOK);
        sqLiteDatabase.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    /**
     * 更新数据库
     * 1.删除原来的表
     * 2.调用onCreate();
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("drop table if exists Book");
//        sqLiteDatabase.execSQL("drop table if exists Category");
//        onCreate(sqLiteDatabase);
        /**
         * 升级数据库最佳写法
         */
        switch (i){
            case 1:
                sqLiteDatabase.execSQL(CREATE_CATEGORY);
            case 2:
                sqLiteDatabase.execSQL("alter table Book add column category_id integer");
            default:
        }
    }
}
