package webuildit.myStartup.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
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
    private List<String> classificationsDown;
    private List<String> classificationsUp;
    private List<Customer> customers;
    private String difference;

    public Startup(UUID sUuid, String revenue, List<String> classificationsDown, List<String> classificationsUp, List<Customer> customers, String difference){
        this.sUuid = sUuid;
        this.revenue = revenue;
        this.classificationsDown = classificationsDown;
        this.classificationsUp = classificationsUp;
        this.customers = customers;
        this.difference = difference;
    }
}
