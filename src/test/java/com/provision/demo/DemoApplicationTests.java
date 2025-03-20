package com.provision.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.provision.demo.model.Id;
import com.provision.demo.model.dto.UserResponseDto;
import lombok.Builder;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("integrationtest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DemoApplicationTests.class)
@AutoConfigureMockMvc
@Import(DemoApplicationTests.TestConfig.class)
class DemoApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testUser() {
        CrudExample example = CrudExample.builder()
                .url("/api/user")
                .post("""
                        {
                             "email": "myemail@gmail.com",
                             "fullName": "имя отчество фамилия",
                             "username": "login",
                             "password": "password",
                             "role": "ADMIN"
                        }""")
                .update("""
                        {
                             "email": "myemail@gmail1.com",
                             "fullName": "имя отчество фамилия",
                             "username": "login",
                             "password": "password",
                             "role": "ADMIN"
                        }""")
                .build();

        testCrud(UserResponseDto.class, example);
    }

    @Test
    public void testMeterDevice() {
        CrudExample example = CrudExample.builder()
                .url("/api/meter-device")
                .post("""
                        {
                          "serialNumber": "123456",
                          "inventoryNumber": "АБВ-123",
                          "manufactureYear": 2025,
                          "transformationCoefficient": 1073741824,
                          "installationDate": "2025-03-20",
                          "sealNumber": "ВА-123123",
                          "antiMagneticSealNumber": "DD-5555",
                          "installationPlace": "Москва, ул. Ленина, 45-89",
                          "note": "string",
                          "gisHousingId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                          "userId": 1
                        }""")
                .update("""
                        {
                          "serialNumber": "123456",
                          "inventoryNumber": "АБВ-123",
                          "manufactureYear": 2025,
                          "transformationCoefficient": 1073741824,
                          "installationDate": "2025-03-20",
                          "sealNumber": "ВА-123123-новый",
                          "antiMagneticSealNumber": "DD-5555",
                          "installationPlace": "Москва, ул. Ленина, 45-89",
                          "note": "string",
                          "gisHousingId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                          "userId": 1
                        }""")
                .build();

        testCrud(UserResponseDto.class, example);
    }

    @Test
    public void testZoneValues() {
        CrudExample example = CrudExample.builder()
                .url("/api/meter-device")
                .post("""
                        {
                          "serialNumber": "123456",
                          "inventoryNumber": "АБВ-123",
                          "manufactureYear": 2025,
                          "transformationCoefficient": 1073741824,
                          "installationDate": "2025-03-20",
                          "sealNumber": "ВА-123123",
                          "antiMagneticSealNumber": "DD-5555",
                          "installationPlace": "Москва, ул. Ленина, 45-89",
                          "note": "string",
                          "gisHousingId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                          "userId": 1
                        }""")
                .update("""
                        {
                          "serialNumber": "123456",
                          "inventoryNumber": "АБВ-123",
                          "manufactureYear": 2025,
                          "transformationCoefficient": 1073741824,
                          "installationDate": "2025-03-20",
                          "sealNumber": "ВА-123123-новый",
                          "antiMagneticSealNumber": "DD-5555",
                          "installationPlace": "Москва, ул. Ленина, 45-89",
                          "note": "string",
                          "gisHousingId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                          "userId": 1
                        }""")
                .build();

        testCrud(UserResponseDto.class, example);
    }

    @SneakyThrows
    private <T extends Id<Long>> void testCrud(Class<T> type, CrudExample crudExample) {
        MvcResult createResult = mvc.perform(post(crudExample.url)
                        .content(crudExample.post)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        T responseDto = objectMapper.readValue(createResult.getResponse().getContentAsString(), type);
        Long id = responseDto.getId();

        mvc.perform(put(crudExample.url + "/" + id)
                        .content(crudExample.update)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        mvc.perform(get(crudExample.url + "/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(responseDto.getId()));


        mvc.perform(get(crudExample.url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        mvc.perform(delete(crudExample.url + "/" + id))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @TestConfiguration
    @ComponentScan("com.provision.demo")
    public static class TestConfig {

        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
            return (web) -> web.ignoring()
                    .requestMatchers(new AntPathRequestMatcher("/**"));
        }

    }

    @Builder
    private static class CrudExample {
        private String url;
        private String post;
        private String update;
    }

}
