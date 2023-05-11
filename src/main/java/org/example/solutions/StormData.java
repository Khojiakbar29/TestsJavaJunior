package org.example.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class StormData {
    public static void getData() {
        try {
            URL url = new URL("https://www.nhc.noaa.gov/data/hurdat/hurdat2-nepac-1949-2016-041317.txt");
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("20141105, 0600")) {
                    break;
                }
            }

            while ((line = reader.readLine()) != null) {
                int year;
                int rowsAmount = 0;
                if (line.startsWith("EP")) {
                    year = Integer.parseInt(line.substring(4, 8));
                    rowsAmount = Integer.parseInt(line.substring(34, 36).trim());
                    String[] parts = line.split(",");
                    String name = parts[1].trim();

                    if (year >= 2015 && name.endsWith("A")) {
                        System.out.println(name);
                        int maxWindSpeed = 0;
                        for (int i = 0 ; i < rowsAmount; i ++) {
                            String infoLine = reader.readLine();
                            String[] cutted = infoLine.split(",");
                            int eachWindSpeed = Integer.parseInt(cutted[6].trim());
                            if (maxWindSpeed < eachWindSpeed ) {
                                maxWindSpeed = eachWindSpeed;
                            }
                        }
                        System.out.println("Maximum sustained wind-speed: " + maxWindSpeed);
                        maxWindSpeed = 0;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
