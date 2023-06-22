package com.ahaidychuk.dependencyversionupdater.model;

import java.util.List;

/**
 * Truncated payload of Git Push event for repository.
 */
public record WebHookPayload(String after, List<Commit> commits) {

  public record Commit(String message) {
  }
}
