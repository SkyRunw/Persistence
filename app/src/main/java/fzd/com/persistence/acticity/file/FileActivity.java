package fzd.com.persistence.acticity.file;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import fzd.com.persistence.R;
import fzd.com.persistence.base.BaseActivity;

/**
 * Created by Administrator on 2016/5/17.
 *
 */

/**
 * 1.两个对象：FileOutputStream out， BufferedWriter writer
 * 2.获取两个对象
 * 3.调用writer.write();
 */
public class FileActivity extends BaseActivity{
    private EditText edit;
    @Override
    protected void loadData() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_file);
        edit = (EditText) findViewById(R.id.edit);
    }

    private void save(String string) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(string);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        save(edit.getText().toString());
    }
}
