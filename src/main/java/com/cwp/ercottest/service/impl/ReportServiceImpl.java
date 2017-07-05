package com.cwp.ercottest.service.impl;

import com.cwp.ercottest.exception.WebParserException;
import com.cwp.ercottest.mapper.ShortTermSystemAdequacyMapper;
import com.cwp.ercottest.model.ShortTermSystemAdequacy;
import com.cwp.ercottest.pojo.STSADocument;
import com.cwp.ercottest.repository.ShortTermSystemAdequacyRepository;
import com.cwp.ercottest.service.ReportService;
import com.cwp.ercottest.utils.CsvUtils;
import com.cwp.ercottest.utils.WebParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    private ShortTermSystemAdequacyRepository repository;
    private ShortTermSystemAdequacyMapper mapper;
    private WebParser webParser;
    private CsvUtils csvUtils;
    private String ercotUrl;
    private Date lastInsertedDate;

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
            Optional<ShortTermSystemAdequacy> lastInsertedEntity = repository.findTopByOrderByOriginalDateTimeDesc();

            if (lastInsertedEntity.isPresent()) {
                lastInsertedDate = new Date(lastInsertedEntity.get().getOriginalDateTime().getTime());
            }

            stsaDocuments.forEach((STSADocument stsaDocument) -> {
                List<String> fileContent = csvUtils.readContentFromUrl(stsaDocument.getHref());
                List<ShortTermSystemAdequacy> shortTermSystemAdequacies = mapper.mapList(stsaDocument.getUploadDate(), fileContent);
                shortTermSystemAdequacies.stream()
                        .filter(Objects::nonNull)
                        .forEach((ShortTermSystemAdequacy shortTermSystemAdequacy) -> {
                            try {

                                Date dateToInsert = new Date(shortTermSystemAdequacy.getOriginalDateTime().getTime());
                                if (lastInsertedDate == null || dateToInsert.after(lastInsertedDate)) {
                                    logger.info("About to save entity with id {}", shortTermSystemAdequacy.getOriginalDateTime());

                                    repository.save(shortTermSystemAdequacy);
                                    lastInsertedDate = dateToInsert;
                                } else {
                                    logger.info("Date {} already inserted", shortTermSystemAdequacy.getOriginalDateTime());
                                }
                            } catch (RuntimeException e) {
                                e.printStackTrace();
                            }
                        });
            });

            logger.info("Import finished.");

        } catch (WebParserException e) {
            e.printStackTrace();
        }
    }
}
