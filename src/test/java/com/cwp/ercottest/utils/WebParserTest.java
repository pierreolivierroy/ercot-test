package com.cwp.ercottest.utils;


import com.cwp.ercottest.pojo.STSADocument;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WebParserTest {

    private WebParser webParser;

    @Before
    public void setUp() throws Exception {
        webParser = new WebParser();
    }

    @Test
    public void parseCsvRows() throws Exception {

        List<STSADocument> stsaDocuments = webParser.parseCsvRows("http://mis.ercot.com/misapp/GetReports.do?reportTypeId=12315", new DateTime(2017, 7, 1, 0, 0).toDate());

        assertThat(stsaDocuments).isNotEmpty();
    }
}