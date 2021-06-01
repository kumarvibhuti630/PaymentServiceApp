package com.paymentSystem.repos;

import com.paymentSystem.models.PaymentModelDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentModelDTO,Long> {

    @Query(value = "select * from paymentmodeldto p where p.user_id =?1 and p.transaction_id=?2 ",nativeQuery = true)
    Optional<PaymentModelDTO> findByUserIDAndTransactionID(@Param("user_id") String userId, @Param("transaction_id") String transactionId);

    @Query(value="select * from paymentmodeldto p where p.user_id=?1",nativeQuery = true)
    List<PaymentModelDTO> findByUserID(@Param("user_id") String userId);

}
