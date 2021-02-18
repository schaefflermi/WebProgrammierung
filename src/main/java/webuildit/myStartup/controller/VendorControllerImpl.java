package webuildit.myStartup.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import webuildit.myStartup.dto.VendorDTO;
import webuildit.myStartup.service.VendorService;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
@RestController
@RequestMapping("/api/v1/vendors")
public class VendorControllerImpl implements VendorController{

    private VendorService vendorService;

    public VendorControllerImpl(VendorService vendorService){
        this.vendorService = vendorService;
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
}
