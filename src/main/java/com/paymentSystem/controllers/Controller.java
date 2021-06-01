package com.paymentSystem.controllers;
import com.paymentSystem.models.PaymentModelDTO;
import com.paymentSystem.service.PaymentService;
import com.paymentSystem.pojos.RequestPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
public class Controller {
    @Autowired
    private PaymentService paymentservice;
    @GetMapping("/home")
    public String home(){
        return "this is the home page";
    }

    @GetMapping("/transaction_status/{userId}/{transactionId}")
    public String transactionStatus(@PathVariable @Valid String userId, @PathVariable @Valid String transactionId)
    {
        return paymentservice.transactionStatus(userId,transactionId);
    }
    @PutMapping("/pspMock/{userID}/{transactionId}")
    public String pspMock(@PathVariable String userID,@PathVariable String transactionId){
        return paymentservice.pspMock(userID, transactionId);
    }
    @PostMapping("/payment")
    public ResponseEntity<?> makePayment(@RequestBody @Valid RequestPojo request_pojo)
    {
        PaymentModelDTO user=paymentservice.makePayment(request_pojo);
        return new ResponseEntity<PaymentModelDTO>(user, HttpStatus.CREATED);
    }

    @GetMapping("transaction_history/{user_id}")
    public ResponseEntity<List<PaymentModelDTO>> transactionHistory(@PathVariable @Valid String user_id){
        List<PaymentModelDTO> list=paymentservice.transactionHistory(user_id);
        return new ResponseEntity<List<PaymentModelDTO>>(paymentservice.transactionHistory(user_id),HttpStatus.FOUND);
    }

}
