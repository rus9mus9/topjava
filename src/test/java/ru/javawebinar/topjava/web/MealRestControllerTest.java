package ru.javawebinar.topjava.web;

import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MealRestControllerTest extends AbstractControllerTest
{
    @Test
    public void testMealsBetween() throws Exception
    {
        mockMvc.perform(get("/rest/meals/filter")
                .param
                ("startDateTime", "2011-12-03T10:15:30")
                .param("endDateTime", "2015-12-03T12:12:30"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}