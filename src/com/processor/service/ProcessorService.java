package com.processor.service;

import com.processor.entity.LoanDO;
import com.processor.req.ProcessorRequest;
import com.processor.res.TableDTO;

import java.util.Vector;

public interface ProcessorService {

    TableDTO retrieveProcessor(ProcessorRequest processorRequest);

    boolean add(LoanDO loanDO);

    LoanDO getByLoanNo(String selectedLoan);

    boolean update(LoanDO loanDO);

    boolean delete(Vector<Object> selectedLoans);
}
