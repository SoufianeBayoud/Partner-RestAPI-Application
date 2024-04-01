package com.B2Boost.RestAPIProblem.ControllerTest;

import com.B2Boost.RestAPIProblem.Model.Partner;
import com.B2Boost.RestAPIProblem.Repository.PartnerRepository;
import com.B2Boost.RestAPIProblem.Service.PartnerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PartnerControllerTest {

    @MockBean
    private PartnerService partnerService;
    @MockBean
    private PartnerRepository repo;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private Partner partner;
    private static final int FROM = 0;

    private static final int SIZE = 10;

    @BeforeEach
    void init(){
        partner = new Partner();
        partner.setId(1L);
        partner.setCompanyName("B2Boost");
        partner.setRef("xxxxxx");
    }

    @Test
    void shouldFetchAllPartners() throws Exception {
        List<Partner> list = Arrays.asList(partner);
        when(partnerService.getAllPartners(FROM, SIZE)).thenReturn(list);
        this.mockMvc.perform(get("/api/partners"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(list.size())));
    }

    @Test
    void shouldFetchOneRecipeById() throws Exception {

        when(partnerService.getPartnerById(anyLong())).thenReturn(partner);
        this.mockMvc.perform(get("/api/partners/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName", is(partner.getCompanyName())))
                .andExpect(jsonPath("$.ref", is(partner.getRef())));
    }

    @Test
    void shouldCreateNewRecipe() throws Exception {

        when(partnerService.savePartner(any(Partner.class))).thenReturn(partner);
        this.mockMvc.perform(post("/api/partners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(partner)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.companyName", is(partner.getCompanyName())))
                .andExpect(jsonPath("$.ref", is(partner.getRef())));
    }

    @Test
    void shouldUpdatePartner() throws Exception {
        Partner updatedPartner = new Partner();
        updatedPartner.setId(2L);
        updatedPartner.setCompanyName("Speos");
        updatedPartner.setRef("YYYYYY");

        when(partnerService.updatePartner(any(Partner.class))).thenReturn(updatedPartner);
        this.mockMvc.perform(put("/api/partners/update/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPartner)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.companyName", is("Speos")))
                .andExpect(jsonPath("$.ref", is("YYYYYY")));
    }

    @Test
    void shouldDeletePartnerById() throws Exception {
        when(repo.existsById(anyLong())).thenReturn(true);
        doNothing().when(partnerService).deletePartnerById(anyLong());

        this.mockMvc.perform(delete("/api/partners/delete/{id}", 1L))
                .andExpect(status().isOk());
    }
}