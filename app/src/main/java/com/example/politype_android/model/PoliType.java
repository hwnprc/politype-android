package com.example.politype_android.model;

import java.util.Map;

public class PoliType {
    private int economic_Score;
    private int societal_Score;
    private int authority_Score;
    private int gender_Score;

    private int economic_percentage;
    private int societal_percentage;
    private int authority_percentage;
    private int gender_percentage;

    private int[] economic_Questions = {0, 1, 4, 7, 17, 25, 26, 27, 37, 41, 44, 45};
    private int[] societal_Questions = {2, 5, 6, 9, 10, 15, 22, 23, 30, 33, 34, 40};
    private int[] authority_Questions = {8, 12, 13, 18, 19, 20, 24, 38, 39, 42, 46, 47};
    private int[] gender_Questions = {3, 11, 14, 16, 21, 28, 29, 31, 32, 35, 36, 43};

    private String politicalType;

    // 생성자
    public PoliType(){
    }

    public void calculateResponses(Map<Integer, Integer> answers){
        // 1. 경제관 계산
        for (int i = 0 ; i < economic_Questions.length; i++){
            int questionIndex = economic_Questions[i];
            int userAnswer = answers.get(questionIndex); // HashMap에서 답변 가져오기

            if (questionIndex == 0 || questionIndex == 4 || questionIndex == 27 || questionIndex == 37 || questionIndex == 45){
                userAnswer = 6 - userAnswer;
            }
            economic_Score += (userAnswer -3);
        }

        // 2. 사회관 계산
        for (int i = 0; i < societal_Questions.length; i++) {
            int questionIndex = societal_Questions[i];
            int userAnswer = answers.get(questionIndex);

            if (questionIndex == 5 || questionIndex == 9 || questionIndex == 22 ||
                    questionIndex == 34) {
                userAnswer = 6 - userAnswer;
            }

            societal_Score += (userAnswer - 3);
        }

        // 3. 권위관 계산
        for (int i = 0; i < authority_Questions.length; i++) {
            int questionIndex = authority_Questions[i];
            int userAnswer = answers.get(questionIndex);

            if (questionIndex == 20 || questionIndex == 46) {
                userAnswer = 6 - userAnswer;
            }

            authority_Score += (userAnswer - 3);
        }

        // 4. 젠더관 계산
        for (int i = 0; i < gender_Questions.length; i++) {
            int questionIndex = gender_Questions[i];
            int userAnswer = answers.get(questionIndex);

            if (questionIndex == 3 || questionIndex == 14 || questionIndex == 16 ||
                    questionIndex == 21 || questionIndex == 28 || questionIndex == 29 ||
                    questionIndex == 31 || questionIndex == 32 || questionIndex == 35 ||
                    questionIndex == 36 || questionIndex == 43) {
                userAnswer = 6 - userAnswer;
            }

            gender_Score += (userAnswer - 3);
        }

        // 5. 퍼센티지 계산 (점수를 0~100% 로 변환)
        economic_percentage = (int) Math.round((economic_Score + 24) / 48.0 * 100);
        societal_percentage = (int) Math.round((societal_Score + 24) / 48.0 * 100);
        authority_percentage = (int) Math.round((authority_Score + 24) / 48.0 * 100);
        gender_percentage = (int) Math.round((gender_Score + 24) / 48.0 * 100);
    }

    // 정치 타입 판별
    public String getPoliticalType() {
        String firstType = (economic_percentage >= 50) ? "R" : "E";
        String secondType = (societal_percentage >= 50) ? "C" : "P";
        String thirdType = (authority_percentage >= 50) ? "A" : "L";
        String fourthType = (gender_percentage >= 50) ? "E" : "F";

        politicalType = firstType + secondType + thirdType + fourthType;
        return politicalType;
    }

    // 각 퍼센티지 getter 메서드들
    public int getEconomicPercentage() {
        return economic_percentage;
    }

    public int getSocietalPercentage() {
        return societal_percentage;
    }

    public int getAuthorityPercentage() {
        return authority_percentage;
    }

    public int getGenderPercentage() {
        return gender_percentage;
    }




    }


