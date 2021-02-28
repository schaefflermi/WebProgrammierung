package webuildit.myStartup.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import webuildit.myStartup.model.Customer;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class StartupDTO {

    private UUID sUuid;
    private double revenue;
    private String classificationsDown;
    private String classificationsUp;
    private String customers;
    private double difference;

    public StartupDTO(UUID sUuid, double revenue, String classificationsDown, String classificationsUp, String customers, double difference){
        this.sUuid = sUuid;
        this.revenue = revenue;
        this.classificationsDown = classificationsDown;
        this.classificationsUp = classificationsUp;
        this.customers = customers;
        this.difference = difference;
    }

}
