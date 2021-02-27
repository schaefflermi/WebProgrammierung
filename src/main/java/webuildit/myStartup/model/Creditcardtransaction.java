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
public class Creditcardtransaction {

    // automatisch generierte UUID, die den Verkäufer eindeutig identifiziert
    @Id
    @Column(name = "UUID",  unique = true, nullable = false)
    private UUID tUuid = UUID.randomUUID();
    private String description;
    private double sum;
    private boolean status;
    private LocalDate tdate;
    private final double TFEE = 0.02;


    // 1:n Beziehung zu Kunde
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="customer_id", nullable = true)
    private Customer customer;

    // 1:n Beziehung zu Verkäufer
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="vendor_id", nullable = true)
    private Vendor vendor;

    // Konstruktor
    public Creditcardtransaction(String description, double sum, boolean status, LocalDate tdate){
        this.description = description;
        this.sum = sum;
        this.status = status;
        this.tdate = tdate;
    }
}
