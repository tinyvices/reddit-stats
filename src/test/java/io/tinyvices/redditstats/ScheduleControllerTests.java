package io.tinyvices.redditstats;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ScheduleController.class, secure = false )
@AutoConfigureMockMvc
public class ScheduleControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    ScheduleRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getAllSchedules() throws Exception {
        String jsonIn = new String(Files.readAllBytes(Paths.get("src/test/resources/automoderatorScheduleList.json")));

        List<Schedule> schedules = mapper.readValue(jsonIn, new TypeReference<List<Schedule>>() {}) ;

        when(repository.findAll()).thenReturn(schedules);

        mvc.perform(MockMvcRequestBuilders.get("/schedules").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json( jsonIn ));

        verify(repository, times(1)).findAll();
    }

    @Test
    public void getScheduleById() throws Exception {
        String jsonIn = new String(Files.readAllBytes(Paths.get("src/test/resources/automoderatorSchedule.json")));

        Schedule schedule = mapper.readValue(jsonIn, Schedule.class);

        when(repository.findById(any())).thenReturn(Optional.of(schedule));

        mvc.perform(MockMvcRequestBuilders.get("/schedules/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json( jsonIn ));

        verify(repository, times(1)).findById(any());
    }

    @Test
    public void addSchedule() throws Exception {
        String jsonIn = new String(Files.readAllBytes(Paths.get("src/test/resources/automoderatorSchedule.json")));

        mvc.perform( post("/schedules")
                .content(jsonIn)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(repository, times(1)).save(any());
    }

    @Test
    public void addScheduleTwo() throws Exception {
        String jsonIn = new String(Files.readAllBytes(Paths.get("src/test/resources/automoderatorScheduleTwo.json")));

        mvc.perform( post("/schedules")
                .content(jsonIn)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(repository, times(1)).save(any());
    }
}
