package webuildit.myStartup.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

// Klasse, die dem Kunden Attribute zuweist
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer {

    // automatisch generierte UUID, die den Kunden eindeutig identifiziert
    @Id
    @Column(name = "UUID", length = 16, unique = true, nullable = false)
    private UUID cUuid = UUID.randomUUID();
    private String cName;
    private String cAddress;
    private int netIncome;

    // 1:n Beziehung Kunde - Transaktion
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Creditcardtransaction> transactions;

    // Konstruktor
    public Customer(String cName, String cAddress, int netIncome){
        this.cName=cName;
        this.cAddress=cAddress;
        this.netIncome=netIncome;
    }
}
