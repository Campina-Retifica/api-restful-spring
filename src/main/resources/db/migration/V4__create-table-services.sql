DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'offered_service_enum') THEN
        CREATE TYPE offered_service_enum AS ENUM ('PENDENTE', 'ATIVO', 'INATIVO');
    END IF;
END$$;

CREATE TABLE tb_services (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    service_value NUMERIC(10, 2) NOT NULL,
    status offered_service_enum DEFAULT 'PENDENTE',
    created TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);