package com.example.alias.datasave;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Textedit extends AppCompatActivity {


    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textedit);

       Button save = findViewById(R.id.save);
       Button load=findViewById(R.id.load);
       Button clear=findViewById(R.id.clear);
        ed=findViewById(R.id.editText);

       save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               try (FileOutputStream fileOutputStream = openFileOutput("a.txt", MODE_PRIVATE)) {
                   String str = ed.getText().toString();
                   fileOutputStream.write(str.getBytes());
                   Log.i("TAG", "Successfully saved file.");
               } catch (IOException ex) {
                   Log.e("TAG", "Fail to save file.");
               }
           }
       });

       load.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               try (FileInputStream fileInputStream = openFileInput("a.txt")) {
                   byte[] contents = new byte[fileInputStream.available()];
                   fileInputStream.read(contents);
                   ed.setText(new String(contents));
               } catch (IOException ex) {
                   Log.e("TAG", "Fail to read file.");
               }
           }
       });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed.setText("");
            }
        });

    }



}
