package net.therap.service;

import net.therap.domain.Food;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 4/22/12
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FoodService {
    public List<Food> populateFoodListService() ;
    public List<String> getFoodNames() ;
    public List<Integer> getVoteCount();
}
