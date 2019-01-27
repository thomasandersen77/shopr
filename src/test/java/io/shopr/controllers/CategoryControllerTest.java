package io.shopr.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.shopr.controllers.transferobjects.CategoryRequestDto;
import io.shopr.entities.Category;
import io.shopr.testutils.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestEntityManager em;

    @Test
    public void create_new_catgory() throws Exception {

        mockMvc.perform(post("/category")
                        .content(toJson(new CategoryRequestDto("test")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{\"name\":\"test\"}", false))
                .andReturn();

        Category category = em.getEntityManager().createQuery("from Category where name = :name", Category.class)
                .setParameter("name", "test")
                .getSingleResult();
        assertEquals("test", category.getName());
    }

    private <T> String toJson(T type) {
        try {
            return new ObjectMapper().writeValueAsString(type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
