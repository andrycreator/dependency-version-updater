package com.ahaidychuk.dependencyversionupdater.repository;

import com.ahaidychuk.dependencyversionupdater.entity.Project;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DBRider
@DBUnit(caseSensitiveTableNames = true)
class ProjectRepositoryIT extends DBTestContainer {

  @Autowired
  private ProjectRepository projectRepository;

  @Test
  @DataSet("project_insert.yml")
  void testFindAllActive() {
    final var projects = projectRepository.findAllByActiveTrue();

    assertThat(projects, hasSize(2));
    assertThat(
            projects.stream().map(Project::getProjectName).collect(Collectors.toList()),
            containsInAnyOrder("activeProject_1", "activeProject_2")
    );
  }

  @Test
  @DataSet("project_insert.yml, library_insert.yml, projectLibrary_insert.yml")
  void testFindByLibraryName() {
    final var projectsByLibrary = projectRepository.findByLibraryName("library_3");

    assertThat(projectsByLibrary, hasSize(2));
    assertThat(
            projectsByLibrary.stream().map(Project::getProjectName).collect(Collectors.toList()),
            containsInAnyOrder("activeProject_2", "inactiveProject_3")
    );
  }
}