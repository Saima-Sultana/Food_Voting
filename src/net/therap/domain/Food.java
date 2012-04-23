package net.therap.domain;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 4/22/12
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class Food {


    private String foodName;
    private int voteCount;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
