package webuildit.myStartup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webuildit.myStartup.dto.VendorDTO;
import webuildit.myStartup.mapper.VendorMapper;
import webuildit.myStartup.model.Vendor;
import webuildit.myStartup.repository.CustomerRepository;
import webuildit.myStartup.repository.CreditcardtransactionRepository;
import webuildit.myStartup.repository.VendorRepository;

import javax.transaction.Transactional;
import java.time.*;
import java.util.*;

@Service
public class VendorServiceImpl implements VendorService {
    CreditcardtransactionRepository transactionRepository;
    VendorRepository vendorRepository;
    CustomerRepository customerRepository;
    VendorMapper vendorMapper;


    @Autowired
    public VendorServiceImpl(CreditcardtransactionRepository transactionRepository, VendorRepository vendorRepository, CustomerRepository customerRepository, VendorMapper vendorMapper){
        this.transactionRepository = transactionRepository;
        this.vendorRepository = vendorRepository;
        this.customerRepository = customerRepository;
        this.vendorMapper = vendorMapper;


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

        // Summe + Transaktionskosten
        this.transactionRepository.findSumByVendor_vUuidAndStatusIsTrueAndTdateBetween(uuid, LocalDate.of(dateYear1, dateMonth1, 1), LocalDate.of(dateYear1, dateMonth1,actualtDayOfMonth)).
                forEach(creditcardtransaction -> sum[0] = sum[0] + creditcardtransaction.getSum() + creditcardtransaction.getSum() * creditcardtransaction.getTFEE());

        return String.valueOf(sum[0]);
    }

    @Override
    public VendorDTO addVendor(VendorDTO vendorDTO) {
        Vendor tmp = new Vendor(vendorDTO.getVName(), vendorDTO.getVAddress(), vendorDTO.getClassification());
        this.vendorRepository.save(tmp);
        return new VendorDTO(tmp.getVUuid(), tmp.getVName(), tmp.getVAddress(), tmp.getClassification());
    }

    @Override
    public VendorDTO getVendorByUUID(UUID vUuid) {
        Vendor tmp = this.vendorRepository.findById(vUuid).get();
        return new VendorDTO(tmp.getVUuid(), tmp.getVName(), tmp.getVAddress(), tmp.getClassification());
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        List<Vendor> tmp = this.vendorRepository.findAll();
        List<VendorDTO> result = new ArrayList<>();
        for(Vendor vendor:tmp){
            result.add(new VendorDTO(vendor.getVUuid(), vendor.getVName(), vendor.getVAddress(), vendor.getClassification()));
        }
        return result;
    }

    @Transactional
    @Override
    public VendorDTO updateVendor(VendorDTO newVendor) {
        Vendor tmp = vendorMapper.vendorDTOToVendor(newVendor);
        this.vendorRepository.save(tmp);
        return this.vendorMapper.vendorToVendorDTO(tmp);
    }

    @Override
    public void removeVendorById(UUID vUuid) {
        this.vendorRepository.deleteById(vUuid);
    }

}
