package com.example.Cargo.Service;

import com.example.Cargo.Entity.Kargo;
import com.example.Cargo.Repository.KargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class KargoService {
    private final KargoRepository kargoRepository;
    RestClient restClient;
    @Autowired
    public KargoService(KargoRepository kargoRepository) {
        this.kargoRepository = kargoRepository;
    }

    public Kargo saveKargo(Kargo kargo) {
        return kargoRepository.save(kargo);
    }

    public List<Kargo> getAllKargos() {
        return kargoRepository.findAll();
    }

    public Optional<Kargo> getKargoById(Long id) {
        return kargoRepository.findById(id);
    }

    public void deleteKargo(Long id) {
        kargoRepository.deleteById(id);
    }

    public Kargo createNewKargo(Kargo kargo) {
        try {
            // RestClient kullanımı
            Kargo savedKargo = restClient.post() // RestClient'in post() metodu
                    .uri("http://192.168.137.179:8011/api/kargo/create")  // Kargo mikroservisindeki "/create" endpoint'i
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(kargo)  // Gönderilecek veri
                    .retrieve()  // Yanıtı al
                    .body(Kargo.class);  // Gelen yanıtı Kargo sınıfına dönüştür

            // Eğer kargo başarıyla oluşturulduysa
            if (savedKargo != null) {
                // 2. Adım: Notification servisine kargo ile ilgili bildirim gönderiliyor
                restClient.post()  // Notification servisine POST isteği gönderiliyor
                        .uri("http://192.168.137.111:8004/api/v1/notification")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(savedKargo)  // Gönderilecek kargo bilgisi
                        .retrieve()  // Bildirim servisine yanıt alınıyor
                        .body(Void.class);  // Bildirim servisi yanıtı önemli değil

                return savedKargo;  // Kargo oluşturulduktan sonra kargo verisini döndür
            }
            return null;  // Kargo oluşturulamadıysa null döndür

        } catch (Exception e) {
            // Hata durumunda loglama yapılabilir
            System.err.println("Kargo uzak bilgisayarda yaratılırken hata oluştu: " + e.getMessage());
            e.printStackTrace();  // Hata hakkında daha fazla bilgi
            return null;  // Hata durumunda null döndür
        }
    }




}
