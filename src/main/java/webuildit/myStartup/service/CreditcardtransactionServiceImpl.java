package webuildit.myStartup.service;

import org.springframework.stereotype.Service;
import webuildit.myStartup.dto.CreditcardtransactionDTO;
import webuildit.myStartup.mapper.CreditcardtransactionMapper;
import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.repository.TransactionRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
* @Autor Johannes Wiest
* */

@Service
public class CreditcardtransactionServiceImpl implements CreditcardtransactionService{

    TransactionRepository transactionRepository;
    CreditcardtransactionMapper creditcardtransactionMapper;

    public CreditcardtransactionServiceImpl(TransactionRepository transactionRepository, CreditcardtransactionMapper creditcardtransactionMapper){
        this.transactionRepository = transactionRepository;
        this.creditcardtransactionMapper = creditcardtransactionMapper;
    }

    @Override
    public CreditcardtransactionDTO addCreditcardtransaction(CreditcardtransactionDTO creditcardtransactionDTO) {
        Creditcardtransaction tmp = new Creditcardtransaction(creditcardtransactionDTO.getDescription(), creditcardtransactionDTO.getSum(), creditcardtransactionDTO.isStatus(), creditcardtransactionDTO.getTdate());
        this.transactionRepository.save(tmp);
        return new CreditcardtransactionDTO(tmp.getTUuid(), tmp.getDescription(), tmp.getSum(), tmp.isStatus(), tmp.getTdate());
    }

    @Override
    public CreditcardtransactionDTO getCreditcardtransactionByUUID(UUID tUuid) {
        Creditcardtransaction tmp = this.transactionRepository.findById(tUuid).get();
        return new CreditcardtransactionDTO(tmp.getTUuid(), tmp.getDescription(), tmp.getSum(), tmp.isStatus(), tmp.getTdate());
    }

    @Override
    public List<CreditcardtransactionDTO> getAllCreditcardtransaction() {
        List<Creditcardtransaction> tmp = this.transactionRepository.findAll();
        List<CreditcardtransactionDTO> result = new ArrayList<>();
        for(Creditcardtransaction creditcardtransaction:tmp){
            result.add(new CreditcardtransactionDTO(creditcardtransaction.getTUuid(), creditcardtransaction.getDescription(), creditcardtransaction.getSum(), creditcardtransaction.isStatus(), creditcardtransaction.getTdate()));
        }
        return result;
    }

    @Override
    @Transactional
    public CreditcardtransactionDTO updateCreditcardtransaction(CreditcardtransactionDTO creditcardtransactionDTO) {
        Creditcardtransaction tmp = this.creditcardtransactionMapper.creditcardtransactionDTOToCreditcardtransaction(creditcardtransactionDTO);
        this.transactionRepository.save(tmp);
        return this.creditcardtransactionMapper.creditcardtransactionToCreditcardtransactionDTO(tmp);
    }

    @Override
    public void removeCreditcardtransactionById(UUID tUuid) {

        this.transactionRepository.deleteById(tUuid);

    }
}
