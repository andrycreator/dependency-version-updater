package com.ahaidychuk.dependencyversionupdater.service;

import com.ahaidychuk.dependencyversionupdater.model.WebHookPayload;

public interface RepoService {
    String readReleasedVersion(WebHookPayload payload);
}
