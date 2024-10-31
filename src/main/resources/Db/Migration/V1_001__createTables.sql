ALTER TABLE kargo
    ADD COLUMN gonderici_id INT REFERENCES musteri(musteri_id) ON DELETE SET NULL,
    ADD COLUMN alici_id INT REFERENCES musteri(musteri_id) ON DELETE SET NULL;


ALTER TABLE kargo_sistemi.kargo
    ADD COLUMN teslim_eden_calisan_id INT REFERENCES calisan(calisan_id) ON DELETE SET NULL;








SELECT table_schema, table_name
FROM information_schema.tables
WHERE table_schema = 'kargo_sistemi';
