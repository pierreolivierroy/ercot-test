package com.cwp.ercottest.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Component
public class CsvUtils {

    private String baseErcotUrl;

    public CsvUtils(@Value("${ercot.base.url}") String baseErcotUrl) {
        this.baseErcotUrl = baseErcotUrl;
    }

    public List<String> readContentFromUrl(String url) {

        List<String> fileContent = new ArrayList<>();

        try {
            File file =  downloadFileFromUrl(url);
            InputStream inputStream = unzipFile(file);

            if (inputStream != null) {
                Scanner scanner = new Scanner(inputStream);
                scanner.useDelimiter("\n");
                scanner.nextLine();

                while (scanner.hasNext()) {
                    fileContent.add(scanner.next());
                }

                scanner.close();
                inputStream.close();
            }

            file.deleteOnExit();
        } catch (IOException e) {
            throw new RuntimeException(String.format("Could not read content from file downloaded at [%s]",
                    baseErcotUrl + url));
        }

        return fileContent;
    }

    private File downloadFileFromUrl(String urlPath) throws IOException {

        File tmpFile = File.createTempFile("tmp_", ".zip");
        URL url = new URL(baseErcotUrl + urlPath);
        FileUtils.copyURLToFile(url, tmpFile);

        return tmpFile;
    }

    private InputStream unzipFile(File file) {

        try {
            ZipFile zipFile = new ZipFile(file.getPath());
            ZipEntry zipEntry = zipFile.entries().nextElement();
            InputStream inputStream = zipFile.getInputStream(zipEntry);

            return inputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
