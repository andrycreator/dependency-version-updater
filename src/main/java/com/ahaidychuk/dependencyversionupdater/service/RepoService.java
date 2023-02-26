package com.ahaidychuk.dependencyversionupdater.service;

import java.util.Map;

public interface RepoService {
    String readReleasedVersion(Map<String, Object> payload);
}
