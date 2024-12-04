package com.example.Cargo.Service;

import com.example.Cargo.Entity.Kargo;
import com.example.Cargo.Entity.KargoTakip;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class KargoTakipServiceTest {

    static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"));
    static GenericContainer<?> redisContainer = new GenericContainer<>(DockerImageName.parse("redis:latest"))
            .withExposedPorts(6379);

    @Autowired
    private KargoTakipService kargoTakipService;

    private Kargo kargo;

    @BeforeEach
    public void setUp() {
        postgresContainer.start();
        redisContainer.start();
        kargoTakipService.deleteAllKargoTakip(); // Tüm takip kayıtlarını temizle
        kargo = new Kargo();
        kargo.setKargoId(1L);
    }

    @AfterEach
    public void tearDown() {
        postgresContainer.stop();
        redisContainer.stop();
    }

    @Test
    public void testSaveKargoTakip() {
        KargoTakip kargoTakip = new KargoTakip();
        kargoTakip.setKargo(kargo);
        kargoTakip.setKonum("İstanbul");
        kargoTakip.setTarih(LocalDateTime.now());
        kargoTakip.setAciklama("Kargo yola çıktı");

        // Act
        kargoTakipService.saveKargoTakip(kargoTakip);
        List<KargoTakip> takipler = kargoTakipService.getAllKargoTakip();

        // Assert
        assertThat(takipler).hasSize(1);
        assertThat(takipler.get(0).getKonum()).isEqualTo("İstanbul");
    }

    @Test
    public void testGetAllKargoTakip() {
        KargoTakip kargoTakip1 = new KargoTakip();
        kargoTakip1.setKargo(kargo);
        kargoTakip1.setKonum("İstanbul");
        kargoTakip1.setTarih(LocalDateTime.now());
        kargoTakip1.setAciklama("Kargo dağıtım merkezine ulaştı");

        KargoTakip kargoTakip2 = new KargoTakip();
        kargoTakip2.setKargo(kargo);
        kargoTakip2.setKonum("Bursa");
        kargoTakip2.setTarih(LocalDateTime.now());
        kargoTakip2.setAciklama("Kargo teslim edildi");

        kargoTakipService.saveKargoTakip(kargoTakip1);
        kargoTakipService.saveKargoTakip(kargoTakip2);

        // Act
        List<KargoTakip> takipler = kargoTakipService.getAllKargoTakip();

        // Assert
        assertThat(takipler).hasSize(2);
    }

    @Test
    public void testGetKargoTakipByKargoId() {
        KargoTakip kargoTakip = new KargoTakip();
        kargoTakip.setKargo(kargo);
        kargoTakip.setKonum("İzmir");
        kargoTakip.setTarih(LocalDateTime.now());
        kargoTakip.setAciklama("Kargo işleme alındı");

        kargoTakipService.saveKargoTakip(kargoTakip);

        // Act
        List<KargoTakip> takipler = kargoTakipService.getKargoTakipByKargoId(kargo.getKargoId());

        // Assert
        assertThat(takipler).isNotEmpty();
        assertThat(takipler.get(0).getKonum()).isEqualTo("İzmir");
    }

    @Test
    public void testDeleteKargoTakip() {
        KargoTakip kargoTakip = new KargoTakip();
        kargoTakip.setKargo(kargo);
        kargoTakip.setKonum("Ankara");
        kargoTakip.setTarih(LocalDateTime.now());
        kargoTakip.setAciklama("Kargo teslim edildi");

        kargoTakipService.saveKargoTakip(kargoTakip);

        // Act
        kargoTakipService.deleteKargoTakip(kargoTakip.getTakipId());
        List<KargoTakip> result = kargoTakipService.getKargoTakipByKargoId(kargoTakip.getKargo().getKargoId());

        // Assert
        assertThat(result).isEmpty();
    }

    @Test
    public void testCacheKargoTakip() {
        // Redis ile önbelleğe almanın test edilmesi
        // Bu test ileride Redis entegrasyonu tamamlandıktan sonra yazılacaktır.
    }
}
