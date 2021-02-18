package webuildit.myStartup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webuildit.myStartup.dto.VendorDTO;
import webuildit.myStartup.mapper.VendorMapper;
import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.model.Vendor;
import webuildit.myStartup.repository.CustomerRepository;
import webuildit.myStartup.repository.TransactionRepository;
import webuildit.myStartup.repository.VendorRepository;

import javax.transaction.Transactional;
import java.time.*;
import java.util.*;

@Service
public class VendorServiceImpl implements VendorService {
    TransactionRepository transactionRepository;
    VendorRepository vendorRepository;
    CustomerRepository customerRepository;
    VendorMapper vendorMapper;

    @Autowired
    public VendorServiceImpl(TransactionRepository transactionRepository, VendorRepository vendorRepository, CustomerRepository customerRepository, VendorMapper vendorMapper){
        this.transactionRepository = transactionRepository;
        this.vendorRepository=vendorRepository;
        this.customerRepository=customerRepository;
        this.vendorMapper = vendorMapper;

    }
    // this method calculate
    @Override
    public Double compareIncomeBeetweenOneMonth(int month, int year) {
        // Das derzeitige Datum
        LocalDate date1 = LocalDate.now();
        // Derzeitiges Datum wird um 1 reduziert
        LocalDate date2 = date1.minusMonths(1);
        // Summe aller Transaktionen von einem bestimmten Monat
        Double List1 = transactionRepository.findSumOfAllTransactionsByMonth(month, year);
        // Summe aller Transaktionen von dem Vormonat
        Double List2 = transactionRepository.findSumOfAllTransactionsByPreviousMonth3(month -1, year, date2);
        // Differenz der beiden Summen
        return List1-List2;
    }
    // Gibt alle Werte für Aufgabe 1 zurück
    @Override
    public void getStatisticsAboutMonth(int month, int year) {
       System.out.println("1.2 aktuellen Einnahmen des StartUps für diesen Monat " + transactionRepository.findSumOfAllTransactionsByDay(month, year));
       System.out.println("1.3 drei Gewerbe mit den höchsten Einkommen " + vendorRepository.findTop3Desc());
       System.out.println("1.3 drei Gewerbe mit den niedrigsten Einkommen " + vendorRepository.findAllTop3Asc());
       System.out.println("1.4 alle Kunden mit mindestens 5 erfolglosen Transaktionen in dem Monat "+ customerRepository.findAllCustomerWithFiveFailedTransaction(month, year));
       System.out.println("1.5 Differenz zwischen den Einnahmen des eingegebenen Monats mit dem Vormonat "+ this.compareIncomeBeetweenOneMonth(month, year));
    }

    @Override
    public String getFeeForVendor(UUID uuid) {
        // Aktuelles Datum beschaffen
        LocalDate date1 = LocalDate.now();
        // Aktuellen Monat beschaffen
        Month dateMonth1 = date1.getMonth();
        // Aktuelles Jahr beschaffen
        int dateYear1 = date1.getYear();
        //Anz Tage bis zum aktuellen Tag
        int actualtDayOfMonth = date1.getDayOfMonth();
        // Variable um die Einahmen eines Verkäufers zu zählen
        final double[] sum = {0};

        System.out.println("--------------- TEST AUSGABE -------------");
        this.transactionRepository.findSumByVendor_vUuidAndStatusIsTrueAndTdateBetween(uuid, LocalDate.of(dateYear1, dateMonth1, 1), LocalDate.of(dateYear1, dateMonth1,actualtDayOfMonth)).
                forEach(creditcardtransaction -> sum[0] = sum[0] + creditcardtransaction.getSum() + creditcardtransaction.getSum() * creditcardtransaction.getTFEE());

        return String.valueOf(sum[0]);
    }

    @Override
    public VendorDTO addVendor(VendorDTO vendorDTO) {
        Vendor tmp = new Vendor(vendorDTO.getVName(), vendorDTO.getVAddress(), vendorDTO.getClassifictation());
        this.vendorRepository.save(tmp);
        return new VendorDTO(tmp.getVUuid(), tmp.getVName(), tmp.getVAddress(), tmp.getClassification());
    }

    @Override
    public VendorDTO getVendorByUUID(UUID vUuid) {
        Vendor tmp = this.vendorRepository.findById(vUuid).get();
        return new VendorDTO(tmp.getVUuid(), tmp.getVName(), tmp.getVAddress(), tmp.getClassification(), null);
    }

    @Override
    public List<VendorDTO> getAllVendors() {
       /* List<String> result = new LinkedList<>();
        List<Vendor> tmp = this.vendorRepository.findAll();
        tmp.forEach(vendor -> result.add(vendor.getTitle()));
        return result;
        }*/
        return null;
    }
    @Transactional
    @Override
    public VendorDTO updateVendor(VendorDTO newVendor) {
        Vendor tmp = this.;

        List<Creditcardtransaction> newCast = new ArrayList<>();
        newVendor.get().forEach(castId -> newCast.add(this.castRepository.findById(castId).get()));
        tmp.setInvolvedCast(newCast);

        this.movieRepository.save(tmp);
        return this.movieMapper.movieToMovieDTO(tmp);
    }

    @Override
    public void removeVendorById(UUID vUuid) {
        this.vendorRepository.deleteById(vUuid);
    }
}
