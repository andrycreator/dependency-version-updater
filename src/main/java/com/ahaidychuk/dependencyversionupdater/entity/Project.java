package com.ahaidychuk.dependencyversionupdater.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "project")
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "project_name", nullable = false, unique = true, length = 50)
  private String projectName;

  @Column(name = "version_path", length = 300)
  private String versionPath;

  @Column(nullable = false)
  private boolean active;

  @Column(nullable = false)
  @CreatedBy
  private Instant created;

  @Column(name = "last_update", nullable = false)
  @LastModifiedBy
  private Instant lastUpdate;

  @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
  private List<ProjectLibrary> projectLibraries = new ArrayList<>();
}
