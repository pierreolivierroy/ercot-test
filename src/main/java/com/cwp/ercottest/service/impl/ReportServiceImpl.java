package com.cwp.ercottest.service.impl;

import com.cwp.ercottest.repository.ShortTermSystemAdequacyRepository;
import com.cwp.ercottest.service.ReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    private ShortTermSystemAdequacyRepository repository;

    public ReportServiceImpl(ShortTermSystemAdequacyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void importFromDate() {
        
    }
}
