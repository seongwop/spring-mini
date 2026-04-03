package org.example.mini.order;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @NonNull
    @Query(value = "select o from Order o join fetch o.product")
    Page<Order> findAll(@NonNull Pageable pageable);
}
