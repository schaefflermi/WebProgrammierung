package webuildit.myStartup.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class StartupDTO {

    private UUID sUuid;
    private String revenue;
    private String classificationsDown;
    private String classificationsUp;
    private String customers;
    private String difference;

    public StartupDTO(UUID sUuid, String revenue, String classificationsDown, String classificationsUp, String customers, String difference){
        this.sUuid = sUuid;
        this.revenue = revenue;
        this.classificationsDown = classificationsDown;
        this.classificationsUp = classificationsUp;
        this.customers = customers;
        this.difference = difference;
    }

}
