CREATE TABLE IF NOT EXISTS dependency (
    id int NOT NULL AUTO_INCREMENT,
    project_name varchar(50) NOT NULL,
    file_path varchar(300) NOT NULL,
    active TINYINT NOT NULL DEFAULT 1,
    created datetime NOT NULL,
    last_update datetime NOT NULL,
    used_version varchar(10),
    PRIMARY KEY (id),
    CONSTRAINT uq_dependency_project_name UNIQUE (project_name)
) ENGINE InnoDB CHARACTER SET utf8mb4;