package com.ahaidychuk.dependencyversionupdater.repository;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DBRider
@DBUnit(caseSensitiveTableNames = true)
class LibraryRepositoryIT extends DBTestContainer {

  @Autowired
  private LibraryRepository libraryRepository;

  @Test
  @DataSet("library_insert.yml")
  void findByLibraryName() {
    final var expectedLibraryName = "library_1";
    final var library = libraryRepository.findByLibraryName(expectedLibraryName);

    assertThat(library.get().getLibraryName(), is(expectedLibraryName));
  }
}