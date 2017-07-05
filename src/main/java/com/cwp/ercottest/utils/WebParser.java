package com.cwp.ercottest.utils;

import com.cwp.ercottest.exception.WebParserException;
import com.cwp.ercottest.pojo.STSADocument;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class WebParser {

    public List<STSADocument> parseCsvRows(String url, Date fromDate) {

        try {
            Document document = Jsoup.connect(url).get();
            Elements csvRows = document.select("tr:contains(_csv.zip)");

            return getHrefs(csvRows, fromDate);
        } catch (IOException e) {
            e.printStackTrace();
            throw new WebParserException(String.format("Could not parse url [%s]", url  ));
        }
    }

    private List<STSADocument> getHrefs(Elements rows, Date fromDate) {

        return rows.stream()
                .map(element -> getSTSADocument(element, fromDate))
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(STSADocument::getUploadDate))
                .collect(Collectors.toList());
    }

    private STSADocument getSTSADocument(Element element, Date fromDate) {

        String filename = element.select("td:contains(.zip)").html();

        if (filename.length() < 1000) {

            String[] split = filename.split("\\.");

            if (split.length > 4 && StringUtils.isNotBlank(split[3]) && StringUtils.isNotBlank(split[4])) {

                try {
                    String date = split[3] + split[4].substring(0, 9);
                    DateFormat dateFormat = new SimpleDateFormat("yyyyMMddkkmmssSSS");
                    Date fileDate = dateFormat.parse(date);

                    if (fileDate.after(fromDate)) {
                        return new STSADocument(fileDate, element.select("a").attr("href"));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
