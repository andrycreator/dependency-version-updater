package com.ahaidychuk.dependencyversionupdater.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class RepoServiceImplTest {

    private RepoService repoService = new RepoServiceImpl();

    @Test
    @DisplayName("Commit with non Release message")
    void testReadReleasedVersionForNonReleaseCommit() throws IOException {
        var requestPayload = readFileFromResources("nonReleaseCommitPayload.json");
        var result = repoService.readReleasedVersion(requestPayload);

        assertNull(result);
    }

    @Test
    @DisplayName("Commit with Release message")
    void testReadReleasedVersionForReleaseCommit() throws IOException {
        var expectedResult = "Commit SHA: efcf4041b8d7ff34b51c2a9aa3582af116816c62, commit message: Release v1.0.0";
        var requestPayload = readFileFromResources("releaseCommitPayload.json");

        var actualResult = repoService.readReleasedVersion(requestPayload);

        assertEquals(expectedResult, actualResult);
    }

    private Map<String, Object> readFileFromResources(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        InputStream is = resource.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(bis, new TypeReference<Map<String, Object>>(){});
    }
}