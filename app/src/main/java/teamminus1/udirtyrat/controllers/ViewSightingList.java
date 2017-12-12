package teamminus1.udirtyrat.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import teamminus1.udirtyrat.R;
import teamminus1.udirtyrat.models.Model;
import teamminus1.udirtyrat.models.RatSighting;

/**
 * Activity controller for viewing the list of all the rat sightings within the database
 */

public class ViewSightingList extends AppCompatActivity {
    private final SightingListRecyclerAdapter adapter =
            new SightingListRecyclerAdapter(Model.Instance().getFilteredSightings());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sighting_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView recyclerViewSightings = findViewById(R.id.recycler_view_sighting_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewSightings.setLayoutManager(linearLayoutManager);
        recyclerViewSightings.setAdapter(adapter);
    }


    public class SightingListRecyclerAdapter extends
            RecyclerView.Adapter<SightingListRecyclerAdapter.SightingViewHolder> {
        private final List<RatSighting> sightings;

        /**
         * Class constructor
         * @param l sightings list
         */
        public SightingListRecyclerAdapter(List<RatSighting> l) {
            this.sightings = new ArrayList<>(l);
        }

        @Override
        public SightingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.ratsightingviewlayout, parent, false);
            return new SightingViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final SightingViewHolder viewHolder, int position) {
            Log.d("First index", sightings.get(0).toString());
            viewHolder.ratsighting = sightings.get(position);
            viewHolder.text1.setText(Integer.toString(viewHolder.ratsighting.getUniqueKey())
                    + "   ");
            viewHolder.text2.setText(viewHolder.ratsighting.getCreatedDateAsString());

            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), SightingDetailsView.class);
                    intent.putExtra(SightingDetailsView.ARG_UNIQUE_KEY,
                            viewHolder.ratsighting.getUniqueKey());
                    v.getContext().startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return sightings.size();
        }

        public class SightingViewHolder extends RecyclerView.ViewHolder {
            final View view;
            RatSighting ratsighting;
            final TextView text1;
            final TextView text2;

            /**
             * Contains needed information for rat sighting
             * @param v view to set the text to
             */
            public SightingViewHolder(View v) {
                super(v);
                view = v;
                text1 = v.findViewById(R.id.textView1);
                text2 = v.findViewById(R.id.textView2);
            }
        }
    }
}
