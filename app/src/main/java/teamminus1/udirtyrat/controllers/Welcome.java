package teamminus1.udirtyrat.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import teamminus1.udirtyrat.R;
import teamminus1.udirtyrat.models.RatRegistry;

/**
 * Activity controller for main welcome page.  This is the main activity that first launches
 */

public class Welcome extends AppCompatActivity {

    Button login_button;
    Button register_button;
    TextView recover_Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // this is for the GIF animation --------------------------------------------------
        ImageView imageView = (ImageView) findViewById(R.id.imgRatWelcome);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(R.raw.animated_rat).into(imageViewTarget);
        //-----------------------------------------------------------------------------

        login_button = (Button)findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this, Login.class));
                finish();
            }
        });
        //the 2nd way in implementing the Welcome class to View.OnClickListener
        //then override the method onClick() as below
        register_button = (Button) findViewById(R.id.register_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this, Register.class));
                finish();
            }
        });

        recover_Pass = (TextView)  findViewById(R.id.ForgotPassword_text);
        recover_Pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this, PasswordRecovery.class));
            }
        });




    }
}
