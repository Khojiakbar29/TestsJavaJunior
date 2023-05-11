package org.example;

import org.example.solutions.DownloadXLS;
import org.example.solutions.ElectricityImbalanceDownloader;
import org.example.solutions.PrintingValueOfO3;
import org.example.solutions.StormData;

public class Main {
    public static void main(String[] args) {
        ElectricityImbalanceDownloader.downloader(); //working
        DownloadXLS.getDateXls(); //working
        PrintingValueOfO3.getValue(); //working
        StormData.getData(); //working
    }
}
