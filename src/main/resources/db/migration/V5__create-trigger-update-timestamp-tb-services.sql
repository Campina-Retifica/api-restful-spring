CREATE OR REPLACE FUNCTION update_timestamp_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_timestamp
BEFORE UPDATE ON tb_services
FOR EACH ROW
EXECUTE FUNCTION update_timestamp_column();