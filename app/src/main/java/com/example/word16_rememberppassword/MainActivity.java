package com.example.word16_rememberppassword;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username;//用户名
    private EditText password;//密码
    private CheckBox checkBox;//记住密码选项
    private Button button;//登录按钮
    private SharedPreferences sp;//获取
    private SharedPreferences.Editor editor;//添加

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText)findViewById(R.id.account);
        password=(EditText)findViewById(R.id.password);
        checkBox=(CheckBox)findViewById(R.id.checkbox);
        button=(Button) findViewById(R.id.login);
        sp= PreferenceManager.getDefaultSharedPreferences(this);//获取SharedPreferences的方法之一
        boolean b=sp.getBoolean("remember_password",false);
        if(b){
            String acount=sp.getString("acount","");
            String pass=sp.getString("pass","");
            username.setText(acount);
            password.setText(pass);
            checkBox.setChecked(true);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin")&&password.getText().toString().equals("123456")) {
                    editor=sp.edit();
                    if(checkBox.isChecked()){
                        editor.putString("acount",username.getText().toString());
                        editor.putString("pass",password.getText().toString());
                        editor.putBoolean("remember_password",true);
                    }
                    else{
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent=new Intent(MainActivity.this,Welcome.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
