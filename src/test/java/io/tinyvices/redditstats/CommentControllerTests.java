package io.tinyvices.redditstats;


import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CommentController.class, secure = false )
@AutoConfigureMockMvc
public class CommentControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CommentRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    //@Test
    public void getAllComments() throws Exception {
        String jsonIn = new String(Files.readAllBytes(Paths.get("src/test/resources/commentList.json")));

        List<Comment> comments = mapper.readValue(jsonIn, new TypeReference<List<Comment>>() {}) ;

        when(repository.findAll()).thenReturn(comments);

        mvc.perform(MockMvcRequestBuilders.get("/comments").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json( jsonIn ));

        verify(repository, times(1)).findAll();
    }

    @Test
    public void getCommentByRedditId() throws Exception {
        String jsonIn = new String(Files.readAllBytes(Paths.get("src/test/resources/commentSingle.json")));

        Comment comment = new Comment("asd");

        when(repository.findByRedditId(any())).thenReturn(Collections.singletonList(comment));

        mvc.perform(MockMvcRequestBuilders.get("/comments?redditId={}", "asd").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json( jsonIn ));

        verify(repository, times(1)).findByRedditId(any());
    }
}
