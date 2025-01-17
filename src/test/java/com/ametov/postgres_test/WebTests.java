package com.ametov.postgres_test;

import com.ametov.postgres_test.user.dto.request.UserRequest;
import com.ametov.postgres_test.user.dto.response.UserResponse;
import com.ametov.postgres_test.user.entity.UserEntity;
import com.ametov.postgres_test.user.repository.UserRepository;
import com.ametov.postgres_test.user.routes.UserRoutes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.h2.engine.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class WebTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoad() throws Exception {
        UserEntity user = UserEntity.builder()
                .firstName("a")
                .lastName("b")
                .build();

        user = userRepository.save(user);

        mockMvc.perform(
                        MockMvcRequestBuilders.get(UserRoutes.BY_ID, user.getId().toString())
                                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void createTest() throws Exception {
        UserRequest request = UserRequest.builder()
                .firstName("createTest")
                .lastName("createTest")
                .build();

        mockMvc.perform(
                        MockMvcRequestBuilders.post(UserRoutes.CREATE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                ).andDo(print())
                .andExpect(content().string(containsString("createTest")));

    }

    @Test
    void findByIdTest() throws Exception {
        UserEntity user = UserEntity.builder()
                .firstName("findByIdTest")
                .lastName("findByIdTest")
                .build();

        userRepository.save(user);

        mockMvc.perform(
                        MockMvcRequestBuilders.get(UserRoutes.BY_ID, user.getId()).contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("findByIdTest")));
    }

    @Test
    void findById_NotFoundTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get(UserRoutes.BY_ID, 1000).contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void updateTest() throws Exception {
        UserEntity user = UserEntity.builder()
                .firstName("q")
                .lastName("q")
                .build();
        userRepository.save(user);

        UserRequest request = UserRequest.builder()
                .firstName("updateTest")
                .lastName("updateTest")
                .build();

        mockMvc.perform(
                MockMvcRequestBuilders.put(UserRoutes.BY_ID, user.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print()).andExpect(content().string(containsString("updateTest")));
    }

    @Test
    void deleteTest() throws Exception {
        UserEntity user = UserEntity.builder()
                .firstName("deleteTest")
                .lastName("deleteTest")
                .build();

        user = userRepository.save(user);

        mockMvc.perform(
                MockMvcRequestBuilders.delete(UserRoutes.BY_ID, user.getId())
        ).andDo(print()).andExpect(status().isOk());

        assert userRepository.findById(user.getId()).isEmpty();
    }

    @Test
    void searchTest() throws Exception {
        List<UserResponse> result = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            UserEntity user = UserEntity.builder()
                    .firstName("firstName" + i)
                    .lastName("lastName" + i)
                    .build();

            user = userRepository.save(user);
            result.add(UserResponse.of(user));
        }

        mockMvc.perform(
                MockMvcRequestBuilders.get(UserRoutes.SEARCH)
                        .param("size", "100")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(result)));
    }
}
