package com.ahaidychuk.dependencyversionupdater.repository;

import com.ahaidychuk.dependencyversionupdater.entity.Dependency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DependencyRepository extends JpaRepository<Dependency, Long> {

  List<Dependency> findAllByActiveTrue();
}
