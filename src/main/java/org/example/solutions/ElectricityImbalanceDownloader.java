package org.example.solutions;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ElectricityImbalanceDownloader {
    public static void downloader() {
        String url = "https://www.ote-cr.cz/en/statistics/electricity-imbalances";
        String outputFileName = "electricity_imbalances.html";
        try {
            Document doc = Jsoup.connect(url).get();
            Element table = doc.select(".report_content").first();
            FileWriter writer = new FileWriter(new File(outputFileName));
            writer.write(table.outerHtml());
            writer.close();
            System.out.println("Table downloaded and saved to " + outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
