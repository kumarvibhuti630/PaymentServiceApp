package com.paymentSystem.service;
import com.paymentSystem.customExceptions.ErrorResponse;
import com.paymentSystem.enums.transactionStatus;
import com.paymentSystem.models.PaymentModelDTO;
import com.paymentSystem.repos.PaymentRepository;
import com.paymentSystem.pojos.RequestPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.Random;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    public PaymentModelDTO getUserByTransactionAndUserId(String userId, String transactionId){
        return paymentRepository.findByUserIDAndTransactionID(userId,transactionId).
                orElseThrow();
    }

    public List<PaymentModelDTO> getUserByUserId(String userId){
        return paymentRepository.findByUserID(userId);
    }

    @Transactional
    public PaymentModelDTO makePayment(@Valid RequestPojo requestPojo){
        PaymentModelDTO paymentModelDTO=new PaymentModelDTO();
        paymentModelDTO.setOrderId(requestPojo.getOrderId());
        paymentModelDTO.setOrderAmount(requestPojo.getOrderAmount());
        paymentModelDTO.setCurrency(requestPojo.getCurrency());
        paymentModelDTO.setUserId(requestPojo.getUserId());
        UUID transactionId = UUID.randomUUID();
        paymentModelDTO.setTransactionId(transactionId.toString());
        paymentModelDTO.setTransactionStatus(transactionStatus.Initiated);
        paymentModelDTO.setCreated(new Timestamp(System.currentTimeMillis()));
        paymentModelDTO.setModified(new Timestamp(System.currentTimeMillis()));
        paymentRepository.save(paymentModelDTO);
       return paymentModelDTO;
    }
    @Transactional
    public String pspMock(String userId,String transactionId){
        PaymentModelDTO user= getUserByTransactionAndUserId(userId,transactionId);
        int pick = new Random().nextInt(transactionStatus.values().length);
        user.setTransactionStatus(transactionStatus.values()[pick]);
        user.setModified(new Timestamp(System.currentTimeMillis()));
        paymentRepository.save(user);
        return "Transaction ID:"+user.getTransactionId()+"\n"+
                "Transaction Status:"+ user.getTransactionStatus();
    }

    public List<PaymentModelDTO> transactionHistory(String userId){
        List<PaymentModelDTO> list= getUserByUserId(userId);
        if (list.size()==0){
            throw new ErrorResponse("404","No such User exist");
        }
        return list;
    }

    public String transactionStatus(String userId, String transactionId){
        PaymentModelDTO user= getUserByTransactionAndUserId(userId,transactionId);
        return "Transaction_id"+transactionId+"\n"+
                "Transaction_status:"+ user.getTransactionStatus();
    }
}
