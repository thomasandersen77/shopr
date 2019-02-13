package io.shopr.category;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.shopr.dto.CategoryRequestDto;
import io.shopr.controllers.CategoryController;
import io.shopr.repositories.api.CategoryRepository;
import io.shopr.repositories.domain.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {CategoryController.class})
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
                .content(toJson(new CategoryRequestDto("")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(toJson(category)));

        verify(repository, times(1)).save(any());
    }

    @Test
    public void create_will_throw_server_error() throws Exception {
        given(repository.save(any())).willThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not connect to database"));

        MvcResult result = mockMvc.perform(post("/category")
                .content(toJson(new CategoryRequestDto("test")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andReturn();
        Exception resolvedException = result.getResolvedException();
        assertThat(resolvedException).isExactlyInstanceOf(ResponseStatusException.class);
        assertThat(resolvedException != null ? resolvedException.getMessage() : null).contains("Could not connect to database");
    }

    private <T> String toJson(T type) {
        try {
            return new ObjectMapper().writeValueAsString(type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
