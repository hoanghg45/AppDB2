package com.example.appdb2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdb2.Adapter.PeopleAdapter;
import com.example.appdb2.Model.People;
import com.example.appdb2.Others.DBManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public RecyclerView rclPeopleList;
    public PeopleAdapter peopleAdapter;
    public Button btnIs;
    ArrayList<People> alPeople = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rclPeopleList = findViewById(R.id.rclPeople);
        btnIs = findViewById(R.id.btnIs);
        DBManager dbManager = new DBManager(this);
        alPeople = new ArrayList<>(dbManager.getAllPeople());

        rclPeopleList.setHasFixedSize(true);

        peopleAdapter = new PeopleAdapter(alPeople,MainActivity.this, new PeopleAdapter.PeopleAdapterListener() {
            @Override
            public void click(View v, int position) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();            }
        });
        Intent intent = new Intent(MainActivity.this,EditActivity.class);
        peopleAdapter = new PeopleAdapter(alPeople, MainActivity.this, new PeopleAdapter.PeopleAdapterListener() {
            @Override
            public void click(View v, int position) {


                int id = alPeople.get(position).getID();
                People people = dbManager.getPeopleByID(id);
                intent.putExtra("id",people.getID());
                startActivity(intent);
            }
        });
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rclPeopleList.setLayoutManager(linearLayoutManager);
        rclPeopleList.setAdapter(peopleAdapter);


        btnIs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,InsertActivity.class);
                startActivity( intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.menuExit:
                AlertDialog.Builder bu = new AlertDialog.Builder(MainActivity.this);
                bu.setTitle("Thông báo");
                bu.setMessage("Bạn có muốn thoát chương trình?");
                bu.setPositiveButton("Baiii", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                bu.setNegativeButton("Khum", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog al = bu.create();
                al.show();
                break;

            default:break;
        }


        return super.onOptionsItemSelected(item);


    }
}