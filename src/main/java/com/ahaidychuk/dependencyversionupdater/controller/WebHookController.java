package com.ahaidychuk.dependencyversionupdater.controller;

import com.ahaidychuk.dependencyversionupdater.model.WebHookPayload;
import com.ahaidychuk.dependencyversionupdater.service.RepoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/webhook")
@RequiredArgsConstructor
@Slf4j
public class WebHookController {

    private final RepoService repoService;

    @PostMapping(value = "/payload", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void getWebHootFromGitHub(@RequestBody Map<String, Object> body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
        log.debug("Webhook payload:\n{}", jsonPayload);

        repoService.readReleasedVersion(mapper.convertValue(body, WebHookPayload.class));
    }
}
