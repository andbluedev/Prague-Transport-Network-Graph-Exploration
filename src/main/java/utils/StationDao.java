package utils;

import dto.StationDescription;

import java.io.*;
import java.util.*;

public class StationDao {
    public ArrayList<String[]> getLinesFromInputStream(String path) {
        ArrayList<String[]> listLines = new ArrayList<>();

        Set<int[]> listCouples = new TreeSet<>();

        BufferedReader bufferedReader = null;
        String line = "";
        String cvsSplitBy = ";";

        try {
            bufferedReader = new BufferedReader(new FileReader(path));
            while ((line = bufferedReader.readLine()) != null) {
                String[] node = line.split(cvsSplitBy);
                listLines.add(node);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erreur");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Erreur");
            e.printStackTrace();
        }
        return listLines;
    }

    public StationDescription getStationDescription(int stationNumber) {
        ArrayList<String[]> listLinesStops = getLinesFromInputStream("stops_prague.csv");
        StationDescription stationDescription = new StationDescription();
        boolean foundStation = false;
        int count = 0;

        while (!foundStation) {
            String[] line =  listLinesStops.get(count);
            if (Integer.parseInt(line[5]) == stationNumber) {
                foundStation = true;
                double lat = Float.parseFloat(line[0]);
                double lon = Float.parseFloat(line[1]);
                String name = line[4];

                stationDescription.setLatitude(lat);
                stationDescription.setLongitude(lon);
                stationDescription.setName(name);
                stationDescription.setNumber(stationNumber);
            }
            count++;
        }
        return stationDescription;
    }
}







