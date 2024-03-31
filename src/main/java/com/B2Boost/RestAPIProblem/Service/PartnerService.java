package com.B2Boost.RestAPIProblem.Service;

import com.B2Boost.RestAPIProblem.Exception.BadRequestException;
import com.B2Boost.RestAPIProblem.Exception.ResourceNotFoundException;
import com.B2Boost.RestAPIProblem.Model.Partner;
import com.B2Boost.RestAPIProblem.Repository.PartnerRepository;
import jakarta.transaction.Transactional;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public List<Partner> getAllPartners(int from, int size)  {
        Pageable pages = PageRequest.of(from, size, Sort.Direction.ASC, "id");


        //return repo.findAll(pages).getContent();
        List<Partner> partners = repo.findAll(pages).getContent();
//        if(partners.isEmpty()){
//            throw new BadRequestException();
//        } else {
//            return partners;
//        }
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
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }


    public void deletePartnerById(Long id) {

        if(repo.existsById(id)){
            repo.deleteById(id);
        } else {
            throw new ResourceNotFoundException(id);
        }
        //Ici on peut utiliser Optional partner = repo.findById et ensuite throw si y a une exception
    }

    public Partner UpdatePartner(Partner partner){
        if(repo.existsById(partner.getId())){
            return repo.save(partner);
        } else {
            throw new ResourceNotFoundException(partner.getId());
        }

        //Pour le 404, je ne sais pas si c'est logique qu'on fasse le 404 car si il existe pas il sera créé non ?
    }

}
