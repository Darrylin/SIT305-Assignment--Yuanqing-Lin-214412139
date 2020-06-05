package com.example.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.model.User;
import com.example.demo.util.AppDBHelp;
import com.example.demo.util.SPHelper;

/**
 * login
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText edit_username, edit_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        edit_username = findViewById(R.id.edit_username);
        edit_password = findViewById(R.id.edit_password);
        findViewById(R.id.tv_login).setOnClickListener(this);
        findViewById(R.id.tv_reg).setOnClickListener(this);

        if (SPHelper.getInstance(this).getUserId() != 0 && !TextUtils.isEmpty(SPHelper.getInstance(this).getUserEmail()) && SPHelper.getInstance(this).getRemeber() == 1) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                if (TextUtils.isEmpty(edit_username.getText().toString()) || TextUtils.isEmpty(edit_password.getText().toString())) {
                    Toast.makeText(this, "Account or password cannot be blank", Toast.LENGTH_SHORT).show();
                } else {
                    User user = AppDBHelp.getInstance(this).findUser(edit_username.getText().toString(), edit_password.getText().toString());
                    if (user == null) {
                        Toast.makeText(this, "wrong password or account", Toast.LENGTH_SHORT).show();
                    } else {
                        SPHelper.getInstance(this).setRemeber(1);
                        SPHelper.getInstance(this).saveUserId(user.getId());
                        SPHelper.getInstance(this).saveUserEmail(user.getEmail());
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    }
                }
                break;
            case R.id.tv_reg:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

}