package com.nerdlabs.yogaarchive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText rUserNameEt, rPassEt, rConfirmPassEt;
    private Button rRegBtn;
    private CheckBox agreeToTermsCheck;
    private AppDatabase mDb;

    String confirmPassword, name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rUserNameEt = findViewById(R.id.r_un_et);
        rPassEt = findViewById(R.id.r_pass_et);
        rConfirmPassEt = findViewById(R.id.r_cn_pwd_et);
        rRegBtn = findViewById(R.id.r_reg_btn);
        agreeToTermsCheck = findViewById(R.id.check_terms_and_condtions);

        //Db Instance initialisation
        mDb = AppDatabase.getInstance(getApplicationContext());

        rPassEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length() < 6){
                    rPassEt.setError(" Password must be greater than 6 characters");
                    //rRegBtn.setClickable(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        rRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = rUserNameEt.getText().toString().trim();
                password = rPassEt.getText().toString().trim();
                confirmPassword = rConfirmPassEt.getText().toString().trim();

                //Input Validation
                if(TextUtils.isEmpty(name)){
                    rUserNameEt.setError("user name is required");
                }

                else if(TextUtils.isEmpty(password)){
                    rPassEt.setError("password is required");
                }
                else if(TextUtils.isEmpty(confirmPassword)){
                    rConfirmPassEt.setError("confirm your password");
                }
                else if(!password.equals(confirmPassword)){
                    rConfirmPassEt.setError("pass dont match");

                }
                else if(!agreeToTermsCheck.isChecked()){
                    agreeToTermsCheck.setError("please agree to the terms to sign in");

                }
                else {
                    User mUser = new User(name, password);

                    //inserting in room database
                    mDb.UserDao().insertUser(mUser);
                    rRegBtn.setEnabled(false);

                    //saving login info in sahred prefernces for session persistence
                    PreferenceUtils.saveUsername(name, getApplicationContext());

                    //starting a new MainActivity Intent
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();

                }
            }
        });

    }
}