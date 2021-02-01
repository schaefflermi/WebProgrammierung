package webuildit.myStartup.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

// Klasse, die dem Verkäufer Attribute zuweist
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Vendor {
    // automatisch generierte UUID, die den Verkäufer eindeutig identifiziert

   // @Column(name = "UUID", length = 16, unique = true, nullable = false)
   // private UUID vUuid = UUID.randomUUID();
    @Id
    private long vId;
    private String vName;
    private String vAddress;
    private Classification classification;



    // 1:n Beziehung Verkäufer - Transaktion
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private List<CreditCardTransaction> transactions;


    // Konstruktor
    public Vendor(long vId,String vName, String vAddress, Classification classification){
        this.vName=vName;
        this.vAddress=vAddress;
        this.classification=classification;
    }

}
