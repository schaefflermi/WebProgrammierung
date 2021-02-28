package webuildit.myStartup.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import webuildit.myStartup.dto.CreditcardtransactionDTO;
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

    // Konstruktor
    public CreditcardtransactionControllerImpl(CreditcardtransactionService creditcardtransactionService){
        this.creditcardtransactionService = creditcardtransactionService;
    }

    // Methode mit Dokumentation, die alle Kreditkartentransactionen zurückgibt
    @Override
    @Operation(summary = "Returns a list of all creditcardtransactions that are saved in the database.", description = "This " +
            "operation returns all creditcardtransactions that are saved in the database in form of CreditcardtransactionDTO. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All creditcardtransactions are returned successfully."),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing.")
    })
    @GetMapping
    public List<CreditcardtransactionDTO> getAllCreditcardtransaction() {
        return this.creditcardtransactionService.getAllCreditcardtransaction();
    }

    // Methode mit Dokumentation, die die Details für eine Creditcardtransaction zurückgibt
    @Override
    @Operation(summary = "Returns all details for a creditcardtransaction.", description = "This operation returns all details" +
            "for the selected creditcardtransactionUUID in form of the CreditcardtransactionDTO.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The creditcardtransaction details are returned successfully"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @GetMapping("/{tuuid}")
    public CreditcardtransactionDTO getCreditcardtransactionByUUID(
            @Parameter(description = "The unique creditcardtransactionUUID")
            @PathVariable("tuuid") UUID tUuid) {
        try{
            return this.creditcardtransactionService.getCreditcardtransactionByUUID(tUuid);
        } catch(NoSuchElementException noSuchElementException){
            throw new NoSuchElementException("The creditcardtransaction with this UUID does not exist.");
        }
    }

    // Methode mit Dokumentaion, die eine Kreditkartentransaktion hinzufügt
    @Override
    @Operation(summary = "Adds a new creditcardtransaction to the database.", description = "This operation " +
            "creates and persists the given creditcardtransaction in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The creditcardtransaction has been successfully added."),
            @ApiResponse(responseCode = "400", description = "The passed creditcardtransaction details are not valid"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @PostMapping
    public CreditcardtransactionDTO addCreditcardtransaction(
            @Parameter(description = "The new creditcardtransactionDTO which will be stored.")
            @Valid @RequestBody CreditcardtransactionDTO creditcardtransactionDTO) {
        try {
            return this.creditcardtransactionService.addCreditcardtransaction(creditcardtransactionDTO);
        } catch (ConstraintViolationException constraintViolationException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    // Methode mit Dokumentation, die die Daten einer Kreditkartentransaktion ändert
    @Override
    @Operation(summary = "Updates a creditcardtransaction in the database with the given input.", description = "This operation " +
            "updates a given creditcardtransaction in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The creditcardtransaction has been successfully changed."),
            @ApiResponse(responseCode = "400", description = "The passed creditcardtransaction details are not valid"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @PutMapping("/{tuuid}")
    public CreditcardtransactionDTO updateCreditcardtransaction(
            @Parameter(description = "The unique creditcardtransactionUUID")
            @PathVariable("tuuid") UUID tUuid,
            @Parameter(description = "The updated creditcardtransactionDTO")
            @Valid @RequestBody CreditcardtransactionDTO creditcardtransactionDTO) {
        creditcardtransactionDTO.setTUuid(tUuid);
        try {
            return this.creditcardtransactionService.updateCreditcardtransaction(creditcardtransactionDTO);
        } catch (EntityNotFoundException notFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException noSuchElementException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    // Methode mit Dokumentation, die eine Kreditkartentransaktion aus der Datenbank löscht
    @Override
    @Operation(summary = "Removes the referenced creditcardtransaction from the database.", description = "This operation " +
            "removes the creditcardtransaction which is referenced in form of the passed creditcardtransactionUUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The creditcardtransaction is successfully removed"),
            @ApiResponse(responseCode = "404", description = "The given creditcardtransactionUUID does not exist in the database"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @DeleteMapping("/{tuuid}")
    public void removeCreditcardtransactionById(
            @Parameter(description = "The unique creditcardtransactionUUID")
            @PathVariable("tuuid") UUID tUuid) {
        try {
            this.creditcardtransactionService.removeCreditcardtransactionById(tUuid);
        } catch (EntityNotFoundException notFoundException){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
