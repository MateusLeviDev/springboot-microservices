package com.levi.java.backend.repository;

import com.levi.java.backend.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    List<Shop> findAllByUserIdentifier(String userIdentifier);

    List<Shop> findAllByDateGreaterThanEquals(Float total);

    List<Shop> findAllByDateGreaterThan(LocalDateTime date);

}
