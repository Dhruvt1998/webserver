package com.example.hms.repository;

import com.example.hms.Middleware.ApplicationStub.HospitalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KrankenhausSystemRepository extends JpaRepository<HospitalInfo,Integer> {

}
