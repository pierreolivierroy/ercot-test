package com.cwp.ercottest.utils;

import org.junit.Before;
import org.junit.Test;

public class CsvUtilsTest {

    private CsvUtils csvUtils;

    @Before
    public void setUp() throws Exception {
        csvUtils = new CsvUtils("http://mis.ercot.com");
    }

    @Test
    public void readContentFromUrl() throws Exception {
        csvUtils.readContentFromUrl("/misdownload/servlets/mirDownload?mimic_duns=&doclookupId=568695506");
    }
}