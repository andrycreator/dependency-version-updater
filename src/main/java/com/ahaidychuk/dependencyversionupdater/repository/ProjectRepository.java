package com.ahaidychuk.dependencyversionupdater.repository;

import com.ahaidychuk.dependencyversionupdater.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

  List<Project> findAllByActiveTrue();

  @Query("FROM Project p JOIN p.projectLibraries pl JOIN pl.library l WHERE l.libraryName = :libraryName")
  List<Project> findByLibraryName(String libraryName);
}
