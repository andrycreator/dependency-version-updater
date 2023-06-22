package com.ahaidychuk.dependencyversionupdater.repository;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DBRider
@DBUnit(caseSensitiveTableNames = true)
class DependencyRepositoryTest {

  @Autowired
  private DependencyRepository dependencyRepository;

  @Container
  private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0")
      .withDatabaseName("testDB")
      .withUsername("testUser")
      .withPassword("testPassword");

  @DynamicPropertySource
  static void loadProps(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
    registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
    registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
  }

  @Test
  @DataSet("DependencyRepositoryTest/insert_dependencies.yml")
  void testFindAllActive() {
    final var dependencies = dependencyRepository.findAllByActiveTrue();
    assertThat(dependencies).isNotEmpty();
    assertThat(dependencies).hasSize(2);
    assertThat(dependencies.get(0).getProjectName()).isIn("activeProject_1", "activeProject_2");
  }
}