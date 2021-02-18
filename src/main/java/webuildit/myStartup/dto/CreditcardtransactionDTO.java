package webuildit.myStartup.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import webuildit.myStartup.model.Customer;
import webuildit.myStartup.model.Vendor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
public class CreditcardtransactionDTO {
    private UUID tUuid;
    private String description;
    private double sum;
    private boolean status;
    private LocalDate tdate;
    private final double TFEE = 0.02;

    public CreditcardtransactionDTO(UUID tUuid, String description, double sum, boolean status, LocalDate tdate){
        this.tUuid = tUuid;
        this.description = description;
        this.sum = sum;
        this.status = status;
        this.tdate = tdate;
    }



}
