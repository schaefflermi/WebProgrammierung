package webuildit.myStartup.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import webuildit.myStartup.dto.VendorDTO;
import webuildit.myStartup.service.VendorService;
import io.swagger.v3.oas.annotations.Operation;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/v1/vendors")
public class VendorControllerImpl implements VendorController{

    private VendorService vendorService;

    public VendorControllerImpl(VendorService vendorService){
        this.vendorService = vendorService;

    }

    // Methode mit Dokumentation, die alle Verkäufer zurückgibt
    @Override
    @Operation(summary = "Returns a list of all vendors who are saved in the database.", description = "This " +
            "operation returns all vendors who are saved in the database in form of VendorDTO. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All vendors are returned successfully."),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing.")
    })
    @GetMapping
    public List<VendorDTO> getAllVendors() {
        return this.vendorService.getAllVendors();
    }


    // Methode mit Dokumentation, die die Details für einen Verkäufer zurückgibt
    @Override
    @Operation(summary = "Returns all details for a vendor.", description = "This operation returns all details" +
            "for the selected vendorUUID in form of the VendorDTO.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The vendor details are returned successfully"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @GetMapping("/{vuuid}")
    public VendorDTO getVendorByUUID(@Parameter(description = "The unique vendorUUID")
            @PathVariable("vuuid") UUID vUuid) {
        try{
            return this.vendorService.getVendorByUUID(vUuid);
        } catch(NoSuchElementException noSuchElementException){
            throw new NoSuchElementException("Der Verkäufer mit dieser UUID existiert nicht.");
        }
    }



    // Methode mit Dokumentation, die einen Verkäufer hinzufügt.
    @Override
    @Operation(summary = "Adds a new vendor to the database.", description = "This operation " +
            "creates and persists the given vendor in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The vendor has been successfully added."),
            @ApiResponse(responseCode = "400", description = "The passed vendor details are not valid"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @PostMapping
    public VendorDTO addVendor(@Parameter(description = "The new vendorDTO which will be stored.")
                          @Valid @RequestBody VendorDTO vendorDTO) {
        try {
            return this.vendorService.addVendor(vendorDTO);
        } catch (ConstraintViolationException constraintViolationException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


    // Methode mit Dokumentation, die die Daten eines Vekäufers ändert
    @Override
    @Operation(summary = "Updates a vendor in the database with the given input.", description = "This operation " +
            "updates a given vendor in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The vendor has been successfully changed."),
            @ApiResponse(responseCode = "400", description = "The passed vendor details are not valid"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @PutMapping("/{vuuid}")
    public VendorDTO updateVendor(@Parameter(description = "The unique vendorId")
                                @PathVariable("vuuid") UUID vUuid,
                                @Parameter(description = "The updated vendorDTO")
                                @Valid @RequestBody VendorDTO vendorDTO) {
        vendorDTO.setVUuid(vUuid);
        try {
            return this.vendorService.updateVendor(vendorDTO);
        } catch (EntityNotFoundException notFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException noSuchElementException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


    // Methode mit Dokumentation, die einen Verkäufer aus der Datenbank löscht
    @Override
    @Operation(summary = "Removes the referenced vendor from the database.", description = "This operation " +
            "removes the vendor which is referenced in form of the passed vendorUUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The vendor is successfully removed"),
            @ApiResponse(responseCode = "404", description = "The given vendorUUID does not exist in the database"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @DeleteMapping("/{vuuid}")
    public void removeVendorById(@Parameter(description = "The unique vendorUUID")
            @PathVariable("vuuid") UUID vUuid) {
        try {
            this.vendorService.removeVendorById(vUuid);
        } catch (EntityNotFoundException notFoundException){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    // ab hier noch Doku machen!!!
    @Override
    @GetMapping("/1.1/{vuuid}")
    public String getFeeForVendor(@PathVariable("vuuid")UUID vUuid) {

        String sum;

        try {
            sum = vendorService.getFeeForVendor(vUuid);
        } catch (EntityNotFoundException notFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return sum;
    }

}
