package com.example.vamsi.task;

import android.app.DatePickerDialog;
import android.app.SearchManager;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.vamsi.task.Adapter.HomepageAdapter;
import com.example.vamsi.task.Class.mySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HomepageAdapter homepageAdapter;
    String keyword;
    BottomSheetBehavior bottomSheetBehavior;
    String dateSelect;
    Button clickedBtn = null;
    String url;
    StringBuffer filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        url = "https://api.github.com/search/repositories?q=topic:";

//this is main layout


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_home);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                filter=new StringBuffer();
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                View parentview = getLayoutInflater().inflate(R.layout.bottomsheet, null);
                bottomSheetDialog.setContentView(parentview);
                final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) parentview.getParent());
                bottomSheetBehavior.setPeekHeight(
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 550, getResources().getDisplayMetrics()));
                bottomSheetDialog.show();


                ImageView imageView = (ImageView) parentview.findViewById(R.id.cancel);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });

                final Button b = (Button) parentview.findViewById(R.id.star);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnClicked(b);
                        filter.append("&sort=star");
                    }
                });
                final Button b1 = (Button) parentview.findViewById(R.id.forks);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnClicked(b1);
                        filter.append("&sort=forks");
                    }
                });
                final Button b2 = (Button) parentview.findViewById(R.id.updated);
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnClicked(b2);
                        filter.append("&sort=updated");
                    }
                });

                final Button desc = (Button) parentview.findViewById(R.id.desc);
                desc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnClicked(desc);
                        filter.append("&order=desc");
                    }
                });
                final Button asc = (Button) parentview.findViewById(R.id.asc);
                asc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnClicked(asc);
                        filter.append("&order=asc");
                    }
                });


                Calendar calendar = Calendar.getInstance();
                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                final int month = calendar.get(Calendar.MONTH);


                final TextView from = (TextView) parentview.findViewById(R.id.from);
                final int year = calendar.get(Calendar.YEAR);
                from.setText("" + year + "-" + (month + 1) + "-" + day);
                LinearLayout fromdate = (LinearLayout) parentview.findViewById(R.id.fromdate);
                fromdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                from.setText("" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                if (dayOfMonth < 10)
                                    dateSelect = "" + year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth;
                                else
                                    dateSelect = "" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                Log.i("date", dateSelect);
                            }
                        }, year, month, day);
                        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                        datePickerDialog.show();
                    }
                });

                final TextView to = (TextView) parentview.findViewById(R.id.to);
                from.setText("" + year + "-" + (month + 1) + "-" + day);
                LinearLayout todate = (LinearLayout) parentview.findViewById(R.id.todate);
                todate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                to.setText("" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                if (dayOfMonth < 10)
                                    dateSelect = "" + year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth;
                                else
                                    dateSelect = "" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                Log.i("date", dateSelect);
                            }
                        }, year, month, day);
                        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                        datePickerDialog.show();
                    }
                });

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        homepageAdapter = new HomepageAdapter(MainActivity.this, new JSONArray());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(homepageAdapter);
        searchRepo("android");
    }

    void btnClicked(Button v) {
        if (clickedBtn != null) {
            clickedBtn.setBackgroundResource(R.drawable.background1);
        }
        v.setBackgroundResource(R.drawable.background);
        clickedBtn = v;
    }


    void searchRepo(String query) {


        StringBuffer q=new StringBuffer();
        q.append(query);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url +q.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i("Searched repos", String.valueOf(response));
                    JSONArray items = response.getJSONArray("items");
                    homepageAdapter.dataChanged(items);
                    homepageAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu_items, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                keyword = query.toString();
                Log.i("Text submited", keyword);
                searchRepo(keyword);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
