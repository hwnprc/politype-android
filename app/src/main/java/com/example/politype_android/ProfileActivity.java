package com.example.politype_android;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.util.Log;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnApply = findViewById(R.id.btnApply);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1단계: EditText 객체 가져오기 (xml에서 정의한 입력창을 찾아 변수에 저장)
                EditText etFirstname = findViewById(R.id.etFirstname);  // 입력창 전체 객체 (커서, 배경색, 크기 등 모든 속성 포함)
                EditText etLastname = findViewById(R.id.etLastname);
                EditText etAge = findViewById(R.id.etAge);
                EditText etGender = findViewById(R.id.etGender);
                EditText etCountry = findViewById(R.id.etCountry);

                // 2단계: 텍스트 내용 추출하기 (EditText에서 사용자가 입력한 텍스트를 가져와서, 그걸 string 형태로 변환 후 변수 저장)
                String firstName = etFirstname.getText().toString();
                String lastName = etLastname.getText().toString();
                String age = etAge.getText().toString();
                String gender = etGender.getText().toString();
                String country = etCountry.getText().toString();

                Log.d("UserInfo", "Name: " + firstName + " " + lastName);
                Log.d("UserInfo", "Age: " + age + ", Gender: " + gender);
                Log.d("UserInfo", "Nationality: " + country);

                Intent intent = new Intent(ProfileActivity.this, QuestionActivity.class);
                startActivity(intent);

            }
        });



    }
}