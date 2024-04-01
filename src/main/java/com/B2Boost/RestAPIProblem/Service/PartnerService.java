package com.B2Boost.RestAPIProblem.Service;


import com.B2Boost.RestAPIProblem.Exception.DuplicateDataException;
import com.B2Boost.RestAPIProblem.Exception.ResourceNotFoundException;
import com.B2Boost.RestAPIProblem.Model.Partner;
import com.B2Boost.RestAPIProblem.Repository.PartnerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class PartnerService {

    @Autowired
    public PartnerRepository repo;

    public List<Partner> getAllPartners(int from, int size)  {
        Pageable pages = PageRequest.of(from, size, Sort.Direction.ASC, "id");

        return repo.findAll(pages).getContent();
    }
    public Partner savePartner(Partner partner){
        if (repo.existsByRef(partner.getRef())) {
            throw new DuplicateDataException();
        }

        return repo.save(partner);
    }
    public Partner getPartnerById(Long id){

        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }


    public void deletePartnerById(Long id) {
        if(repo.existsById(id)){
            repo.deleteById(id);
        } else {
            throw new ResourceNotFoundException(id);
        }

    }

    public Partner updatePartner(Partner partner){
        if(repo.existsById(partner.getId())){
            return repo.save(partner);
        } else {
            throw new ResourceNotFoundException(partner.getId());
        }

    }
}
