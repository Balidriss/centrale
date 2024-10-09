package fr.balijon.centrale.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testLoginSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getLoginJsonFromData("zoe.maillard@hotmail.fr136", "12345")));
        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.token").exists());
    }
    @Test
    public void testLoginFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getLoginJsonFromData("matheo.le roux@yahoo.fr199", "12346")));

        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    public void testLoginValidationFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getLoginJsonFromData("matheo.le roux@yahoo.fr199", "")));

        resultActions.andExpect(status().is4xxClientError());
    }
    @Test
    public void testRegisterValidationSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getRegisterJsonFromData("titeuf.iltest@yahoo.fr","0606060606", LocalDate.now().minusYears(10),"12345","12345",1L)));
        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.birthAt").exists());
    }
    @Test
    public void testRegisterValidationEmailFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getRegisterJsonFromData("matheo.lerouxyahoo.fr199","0606060606", LocalDate.now().minusYears(10),"12345","12345",1L)));
        resultActions.andExpect(status().is4xxClientError())
                .andExpect(status().is4xxClientError());
    }

    private String getLoginJsonFromData(String email, String pwd) {
        JSONObject object = new JSONObject();
        object.put("email", email);
        object.put("password", pwd);
        return object.toString();
    }



    private String getRegisterJsonFromData(String email, String phone, LocalDate birthAt , String pwd, String pwd2, Long addressId) {
        JSONObject object = new JSONObject();
        object.put("email", email);
        object.put("password", pwd);
        object.put("confirmedPassword", pwd2);
        object.put("phone", phone);
        object.put("birthAt", birthAt);
        object.put("addressId", addressId);
        return object.toString();
    }

}
