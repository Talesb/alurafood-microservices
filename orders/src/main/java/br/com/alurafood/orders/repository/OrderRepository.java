package br.com.alurafood.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.alurafood.orders.model.OrderRequest;
import br.com.alurafood.orders.model.Status;

public interface OrderRepository extends JpaRepository<OrderRequest, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update OrderRequest o set o.status = :status where o = :order")
    void updateStatus(Status status, OrderRequest order);

    @Query(value = "SELECT o from OrderRequest o LEFT JOIN FETCH o.oderItems where o.id = :id")
    OrderRequest getByIdWithItems(Long id);


}
