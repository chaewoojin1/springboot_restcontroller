package org.spring.restcontrollerex.repository;

import org.spring.restcontrollerex.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Long> {

//    @Modifying
//    @Query("update BoardEntity b set b.hit = b.hit+1 where b.id=:id ")
//    void updateHit(@Param("id") Long id);

//    @Override
//    <S extends BoardEntity> S save(S entity);
//
//    @Override
//    List<BoardEntity> findAll();
}
