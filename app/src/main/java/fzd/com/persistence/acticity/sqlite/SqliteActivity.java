package fzd.com.persistence.acticity.sqlite;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

    @Override
    protected void initViews() {
        setContentView(R.layout.acitivity_sqlite);
        Button create_database = (Button) findViewById(R.id.create_database);
        myDatabaseHelper = new MyDatabaseHelper(this, "BookStrore.db", null, 1);
        create_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabaseHelper.getWritableDatabase();
            }
        });
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
