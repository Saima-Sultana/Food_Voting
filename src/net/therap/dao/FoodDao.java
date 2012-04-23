package net.therap.dao;

import net.therap.domain.Food;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 4/22/12
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class FoodDao {
    static DatabaseTemplate dbTemplate;

    public List<Food> getFoodList() {
        List<Food> foodList = new ArrayList<Food>();

        foodList = dbTemplate.queryForObject("select * from saima_tahmid_food", new RowObjectMapper<Food>() {
            Food food = new Food();

            public Food mapRowToObject(ResultSet resultSet) {
                String name;
                int voteCount;

                try {
                    name = resultSet.getString("NAME");
                    voteCount = resultSet.getInt("voteCount");

                    food.setFoodName(name);
                    food.setVoteCount(voteCount);
                } catch (SQLException e) {
                    e.printStackTrace();

                }
                return food;
            }
        });
        return foodList;
    }

    public void updateVoteCount(String name) {
        dbTemplate.executeQuery("update saima_tahmid_food set votecount = votecount + 1 where name = ?", name);

    }
}
