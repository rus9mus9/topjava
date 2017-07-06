package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil
{
    public static void main(String[] args)
    {
        List<UserMeal> mealList = Arrays.asList
       (
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay)
    {
        List<UserMealWithExceed> exceedList = new ArrayList<>();
        Map<Integer, Integer> dayAndCal = new HashMap<>();

        for(UserMeal userMeal : mealList)
        {
            if(dayAndCal.containsKey(userMeal.getDateTime().toLocalDate().getDayOfMonth()))
            {
                dayAndCal.put(userMeal.getDateTime().toLocalDate().getDayOfMonth(), dayAndCal.get(userMeal.getDateTime().toLocalDate().getDayOfMonth()) + userMeal.getCalories());
            }
            else dayAndCal.put(userMeal.getDateTime().toLocalDate().getDayOfMonth(), userMeal.getCalories());

        }

        for(Map.Entry<Integer, Integer> entry : dayAndCal.entrySet())
        {
            for(UserMeal userMeal : mealList)
            {
                if(TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime) && entry.getKey() == userMeal.getDateTime().getDayOfMonth())
                {
                    exceedList.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), entry.getValue(), entry.getValue() > caloriesPerDay));
                }
            }
        }

        // P.S Решение деревенское, я сам им недоволен. Буду переделывать и осваивать Stream API
        return exceedList;
    }
}

