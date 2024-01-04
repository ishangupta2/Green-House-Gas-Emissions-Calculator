package emissions;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class HomeEnergyForm extends JFrame implements ActionListener {

    JLabel naturalGasLabel, electricityLabel, greenPowerLabel,
        greenPercentLabel, fuelLabel, propaneLabel;
    ButtonGroup greenPowerCheck;
    JRadioButton greenPowerYes, greenPowerNo;
    JTextField naturalGas, electricity, greenPercent, fuel, propane;


    public HomeEnergyForm() {
        GridLayout grid = new GridLayout(6, 3);
        BorderLayout borders = new BorderLayout();

        naturalGasLabel = new JLabel(
            "<html><p>How much natural gas does your household use per month? Enter the cost of your natural gas bill.</p></html>");
        electricityLabel = new JLabel(
            "<html><p>How much electricity does your household use per month? Enter the cost of your electric bill.</html></p>");
        greenPowerLabel = new JLabel(
            "<html><p>Does your household currently purchase green power?</html></p>");
        greenPercentLabel = new JLabel(
            "<html><p>If so, what portion of your household's total purchased electricity use is green power? Enter 0, if none.</html></p>");
        fuelLabel = new JLabel(
            "<html><p>How much fuel oil does your household use per month? Enter the average amount you pay each month.</p></html>");
        propaneLabel = new JLabel(
            "<html><p>How much propane does your household use per month? Enter the average amount you pay each month.</p></html>");

        naturalGas = new JTextField();
        electricity = new JTextField();
        greenPercent = new JTextField();
        fuel = new JTextField();
        propane = new JTextField();

        JPanel panel = new JPanel(borders);
        JPanel questions = new JPanel(grid);

        JButton submit = new JButton("Submit");
        submit.addActionListener(this);

        greenPowerCheck = new ButtonGroup();
        greenPowerYes = new JRadioButton("Yes");
        greenPowerNo = new JRadioButton("No");
        greenPowerCheck.add(greenPowerYes);
        greenPowerCheck.add(greenPowerNo);

        JLabel temp = new JLabel("$");
        temp.setHorizontalAlignment(JLabel.RIGHT);

        questions.add(naturalGasLabel);
        questions.add(temp);
        questions.add(naturalGas);

        temp = new JLabel("$");
        temp.setHorizontalAlignment(JLabel.RIGHT);

        questions.add(electricityLabel);
        questions.add(temp);
        questions.add(electricity);

        temp = new JLabel("%");
        temp.setHorizontalAlignment(JLabel.RIGHT);

        questions.add(greenPowerLabel);
        questions.add(greenPowerYes);
        questions.add(greenPowerNo);

        questions.add(greenPercentLabel);
        questions.add(temp);
        questions.add(greenPercent);

        temp = new JLabel("$");
        temp.setHorizontalAlignment(JLabel.RIGHT);

        questions.add(fuelLabel);
        questions.add(temp);
        questions.add(fuel);

        temp = new JLabel("$");
        temp.setHorizontalAlignment(JLabel.RIGHT);

        questions.add(propaneLabel);
        questions.add(temp);
        questions.add(propane);

        panel.add(EmissionsData.INSTRUCTIONS, BorderLayout.NORTH);
        panel.add(submit, BorderLayout.SOUTH);

        panel.add(questions, BorderLayout.CENTER);
        this.add(panel);
        this.setSize(500, 650);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (validateData()) {
            this.dispatchEvent(new WindowEvent(this,
                WindowEvent.WINDOW_CLOSING));
            new WasteForm();
        }
    }


    public boolean validateData() {
        String errors = "";
// Check the Natural Gas data
        double naturalGasCost = -1;
        try {
            naturalGasCost = Double.parseDouble(naturalGas.getText());
            if (naturalGasCost < 0) {
                errors += naturalGasLabel.getText() + "\n";
            }
        }
        catch (NumberFormatException e) {
            errors += naturalGasLabel.getText() + "\n";
        }
// Check the Electricity Data
        double electricCost = -1;
        try {
            electricCost = Double.parseDouble(electricity.getText());
            if (electricCost < 0) {
                errors += electricityLabel.getText() + "\n";
            }
        }
        catch (NumberFormatException e) {
            errors += electricityLabel.getText() + "\n";
        }

        // Check if a choice was made for green power
        boolean green = false;
        if (!greenPowerYes.isSelected() && !greenPowerNo.isSelected()) {
            errors += greenPowerLabel.getText() + "\n";
        }
        else if (!greenPowerYes.isSelected()) {
            green = true;
        }

        // check the green percentage data
        double greenPercentage = -1;
        try {
            greenPercentage = Double.parseDouble(greenPercent.getText());
            if (greenPercentage < 0 || greenPercentage > 100) {
                errors += greenPercentLabel.getText() + "\n";
            }
        }
        catch (NumberFormatException e) {
            errors += greenPercentLabel.getText() + "\n";
        }

        // check the fuel oil data
        double fuelCost = -1;
        try {
            fuelCost = Double.parseDouble(fuel.getText());
            if (fuelCost < 0) {
                errors += fuelLabel.getText() + "\n";
            }
        }
        catch (NumberFormatException e) {
            errors += fuelLabel.getText() + "\n";
        }

        // check the propane data
        double propaneCost = -1;
        try {
            propaneCost = Double.parseDouble(propane.getText());
            if (propaneCost < 0) {
                errors += propaneLabel.getText() + "\n";
            }
        }
        catch (NumberFormatException e) {
            errors += propaneLabel.getText() + "\n";
        }

        if (errors.length() > 0) {
            JOptionPane.showMessageDialog(this, EmissionsData.ERROR_MESSAGE
                + errors);
        }
        else {
            EmissionsData.setHomeEnergyValues(naturalGasCost, electricCost,
                green, greenPercentage, fuelCost, propaneCost);
        }

        return errors.length() == 0;
    }

}
