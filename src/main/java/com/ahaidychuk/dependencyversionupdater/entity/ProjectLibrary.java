package com.ahaidychuk.dependencyversionupdater.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ProjectLibraryId.class)
@Table(name = "projectLibrary")
public class ProjectLibrary {

  @Id
  @Column(name = "project_id")
  private int projectId;

  @Id
  @Column(name = "library_id")
  private int libraryId;

  @Column(nullable = false)
  @CreatedDate
  private Instant created;

  @Column(name = "last_update", nullable = false)
  @LastModifiedDate
  private Instant lastUpdate;

  @Column(name = "used_version", nullable = false, length = 10)
  private String usedVersion;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "project_id")
  private Project project;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "library_id")
  private Library library;
}
