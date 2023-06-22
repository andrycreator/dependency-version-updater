package com.ahaidychuk.dependencyversionupdater.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dependency")
public class Dependency {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "project_name", nullable = false, unique = true, length = 50)
  private String projectName;

  @Column(name = "file_path", nullable = false, length = 300)
  private String filePath;

  @Column(name = "active", nullable = false)
  private boolean active;

  @Column(name = "created", nullable = false)
  private LocalDateTime created;

  @Column(name = "last_update", nullable = false)
  private LocalDateTime lastUpdate;

  @Column(name = "used_version", length = 10)
  private String usedVersion;
}
