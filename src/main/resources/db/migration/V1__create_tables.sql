CREATE TABLE users
(
    id    BIGINT(20) NOT NULL AUTO_INCREMENT,
    name  VARCHAR(50)  NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE places
(
    id      BIGINT(20) NOT NULL AUTO_INCREMENT,
    name    VARCHAR(50)  NOT NULL,
    address VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tags
(
    id   BIGINT(20) NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE `tasks`
(
    id          BIGINT(20) NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    status      VARCHAR(10)  NOT NULL,
    user_id     BIGINT(20),
    place_id    BIGINT(20),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (place_id) REFERENCES places (id)
);

CREATE TABLE calendar_events
(
    id          BIGINT(20) NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    start_date  DATETIME     NOT NULL,
    end_date    DATETIME     NOT NULL,
    user_id     BIGINT(20),
    place_id    BIGINT(20),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (place_id) REFERENCES places (id)
);

CREATE TABLE task_tags
(
    task_id BIGINT(20) NOT NULL,
    tag_id  BIGINT(20) NOT NULL,
    PRIMARY KEY (task_id, tag_id),
    FOREIGN KEY (task_id) REFERENCES tasks (id),
    FOREIGN KEY (tag_id) REFERENCES tags (id)
);

CREATE TABLE calendar_event_tags
(
    calendar_event_id BIGINT(20) NOT NULL,
    tag_id            BIGINT(20) NOT NULL,
    PRIMARY KEY (calendar_event_id, tag_id),
    FOREIGN KEY (calendar_event_id) REFERENCES calendar_events (id),
    FOREIGN KEY (tag_id) REFERENCES tags (id)
);
