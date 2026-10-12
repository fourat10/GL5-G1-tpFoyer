package tn.esprit.tpfoyer17.services;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tpfoyer17.entities.Universite;
import tn.esprit.tpfoyer17.repositories.UniversiteRepository;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UniversiteServiceIntegrationTest {

    @Autowired
    private IUniversiteService universiteService;

    @Autowired
    private UniversiteRepository universiteRepository;

    private Universite createUniversite() {
        return Universite.builder()
                .nomUniversite("Universite-" + UUID.randomUUID())
                .adresse("Tunis")
                .build();
    }

    // CREATE
    @Test
    void testAddUniversite() {
        Universite universite = createUniversite();

        Universite saved =
                universiteService.addUniversite(universite);

        assertNotNull(saved);
        assertTrue(saved.getIdUniversite() > 0);
        assertEquals(
                universite.getNomUniversite(),
                saved.getNomUniversite()
        );
    }

    // READ
    @Test
    void testGetUniversiteById() {
        Universite saved =
                universiteService.addUniversite(createUniversite());

        Universite found =
                universiteService.getUniversiteById(
                        saved.getIdUniversite()
                );

        assertNotNull(found);
        assertEquals(
                saved.getIdUniversite(),
                found.getIdUniversite()
        );
        assertEquals(
                saved.getNomUniversite(),
                found.getNomUniversite()
        );
    }

    // UPDATE
    @Test
    void testUpdateUniversite() {
        Universite saved =
                universiteService.addUniversite(createUniversite());

        saved.setNomUniversite("Universite-Modifiee");
        saved.setAdresse("Sousse");

        Universite updated =
                universiteService.updateUniversite(saved);

        assertEquals(
                "Universite-Modifiee",
                updated.getNomUniversite()
        );
        assertEquals("Sousse", updated.getAdresse());
    }

    // DELETE
    @Test
    void testDeleteUniversite() {
        Universite saved =
                universiteService.addUniversite(createUniversite());

        long id = saved.getIdUniversite();

        universiteService.deleteUniversite(id);

        assertFalse(universiteRepository.findById(id).isPresent());
    }
}
