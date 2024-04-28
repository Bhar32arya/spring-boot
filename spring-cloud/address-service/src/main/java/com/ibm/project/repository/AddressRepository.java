package com.ibm.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ibm.project.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}