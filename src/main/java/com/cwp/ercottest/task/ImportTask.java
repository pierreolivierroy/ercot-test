package com.cwp.ercottest.task;

import com.cwp.ercottest.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ImportTask {

    private static final Logger log = LoggerFactory.getLogger(ImportTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private ReportService reportService;

    public ImportTask(ReportService reportService) {
        this.reportService = reportService;
    }

    @Scheduled(cron = "0 05 * * * *")
    public void reportCurrentTime() {
        Date currentDate = new Date();
        log.info("The time is {} about to import report", dateFormat.format(currentDate));

        reportService.importFromDate(new Date());
    }
}
