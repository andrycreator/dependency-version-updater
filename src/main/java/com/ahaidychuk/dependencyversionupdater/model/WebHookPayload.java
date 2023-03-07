package com.ahaidychuk.dependencyversionupdater.model;

import lombok.Data;

import java.util.List;

/**
 * Truncated payload of Git Push event for repository.
 */
@Data
public class WebHookPayload {

  private String after;   // commit Hash
  private List<Commit> commits;

  @Data
  public static class Commit {
    private String message;
  }
}
