
package teamminus1.udirtyrat.controllers;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;



import java.util.Random;

import teamminus1.udirtyrat.R;
import teamminus1.udirtyrat.models.IUser;
import teamminus1.udirtyrat.models.PermissionUtils;
import teamminus1.udirtyrat.models.UserRegistry;


public class PasswordRecovery extends AppCompatActivity {

    final int SEND_SMS_PERMISSION_REQUEST_CODE = 111;
    private  Button submitEmail_button;
    private EditText userNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        submitEmail_button = (Button) findViewById(R.id.buttonSubmitEmail);

        userNameTextView = (EditText) findViewById(R.id.editEnterUserName);

        submitEmail_button.setEnabled(true);

        // this is for the GIF animation --------------------------------------------------
        ImageView imageView = (ImageView) findViewById(R.id.imgRat);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(R.raw.animated_rat).into(imageViewTarget);
        //-----------------------------------------------------------------------------

        if(checkPermission(Manifest.permission.SEND_SMS)) {
            submitEmail_button.setEnabled(true);
        }else {
            ActivityCompat.requestPermissions(PasswordRecovery.this, new String[] {Manifest.permission.SEND_SMS},
                    SEND_SMS_PERMISSION_REQUEST_CODE);
        }


        submitEmail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // verify userName being submitted
                boolean userFound = false;
                String thisUser;
                thisUser = userNameTextView.getText().toString();

                userFound = UserRegistry.Instance().containsUser(thisUser);
                if ( ! userFound ){

                    AlertDialog.Builder builder = new AlertDialog.Builder( PasswordRecovery.this);

                    builder.setTitle("Sorry");
                    builder.setMessage("We did not find this username registered");

                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // dismiss activity
                            finish();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.setCancelable(false);
                    alert.setCanceledOnTouchOutside(false);
                    alert.show();
                    try{ Looper.loop(); } //--- dialog won't stay up waiting for input without this
                    catch(RuntimeException e){}
                }



                if (PermissionUtils.checkPermission(PasswordRecovery.this, Manifest.permission.SEND_SMS)) {
                    //Do your work
                    sendSMS();
                } else {
                    PermissionUtils.requestPermissions(PasswordRecovery.this, "You need to provide permission for SMS service", SEND_SMS_PERMISSION_REQUEST_CODE, new String[]{Manifest.permission.SEND_SMS});//ask for permission
                }


                startActivity(new Intent(PasswordRecovery.this, Login.class));
                finish();
            }
        });
    }

    private boolean checkPermission(String permission) {
        int checkPermission = ContextCompat.checkSelfPermission(this, permission);
        return (checkPermission == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case SEND_SMS_PERMISSION_REQUEST_CODE: {

                Toast.makeText(PasswordRecovery.this, "Token has been sent", Toast.LENGTH_SHORT).show();

                if(grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {


                    sendSMS();
                }
                return;
            }
        }
    }



    private void sendSMS(){


        try {


            String thisUser;
            int token;
            String tokenStr;
            Random rand = new Random();
            token = (int) rand.nextInt(10000);
            tokenStr = String.valueOf(token);

            thisUser = userNameTextView.getText().toString();

            String message = thisUser + ", your token for DirtyRat is " + tokenStr;
            String phoneNo = "5554";

            IUser user = UserRegistry.Instance().getUser(thisUser);
            user.setToken(tokenStr);


            if (!TextUtils.isEmpty(message) && !TextUtils.isEmpty(phoneNo)) {

                if (checkPermission(Manifest.permission.SEND_SMS)) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                } else {
                    Toast.makeText(PasswordRecovery.this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception e) {
            Toast.makeText(PasswordRecovery.this, "No email clients installed.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }
}

