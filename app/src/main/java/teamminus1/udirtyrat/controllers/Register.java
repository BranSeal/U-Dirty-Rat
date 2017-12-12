package teamminus1.udirtyrat.controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import teamminus1.udirtyrat.R;
import teamminus1.udirtyrat.models.LoginResult;
import teamminus1.udirtyrat.models.Model;
import teamminus1.udirtyrat.models.RegistrationResult;

/**
 * Activity controller for registration page
 */

public class Register extends AppCompatActivity {
    private EditText etUserName;
    private EditText etPassword;
    private RadioButton rAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //EditText etName = findViewById(R.id.etName);
        etUserName = findViewById(R.id.etUserName);
        //EditText etAge = findViewById(R.id.etAge);
        etPassword = findViewById(R.id.etPassword);
        Button bRegister = findViewById(R.id.bRegister);
        //RadioButton rUser = findViewById(R.id.radioButtonUser);
        rAdmin = findViewById(R.id.radioButtonAdmin);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrationResult  registrationResult =
                        Model.Instance().RegisterUser(etUserName.getText().toString(),
                                etPassword.getText().toString(), rAdmin.isChecked());

                if (registrationResult.isSuccess()) {
                    Model.Instance().writeUsersToFile(getApplicationContext());

                    LoginResult loginResult = Model.Instance().
                            LoginUser(etUserName.getText().toString(),
                                    etPassword.getText().toString());
                    if (loginResult.isSuccess()) {
                        startActivity(new Intent(Register.this, Home.class));
                        finish();
                    } else {
                        Log.d("Login", "Login fail");
                        // Since we just registered the user so we should be able to log them in
                    }
                } else {
                    Log.d("Register", "Registration fail");
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Register.this);
                    builder1.setMessage(registrationResult.getDefaultMessage());
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                    etUserName.setText("");
                    etPassword.setText("");
                }
            }
        });
    }
}
