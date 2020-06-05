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
 * register
 */
public class RegisterActivity extends Activity implements View.OnClickListener {

    private EditText edit_username, edit_password, edit_password2, edit_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_reg);
        edit_username = findViewById(R.id.edit_username);
        edit_password = findViewById(R.id.edit_password);
        edit_password2 = findViewById(R.id.edit_password2);
        edit_email = findViewById(R.id.edit_email);
        findViewById(R.id.tv_login).setOnClickListener(this);
        findViewById(R.id.tv_reg).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                String username = edit_username.getText().toString();
                String password = edit_password.getText().toString();
                String password2 = edit_password2.getText().toString();
                String email = edit_email.getText().toString();
                if (!TextUtils.isEmpty(username)) {
                    if (!TextUtils.isEmpty(password)) {
                        if (!TextUtils.isEmpty(password2) && password.equals(password2)) {
                            if(!TextUtils.isEmpty(email)) {
                                User user = new User();
                                user.setPassword(password);
                                user.setUsername(username);
                                user.setEmail(email);
                                if (AppDBHelp.getInstance(this).findUser(username, password) == null) {
                                    AppDBHelp.getInstance(this).saveUser(user);
//                                    SPHelper.getInstance(this).saveUserId(AppDBHelp.getInstance(this).findUser(username, password));
                                    Toast.makeText(this, "Successful, pleas log in", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(this, LoginActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(this, "This Account has exists", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(this, "please enter your email", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "Password do not matech", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "must enter password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "must enter account", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_reg:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

}