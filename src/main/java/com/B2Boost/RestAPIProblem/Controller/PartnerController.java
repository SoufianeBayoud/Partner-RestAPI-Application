
package com.B2Boost.RestAPIProblem.Controller;

import com.B2Boost.RestAPIProblem.Exception.BadRequestException;
import com.B2Boost.RestAPIProblem.Model.Partner;
import com.B2Boost.RestAPIProblem.Service.PartnerService;

import jakarta.validation.Valid;
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

    @GetMapping
    public ResponseEntity<Object> getAllPartners(@RequestParam(defaultValue = "0") int from,
                                                 @RequestParam(defaultValue = "10") int size){
        List<Partner> partners = partnerService.getAllPartners(from, size);
        if (partners.isEmpty()) {
            throw new BadRequestException();
        }

        return new ResponseEntity<>(partnerService.getAllPartners(from, size), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPartnerById(@PathVariable("id") Long id) {
        Partner partner = partnerService.getPartnerById(id);

        return new ResponseEntity<>(partner, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> savePartner(@Valid @RequestBody Partner partner){

        return new ResponseEntity<>(partnerService.savePartner(partner), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Partner> updatePartner(@Valid @PathVariable(value = "id") Long id, @RequestBody Partner partner){

        partner.setId(id);
        return new ResponseEntity<>(partnerService.updatePartner(partner), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePartnerById(@Valid @PathVariable(value = "id") Long id){

        partnerService.deletePartnerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
