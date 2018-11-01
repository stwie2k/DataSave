package com.example.alias.datasave;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText password1;
    EditText password2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);

         password1=findViewById(R.id.editText1);
         password2=findViewById(R.id.editText2);

        //获得保存在SharedPredPreferences中的用户名和密码
        String p1 = sp.getString("p1", "");
        String p2 = sp.getString("p2", "");

        //在用户名和密码的输入框中显示用户名和密码
        password1.setText(p1);



        if(!p2.equals(""))
        {
            password1.setVisibility(View.INVISIBLE);
            password2.setHint("Password");
            password2.setText("");
        }


        Button ok = findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();

                String p1 = password1.getText().toString();
                String p2 = password2.getText().toString();

                if (p1.equals("")||p2.equals("")){
                    Toast.makeText(MainActivity.this, "Password cannot be empty.", Toast.LENGTH_LONG).show();
                    return ;
                }
                if (!p1.equals(p2)) {
                    Toast.makeText(MainActivity.this, "Password Mismatch", Toast.LENGTH_LONG).show();
                    return ;
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, Textedit.class);
                    MainActivity.this.startActivity(intent);
                }

                //以键值对的显示将用户名和密码保存到sp中
                ed.putString("p1", p1);
                ed.putString("p2", p2);
                ed.apply();

            }
        });
        Button clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                password1.setText("");
                password2.setText("");



             }

        });


    }
}
