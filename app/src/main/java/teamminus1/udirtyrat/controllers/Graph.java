package teamminus1.udirtyrat.controllers;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import teamminus1.udirtyrat.R;
import teamminus1.udirtyrat.models.Model;
import teamminus1.udirtyrat.models.RatSighting;

/**
 * Class for controlling the graph to display rat sightings
 */
public class Graph extends AppCompatActivity {

    private static final int RED = 64;
    private static final int GREEN = 89;
    private static final int BLUE = 128;
    private static final int DURATION = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);


//HorizontalBarChart barChart = (HorizontalBarChart) findViewById(R.id.barchart);
// vertical by default

        BarChart barChart = findViewById(R.id.barchart); // vertical by default

        //RatRegistry instance = RatRegistry.Instance();
        //Model.Instance().getFilteredSightings();
        List<RatSighting> thisRatList = Model.Instance().getFilteredSightings();

//        ArrayList<String> labels = new ArrayList<>();
//        labels.add("2016");
//        labels.add("2015");
//        labels.add("2014");
//        labels.add("2013");
//        labels.add("2012");


        ArrayList<BarEntry> entries1 = new ArrayList<>();
//
//
//        entries1.add(new BarEntry(2, 1));
//        entries1.add(new BarEntry(5, 2));
//        entries1.add(new BarEntry(20, 3));
//        entries1.add(new BarEntry(15, 4));
//        entries1.add(new BarEntry(19, 5));
//
//        ArrayList<BarEntry> entries2 = new ArrayList<>();
//        entries2.add(new BarEntry(5, 1));
//        entries2.add(new BarEntry(9, 2));
//        entries2.add(new BarEntry(11, 3));
//        entries2.add(new BarEntry(8, 4));
//        entries2.add(new BarEntry(26, 5));




        Map<String, Integer> combo = new HashMap<>();
        combo = rat_loop(combo, thisRatList);



        Set<String> unsortedDates = combo.keySet();
        ArrayList<String> unsortedList = new ArrayList<>(unsortedDates);
        unsortedList = collection_sort(unsortedList);

        for(int i = 0; i < unsortedList.size(); i++)
        {
            String key = unsortedList.get(i);
            entries1.add(new BarEntry(combo.get(key), i));
        }

        // creating dataset for Bar Group1
        BarDataSet barDataSet1 = new BarDataSet(entries1, "Bar Group 1");

        barDataSet1.setColors(new int[]{Color.rgb(RED, GREEN, BLUE)});

        // combined all dataset into array list
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);

        // v2.2.4 accepts labels but app crashes
        BarData data = new BarData(unsortedList, dataSets);

// BarData data = new BarData(dataSets);
// compiles with v3.0.2' -FIND WAY TO PUT LABELS WITHOUT BREAKING
        setup_chart(barChart, data);
    }
    public Map<String, Integer> rat_loop(Map<String, Integer> combo, List<RatSighting> thisRatList) {
        //Date date; // your date
        Calendar cal = Calendar.getInstance();
        int month;
        int day;
        int year;
        for ( RatSighting r : thisRatList ) { // THIS TAKES ALL RAT INFO FIND WAY TO SORT

            cal.setTime(r.getCreatedDate());
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH) + 1; // January is month 0 so add 1
            day = cal.get(Calendar.DAY_OF_MONTH);
            String cur = month + "/" + year;
            if (!(combo.containsKey(cur)))
            {
                combo.put(cur, 0);
                //System.out.println(combo);
            }
            else
            {
                combo.put(cur, combo.get(cur) + 1);
                //System.out.println(combo);
            }
// entries1.add(new BarEntry(month, day)); // ( x, y )
        }
        return combo;
    }


    public void setup_chart(BarChart barChart, BarData data) {
        barChart.setData(data); // set the data and list of labels into chart
        barChart.animateY(DURATION);
        barChart.setDescription(null);
        barChart.setNoDataText("No Sightings");
        barChart.getLegend().setEnabled(false);
    }
    public ArrayList<String> collection_sort(ArrayList<String> unsortedList) {
        Collections.sort(unsortedList, new Comparator<String>() {
            final DateFormat f = new SimpleDateFormat("MM/yyyy", Locale.US);
            @Override
            public int compare(String o1, String o2) {
                try {
                    return f.parse(o1).compareTo(f.parse(o2));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        return unsortedList;
    }
}

