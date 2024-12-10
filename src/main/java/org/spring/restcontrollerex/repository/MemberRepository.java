package org.spring.restcontrollerex.repository;


import org.spring.restcontrollerex.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Long> {


    Optional<MemberEntity> findByEmail(String email);

//    Optional<MemberEntity> findByUserEmail(String email);
}
