package com.B2Boost.RestAPIProblem.Repository;

import com.B2Boost.RestAPIProblem.Model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long>, PagingAndSortingRepository<Partner, Long> {
    boolean existsByRef(String ref);
}
