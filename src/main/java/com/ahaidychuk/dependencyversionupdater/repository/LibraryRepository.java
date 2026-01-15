package com.ahaidychuk.dependencyversionupdater.repository;

import com.ahaidychuk.dependencyversionupdater.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library, Long> {

  Optional<Library> findByLibraryName(String libraryName);
}
