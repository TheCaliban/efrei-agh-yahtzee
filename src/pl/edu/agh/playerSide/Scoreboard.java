package pl.edu.agh.playerSide;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class Scoreboard implements Serializable {

    private HashMap<Scores, Integer> scores;

    public Scoreboard() {
        this.scores = new HashMap<>();
        this.scores.put(Scores.ONES, -1);
        this.scores.put(Scores.TWOS, -1);
        this.scores.put(Scores.THREES, -1);
        this.scores.put(Scores.FOURS, -1);
        this.scores.put(Scores.FIVES, -1);
        this.scores.put(Scores.SIXES, -1);
        this.scores.put(Scores.TOTAL, 0);
        this.scores.put(Scores.BONUS, 0);
        this.scores.put(Scores.THREE_OA_KIND, -1);
        this.scores.put(Scores.FOUR_OA_KIND, -1);
        this.scores.put(Scores.FULL_HOUSE, -1);
        this.scores.put(Scores.SMALL_STRAIGHT, -1);
        this.scores.put(Scores.LARGE_STRAIGHT, -1);
        this.scores.put(Scores.CHANCE, -1);
        this.scores.put(Scores.YAHTZEE, -1);
        this.scores.put(Scores.SCORE, 0);
    }

    private void setThisAdvanced(Scores key, int value) {
        this.scores.replace(key, value);
        this.scores.replace(Scores.SCORE, this.scores.get(Scores.SCORE) + value);
        this.scores.replace(Scores.TOTAL, this.scores.get(Scores.TOTAL) + value);
        if (this.scores.get(key) >= 63 && this.scores.get(Scores.BONUS) != 35) {
            this.scores.replace(Scores.BONUS, 35);
            this.scores.replace(Scores.SCORE, this.scores.get(Scores.SCORE) + 35);
        }
    }

    private void setThisBasic(Scores key, int value) {
        this.scores.replace(key, value);
        this.scores.replace(Scores.SCORE, this.scores.get(Scores.SCORE) + value);
    }

    public void setOnes(int ones) {
        setThisAdvanced(Scores.ONES, ones);
    }

    public void setTwos(int twos) {
        setThisAdvanced(Scores.TWOS, twos);
    }

    public void setThrees(int threes) {
        setThisAdvanced(Scores.THREES, threes);
    }

    public void setFours(int fours) {
        setThisAdvanced(Scores.FOURS, fours);
    }

    public void setFives(int fives) {
        setThisAdvanced(Scores.FIVES, fives);
    }

    public void setSixes(int sixes) {
        setThisAdvanced(Scores.SIXES, sixes);
    }

    public void setThree_oa_kind(int three_oa_kind) {
        setThisBasic(Scores.THREE_OA_KIND, three_oa_kind);
    }

    public void setFour_oa_kind(int four_oa_kind) {
        setThisBasic(Scores.FOUR_OA_KIND, four_oa_kind);
    }

    public void setFull_house(boolean available) {
        if (available) {
            setThisBasic(Scores.FULL_HOUSE, 25);
        } else setThisBasic(Scores.FULL_HOUSE, 0);
    }

    public void setSmall_straight(boolean available) {
        if (available) {
            setThisBasic(Scores.SMALL_STRAIGHT, 30);
        } else setThisBasic(Scores.SMALL_STRAIGHT, 0);
    }

    public void setLarge_straight(boolean available) {
        if (available) {
            setThisBasic(Scores.LARGE_STRAIGHT, 40);
        } else setThisBasic(Scores.LARGE_STRAIGHT, 0);
    }

    public void setChance(int chance) {
        setThisBasic(Scores.CHANCE, chance);
    }

    public void setYahtzee(boolean available) {
        if (available) {
            setThisBasic(Scores.YAHTZEE, 50);
        } else setThisBasic(Scores.YAHTZEE, 0);
    }

    public int getScore() {
        return scores.get(Scores.SCORE);
    }

    public HashMap<Scores, Integer> getScoresTab() {
        return this.scores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scoreboard that = (Scoreboard) o;
        return Objects.equals(scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Scoreboard{");
        for (Scores type : Scores.values()) {
            builder.append("\n" + type.toString() + "=");
            if (this.scores.get(type) == -1) {
                builder.append("Unscored");
            } else {
                builder.append(this.scores.get(type));
            }
        }
        builder.append("}");
        return builder.toString();
    }

}
