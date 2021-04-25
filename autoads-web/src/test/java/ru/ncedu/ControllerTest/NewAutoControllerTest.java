package ru.ncedu.ControllerTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.ncedu.AutoAdsApplication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//java.lang.ExceptionInInitializerError ???????
@WebMvcTest(AutoAdsApplication.class)
@RunWith(SpringRunner.class)
public class NewAutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAutoById() throws Exception {
        String url = "/api/all/1";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }
}
