package teamminus1.udirtyrat.controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import teamminus1.udirtyrat.R;
import teamminus1.udirtyrat.models.LoginResult;
import teamminus1.udirtyrat.models.Model;

/**
 * Activity controller for login page.
 */

public class Login extends AppCompatActivity {

    private EditText login_input;
    private EditText password_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button cancel_button = findViewById(R.id.cancel_button);
        Button login_button = findViewById(R.id.login_button);
        login_input = findViewById(R.id.login_input);
        password_input = findViewById(R.id.password_input);
        TextView register_link = findViewById(R.id.tvregister_link);

        login_input.requestFocus();
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Welcome.class));
                finish();
            }
        });

        register_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = login_input.getText().toString();
                String password = password_input.getText().toString();

                LoginResult loginResult = Model.Instance().LoginUser(username, password);
                if (loginResult.isSuccess()) {
                    startActivity(new Intent(Login.this, Home.class));
                    finish();
                }
                else {
                    Log.d("Login", "Login fail");
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Login.this);
                    builder1.setMessage(loginResult.getDefaultMessage());
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
                    login_input.setText("");
                    password_input.setText("");
                }
            }
        });
    }
}
