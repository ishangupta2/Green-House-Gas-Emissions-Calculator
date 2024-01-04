package emissions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ZipEmissionsList {

    ArrayList<ZipEmissions> zipEmissionsList;


    public ZipEmissionsList(String filename) {
        zipEmissionsList = new ArrayList<ZipEmissions>();
        Scanner input;
        try {
            input = new Scanner(new File(filename));
        }
        catch (IOException e) {
            System.out.println("Invalid File for ZipEmissionsList");
            return;
        }
        input.nextLine();
        while (input.hasNextLine()) {
            String[] temp = input.nextLine().split(",");
            zipEmissionsList.add(new ZipEmissions(temp[0], Double.parseDouble(
                temp[3])));
        }
    }


    public double getEmissionsForZip(String zip) {
        for (int x = 0; x < zipEmissionsList.size(); x++) {
            if (zip.equals(zipEmissionsList.get(x).getZip())) {
                return zipEmissionsList.get(x).getEmission();
            }
        }
        return -1;
    }


    class ZipEmissions {
        String zip;
        double emission;


        ZipEmissions(String s, double e) {
            this.zip = s;
            this.emission = e;
        }


        String getZip() {
            return zip;
        }


        double getEmission() {
            return emission;
        }

    }
}
