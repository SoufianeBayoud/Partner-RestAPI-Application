package com.B2Boost.RestAPIProblem.Controller;

import com.B2Boost.RestAPIProblem.Exception.ErrorResponse;
import com.B2Boost.RestAPIProblem.Model.Partner;
import com.B2Boost.RestAPIProblem.Repository.PartnerRepository;
import com.B2Boost.RestAPIProblem.Service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partners")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;
    @Autowired
    private PartnerRepository partnerRepository;


    @GetMapping
    public ResponseEntity<Object> getAllPartners(){
//        Page partnersPage = partnerService.getAllPartners(PageRequest.of(0, 10)); // Page 0, Size 10
        List<Partner> partners = partnerService.getAllPartners();
        if (partners.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(500, "An internal error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(partnerService.getAllPartners(), HttpStatus.OK);


    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPartnerById(@PathVariable("id") Long id){
        Partner partner = partnerService.getPartnerById(id);

        if (partner == null) {
            return new ResponseEntity<>(new ErrorResponse(404, "Partner with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(partner, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> savePartner(Partner partner){
        if (partner == null) {
            return new ResponseEntity<>(new ErrorResponse(400, "Invalid request body"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(partnerService.savePartner(partner), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Partner> updatePartner(@PathVariable(value = "id") Long id, @RequestBody Partner partner){
        partner.setId(id);

        return new ResponseEntity<>(partnerService.UpdatePartner(partner), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePartnerById(@PathVariable(value = "id") Long id){
        if (partnerRepository.existsById(id)) {
            partnerService.deletePartnerById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse(404, "Partner with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
    }

}
