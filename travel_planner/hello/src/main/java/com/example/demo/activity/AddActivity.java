package com.example.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.model.Note;
import com.example.demo.util.AppDBHelp;
import com.example.demo.util.SPHelper;
import com.example.demo.util.ToolUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;

/**
 *
 */
public class AddActivity extends Activity {

    private TextView tv_datetime;
    private EditText editText;
    private ImageView img_delete;
    private int model;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_add);
        editText = findViewById(R.id.edittext);
        tv_datetime = findViewById(R.id.tv_datetime);
        img_delete = findViewById(R.id.img_delete);
        tv_datetime.setText(ToolUtils.getCurDateTime());
        findViewById(R.id.layout_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDBHelp.getInstance(AddActivity.this).deleteNote(note.getId());
                Toast.makeText(AddActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();
                //
                EventBus.getDefault().post("homeRefresh");
                finish();
            }
        });
        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editText.getText().toString())) {
                    if (note == null) {
                        note = new Note();
                        note.setUid(SPHelper.getInstance(AddActivity.this).getUserId());
                    }
                    note.setContent(editText.getText().toString());
                    note.setDate(ToolUtils.getCurDate());
                    if (model == 0) {
                        AppDBHelp.getInstance(AddActivity.this).saveNote(note);
                        Toast.makeText(AddActivity.this, "Add success", Toast.LENGTH_SHORT).show();
                    } else {
                        AppDBHelp.getInstance(AddActivity.this).updateNote(note);
                        Toast.makeText(AddActivity.this, "Change Success", Toast.LENGTH_SHORT).show();
                    }
                    //
                    EventBus.getDefault().post("homeRefresh");
                    finish();
                } else {
                    Toast.makeText(AddActivity.this, "Please enter your note", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //
        Intent intent = getIntent();
        Serializable s = intent.getSerializableExtra("note");
        if (s != null) {
            model = 1;
            note = (Note) s;
            ((TextView) findViewById(R.id.tv_title)).setText("Change");
            editText.setText(note.getContent());
            img_delete.setVisibility(View.VISIBLE);
        }
    }

}
