package io.shopr.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.shopr.common.ResponsStatusExceptionAdvice;
import io.shopr.controllers.transferobjects.CategoryRequestDto;
import io.shopr.entities.Category;
import io.shopr.repositories.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {CategoryController.class, ResponsStatusExceptionAdvice.class})
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryRepository repository;

    @Test
    public void create_new_catgory() throws Exception {

        var category = new Category("test");
        given(repository.save(any())).willReturn(category);

        mockMvc.perform(post("/category")
                        .content(toJson(new CategoryRequestDto("test")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(toJson(category)))
                .andReturn();
        verify(repository, times(1)).save(any());
    }

    @Test(expected = NestedServletException.class)
    public void create_will_throw_server_error() throws Exception {
        given(repository.save(any())).willThrow(new RuntimeException("Could not connect to database"));

        mockMvc.perform(post("/category")
                .content(toJson(new CategoryRequestDto("test")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andReturn();
    }

    private <T> String toJson(T type) {
        try {
            return new ObjectMapper().writeValueAsString(type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
