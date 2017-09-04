package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;


@Controller
public class MealController
{
    @Autowired
    private MealService service;

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String meals(Model model)
    {
        model.addAttribute("meals", MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()),  AuthorizedUser.getCaloriesPerDay()));
        return "meals";
    }

    @RequestMapping(value = "/meals", params = {"action=filter"}, method = RequestMethod.POST)
    public String filterByDate(HttpServletRequest request, Model model)
    {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));

        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));

        model.addAttribute("meals", MealsUtil.getFilteredWithExceeded(
                service.getBetweenDates(startDate != null ? startDate : DateTimeUtil.MIN_DATE, endDate
                        != null ? endDate : DateTimeUtil.MAX_DATE, AuthorizedUser.id()), startTime != null ?
                        startTime : LocalTime.MIN, endTime != null ? endTime : LocalTime.MAX
                , AuthorizedUser.getCaloriesPerDay()));
        return "meals";
    }

    @RequestMapping(value = "/meals", params = {"action=delete"} , method = RequestMethod.GET)
    public String removeMeal(HttpServletRequest request)
    {
        service.delete(Integer.parseInt(request.getParameter("id")), AuthorizedUser.id());
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals", params = {"action=create"}, method = RequestMethod.GET)
    public String createMeal(Model model)
    {
        model.addAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "", 1000));
        return "mealForm";
    }

    @RequestMapping(value = "/meals", params = {"action=update"} , method = RequestMethod.GET)
    public String editMeal(HttpServletRequest request, Model model)
    {
       System.out.println("In edit method");
       model.addAttribute("meal", service.get(Integer.valueOf(request.getParameter("id")), AuthorizedUser.id()));
       return "mealForm";
    }

    @RequestMapping(value = "/meals" , method = RequestMethod.POST)
    public String updateCreate(HttpServletRequest request)
    {
      Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime")),
      request.getParameter("description"),
      Integer.valueOf(request.getParameter("calories")));
      if (request.getParameter("id").isEmpty())
      {
          service.create(meal, AuthorizedUser.id());
      }
      else
      {
          meal.setId(Integer.valueOf(request.getParameter("id")));
          service.update(meal, AuthorizedUser.id());
      }
      return "redirect:meals";
    }
}

