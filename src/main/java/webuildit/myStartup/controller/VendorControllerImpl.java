package webuildit.myStartup.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import webuildit.myStartup.dto.VendorDTO;
import webuildit.myStartup.service.VendorService;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/v1/vendors")
public class VendorControllerImpl implements VendorController{

    private VendorService vendorService;
    private CustomerController customerController;

    public VendorControllerImpl(VendorService vendorService, CustomerController customerController){
        this.vendorService = vendorService;
        this.customerController = customerController;
    }

    @Override
    @PostMapping
    public VendorDTO addVendor(@Valid @RequestBody VendorDTO vendorDTO) {
        try {
            return this.vendorService.addVendor(vendorDTO);
        } catch (ConstraintViolationException constraintViolationException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @GetMapping("/{vuuid}")
    public VendorDTO getVendorByUUID(@PathVariable("vuuid") UUID vUuid) {

        return this.vendorService.getVendorByUUID(vUuid);
    }

    @GetMapping
    public List<VendorDTO> getAllVendors() {
        return this.vendorService.getAllVendors();
    }

    @Override
    @PutMapping("/{vuuid}")
    public VendorDTO updateVendor(@PathVariable("vuuid") UUID vUuid, @Valid @RequestBody VendorDTO vendorDTO) {
        vendorDTO.setVUuid(vUuid);
        try {
            return this.vendorService.updateVendor(vendorDTO);
        } catch (EntityNotFoundException notFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException noSuchElementException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @DeleteMapping("/{vuuid}")
    public void removeVendorById(@PathVariable("vuuid") UUID vUuid) {
        try {
            this.vendorService.removeVendorById(vUuid);
        } catch (EntityNotFoundException notFoundException){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    @GetMapping("/1.1/{vuuid}")
    public String getFeeForVendor(@PathVariable("vuuid")UUID vUuid) {
        String sum = vendorService.getFeeForVendor(vUuid);

        return sum;
    }

    @Override
    @GetMapping("/1.2/{month}/{year}")
    public String findSumOfAllTransactionsByDay(@PathVariable("month") int month, @PathVariable("year") int year) {
        String sum = String.valueOf(vendorService.findSumOfAllTransactionsByDay(month, year));
        return sum;
    }

    @Override
    @GetMapping("/1.3/{month}/{year}")
    public List<String> findTop3Desc(@PathVariable("month") int month, @PathVariable("year") int year) {
        List<String> tmp = vendorService.findTop3Desc(month, year);
        return tmp;
    }

    @Override
    @GetMapping("/1.3.1/{month}/{year}")
    public List<String> findAllTop3Asc(@PathVariable("month") int month, @PathVariable("year") int year) {
        List<String> tmp = vendorService.findAllTop3Asc(month, year);
        return tmp;
    }

    @Override
    @GetMapping("/1.5/{month}/{year}")
    public String compareIncomeBeetweenOneMonth(@PathVariable("month") int month, @PathVariable("year") int year) {
        String tmp = String.valueOf(vendorService.compareIncomeBeetweenOneMonth(month, year));
        return tmp;
    }
    @Override
    @GetMapping("/2/{month}/{year}")
    public String getStatisticForOneMonth(@PathVariable("month") int month, @PathVariable("year") int year) {
        try {


        String tmp = "Statistik für den eingegeben Monat \nSumme: " + this.findSumOfAllTransactionsByDay(month, year) + "\n"
                + "Die 3 Gewerbe mit den höchsten Umsatz: " + this.findTop3Desc(month, year) + "\n"
                + "Die 3 Gewerbe mit den niedrigsten Umsatz: " + this.findAllTop3Asc(month, year) + "\n"
                + "Kunden mit 5 fehlgeschlagenen Transaktionen: " + customerController.findAllCustomerWithFiveFailedTransaction(month, year) + "\n"
                + "Differenz zum Vormonat: " + this.compareIncomeBeetweenOneMonth(month, year);
            return tmp;
        } catch (NullPointerException exception){
            throw new NullPointerException();

        }



       /* tmp.addAll(this.findTop3Desc(month, year));
        tmp.addAll(this.findAllTop3Asc(month, year));
        tmp.addAll(customerController.findAllCustomerWithFiveFailedTransaction(month, year));
        tmp.add(this.compareIncomeBeetweenOneMonth(month, year));*/


    }


}
