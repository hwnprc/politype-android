package com.example.politype_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.politype_android.model.PoliType;
import com.example.politype_android.model.Question;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Result;

public class QuestionActivity extends AppCompatActivity {

    // 1단계: 변수 선언
    private TextView tvQuestionNumber;
    private TextView tvQuestion;
    private RadioGroup radioGroup;
    private Button btnNext;

    private Question questionModel; // Question 클래스 객체
    private int currentQuestionIndex = 0; // 현재 질문 번호 (0부터 시작)
    private Map<Integer, Integer> answers; // 답변 저장 (질문 번호 -> 답변 값)
    private PoliType poliType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 2단계: View 연결
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvQuestion = findViewById(R.id.tvQuestion);
        radioGroup = findViewById(R.id.radioGroup);
        btnNext = findViewById(R.id.btnNext);

        // Question 모델 초기화
        questionModel = new Question();
        answers = new HashMap<>();
        poliType = new PoliType();

        // 첫 질문 로드
        loadQuestion();

        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // 답변을 선택했는지 확인.
                if (radioGroup.getCheckedRadioButtonId() == -1){  // -1은 '없음' , '선택안함'을 의미하는 값.
                    Toast.makeText(QuestionActivity.this, "Select your answer", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 선택한 답변 저장.
                int selectedId = radioGroup.getCheckedRadioButtonId();
                int answer = 0; // 답변 값 (1-5)

                if (selectedId == R.id.radio1) answer = 1;
                else if (selectedId == R.id.radio2) answer = 2;
                else if (selectedId == R.id.radio3) answer = 3;
                else if (selectedId == R.id.radio4) answer = 4;
                else if (selectedId == R.id.radio5) answer = 5;

                // HashMap에 저장. (질문 번호 -> 답변값)
                answers.put(currentQuestionIndex, answer);

                // 다음 질문으로 이동 (여기 추가!)
                if (currentQuestionIndex < questionModel.getTotalQuestions()-1) {
                    currentQuestionIndex++;
                    loadQuestion();
                } else {
                    // 마지막 질문이면..
                    calculateResult();
                }
            }

        });

    }

    private void loadQuestion(){
        // 1. 현재 질문 가져오기
        String question = questionModel.getQuestion(currentQuestionIndex);

        // 2. 질문 번호 표시 (01, 02, 03..)
        String questionNum = String.format("%02d", currentQuestionIndex + 1);
        tvQuestionNumber.setText(questionNum);

        // 3. 질문 텍스트 표시
        tvQuestion.setText(question);

        // 4. 라디오 버튼 선택 초기화
        radioGroup.clearCheck();
    }

    private void calculateResult(){
        Log.d("QuestionActivity", "calculateResult START");

        poliType.calculateResponses(answers);
        String type = poliType.getPoliticalType();

        int economic = poliType.getEconomicPercentage();
        int societal = poliType.getSocietalPercentage();
        int authority = poliType.getAuthorityPercentage();
        int gender = poliType.getGenderPercentage();

        Log.d("QuestionActivity", "Type" + type + ", Economic: " + economic);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("economic", economic);
        intent.putExtra("societal", societal);
        intent.putExtra("authority", authority);
        intent.putExtra("gender", gender);

        Log.d("QuestionActivity", "Starting ResultActivity");
        startActivity(intent);

        finish();
        Log.d("QuestionActivity", "calculateResult END");

    }


}