package teamminus1.udirtyrat.controllers;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import teamminus1.udirtyrat.R;
import teamminus1.udirtyrat.models.Model;
import teamminus1.udirtyrat.models.RatSightingFilter;

/**
 * Activity for filtering rat sightings
 */
public class FilterActivity extends AppCompatActivity {

    public static final String FILTER_ACTIVITY_NEXT_SCREEN = "next_screen";
    public static final String GO_TO_MAP = "map";
    public static final String GO_TO_LIST = "list";
    public static final String GO_TO_GRAPH = "graph";

    private static final int CURRENT_YEAR = 2017;

    private Date startDate;
    private Date endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(Calendar.YEAR, CURRENT_YEAR);
        myCalendar.set(Calendar.MONTH, 0);
        myCalendar.set(Calendar.DAY_OF_MONTH, 1);
        myCalendar.set(Calendar.HOUR, 1);
        myCalendar.set(Calendar.MINUTE, 0);
        myCalendar.set(Calendar.SECOND, 0);
        startDate = myCalendar.getTime();
        endDate = new Date();



        final TextView startTextView = findViewById(R.id.text_view_filter_start);
        final TextView endTextView = findViewById(R.id.text_view_filter_end);


        final DateFormat dateFormatter = new SimpleDateFormat(Model.DATE_STRING_FORMAT, Locale.US);
        startTextView.setText(dateFormatter.format(startDate));
        endTextView.setText(dateFormatter.format(endDate));

        final DatePickerDialog.OnDateSetListener startDatePicker =
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar myCalendar = Calendar.getInstance();
                Log.v("datepicker", "1");
                myCalendar.set(Calendar.YEAR, year);
                Log.v("datepicker", "2");
                myCalendar.set(Calendar.MONTH, month);
                Log.v("datepicker", "3");
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                Log.v("datepicker", "4");
                startDate = myCalendar.getTime();
                Log.v("datepicker", "last");
                startTextView.setText(dateFormatter.format(startDate));

            }
        };

        startTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(FilterActivity.this, startDatePicker,year,month,day ).show();
            }
        });

        final DatePickerDialog.OnDateSetListener endDatePicker =
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar myCalendar = Calendar.getInstance();
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                endDate = myCalendar.getTime();
                endTextView.setText(dateFormatter.format(endDate));

            }
        };

        endTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                if (calendar == null) {
                    return;
                }
                if (endDate == null) {
                    return;
                }
                calendar.setTime(endDate);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(FilterActivity.this, endDatePicker,year,month,day ).show();
            }
        });

        Button OK_Button = findViewById(R.id.button_filter_ok);
        OK_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Filter stuff
                RatSightingFilter filter = new RatSightingFilter();
                filter.setStartDate(startDate);
                filter.setEndDate(endDate);
                Model.Instance().filterSightings(filter);

                //Switch to next screen as appropriate
                String nextScreen = getIntent().getStringExtra(FILTER_ACTIVITY_NEXT_SCREEN);
                Intent intent;
                switch (nextScreen) {
                    case GO_TO_MAP:
                        intent = new Intent(FilterActivity.this, MapsActivity.class);
                        break;
                    case GO_TO_LIST:
                        intent = new Intent(FilterActivity.this, ViewSightingList.class);
                        break;
                    case GO_TO_GRAPH:
                        intent = new Intent(FilterActivity.this, Graph.class);
                        break;
                    default:
                        throw new IllegalArgumentException(nextScreen + " is not a screen.");
                }


                startActivity(intent);
                finish();
            }
        });
    }
}
