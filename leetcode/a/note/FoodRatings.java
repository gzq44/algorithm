package a.note;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * ss
 *
 * @author gzq44
 * @date 2023/12/23 17:13
 **/
public class FoodRatings {
    HashMap<String, TreeMap<String, Integer>> map;
    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        map = new HashMap<>();
        for (int i = 0; i < foods.length; i++) {

            TreeMap<String, Integer> orDefault = map.getOrDefault(cuisines[i], new TreeMap<>());
            orDefault.put(foods[i], ratings[i]);
        }

    }

    public void changeRating(String food, int newRating) {
//        map.get(
    }

    public String highestRated(String cuisine) {
        return null;
    }
}
