
package tn.esprit.tpfoyer17.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer17.entities.Universite;
import tn.esprit.tpfoyer17.repositories.FoyerRepository;
import tn.esprit.tpfoyer17.repositories.UniversiteRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UniversiteServiceMockitoTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private UniversiteService universiteService;

    private Universite createUniversite() {
        return Universite.builder()
                .nomUniversite("Universite-Test")
                .adresse("Tunis")
                .build();
    }

    // CREATE
    @Test
    void testAddUniversite() {
        Universite universite = createUniversite();

        when(universiteRepository.save(any(Universite.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Universite result =
                universiteService.addUniversite(universite);

        assertNotNull(result);
        assertEquals("Universite-Test", result.getNomUniversite());
        assertEquals("Tunis", result.getAdresse());

        verify(universiteRepository).save(universite);
    }

    // READ
    @Test
    void testGetUniversiteById() {
        Universite universite = createUniversite();

        when(universiteRepository.findById(1L))
                .thenReturn(Optional.of(universite));

        Universite result =
                universiteService.getUniversiteById(1L);

        assertNotNull(result);
        assertEquals("Universite-Test", result.getNomUniversite());

        verify(universiteRepository).findById(1L);
    }

    // UPDATE
    @Test
    void testUpdateUniversite() {
        Universite universite = createUniversite();
        universite.setAdresse("Sousse");

        when(universiteRepository.save(any(Universite.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Universite result =
                universiteService.updateUniversite(universite);

        assertEquals("Sousse", result.getAdresse());

        verify(universiteRepository).save(universite);
    }

    // DELETE
    @Test
    void testDeleteUniversite() {
        doNothing().when(universiteRepository).deleteById(1L);

        universiteService.deleteUniversite(1L);

        verify(universiteRepository).deleteById(1L);
    }
}
