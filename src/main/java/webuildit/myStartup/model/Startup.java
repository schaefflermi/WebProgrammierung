package webuildit.myStartup.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Startup {

    @Id
    @Column(name = "UUID",  unique = true, nullable = false)
    private UUID sUuid = UUID.randomUUID();

    private String revenue;
    private String classificationsDown;
    private String classificationsUp;
    private String customers;
    private String difference;

    public Startup(UUID sUuid, String revenue, String classificationsDown, String classificationsUp, String customers, String difference){
        this.sUuid = sUuid;
        this.revenue = revenue;
        this.classificationsDown = classificationsDown;
        this.classificationsUp = classificationsUp;
        this.customers = customers;
        this.difference = difference;
    }
}
