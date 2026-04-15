package com.example.politype_android.moral;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/**
 * Moral Foundation Baseline System
 * Based on Graham, Haidt, & Nosek (2009) research
 */
public class MoralFoundationBaselineSystem {

    private static final String TAG = "MoralBaseline";

    /**
     * The 7 political categories from research
     */
    public enum PoliticalCategory {
        VERY_LIBERAL("Very Liberal"),
        LIBERAL("Liberal"),
        SLIGHTLY_LIBERAL("Slightly Liberal"),
        MODERATE("Moderate"),
        SLIGHTLY_CONSERVATIVE("Slightly Conservative"),
        CONSERVATIVE("Conservative"),
        VERY_CONSERVATIVE("Very Conservative");

        private final String displayName;

        PoliticalCategory(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    // Research data storage
    // Array order: [Care, Equality, Proportionality, Loyalty, Authority, Purity]
    private static final Map<PoliticalCategory, int[]> RESEARCH_DATA = new HashMap<>();

    static {
        RESEARCH_DATA.put(PoliticalCategory.VERY_LIBERAL,
                new int[]{88, 88, 30, 50, 44, 50});

        RESEARCH_DATA.put(PoliticalCategory.LIBERAL,
                new int[]{86, 86, 32, 52, 48, 54});

        RESEARCH_DATA.put(PoliticalCategory.SLIGHTLY_LIBERAL,
                new int[]{80, 80, 40, 56, 52, 58});

        RESEARCH_DATA.put(PoliticalCategory.MODERATE,
                new int[]{80, 78, 50, 60, 56, 60});

        RESEARCH_DATA.put(PoliticalCategory.SLIGHTLY_CONSERVATIVE,
                new int[]{82, 80, 52, 58, 56, 60});

        RESEARCH_DATA.put(PoliticalCategory.CONSERVATIVE,
                new int[]{76, 76, 60, 56, 58, 60});

        RESEARCH_DATA.put(PoliticalCategory.VERY_CONSERVATIVE,
                new int[]{70, 68, 75, 62, 62, 66});
    }

    /**
     * Categorize a political type into research category
     *
     * @param politicalType 4-letter code like "EPLE"
     * @return PoliticalCategory enum
     */
    public static PoliticalCategory categorize(String politicalType) {
        if (politicalType == null || politicalType.length() != 4) {
            Log.w(TAG, "Invalid political type: " + politicalType);
            return PoliticalCategory.MODERATE;
        }

        politicalType = politicalType.toUpperCase();

        int score = 0;

        // Economic: E = liberal (+2), R = conservative (-2)
        if (politicalType.charAt(0) == 'E') {
            score += 2;
        } else {
            score -= 2;
        }

        // Social: P = liberal (+2), C = conservative (-2)
        if (politicalType.charAt(1) == 'P') {
            score += 2;
        } else {
            score -= 2;
        }

        // Authority: L = liberal (+1), A = conservative (-1)
        if (politicalType.charAt(2) == 'L') {
            score += 1;
        } else {
            score -= 1;
        }

        // Gender axis has minimal impact (F = 0, E = 0)

        // Map score to category
        if (score >= 4) return PoliticalCategory.VERY_LIBERAL;
        if (score >= 2) return PoliticalCategory.LIBERAL;
        if (score == 1) return PoliticalCategory.SLIGHTLY_LIBERAL;
        if (score >= -1) return PoliticalCategory.MODERATE;
        if (score == -2) return PoliticalCategory.SLIGHTLY_CONSERVATIVE;
        if (score >= -4) return PoliticalCategory.CONSERVATIVE;
        return PoliticalCategory.VERY_CONSERVATIVE;
    }

    /**
     * Get baseline scores for a category
     *
     * @param category PoliticalCategory
     * @return Map with foundation names and scores
     */
    public static Map<String, Integer> getBaseline(PoliticalCategory category) {
        int[] data = RESEARCH_DATA.get(category);

        Map<String, Integer> baseline = new HashMap<>();
        baseline.put("care", data[0]);
        baseline.put("equality", data[1]);
        baseline.put("proportionality", data[2]);
        baseline.put("loyalty", data[3]);
        baseline.put("authority", data[4]);
        baseline.put("purity", data[5]);

        return baseline;
    }

    /**
     * Compare user score to baseline
     *
     * @param userScore User's score (0-100)
     * @param baselineScore Baseline score (0-100)
     * @return English description with symbol
     */
    public static String compareScore(int userScore, int baselineScore) {
        int diff = userScore - baselineScore;

        if (diff > 15) return "Higher than average ↑";
        if (diff < -15) return "Lower than average ↓";
        return "Similar to average ≈";
    }

    /**
     * Generate full comparison text for display
     *
     * @param politicalType User's 4-letter type (e.g., "EPLE")
     * @param userScores User's moral foundation scores
     * @return Formatted comparison text
     */
    public static String generateComparisonText(String politicalType,
                                                Map<String, Integer> userScores) {
        PoliticalCategory category = categorize(politicalType);
        Map<String, Integer> baseline = getBaseline(category);

        StringBuilder text = new StringBuilder();

        // Header
        text.append("📊 Your Political Type: ").append(politicalType).append("\n");
        text.append("General Category: ").append(category.getDisplayName()).append("\n\n");

        // General pattern description
        text.append("💡 According to research, ").append(category.getDisplayName())
                .append(" individuals typically:\n\n");

        text.append(getCategoryDescription(category));
        text.append("\n");

        // Individual comparisons
        text.append("Your Profile Compared:\n\n");

        String[] foundations = {"care", "equality", "proportionality", "loyalty", "authority", "purity"};
        String[] names = {"Care", "Equality", "Proportionality", "Loyalty", "Authority", "Purity"};

        for (int i = 0; i < foundations.length; i++) {
            String foundation = foundations[i];
            int userScore = userScores.get(foundation);
            int baselineScore = baseline.get(foundation);

            text.append("• ").append(names[i]).append(": ")
                    .append(compareScore(userScore, baselineScore)).append("\n");
        }

        // Closing message
        text.append("\n💬 What this means:\n");
        text.append("Even within the same ").append(category.getDisplayName())
                .append(" category, individual differences exist.\n");
        text.append("Having your own unique moral foundation combination is natural.\n");

        return text.toString();
    }

    /**
     * Get description of typical pattern for each category
     */
    private static String getCategoryDescription(PoliticalCategory category) {
        switch (category) {
            case VERY_LIBERAL:
            case LIBERAL:
                return "✓ Emphasize Care and Equality highly\n" +
                        "✓ Place relatively less emphasis on Loyalty, Authority, and Purity\n";

            case SLIGHTLY_LIBERAL:
                return "✓ Value Care and Equality, but\n" +
                        "✓ Also consider other moral foundations to some degree\n";

            case MODERATE:
                return "✓ Value various moral foundations relatively evenly\n";

            case SLIGHTLY_CONSERVATIVE:
                return "✓ Value multiple moral foundations evenly, and\n" +
                        "✓ Also consider Loyalty and Authority important\n";

            case CONSERVATIVE:
            case VERY_CONSERVATIVE:
                return "✓ Value all five moral foundations relatively evenly\n" +
                        "✓ Particularly emphasize Loyalty, Authority, and Purity\n";

            default:
                return "";
        }
    }

    /**
     * Get pattern visualization for educational display
     */
    public static String getPatternVisualization() {
        return "📈 Key Research Findings:\n\n" +
                "Liberal Orientation:\n" +
                "  Care        ████████░░ Very High\n" +
                "  Equality    ████████░░ Very High\n" +
                "  Loyalty     ████░░░░░░ Low\n" +
                "  Authority   ████░░░░░░ Low\n" +
                "  Purity      ████░░░░░░ Low\n\n" +
                "Conservative Orientation:\n" +
                "  Care        ██████░░░░ Medium\n" +
                "  Equality    ██████░░░░ Medium\n" +
                "  Loyalty     ██████░░░░ Medium-High\n" +
                "  Authority   ██████░░░░ Medium-High\n" +
                "  Purity      ██████░░░░ Medium-High\n\n" +
                "💡 Political differences = differences in moral foundation weights\n\n" +
                "Liberal vs Conservative isn't about 'right or wrong',\n" +
                "but about 'what we prioritize more'.";
    }

    /**
     * Get methodology note and citation
     */
    public static String getMethodologyNote() {
        return "This comparison is based on research from:\n\n" +
                "Graham, J., Haidt, J., & Nosek, B. A. (2009). " +
                "Liberals and conservatives rely on different sets of moral foundations. " +
                "Journal of Personality and Social Psychology, 96(5), 1029-1046.\n\n" +
                "The researchers measured the relationship between political orientation " +
                "and moral foundations with over 1,600 participants.\n\n" +
                "⚠️ Important Note:\n" +
                "• This shows general tendencies and may not apply precisely to everyone\n" +
                "• Individual differences exist within the same category\n" +
                "• The research was primarily conducted with US populations, " +
                "so patterns may differ for other cultures";
    }
}