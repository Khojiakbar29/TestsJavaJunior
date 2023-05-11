package org.example.solutions;

import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DownloadXLS {
    public static void getDateXls() {
        try {
            Document doc = Jsoup.connect("https://www.ote-cr.cz/en/statistics/electricity-imbalances").get();
            Element attachmentLinks = doc.selectFirst(".report_attachment_links");
            Element link = attachmentLinks.selectFirst("a");
            String additionalLink = "https://www.ote-cr.cz/en/statistics/electricity-imbalances";
            String xlsUrl = additionalLink + link.attr("href");

            URL url = new URL(xlsUrl);
            InputStream inputStream = url.openStream();
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0);
            int rowsAmount = 30;
            int columnsAmount = sheet.getRow(5).getLastCellNum();

            Row dateRow = sheet.getRow(2);
            String headerInfo = String.valueOf(dateRow.getCell(0));
            String dateInfo = headerInfo.substring(headerInfo.lastIndexOf("-") + 2);
            int j = 6;

            for (j = 6; j < rowsAmount; j++) {
                for (int i = 1; i < columnsAmount; i++) {
                    Row row1 = sheet.getRow(5);
                    String entityName = String.valueOf(row1.getCell(i)).replaceAll("\\(.*?\\)", "").trim();

                    Row row2 = sheet.getRow(j);
                    String entityValue = String.valueOf(row2.getCell(i));
                    String entityTime = String.valueOf(row2.getCell(0)).replace(".0", "");
                    int numTime = Integer.parseInt(entityTime);
                    String correctedTime = "";

                    if (!(numTime < 1 || numTime > 24)) {
                        correctedTime = String.format("%02d:00", numTime - 1);
                    }
                    System.out.println(entityName + ";" + dateInfo + " " + correctedTime + "; " + entityValue);
                }
            }
            inputStream.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




