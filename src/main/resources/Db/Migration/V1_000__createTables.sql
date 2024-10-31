CREATE TABLE kargo_sistemi.kargo (
                       kargo_id SERIAL PRIMARY KEY,
                       kargo_no VARCHAR(20) NOT NULL UNIQUE,
                       gonderici_adi VARCHAR(100) NOT NULL,
                       gonderici_soyadi VARCHAR(100) NOT NULL,
                       gonderici_telefon VARCHAR(20) NOT NULL CHECK (gonderici_telefon ~ '^[0-9]+$'),
                       alici_adi VARCHAR(100) NOT NULL,
                       alici_soyadi VARCHAR(100) NOT NULL,
                       alici_telefon VARCHAR(20) NOT NULL CHECK (alici_telefon ~ '^[0-9]+$'),
                       alici_adres TEXT NOT NULL,
                       kargo_durumu VARCHAR(50) DEFAULT 'Hazırlanıyor',
                       gonderim_tarihi TIMESTAMP,
                       teslim_tarihi TIMESTAMP,
                       urunler JSONB NOT NULL,
                       toplam_agirlik DECIMAL(5, 2) CHECK (toplam_agirlik >= 0),
                       ucret DECIMAL(10, 2) CHECK (ucret >= 0),
                       olusturma_tarihi TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE kargo_sistemi.kargo
    ADD COLUMN created_by VARCHAR(50),
    ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN updated_by VARCHAR(50),
    ADD COLUMN updated_at TIMESTAMP,
    ADD COLUMN deleted_by VARCHAR(50),
    ADD COLUMN deleted_at TIMESTAMP,
    ADD COLUMN version INT DEFAULT 1;


CREATE TABLE kargo_sistemi.kargo_takip (
                             takip_id SERIAL PRIMARY KEY,
                             kargo_id INT NOT NULL REFERENCES kargo_sistemi.kargo(kargo_id) ON DELETE CASCADE,
                             konum TEXT NOT NULL,
                             durum VARCHAR(50) NOT NULL,
                             aciklama TEXT,
                             guncelleme_tarihi TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE kargo_sistemi.kargo_takip
    ADD COLUMN created_by VARCHAR(50),
    ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN updated_by VARCHAR(50),
    ADD COLUMN updated_at TIMESTAMP,
    ADD COLUMN deleted_by VARCHAR(50),
    ADD COLUMN deleted_at TIMESTAMP,
    ADD COLUMN version INT DEFAULT 1;


CREATE TABLE kargo_sistemi.musteri (
                         musteri_id SERIAL PRIMARY KEY,
                         adi VARCHAR(100) NOT NULL,
                         soyadi VARCHAR(100) NOT NULL,
                         telefon VARCHAR(20) NOT NULL CHECK (telefon ~ '^[0-9]+$'),
                         adres TEXT NOT NULL,
                         email VARCHAR(100) UNIQUE
);

ALTER TABLE kargo_sistemi.musteri
    ADD COLUMN created_by VARCHAR(50),
    ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN updated_by VARCHAR(50),
    ADD COLUMN updated_at TIMESTAMP,
    ADD COLUMN deleted_by VARCHAR(50),
    ADD COLUMN deleted_at TIMESTAMP,
    ADD COLUMN version INT DEFAULT 1;

CREATE TABLE kargo_sistemi.calisan (
                         calisan_id SERIAL PRIMARY KEY,
                         adi VARCHAR(100) NOT NULL,
                         soyadi VARCHAR(100) NOT NULL,
                         telefon VARCHAR(20) NOT NULL CHECK (telefon ~ '^[0-9]+$'),
                         gorev VARCHAR(50) NOT NULL,
                         ise_baslama_tarihi TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE kargo_sistemi.arac (
                      arac_id SERIAL PRIMARY KEY,
                      plaka VARCHAR(10) NOT NULL UNIQUE,
                      tur VARCHAR(50) NOT NULL,
                      kapasite DECIMAL(5, 2) CHECK (kapasite >= 0),
                      atanan_calisan_id INT REFERENCES kargo_sistemi.calisan(calisan_id) ON DELETE SET NULL,
                      kayit_tarihi TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE kargo_sistemi.calisan
    ADD COLUMN created_by VARCHAR(50),
    ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN updated_by VARCHAR(50),
    ADD COLUMN updated_at TIMESTAMP,
    ADD COLUMN deleted_by VARCHAR(50),
    ADD COLUMN deleted_at TIMESTAMP,
    ADD COLUMN version INT DEFAULT 1;

ALTER TABLE kargo_sistemi.arac
    ADD COLUMN created_by VARCHAR(50),
    ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN updated_by VARCHAR(50),
    ADD COLUMN updated_at TIMESTAMP,
    ADD COLUMN deleted_by VARCHAR(50),
    ADD COLUMN deleted_at TIMESTAMP,
    ADD COLUMN version INT DEFAULT 1;


