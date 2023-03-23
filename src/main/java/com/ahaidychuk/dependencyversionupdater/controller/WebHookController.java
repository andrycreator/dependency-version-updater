package com.ahaidychuk.dependencyversionupdater.controller;

import com.ahaidychuk.dependencyversionupdater.model.WebHookPayload;
import com.ahaidychuk.dependencyversionupdater.service.RepoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/webhook")
@RequiredArgsConstructor
public class WebHookController {

  private final ObjectMapper objectMapper;
  private final RepoService repoService;

  @PostMapping(value = "/payload", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void getWebHookFromGitHub(@RequestBody final Map<String, Object> body) {
    if (log.isDebugEnabled()) {
      final String jsonPayload;
      try {
        jsonPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
      } catch (JsonProcessingException e) {
        log.error("Error happened during deserializing incoming Webhook payload", e);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }
      log.debug("Webhook payload:\n{}", jsonPayload);
    }

    repoService.readReleasedVersion(objectMapper.convertValue(body, WebHookPayload.class));
  }
}
