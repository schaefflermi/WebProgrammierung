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

    private double revenue;
    private String classificationsDown;
    private String classificationsUp;
    private String customers;
    private double difference;

    public Startup(UUID sUuid, double revenue, String classificationsDown, String classificationsUp, String customers, double difference){
        this.sUuid = sUuid;
        this.revenue = revenue;
        this.classificationsDown = classificationsDown;
        this.classificationsUp = classificationsUp;
        this.customers = customers;
        this.difference = difference;
    }
}
