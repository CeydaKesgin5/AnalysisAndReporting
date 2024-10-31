package com.example.Analysis_and_Reporting.Service;

import com.example.Analysis_and_Reporting.Entity.Musteri;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class MusteriServiceTest {

    static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"));
    static GenericContainer<?> redisContainer = new GenericContainer<>(DockerImageName.parse("redis:latest"))
            .withExposedPorts(6379);

    @Autowired
    private MusteriService musteriService;

    @BeforeEach
    public void setUp() {
        postgresContainer.start();
        redisContainer.start();
        musteriService.deleteAllMusteri();
        // Veritabanı bağlantı ayarlarını ayarlayın (application-test.yml dosyasında ayarlanmış olmalı)
    }

    @AfterEach
    public void tearDown() {
        postgresContainer.stop();
        redisContainer.stop();
    }

    @Test
    public void testSaveMusteri() {
        Musteri musteri = new Musteri("Ali", "Yılmaz", "1234567890", "Adres");
        musteriService.saveMusteri(musteri);
        List<Musteri> musteriler = musteriService.getAllMusteris();

        // Beklenen boyut
        Assertions.assertThat(musteriler).hasSize(1); // Beklenen sonuç
        // Beklenen veriyi kontrol et
        Assertions.assertThat(musteriService.getMusteriById(musteri.getId())).isNotNull();
    }

    @Test
    public void testGetAllMusteris() {
        musteriService.saveMusteri(new Musteri("Ali", "Yılmaz", "1234567890", "Adres"));
        musteriService.saveMusteri(new Musteri("Ayşe", "Demir", "0987654321", "Adres 2"));

        // Act
        List<Musteri> musteriler = musteriService.getAllMusteris();

        // Assert
        assertThat(musteriler).hasSize(2); // Beklenen boyut
    }

    @Test
    public void testUpdateMusteri() {
        // İlk olarak bir müşteri oluştur
        Musteri musteri = new Musteri("Ali", "Yılmaz", "1234567890", "Adres");
        musteriService.saveMusteri(musteri);

        // Müşteri adını güncelle
        musteri.setAdi("Veli");
        musteriService.updateMusteri(musteri);

        // Müşteriyi ID'siyle al ve adının "Veli" olduğundan emin ol
        String updatedAd = musteriService.getMusteriById(musteri.getId())
                .map(Musteri::getAdi) // Optional'dan müşteri al
                .orElse(null); // Eğer müşteri yoksa null döndür

        assertThat(updatedAd).isEqualTo("Veli");}

    @Test
    public void testDeleteMusteri() {
        Musteri musteri = new Musteri("Ali", "Yılmaz", "1234567890", "Adres");
        musteriService.saveMusteri(musteri);

        musteriService.deleteMusteri(musteri.getId());

        assertThat(musteriService.getMusteriById(musteri.getId())).isNull();
    }

    @Test
    public void testCacheMusteri() {
        // Redis ile önbelleğe almanın test edilmesi
        // Redis ile entegrasyon testi yazılacak
    }
}
