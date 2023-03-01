package com.ahaidychuk.dependencyversionupdater.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Truncated payload of Git Push event for repository.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebHookPayload {

    private String after;   // commit Hash
    private List<Commit> commits;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Commit {
        String message;
    }
}
