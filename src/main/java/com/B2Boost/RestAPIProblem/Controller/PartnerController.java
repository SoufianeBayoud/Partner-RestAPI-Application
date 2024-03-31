package com.B2Boost.RestAPIProblem.Controller;

import com.B2Boost.RestAPIProblem.Exception.BadRequestException;
import com.B2Boost.RestAPIProblem.Exception.ErrorResponse;
import com.B2Boost.RestAPIProblem.Exception.InternalServerErrorException;
import com.B2Boost.RestAPIProblem.Exception.ResourceNotFoundException;
import com.B2Boost.RestAPIProblem.Model.Partner;
import com.B2Boost.RestAPIProblem.Repository.PartnerRepository;
import com.B2Boost.RestAPIProblem.Service.PartnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    public ResponseEntity<Object> getAllPartners(@RequestParam(defaultValue = "0") int from,
                                                 @RequestParam(defaultValue = "10") int size){
//        Page partnersPage = partnerService.getAllPartners(PageRequest.of(0, 10)); // Page 0, Size 10
        List<Partner> partners = partnerService.getAllPartners(from, size);
        if (partners.isEmpty()) {
            throw new BadRequestException();
        }
        return new ResponseEntity<>(partnerService.getAllPartners(from, size), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPartnerById(@PathVariable("id") Long id) {
        Partner partner = partnerService.getPartnerById(id);

//        if (partner == null) {
//            throw new ResourceNotFoundException(id);
//        }
        return new ResponseEntity<>(partner, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> savePartner(@Valid @RequestBody Partner partner){



            return new ResponseEntity<>(partnerService.savePartner(partner), HttpStatus.CREATED);



    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Partner> updatePartner(@PathVariable(value = "id") Long id, @RequestBody Partner partner){

        //1.
//        try{
//            partner.setId(id);
//            return new ResponseEntity<>(partnerService.UpdatePartner(partner), HttpStatus.OK);
//        } catch(BadRequestException e){
//            throw new BadRequestException();
//        } catch(ResourceNotFoundException e){
//            throw new ResourceNotFoundException(id);
//        } catch(InternalServerErrorException e){
//            throw new InternalServerErrorException();
//        }
        //2.
//        try{
            partner.setId(id);
            return new ResponseEntity<>(partnerService.UpdatePartner(partner), HttpStatus.OK);
//        } catch(InternalServerErrorException e){
//            throw e;
//        } catch(Exception e){
//            throw new BadRequestException();
//        }
        //partner.setId(id);



    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePartnerById(@PathVariable(value = "id") Long id){

        //if (partnerRepository.existsById(id)) {
            partnerService.deletePartnerById(id);
            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            throw new ResourceNotFoundException(id);
//        }
    }

}
