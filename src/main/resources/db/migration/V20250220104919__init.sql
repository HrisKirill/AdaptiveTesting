CREATE TABLE topics
(
    id   BIGSERIAL PRIMARY KEY,
    name TEXT UNIQUE NOT NULL
);

CREATE TABLE questions
(
    id             BIGSERIAL PRIMARY KEY,
    text           TEXT    NOT NULL,
    difficulty     DECIMAL NOT NULL,
    guessing       DECIMAL NOT NULL,
    topic_id       BIGINT  NOT NULL,
    FOREIGN KEY (topic_id) REFERENCES topics (id) ON DELETE CASCADE
);


CREATE TABLE answer_options
(
    id          BIGSERIAL PRIMARY KEY,
    text        TEXT    NOT NULL,
    is_correct  BOOLEAN NOT NULL,
    question_id BIGINT  NOT NULL,
    FOREIGN KEY (question_id) REFERENCES questions (id) ON DELETE CASCADE
);

CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    email    TEXT UNIQUE NOT NULL,
    password TEXT        NOT NULL,
    role     TEXT          NOT NULL
);


CREATE TABLE test_sessions
(
    id            UUID PRIMARY KEY,
    user_id       BIGINT         NOT NULL,
    topic_id      BIGINT         NOT NULL,
    score         DECIMAL(5, 2)  NOT NULL,
    current_theta DECIMAL(10, 2) NOT NULL DEFAULT 0.5,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (topic_id) REFERENCES topics (id) ON DELETE CASCADE
);


CREATE TABLE user_answers
(
    id               BIGSERIAL PRIMARY KEY,
    session_id       UUID  NOT NULL,
    question_id      BIGINT  NOT NULL,
    answer_option_id BIGINT  NOT NULL,
    FOREIGN KEY (session_id) REFERENCES test_sessions (id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES questions (id) ON DELETE CASCADE,
    FOREIGN KEY (answer_option_id) REFERENCES answer_options (id) ON DELETE CASCADE
);

CREATE TABLE refresh_tokens
(
    id         UUID PRIMARY KEY,
    user_id    BIGSERIAL NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);