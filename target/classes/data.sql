-- ================= ROLES =================
INSERT INTO roles (name, created_at, updated_at)
VALUES ('ADMIN', NOW(), NOW())
    ON CONFLICT (name) DO NOTHING;

INSERT INTO roles (name, created_at, updated_at)
VALUES ('CLIENT', NOW(), NOW())
    ON CONFLICT (name) DO NOTHING;

-- ================= ADMIN USER =================
INSERT INTO users (
    username,
    password_hash,
    email,
    full_name,
    active,
    created_at,
    updated_at
)
VALUES (
           'admin',
           '$2b$10$ZdOC9INd8wGvpCF5hYUFOuzCQ/.vWS3XypOFxl7/SJkJz6bVKLuJa', -- password hash for "password123"
           'admin@northwollo.et',
           'System Administrator',
           TRUE,
           NOW(),
           NOW()
       )
    ON CONFLICT (username) DO NOTHING;

-- ================= ASSIGN ADMIN ROLE =================
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u
         JOIN roles r ON r.name = 'ADMIN'
WHERE u.username = 'admin'
    ON CONFLICT DO NOTHING;

-- ================= CLIENT USER =================
INSERT INTO users (
    username,
    password_hash,
    email,
    full_name,
    active,
    created_at,
    updated_at
)
VALUES (
           'client',
           '$2b$10$ZdOC9INd8wGvpCF5hYUFOuzCQ/.vWS3XypOFxl7/SJkJz6bVKLuJa', -- can be same or different password
           'client@northwollo.et',
           'Default Client',
           TRUE,
           NOW(),
           NOW()
       )
    ON CONFLICT (username) DO NOTHING;

-- ================= ASSIGN CLIENT ROLE =================
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u
         JOIN roles r ON r.name = 'CLIENT'
WHERE u.username = 'client'
    ON CONFLICT DO NOTHING;
