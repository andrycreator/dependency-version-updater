package com.ahaidychuk.dependencyversionupdater.service;

import com.ahaidychuk.dependencyversionupdater.model.WebHookPayload;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;


class RepoServiceTest {

  private static ObjectMapper objectMapper;

  private RepoService repoService = new RepoService();

  @BeforeAll
  static void init() {
    objectMapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Test
  @DisplayName("Non-release commit message")
  void testReadReleasedVersionForNonReleaseCommit() throws IOException {
    var requestPayload = readFileFromResources("nonReleaseCommitPayload.json");
    var actualResult = repoService.readReleasedVersion(requestPayload);

    assertThat(actualResult).isNull();
  }

  @Test
  @DisplayName("Release commit message")
  void testReadReleasedVersionForReleaseCommit() throws IOException {
    var expectedResult = "Commit SHA: efcf4041b8d7ff34b51c2a9aa3582af116816c62, commit message: Release v1.0.0";
    var requestPayload = readFileFromResources("releaseCommitPayload.json");

    var actualResult = repoService.readReleasedVersion(requestPayload);

    assertThat(actualResult).isEqualTo(expectedResult);
  }

  private WebHookPayload readFileFromResources(String fileName) throws IOException {
    ClassPathResource resource = new ClassPathResource(fileName);
    InputStream is = resource.getInputStream();
    BufferedInputStream bis = new BufferedInputStream(is);

    return objectMapper.readValue(bis, new TypeReference<>(){});
  }
}