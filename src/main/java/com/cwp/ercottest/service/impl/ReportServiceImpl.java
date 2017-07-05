package com.cwp.ercottest.service.impl;

import com.cwp.ercottest.exception.WebParserException;
import com.cwp.ercottest.mapper.ShortTermSystemAdequacyMapper;
import com.cwp.ercottest.model.ShortTermSystemAdequacy;
import com.cwp.ercottest.pojo.STSADocument;
import com.cwp.ercottest.repository.ShortTermSystemAdequacyRepository;
import com.cwp.ercottest.service.ReportService;
import com.cwp.ercottest.utils.CsvUtils;
import com.cwp.ercottest.utils.WebParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ReportServiceImpl implements ReportService {

    private ShortTermSystemAdequacyRepository repository;
    private ShortTermSystemAdequacyMapper mapper;
    private WebParser webParser;
    private CsvUtils csvUtils;
    private String ercotUrl;

    public ReportServiceImpl(ShortTermSystemAdequacyRepository repository,
                             ShortTermSystemAdequacyMapper mapper,
                             WebParser webParser,
                             CsvUtils csvUtils,
                             @Value("${ercot.url}") String ercotUrl) {
        this.repository = repository;
        this.mapper = mapper;
        this.webParser = webParser;
        this.csvUtils = csvUtils;
        this.ercotUrl = ercotUrl;
    }

    @Override
    public void importFromDate(Date fromDate) {

        try {
            List<STSADocument> stsaDocuments = webParser.parseCsvRows(ercotUrl, fromDate);
            stsaDocuments.forEach(stsaDocument -> {
                List<String> fileContent = csvUtils.readContentFromUrl(stsaDocument.getHref());
                List<ShortTermSystemAdequacy> shortTermSystemAdequacies = mapper.mapList(stsaDocument.getUploadDate(), fileContent);
                shortTermSystemAdequacies.stream()
                        .filter(Objects::nonNull)
                        .forEach(shortTermSystemAdequacy -> repository.save(shortTermSystemAdequacy));
            });

        } catch (WebParserException e) {
            e.printStackTrace();
        }
    }
}
