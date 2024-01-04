package emissions;

import javax.swing.JLabel;

public class EmissionsData {
    public static String CHECK_INFORMATION =
        "<html><p>Please make sure all your information is correct before submitting and moving onto the next form</p></html>";
    public static String ERROR_MESSAGE =
        "<html><p>Please fix the following errors before continuing:</p></html>\n";

    public static JLabel INSTRUCTIONS = new JLabel(
        EmissionsData.CHECK_INFORMATION);

    public static String[] HEATING_CHOICES = new String[] { "Natural Gas",
        "Electric Heat", "Oil", "Propane", "Wood", "No Heating" };

    public static ZipEmissionsList ZIP_EMISSIONS_LIST = new ZipEmissionsList(
        "zipcode_emissions.csv");

    public static double AVERAGE_MPG = 21.6;
    public static double FUEL_COST_PER_GALLON = 4.02;
    public static double PROPANE_COST_PER_GALLON = 2.47;
    public static double NATURAL_GAS_COST_1000CF = 10.68;
    public static double COST_PER_KWH = .1188;

    public static double EF_NATURAL_GAS = 119.58;
    public static double EF_FUEL_OIL = 22.61;
    public static double EF_PROPANE = 12.43;

    public static double EF_VEHICLE = 19.6;
    public static double EF_NONCO2_VEHICLE = 1.01;
    public static double VEHICLE_EFFICIENCY_IMPROVEMENT = 0.04;

    public static double VEHICLE_EMISSIONS;
    public static double VEHICLE_MAINTENANCE_EMISSIONS;

    public static double E_FACTOR_VALUE;
    public static double NATURAL_GAS_CO2;
    public static double ELECTRICITY_CO2;
    public static double GREEN_PERCENTAGE;
    public static double FUEL_CO2;
    public static double PROPANE_CO2;

    public static int HOUSEHOLD_PEOPLE;
    public static int HOUSEHOLD_HEATING;
    public static int[] HOUSEHOLD_VEHICLES;
    public static boolean MAINTENANCE;

    public static double AVERAGE_WASTE_EMISSIONS = 692.0;
    public static double STEEL_RECYCLING_REDUCTION = 89.38;
    public static double PLASTIC_RECYCLING_REDUCTION = 35.56;
    public static double GLASS_RECYCLING_REDUCTION = 25.39;
    public static double NEWSPAPER_RECYCLING_REDUCTION = 113.14;
    public static double MAGAZINE_RECYCLING_REDUCTION = 27.46;

    public static double WASTE_EMISSIONS_BEFORE;
    public static double WASTE_REDUCTION_STEEL;
    public static double WASTE_REDUCTION_PLASTIC;
    public static double WASTE_REDUCTION_GLASS;
    public static double WASTE_REDUCTION_NEWSPAPER;
    public static double WASTE_REDUCTION_MAGAZINE;
    public static double WASTE_EMISSIONS_AFTER;

    public static double TOTAL_EMISSIONS;


    public static void setNumPeople(int num) {
        HOUSEHOLD_PEOPLE = num;
    }


    public static void setHeating(int s) {
        HOUSEHOLD_HEATING = s;
    }


    public static void setVehicles(int[] vehicles) {
        HOUSEHOLD_VEHICLES = vehicles;
    }


    public static void setVehicleEmissions() {
        VEHICLE_MAINTENANCE_EMISSIONS = 0.0;
        VEHICLE_EMISSIONS = 0.0;
        if (!MAINTENANCE) {
            for (int x = 0; x < HOUSEHOLD_VEHICLES.length; x++) {
                VEHICLE_MAINTENANCE_EMISSIONS += ((HOUSEHOLD_VEHICLES[x] * 52)
                    / AVERAGE_MPG) * EF_VEHICLE * EF_NONCO2_VEHICLE
                    * VEHICLE_EFFICIENCY_IMPROVEMENT;
            }
        }
        for (int x = 0; x < HOUSEHOLD_VEHICLES.length; x++) {
            VEHICLE_EMISSIONS += ((HOUSEHOLD_VEHICLES[x] * 52) / AVERAGE_MPG)
                * EF_VEHICLE * EF_NONCO2_VEHICLE;
        }
    }


    public static void setMaintenance(boolean checkMaintenance) {
        MAINTENANCE = checkMaintenance;
    }


    public static void setEFactorValue(String zip) {
        E_FACTOR_VALUE = ZIP_EMISSIONS_LIST.getEmissionsForZip(zip) / 1000;
    }


    public static void setHomeEnergyValues(
        double gasCost,
        double electricCost,
        boolean green,
        double greenPercent,
        double fuelCost,
        double propaneCost) {
        NATURAL_GAS_CO2 = (gasCost / NATURAL_GAS_COST_1000CF) * EF_NATURAL_GAS
            * 12;

        GREEN_PERCENTAGE = greenPercent / 100;

        ELECTRICITY_CO2 = ((green)
            ? ((electricCost / COST_PER_KWH) * E_FACTOR_VALUE * 12) * (1
                - GREEN_PERCENTAGE)
            : (electricCost / COST_PER_KWH) * 12 * E_FACTOR_VALUE);

        FUEL_CO2 = (fuelCost / FUEL_COST_PER_GALLON) * EF_FUEL_OIL * 12;

        PROPANE_CO2 = (propaneCost / PROPANE_COST_PER_GALLON) * EF_PROPANE * 12;
    }


    public static void setWasteValues(
        boolean steel,
        boolean plastic,
        boolean glass,
        boolean newspaper,
        boolean magazine) {

        WASTE_EMISSIONS_BEFORE = HOUSEHOLD_PEOPLE * AVERAGE_WASTE_EMISSIONS;

        WASTE_REDUCTION_STEEL = ((steel)
            ? HOUSEHOLD_PEOPLE * STEEL_RECYCLING_REDUCTION
            : 0);

        WASTE_REDUCTION_PLASTIC = ((plastic)
            ? HOUSEHOLD_PEOPLE * PLASTIC_RECYCLING_REDUCTION
            : 0);

        WASTE_REDUCTION_GLASS = ((glass)
            ? HOUSEHOLD_PEOPLE * GLASS_RECYCLING_REDUCTION
            : 0);

        WASTE_REDUCTION_NEWSPAPER = ((newspaper)
            ? HOUSEHOLD_PEOPLE * NEWSPAPER_RECYCLING_REDUCTION
            : 0);

        WASTE_REDUCTION_MAGAZINE = ((magazine)
            ? HOUSEHOLD_PEOPLE * MAGAZINE_RECYCLING_REDUCTION
            : 0);

        WASTE_EMISSIONS_AFTER = WASTE_EMISSIONS_BEFORE - WASTE_REDUCTION_STEEL
            - WASTE_REDUCTION_PLASTIC - WASTE_REDUCTION_GLASS
            - WASTE_REDUCTION_NEWSPAPER - WASTE_REDUCTION_MAGAZINE;
    }


    public static void calculateTotalEmissions() {
        TOTAL_EMISSIONS = VEHICLE_EMISSIONS + VEHICLE_MAINTENANCE_EMISSIONS
            + NATURAL_GAS_CO2 + ELECTRICITY_CO2 + FUEL_CO2 + PROPANE_CO2
            + WASTE_EMISSIONS_AFTER;
    }

}
