package com.nerdlabs.yogaarchive;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText lUsernameEt, lPwdEt;
    Button lLoginBtn;
    TextView createAccountText;
    String htmlTextForCreateAccount;
    String userID;
    private AppDatabase mDb;
    List<User>userList;

    @Override
    protected void onResume() {
        super.onResume();
        //getting all users from Room Database for authentication
        userList = mDb.UserDao().getAllUsers();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lUsernameEt = findViewById(R.id.l_username_et);
        lPwdEt = findViewById(R.id.l_pwd_et);
        lLoginBtn = findViewById(R.id.l_login_btn);
        createAccountText = findViewById(R.id.tv_create_account);

        //initialising db instance
        mDb = AppDatabase.getInstance(getApplicationContext());

        if(PreferenceUtils.getUsername(this) != null){
            if (!PreferenceUtils.getUsername(this).equals("")){
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }

        }

        htmlTextForCreateAccount = "<u>Don't have an account ? create here</u>";
        createAccountText.setText(Html.fromHtml(htmlTextForCreateAccount));

        lLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = lUsernameEt.getText().toString().trim();
                String password = lPwdEt.getText().toString().trim();

                //Input Validation
                if (TextUtils.isEmpty(userName)) {
                    lUsernameEt.setError("username is required");
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    lPwdEt.setError("password is required");
                    return;
                } else if (password.length() < 6) {
                    lPwdEt.setError("password must be greater than 6");
                    return;
                } else {
                    int serachFlag = 0;
                    User foundUser = null;

                    for (User user: userList){
                        if (TextUtils.equals(userName, user.getUsername())){
                            serachFlag = 1;
                            foundUser = user;
                        }
                    }

                    if (serachFlag == 1){
                        if (TextUtils.equals(password, foundUser.getPassword())){
                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            PreferenceUtils.saveUsername(userName, getApplicationContext());
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        createAccountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccountText.setEnabled(false);
                //starting new RegisterActivity intent
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }
}