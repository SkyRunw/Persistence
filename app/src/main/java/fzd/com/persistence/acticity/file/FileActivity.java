package fzd.com.persistence.acticity.file;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import fzd.com.persistence.R;
import fzd.com.persistence.base.BaseActivity;

/**
 * Created by Administrator on 2016/5/17.
 * 1.默认存储到/data/data/<packagename>/files/目录下
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
        String inputText = load();
        if (!inputText.isEmpty()) {
            edit.setText(inputText);
            edit.setSelection(inputText.length());
            Toast.makeText(this, "Restoring succeeded",
                    Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 保存文件，向文件中写入数据
     * 1.两个对象：FileOutputStream out， BufferedWriter writer
     * 2.获取两个对象
     * 3.调用writer.write();
     */
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

    /**
     * 获取文件中数据
     * 1.两个对象：FileInputStream，BufferedReader
     * 2.获取两个对象
     * 3.读取数据
     * @return
     */
    public String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
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
