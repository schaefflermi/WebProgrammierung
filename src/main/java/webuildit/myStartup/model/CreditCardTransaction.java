package webuildit.myStartup.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

// Klasse, die der Transaktion Attribute zuweist
@Getter
@Setter
@NoArgsConstructor
@Entity
public class CreditCardTransaction {

    // automatisch generierte UUID, die den Verkäufer eindeutig identifiziert
    @Id
    @Column(name = "UUID", length = 16, unique = true, nullable = false)
    private UUID tUuid = UUID.randomUUID();
    private String description;
    private double sum;
    private boolean status;
    private LocalDate tDate;
    private static final float TFEE = 0.02f;


    // 1:n Beziehung zu Kunde
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id", nullable = true)
    private Customer customer;

    // 1:n Beziehung zu Kunde
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="vendor_id", nullable = true)
    private Vendor vendor;

    // Konstruktor
    public CreditCardTransaction(String description, double sum, boolean status, LocalDate tDate){
        this.description=description;
        this.sum=sum;
        this.status=status;
        this.tDate = tDate;
    }

}
