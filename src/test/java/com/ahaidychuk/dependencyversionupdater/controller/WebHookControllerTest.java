package com.ahaidychuk.dependencyversionupdater.controller;

import com.ahaidychuk.dependencyversionupdater.service.RepoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WebHookController.class)
class WebHookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(answer = Answers.RETURNS_DEEP_STUBS)
    private ObjectMapper objectMapper;

    @MockBean
    private RepoService repoService;

    private static String releaseCommitPayload;

    @BeforeAll
    static void init() throws IOException {
        var resourceName = WebHookControllerTest.class.getClassLoader().getResource("releaseCommitPayload.json").getFile();
        var resourcePath = new File(URLDecoder.decode(resourceName, StandardCharsets.UTF_8)).toPath();
        releaseCommitPayload = Files.readString(resourcePath);
    }

    @Test
    void testGetWebHookFromGitHub() throws Exception {
        mockMvc.perform(post("/api/webhook/payload")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(releaseCommitPayload))
                .andExpect(status().isOk());
    }

    @Test
    void testGetWebHookFromGitHubWithException() throws Exception {
        when(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(any()))
                .thenThrow(JsonProcessingException.class);

        mockMvc.perform(post("/api/webhook/payload")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(releaseCommitPayload))
                .andExpect(status().isBadRequest());
    }
}