GRANT USAGE ON SCHEMA auth TO auth;

GRANT DELETE ON ALL TABLES IN SCHEMA auth TO auth;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA auth
TO auth;

CREATE OR REPLACE FUNCTION signup_user()
    RETURNS TRIGGER AS $$
DECLARE
    user_role_id INTEGER;
    the_same_username_user auth.users%ROWTYPE;
BEGIN
    SELECT * INTO the_same_username_user
    FROM auth.users AS all_users
    WHERE all_users.username = NEW.username AND all_users.user_id != NEW.user_id;
    IF FOUND THEN
        RAISE EXCEPTION 'Username already exists : %', NEW.username;
    ELSE
        SELECT role_id INTO user_role_id
        FROM auth.roles
        WHERE role_name = 'ROLE_USER';

        INSERT INTO auth.user_roles(user_id, role_id)
        VALUES (new.user_id, user_role_id);
    END IF;

    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER signup_trigger AFTER INSERT OR UPDATE ON auth.users
FOR EACH ROW EXECUTE PROCEDURE signup_user();

DROP TRIGGER signup_trigger ON auth.users;

