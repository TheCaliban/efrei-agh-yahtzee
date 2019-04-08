package pl.edu.agh.playerSide;

public enum Scores {
    ONES,
    TWOS,
    THREES,
    FOURS,
    FIVES,
    SIXES,
    TOTAL,
    BONUS,
    THREE_OA_KIND,
    FOUR_OA_KIND,
    FULL_HOUSE,
    SMALL_STRAIGHT,
    LARGE_STRAIGHT,
    CHANCE,
    YAHTZEE,
    SCORE;

    public static String getValues() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (Scores s : values()) {
            if (s != SCORE) {
                builder.append(s + ", ");
            }
            else builder.append(s);
        }
        builder.append("}");
        return builder.toString();
    }
}

