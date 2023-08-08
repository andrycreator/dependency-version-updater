package com.ahaidychuk.dependencyversionupdater.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ProjectLibraryId implements Serializable {

  private int projectId;
  private int libraryId;
}
