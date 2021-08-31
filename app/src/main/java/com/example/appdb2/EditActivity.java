package com.example.appdb2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appdb2.Model.People;
import com.example.appdb2.Others.DBManager;

public class EditActivity extends AppCompatActivity {
    public EditText edtName,edtPhone,edtAdd;
    public Spinner spnCon;
    public RadioButton rbMale,rbFemale;
    public Button btnDel,btnEdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edtAdd = findViewById(R.id.edtAdd);
        edtPhone = findViewById(R.id.edtPhone);
        edtName = findViewById(R.id.edtName);
        spnCon = findViewById(R.id.spnCon);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        btnDel = findViewById(R.id.btnDel);
        btnEdt = findViewById(R.id.btnEdt);

        DBManager dbManager = new DBManager(this);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        People people =dbManager.getPeopleByID(id);

        edtAdd.setText(people.getAdd());
        edtName.setText(people.getName());
        edtPhone.setText(String.valueOf(people.getPhone()));
        if(people.getSex().equals("Male"))
            rbMale.setChecked(true);
        else rbFemale.setChecked(true);

        switch (people.getContract()){
            case "Chính thức": spnCon.setSelection(0);
                break;
            case "Part time": spnCon.setSelection(1);
                break;
            case "Thử việc": spnCon.setSelection(2);
                break;
            case "Thời vụ": spnCon.setSelection(3);
                break;
            default:
                break;
        }
    btnEdt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = edtName.getText().toString();
            String add = edtAdd.getText().toString();
            int phone = Integer.parseInt(edtPhone.getText().toString());
            String sex ="";
            if(rbMale.isSelected()){
                sex = "Male";
            }
            else sex = "FeMale";
            String con = spnCon.getSelectedItem().toString();
             People people = new People(id,name,add,sex,con,phone);

            dbManager.updatePeople(people);
            Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EditActivity.this,MainActivity.class));
        }
    });

    btnDel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dbManager.deletePeopleByID(id);
            Toast.makeText(getApplicationContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EditActivity.this,MainActivity.class));
        }
    });
    }

}