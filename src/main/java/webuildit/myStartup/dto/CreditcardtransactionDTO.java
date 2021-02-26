package webuildit.myStartup.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
public class CreditcardtransactionDTO {
    private UUID tUuid;
    private String description;

    @Min(0)
    @NotBlank
    private double sum;
    @NotBlank
    private boolean status;

    @NotBlank
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
