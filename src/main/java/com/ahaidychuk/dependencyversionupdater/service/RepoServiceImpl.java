package com.ahaidychuk.dependencyversionupdater.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RepoServiceImpl implements RepoService {

    private final String VERSION_COMMIT_PATTERN = "^Release v(\\d\\.){2}\\d$";

    @Override
    public String readReleasedVersion(Map<String, Object> payload) {
        var commitHash = (String) payload.get("after");
        var commitInfo = ((List<Map<String, Object>>) payload.get("commits")).get(0);
        var commitMessage = (String)commitInfo.get("message");

        if (commitMessage.matches(VERSION_COMMIT_PATTERN)) {
            var result = String.format("Commit SHA: %s, commit message: %s", commitHash, commitMessage);
            log.info(result);
            return result;
        }
        log.info("Skipped commit with SHA: {} and message: {}", commitHash, commitMessage);
        return null;
    }
}
