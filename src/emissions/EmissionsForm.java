package emissions;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class EmissionsForm extends JFrame implements ActionListener {

    public EmissionsForm() {
        EmissionsData.calculateTotalEmissions();

        GridLayout grid = new GridLayout(10, 2);
        BorderLayout borders = new BorderLayout();
        GridLayout wasteGrid = new GridLayout(5, 2);

        JPanel panel = new JPanel(borders);
        JPanel data = new JPanel(grid);
        JPanel wasteData = new JPanel(wasteGrid);

        JButton exit = new JButton("Exit");
        exit.addActionListener(this);

        Font f = new Font("Calibri", Font.BOLD, 16);

        JLabel vehicle = new JLabel(
            "<html><p>Vehicle Emissions (CO2/year):</html></p>");
        JLabel vehicleMaintenance = new JLabel(
            "<html><p>Vehicle Maintenance Emissions (CO2/year):</html></p>");
        JLabel naturalGas = new JLabel(
            "<html><p>Natural Gas Emissions (CO2/year):</html></p>");
        JLabel electric = new JLabel(
            "<html><p>Electricity Emissions(CO2/year):</html></p>");
        JLabel fuel = new JLabel(
            "<html><p>Fuel Oil Emissions (CO2/year):</html></p>");
        JLabel propane = new JLabel(
            "<html><p>Propane Emissions (CO2/year):</html></p>");
        JLabel wasteBefore = new JLabel(
            "<html><p>Waste Emissions before recycling (CO2/year):</html></p>");
        JLabel wasteReduction = new JLabel(
            "<html><p>Emissions reduction from recycling (CO2/year):</html></p>");
        JLabel wasteAfter = new JLabel(
            "<html><p>Waste Emissions after recycling (CO2/year):</html></p>");
        JLabel total = new JLabel(
            "<html><p>Total Emissions (CO2/year):</html></p>");

        vehicle.setFont(f);
        vehicleMaintenance.setFont(f);
        naturalGas.setFont(f);
        electric.setFont(f);
        fuel.setFont(f);
        propane.setFont(f);
        wasteBefore.setFont(f);
        wasteReduction.setFont(f);
        wasteAfter.setFont(f);
        total.setFont(f);

        wasteData.add(new JLabel("Metal"));
        wasteData.add(new JLabel("-" + EmissionsData.WASTE_REDUCTION_STEEL));
        wasteData.add(new JLabel("Plastic"));
        wasteData.add(new JLabel("-" + EmissionsData.WASTE_REDUCTION_PLASTIC));
        wasteData.add(new JLabel("Glass"));
        wasteData.add(new JLabel("-" + EmissionsData.WASTE_REDUCTION_GLASS));
        wasteData.add(new JLabel("Newspaper"));
        wasteData.add(new JLabel("-"
            + EmissionsData.WASTE_REDUCTION_NEWSPAPER));
        wasteData.add(new JLabel("Magazine"));
        wasteData.add(new JLabel("-" + EmissionsData.WASTE_REDUCTION_MAGAZINE));

        data.add(vehicle);
        data.add(new JLabel("" + EmissionsData.VEHICLE_EMISSIONS));
        data.add(vehicleMaintenance);
        data.add(new JLabel("" + EmissionsData.VEHICLE_MAINTENANCE_EMISSIONS));
        data.add(naturalGas);
        data.add(new JLabel("" + EmissionsData.NATURAL_GAS_CO2));
        data.add(electric);
        data.add(new JLabel("" + EmissionsData.ELECTRICITY_CO2));
        data.add(fuel);
        data.add(new JLabel("" + EmissionsData.FUEL_CO2));
        data.add(propane);
        data.add(new JLabel("" + EmissionsData.PROPANE_CO2));
        data.add(wasteBefore);
        data.add(new JLabel("" + EmissionsData.WASTE_EMISSIONS_BEFORE));
        data.add(wasteReduction);
        data.add(wasteData);
        data.add(wasteAfter);
        data.add(new JLabel("" + EmissionsData.WASTE_EMISSIONS_AFTER));
        data.add(total);
        data.add(new JLabel("" + EmissionsData.TOTAL_EMISSIONS));

        panel.add(data, BorderLayout.CENTER);
        panel.add(exit, BorderLayout.SOUTH);

        this.add(panel);
        this.setSize(500, 700);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
