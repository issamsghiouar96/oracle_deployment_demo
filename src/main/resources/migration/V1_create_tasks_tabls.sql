CREATE TABLE tasks (
                       id BIGSERIAL PRIMARY KEY,

                       title VARCHAR(150) NOT NULL,

                       description VARCHAR(1000),

                       completed BOOLEAN NOT NULL DEFAULT FALSE,

                       created_at TIMESTAMP NOT NULL,

                       updated_at TIMESTAMP NOT NULL
);