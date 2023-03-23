package com.ahaidychuk.dependencyversionupdater.service;

import com.ahaidychuk.dependencyversionupdater.model.WebHookPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RepoService {

  private static final String VERSION_COMMIT_PATTERN = "^Release v(\\d\\.){2}\\d$";

  public String readReleasedVersion(final WebHookPayload payload) {
    final var commitMessage = payload.getCommits().get(0).getMessage();

    if (commitMessage.matches(VERSION_COMMIT_PATTERN)) {
      final var result = String.format("Commit SHA: %s, commit message: %s", payload.getAfter(), commitMessage);
      log.info(result);
      return result;
    }

    log.info("Skipped commit with SHA: {} and message: {}", payload.getAfter(), commitMessage);
    return null;
  }
}
