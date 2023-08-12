CREATE TABLE IF NOT EXISTS project (
    id int AUTO_INCREMENT,
    project_name varchar(50) NOT NULL,
    versionFile_path varchar(300) DEFAULT NULL,
    active TINYINT NOT NULL DEFAULT 1,
    created timestamp NOT NULL,
    last_update timestamp NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uq_project_project_name UNIQUE (project_name),
    INDEX idx_project_project_name (project_name)
) ENGINE InnoDB CHARACTER SET utf8mb4;

CREATE TABLE IF NOT EXISTS library (
    id int AUTO_INCREMENT,
    library_name varchar(50) NOT NULL,
    created timestamp NOT NULL,
    last_update timestamp NOT NULL,
    version varchar(10) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uq_library_library_name UNIQUE (library_name),
    INDEX idx_library_library_name (library_name)
) ENGINE InnoDB CHARACTER SET utf8mb4;

CREATE TABLE IF NOT EXISTS projectLibrary (
    project_id int,
    library_id int,
    created timestamp NOT NULL,
    last_update timestamp NOT NULL,
    used_version varchar(10) NOT NULL,
    PRIMARY KEY (project_id, library_id),
    CONSTRAINT fk_dependencyLibrary_project FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE NO ACTION ON UPDATE CASCADE,
    CONSTRAINT fk_dependencyLibrary_library FOREIGN KEY (library_id) REFERENCES library(id) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE InnoDB CHARACTER SET utf8mb4;