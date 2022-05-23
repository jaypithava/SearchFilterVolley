package com.example.searchfiltervolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView courseRV;
    private CourseAdapter adapter;
    private ArrayList<CourseModal> courseModalArrayList;
    String url = "https://api.androidhive.info/json/contacts.json";
    private ProgressBar progressBar;
    TextView tvHeading;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        courseRV = findViewById(R.id.idRVCourses);
        progressBar = findViewById(R.id.idPB);
        tvHeading = findViewById(R.id.tvHeading);
        searchView =findViewById(R.id.searchView);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                ArrayList<CourseModal> filterShapes = new ArrayList<>();
//                for (CourseModal model : courseModalArrayList) {
//                    if (model.getName().toLowerCase().contains(s.toLowerCase())||
//                            model.getPhone().toLowerCase().contains(s.toLowerCase())) {
//                        filterShapes.add(model);
//                    }
//                }
//                adapter = new CourseAdapter(filterShapes, MainActivity.this);
//                courseRV.setAdapter(adapter);
//                return false;
//            }
//        });


        courseModalArrayList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                showJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        buildRecyclerView();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<CourseModal> filterShapes = new ArrayList<>();
                for (CourseModal model : courseModalArrayList) {
                    if (model.getName().toLowerCase().contains(s.toLowerCase())||
                            model.getPhone().toLowerCase().contains(s.toLowerCase())) {
                        filterShapes.add(model);
                    }
                }
                adapter = new CourseAdapter(filterShapes, MainActivity.this);
                courseRV.setAdapter(adapter);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void showJson(String response) {
        Log.d("jay", response);
        try {
            JSONArray jsonArray = new JSONArray(response);
            progressBar.setVisibility(View.GONE);
            courseRV.setVisibility(View.VISIBLE);
            for (int i = 0; i < response.length(); i++) {
                JSONObject responseObj = jsonArray.getJSONObject(i);
                String name = responseObj.getString("name");
                String image = responseObj.getString("image");
                String phone = responseObj.getString("phone");
                courseModalArrayList.add(new CourseModal(name, image, phone));
                buildRecyclerView();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void buildRecyclerView() {
        adapter = new CourseAdapter(courseModalArrayList, MainActivity.this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        courseRV.setHasFixedSize(true);
        courseRV.setLayoutManager(manager);
        courseRV.setAdapter(adapter);
    }
}