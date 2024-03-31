package com.B2Boost.RestAPIProblem.Service;

import com.B2Boost.RestAPIProblem.Model.Partner;
import com.B2Boost.RestAPIProblem.Repository.PartnerRepository;
import jakarta.servlet.http.Part;
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
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class PartnerServiceTest {

    @InjectMocks
    private PartnerService partnerService;

    @Mock
    private PartnerRepository partnerRepo;

    private Partner partner;

    @BeforeEach
    void init(){
        partner = new Partner();
        partner.setId(1L);
        partner.setCompanyName("B2Boost");
        partner.setRef("xxxxxx");
//        partner.setLocale(Locale.of("en_GB"));

    }

    @Test
    void getAllPartners() {
        List<Partner> list = new ArrayList<>();
        list.add(partner);

        Page<Partner> page = new PageImpl<>(list);
        when(partnerRepo.findAll(any(Pageable.class))).thenReturn(page);
        //List<Partner> listOfPartners = partnerService.getAllPartners();
        //assertEquals(1, listOfPartners.size());
        //assertNotNull(listOfPartners);
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
    void UpdatePartner(){
        partner.setCompanyName("Speos");
        partner.setId(2L);
        partner.setRef("xxxxxx1");

        when(partnerRepo.save(any(Partner.class))).thenReturn(partner); //Pourquoi on doit Mock celui la ?
        Partner updatedPartner = partnerService.UpdatePartner(partner);

        assertNotNull(updatedPartner);
        assertEquals("Speos", updatedPartner.getCompanyName());
    }


    @Test
    void deletePartner(){
        doNothing().when(partnerRepo).deleteById(anyLong());
        partnerService.deletePartnerById(1L);
        verify(partnerRepo, times(1)).deleteById(1L);
    }




}
