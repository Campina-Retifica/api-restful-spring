CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_tb_provided_services
    BEFORE UPDATE ON tb_provided_services
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();