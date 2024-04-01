package com.B2Boost.RestAPIProblem.Service;

import com.B2Boost.RestAPIProblem.Model.Partner;
import com.B2Boost.RestAPIProblem.Repository.PartnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class PartnerServiceTest {

    @InjectMocks
    private PartnerService partnerService;

    @Mock
    private PartnerRepository partnerRepo;

    private Partner partner;
    private int from = 0;
    private int size = 10;

    @BeforeEach
    void init(){
        partner = new Partner();
        partner.setId(1L);
        partner.setCompanyName("B2Boost");
        partner.setRef("XXXXXX");

    }

    @Test
    void getAllPartners() {
        List<Partner> list = new ArrayList<>();
        list.add(partner);

        Page<Partner> page = new PageImpl<>(list);
        when(partnerRepo.findAll(any(Pageable.class))).thenReturn(page);
        List<Partner> listOfPartners = partnerService.getAllPartners(from, size);
        assertEquals(1, listOfPartners.size());
        assertNotNull(listOfPartners);
    }

    @Test
    void getPartnerById(){
        when(partnerRepo.findById(1L)).thenReturn(Optional.of(partner));

        Partner existingPartner = partnerService.getPartnerById(1L);
        assertNotNull(existingPartner);
        assertThat(existingPartner.getId()).isEqualTo(1L);
        assertEquals(existingPartner, partner);
    }

    @Test
    void savePartner(){
        when(partnerRepo.save(any(Partner.class))).thenReturn(partner);

        Partner savedPartner = partnerService.savePartner(partner);
        assertNotNull(savedPartner);
        assertThat(savedPartner.getCompanyName()).isEqualTo("B2Boost");
    }

    @Test
    void updatePartner(){
        Partner updatedPartnerTest = new Partner();
        updatedPartnerTest.setId(2L);
        updatedPartnerTest.setCompanyName("Speos");
        updatedPartnerTest.setRef("XXXXXX1");

        when(partnerRepo.existsById(2L)).thenReturn(true);
        when(partnerRepo.save(any(Partner.class))).thenReturn(updatedPartnerTest);
        Partner updatedPartner = partnerService.updatePartner(updatedPartnerTest);

        assertNotNull(updatedPartner);
        assertEquals("Speos", updatedPartner.getCompanyName());
        verify(partnerRepo).save(updatedPartnerTest);
    }

    @Test
    public void deletePartnerById() {
        Long id = partner.getId();

        when(partnerRepo.existsById(id)).thenReturn(true);
        partnerService.deletePartnerById(id);
        verify(partnerRepo, times(1)).deleteById(id);
    }
}
