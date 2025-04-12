CREATE TABLE tb_users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    creation_timestamp DATE DEFAULT CURRENT_DATE,
    update_timestamp DATE DEFAULT CURRENT_DATE
);