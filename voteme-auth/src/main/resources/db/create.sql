CREATE SCHEMA auth;

CREATE TABLE auth.users
(
  user_id INT PRIMARY KEY NOT NULL,
  user_username VARCHAR(20) NOT NULL,
  user_email VARCHAR(30) NOT NULL,
  user_password VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX users_user_id_uindex ON auth.users (user_id);
CREATE UNIQUE INDEX users_user_username_uindex ON auth.users (user_username);
CREATE UNIQUE INDEX users_user_email_uindex ON auth.users (user_email);

CREATE TABLE auth.roles
(
  role_id INT PRIMARY KEY NOT NULL,
  role_name VARCHAR(50) NOT NULL
);
CREATE UNIQUE INDEX roles_role_id_uindex ON auth.roles (role_id);
CREATE UNIQUE INDEX roles_role_name_uindex ON auth.roles (role_name);

CREATE TABLE auth.user_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    CONSTRAINT user_roles_pk PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES auth.users (user_id),
    CONSTRAINT role_id FOREIGN KEY (role_id) REFERENCES auth.roles (role_id)
);

CREATE TABLE auth.tokens
(
    token_id INT PRIMARY KEY NOT NULL,
    token_value INT NOT NULL,
    token_user_id INT NOT NULL,
    CONSTRAINT tokens_user FOREIGN KEY (token_user_id) REFERENCES users (user_id)
);

CREATE UNIQUE INDEX tokens_token_id_uindex ON auth.tokens (token_id);

CREATE SEQUENCE auth.users_seq;
CREATE SEQUENCE auth.roles_seq;
CREATE SEQUENCE auth.user_roles_seq;

INSERT INTO auth.roles(role_id, role_name) VALUES (nextval('auth.roles_seq'), 'ROLE_USER');
INSERT INTO auth.roles(role_id, role_name) VALUES (nextval('auth.roles_seq'), 'ROLE_ADMIN');