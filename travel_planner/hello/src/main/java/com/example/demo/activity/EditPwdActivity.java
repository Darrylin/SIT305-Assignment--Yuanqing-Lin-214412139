package com.example.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.util.AppDBHelp;
import com.example.demo.util.SPHelper;

/**
 *
 */
public class EditPwdActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_editpwd);
        final EditText edit_password = findViewById(R.id.edit_password);
        final EditText edit_password1 = findViewById(R.id.edit_password1);
        final EditText edit_password2 = findViewById(R.id.edit_password2);
        findViewById(R.id.layout_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.tv_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = SPHelper.getInstance(EditPwdActivity.this).getUserEmail();
                if (!TextUtils.isEmpty(edit_password.getText().toString()) && edit_password.getText().toString().equals(userEmail)) {
                    if (!TextUtils.isEmpty(edit_password1.getText().toString()) && !TextUtils.isEmpty(edit_password2.getText().toString()) && edit_password1.getText().toString().equals(edit_password2.getText().toString())) {
                        AppDBHelp.getInstance(EditPwdActivity.this).updateUser(SPHelper.getInstance(EditPwdActivity.this).getUserId(), edit_password1.getText().toString());
                        Toast.makeText(EditPwdActivity.this, "Change successful", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(EditPwdActivity.this, "Password not matched", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditPwdActivity.this, "Wrong email address", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
