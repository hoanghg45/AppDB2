package com.example.appdb2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdb2.Model.People;
import com.example.appdb2.Others.DBManager;

public class InsertActivity extends AppCompatActivity {
    public EditText edtName,edtPhone,edtAdd;
    public Spinner spnCon;
    public RadioButton rbMale,rbFemale;
    public Button btnIs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        edtAdd = findViewById(R.id.edtAdd);
        edtPhone = findViewById(R.id.edtPhone);
        edtName = findViewById(R.id.edtName);
        spnCon = findViewById(R.id.spnCon);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        btnIs = findViewById(R.id.btnIs);
        DBManager dbManager = new DBManager(this);

        btnIs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sex ="";
                if(rbMale.isChecked())
                {
                    sex = "Male";
                }
                else
                    sex = "FeMale";
                String contract = spnCon.getSelectedItem().toString();


                People people = new People(0,edtName.getText().toString(),edtAdd.getText().toString(),sex,contract,Integer.parseInt(edtPhone.getText().toString()));
                dbManager.insertPeople(people);
                Toast.makeText(getApplicationContext(), "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(InsertActivity.this,MainActivity.class));
            }
        });
    }
}