package com.B2Boost.RestAPIProblem.Service;

import com.B2Boost.RestAPIProblem.Model.Partner;
import com.B2Boost.RestAPIProblem.Repository.PartnerRepository;
import jakarta.transaction.Transactional;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PartnerService {

    @Autowired
    public PartnerRepository repo;

    public List<Partner> getAllPartners(){
        Pageable pages = PageRequest.of(0, 10, Sort.Direction.ASC, "id");

        return repo.findAll(pages).getContent();
    }
    public Partner savePartner(Partner partner){
        return repo.save(partner);
    }
    public Partner getPartnerById(Long id){
//        Optional <Partner> rec = repo.findById(id);
//
//        if(rec.isPresent()){
//            return rec.get();
//        } else {
//            throw new RuntimeException("Doesn't find by id ");
//        }
        return repo.findById(id).orElse(null);
    }

//    public void DeletePartnerById(Long id){
//         repo.deleteById(id);
//    }

    public void deletePartnerById(Long id) {
            repo.deleteById(id);
    }
//        } else {
//            return false; // Partner not found
//        }}

    public Partner UpdatePartner(Partner partner){
        return repo.save(partner);
    }

}
