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

@WebMvcTest(AutoAdsApplication.class)
@RunWith(SpringRunner.class)
public class ListAutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetListAuto() throws Exception {
        String url = "/api/all";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }
}
