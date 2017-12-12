package teamminus1.udirtyrat.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;

import teamminus1.udirtyrat.R;
import teamminus1.udirtyrat.models.Admin;
import teamminus1.udirtyrat.models.IUser;
import teamminus1.udirtyrat.models.Model;
import teamminus1.udirtyrat.models.RatRegistry;
import teamminus1.udirtyrat.models.RatSighting;

/**
 * Activity controller for home page
 */

public class Home extends AppCompatActivity implements View.OnClickListener{

    Button logout_button;
    TextView currentUserType;
    Button view_button;
    Button add_button;
    CSVReader reader;
    Button view_in_map_button;
    Button view_graph_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        currentUserType = (TextView) findViewById(R.id.current_user_type);
        IUser currentUser = Model.Instance().getCurrentUser();
        if (currentUser instanceof Admin) {
            currentUserType.setText("Admin");
        } else {
            currentUserType.setText("User");
        }

        // this is for the GIF animation --------------------------------------------------
        ImageView imageView = (ImageView) findViewById(R.id.cheese);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(R.raw.cheese).into(imageViewTarget);
        //-----------------------------------------------------------------------------

        logout_button = (Button) findViewById(R.id.logout_button);
        logout_button.setOnClickListener(this);

        view_button = (Button) findViewById(R.id.buttonShowSightings);
        view_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Home Activity", "Pressed the load button");
                Intent intent = new Intent(Home.this, FilterActivity.class);
                intent.putExtra(FilterActivity.FILTER_ACTIVITY_NEXT_SCREEN, FilterActivity.GO_TO_LIST);
                // reader = CSVReader.Instance(getResources());
                startActivity(intent);
            }
        });

        add_button = (Button) findViewById(R.id.buttonAddSightings);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Home Activity", "Pressed the New sighting button");
                Intent intent = new Intent(Home.this, EnterNewSighting.class);
                startActivity(intent);
            }
        });

        view_in_map_button = (Button) findViewById(R.id.view_in_map);
        view_in_map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Home Activity", "Pressed the view in map button");
                Intent intent = new Intent(Home.this, FilterActivity.class);
                intent.putExtra(FilterActivity.FILTER_ACTIVITY_NEXT_SCREEN, FilterActivity.GO_TO_MAP);
                startActivity(intent);
            }
        });

        view_graph_button = (Button) findViewById(R.id.view_graph);
        view_graph_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Home Activity", "Pressed the view in map button");
                Intent intent = new Intent(Home.this, FilterActivity.class);

                intent.putExtra(FilterActivity.FILTER_ACTIVITY_NEXT_SCREEN, FilterActivity.GO_TO_GRAPH);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.logout_button) {
            if (Model.Instance().Logout()) {
                Intent logout_intent = new Intent(Home.this, Login.class);
                startActivity(logout_intent);
                finish();
            }
        }
    }


}