package webuildit.myStartup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import webuildit.myStartup.dto.CreditcardtransactionDTO;
import webuildit.myStartup.repository.TransactionRepository;
import webuildit.myStartup.service.CreditcardtransactionService;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ccts")
public class CreditcardtransactionControllerImpl implements CreditcardtransactionController {

    CreditcardtransactionService creditcardtransactionService;


    public CreditcardtransactionControllerImpl(CreditcardtransactionService creditcardtransactionService){
        this.creditcardtransactionService = creditcardtransactionService;
    }

    @Override
    @PostMapping
    public CreditcardtransactionDTO addCreditcardtransaction(@Valid @RequestBody CreditcardtransactionDTO creditcardtransactionDTO) {
        try {
            return this.creditcardtransactionService.addCreditcardtransaction(creditcardtransactionDTO);
        } catch (ConstraintViolationException constraintViolationException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @GetMapping("/{tuuid}")
    public CreditcardtransactionDTO getCreditcardtransactionByUUID(@PathVariable("tuuid") UUID tUuid) {
        return this.creditcardtransactionService.getCreditcardtransactionByUUID(tUuid);
    }

    @Override
    @GetMapping
    public List<CreditcardtransactionDTO> getAllCreditcardtransaction() {
        return this.creditcardtransactionService.getAllCreditcardtransaction();
    }

    @Override
    @PutMapping("/{tuuid}")
    public CreditcardtransactionDTO updateCreditcardtransaction(@PathVariable("tuuid") UUID tUuid, @Valid @RequestBody CreditcardtransactionDTO creditcardtransactionDTO) {
        creditcardtransactionDTO.setTUuid(tUuid);
        try {
            return this.creditcardtransactionService.updateCreditcardtransaction(creditcardtransactionDTO);
        } catch (EntityNotFoundException notFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException noSuchElementException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @DeleteMapping("/{tuuid}")
    public void removeCreditcardtransactionById(@PathVariable("tuuid") UUID tUuid) {
        try {
            this.creditcardtransactionService.removeCreditcardtransactionById(tUuid);
        } catch (EntityNotFoundException notFoundException){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
