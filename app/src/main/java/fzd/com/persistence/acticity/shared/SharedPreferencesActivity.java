package fzd.com.persistence.acticity.shared;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import fzd.com.persistence.R;
import fzd.com.persistence.base.BaseActivity;

/**
 * Created by Administrator on 2016/5/17.
 * 1.SharedPreferences 是使用键值对的方式来存储数据的
 * 2.SharedPreferences 文件都是存放在/data/data/<packagename>/shared_prefs/目录下
 */
public class SharedPreferencesActivity extends BaseActivity{
    @Override
    protected void loadData() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_shared);
        Button save = (Button) findViewById(R.id.save_data);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        Button restore = (Button) findViewById(R.id.restore_data);
        restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restore();
            }
        });
    }

    /**
     * haredPreferences 对象中提供了一系列的get方法用于对存储的数据进行读取，
     * 每种get方法都对应了SharedPreferences.Editor 中的一种 put 方法，
     * 比如读取一个布尔型数据就使用 getBoolean()方法，读取一个字符串就使用 getString()方法
     * 这些 get 方法都接收两个参数，第一个参数是键，传入存储数据时使用的键就可以得到相应的值了，
     * 第二个参数是默认值，即表示当传入的键找不到对应的值时，会以什么样的默认值进行返回。
     */
    private void restore() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String pwd = sharedPreferences.getString("pwd", "");
        boolean isLogin = sharedPreferences.getBoolean("isLogin", false);
        Log.d("MainActivity", "name is " + name);
        Log.d("MainActivity", "age is " + pwd);
        Log.d("MainActivity", "married is " + isLogin);
    }

    /**
     * 1. 调用 SharedPreferences 对象的 edit()方法来获取一个 SharedPreferences.Editor 对象。
     * 2. 向SharedPreferences.Editor 对象中添加数据，比如添加一个布尔型数据就使用
     * putBoolean 方法，添加一个字符串则使用 putString()方法，以此类推。
     * 3. 调用 commit()方法将添加的数据提交，从而完成数据存储操作。
     */
    private void save() {
        /**
         * 获取SharedPreferences的三种方式：
         * 1. Context 类中的 getSharedPreferences()方法（自定义文件名）
         * 2. Activity 类中的 getPreferences()方法 （默认文件名为activity名称，即为AndroidManifest中activity的name）
         * 3. PreferenceManager 类中的 getDefaultSharedPreferences()方法
         */
        SharedPreferences.Editor editor = getSharedPreferences("shared", MODE_PRIVATE).edit();
//        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
//        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putString("name", "fzd");
        editor.putString("pwd", "123456");
        editor.putBoolean("isLogin", true);
        editor.commit();
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
