package com.example.politype_android.model;

public class Question {
    String[] questions;
    int totalQuestions = 48;

    public Question(){ // 생성자 메서드
        questions = new String[totalQuestions];
        // String 배열 객체 생성: 24개의 문자열을 저장할 수 있는 새로운 배열을 만들어서 questions 변수에 저장

        questions[0] = "Corporations are more likely to cause greater harm than the government.";
        questions[1] = "The responsibility for poverty lies fundamentally with the individual.";
        questions[2] = "Wealthy people should have easier access to better medical services regardless of the severity of their condition.";
        questions[3] = "Female models posing in underwear or swimsuits for sexy photoshoots set back the women's rights achievements made so far.";
        questions[4] = "The government should set a cap on CEO salaries.";
        questions[5] = "Feeling that ethnic Koreans from China have a higher probability of committing crimes is a prejudiced and wrong way of thinking.";
        questions[6] = "PC (politically correct) advocates often go too far with their claims.";
        questions[7] = "When the government intervenes in private business, it usually ruins things.";
        questions[8] = "The saying 'If you don't like the temple, the monk should leave' is correct in most cases.";
        questions[9] = "Having positive LGBTQ+ characters frequently appear in mass media is a good way to increase minority visibility.";
        questions[10] = "Disney betrayed fans who cherished memories of the original by casting a Black actress in the live-action Little Mermaid movie.";
        questions[11] = "Living as a woman in today's Korean society has many more advantages than living as a man.";
        questions[12] = "For honest people with nothing to hide, government surveillance actually serves as a means of protection.";
        questions[13] = "I should not have to pay taxes for public projects that don't benefit me.";
        questions[14] = "If women had dominated world history, there would have been much less violence and war.";
        questions[15] = "The world is definitely changing for the worse.";
        questions[16] = "The government should implement gender quotas for women in male-dominated, high-income positions like corporate executives.";
        questions[17] = "Leaving things to the free market is better than creating government policies.";
        questions[18] = "For teamwork, everyone should participate in company dinners even if they don't feel like it.";
        questions[19] = "I cannot agree with disabled people protesting during busy commute hours, causing inconvenience to others.";
        questions[20] = "Even if it's resistance against a dictatorship, violence should not be tolerated.";
        questions[21] = "Women using menstrual leave on weekends is a legitimate exercise of their rights and cannot be criticized.";
        questions[22] = "23. In areas with large Muslim populations, local governments should support halal food certification systems according to Islamic law.";
        questions[23] = "Immigrants coming to Korean society should be assimilated into our culture.";
        questions[24] = "One earns the right to claim rights only after fulfilling duties first.";
        questions[25] = "I would not want to live in a country where everyone receives similar salaries.";
        questions[26] = "Economic growth-first policies will contribute more to escaping poverty than welfare-first policies.";
        questions[27] = "Expensive private education should be regulated.";
        questions[28] = "If men and women receive the same salary for the same job, the woman is probably more competent.";
        questions[29] = "Korean society is more disadvantageous to men because of mandatory military service.";
        questions[30] = "Discrimination against Black people in America has some justifiable grounds.";
        questions[31] = "Compared to men, women find it difficult to be happy without stable relationships.";
        questions[32] = "Adding gender prefixes to professions like 'actress,' 'female poet,' 'female journalist' is one way to devalue women.";
        questions[33] = "Maintaining traditional family systems has value in itself.";
        questions[34] = "Sign language interpretation screens on broadcasts are a natural measure for deaf people's rights and should appear larger and on all broadcasts.";
        questions[35] = "The government should allocate a certain percentage of public positions to women.";
        questions[36] = "If men's average wages are higher than women's, it's because men achieved better results.";
        questions[37] = "Real estate purchases for speculation rather than residence should be regulated.";
        questions[38] = "Obtaining wealth through inheritance is legitimate.";
        questions[39] = "Capital punishment is necessary in our society.";
        questions[40] = "The number of immigrants entering our country should be minimized.";
        questions[41] = "Competition generally makes the world better.";
        questions[42] = "The general public often makes wrong decisions.";
        questions[43] = "The differences between men and women are self-evident, so it's ideal for each to perform separate social roles and complement each other.";
        questions[44] = "Capitalism is superior to any other economic system.";
        questions[45] = "The government should work harder on wealth redistribution than it currently does.";
        questions[46] = "Taxpayer money should not be used to promote pure arts.";
        questions[47] = "It is a principle of democracy for the minority to sacrifice and follow for the majority.";

    }

    public String getQuestion(int index){
        return questions[index];
    }
    public int getTotalQuestions(){
        return totalQuestions;
    }

    }

