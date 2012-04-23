package net.therap.service;

import net.therap.dao.FoodDao;
import net.therap.domain.Food;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 4/22/12
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class FoodServiceImpl implements FoodService {
    public List<Food> populateFoodListService() {
        FoodDao foodDao = new FoodDao();
        return foodDao.getFoodList();

    }

    public List<String> getFoodNames() {
        List<String> foodNames = null;
        List<Food> foodList =  populateFoodListService();

        for(Food food:foodList) {
            foodNames.add(food.getFoodName());
        }
        return foodNames;
    }

    public List<Integer> getVoteCount() {
        List<Integer> voteCount = null;
        List<Food> foodList =  populateFoodListService();

        for(Food food:foodList) {
            voteCount.add(food.getVoteCount());
        }
        return voteCount;
    }
}
