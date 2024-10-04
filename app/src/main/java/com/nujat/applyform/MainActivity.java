package com.nujat.applyform;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText, idEditText, emailEditText, phoneEditText, passEditText;
    private Spinner deptSpinner;
    private String name, id, email, phone, dept, password;
    private Button submit;


    private Pattern namePattern = Pattern.compile("^[a-zA-Z ]+$");
    private Pattern emailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private Pattern phonePattern = Pattern.compile("^\\d{11}$");
    private Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{6,}$");

    LinearLayout inputLayout, outputLayout;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        nameEditText = findViewById(R.id.name);
        idEditText = findViewById(R.id.sId);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.num);
        passEditText = findViewById(R.id.pass);
        deptSpinner = findViewById(R.id.spinner);
        submit = findViewById(R.id.submit);
        inputLayout = findViewById(R.id.inputLayout);
        outputLayout = findViewById(R.id.outputLayout);
        outputText = findViewById(R.id.outputText);


        String[] items = new String[]{"Select Major", "Biology", "Chemistry", "Physics", "Mathematics", "Computer Science", "Engineering", "Psychology", "Economics", "Literature", "Art History"};
        deptSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items));
        deptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dept = deptSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEditText.getText().toString();
                id = idEditText.getText().toString();
                email = emailEditText.getText().toString();
                phone = phoneEditText.getText().toString();
                password = passEditText.getText().toString();


                if (name.isEmpty()) {
                    nameEditText.setError("Empty!!");
                    nameEditText.requestFocus();
                } else if (!namePattern.matcher(name).matches()) {
                    nameEditText.setError("Name can only contain letters");
                    nameEditText.requestFocus();
                } else if (id.isEmpty()) {
                    idEditText.setError("Empty!!");
                    idEditText.requestFocus();
                } else if (email.isEmpty()) {
                    emailEditText.setError("Empty!!");
                    emailEditText.requestFocus();
                } else if (!emailPattern.matcher(email).matches()) {
                    emailEditText.setError("Invalid email format");
                    emailEditText.requestFocus();
                } else if (phone.isEmpty()) {
                    phoneEditText.setError("Empty!!");
                    phoneEditText.requestFocus();
                } else if (!phonePattern.matcher(phone).matches()) {
                    phoneEditText.setError("Phone number must be 11 digits");
                    phoneEditText.requestFocus();
                } else if (dept.equals("Select Major")) {
                    Toast.makeText(getApplicationContext(), "Please Select Major", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    passEditText.setError("Empty!!");
                    passEditText.requestFocus();
                } else if (!passwordPattern.matcher(password).matches()) {
                    passEditText.setError("Password must be at least 6 characters, include an uppercase letter, a lowercase letter, a digit, and a special character");
                    passEditText.requestFocus();
                } else {

                    inputLayout.setVisibility(View.GONE);
                    outputLayout.setVisibility(View.VISIBLE);
                    String s = "Name: " + name + "\nID: " + id + "\nEmail: " + email + "\nMobile Number: " + phone + "\nMajor: " + dept;
                    outputText.setText(s);
                }
            }
        });
    }
}
