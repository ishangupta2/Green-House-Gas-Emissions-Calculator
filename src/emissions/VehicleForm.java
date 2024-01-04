package emissions;

import java.awt.BorderLayout;
import java.awt.Font;
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
public class VehicleForm extends JFrame implements ActionListener {

    JTextField[] carInfo;
    JLabel[] carLabel;
    JRadioButton maintenance;
    JRadioButton noMaintenance;
    ButtonGroup checkMaintenance;
    JLabel maintenanceLabel;


    public VehicleForm() {
        carLabel = new JLabel[5];
        for (int x = 1; x <= carLabel.length; x++) {
            carLabel[x - 1] = new JLabel("Vehicle " + x);
        }

        carInfo = new JTextField[5];
        for (int x = 0; x < carInfo.length; x++) {
            carInfo[x] = new JTextField(6);
        }

        String carInfoLabel =
            "<html><p>Enter average miles driven per week</p></html>";

        GridLayout grid = new GridLayout(6, 3);
        BorderLayout borders = new BorderLayout();

        JPanel panel = new JPanel(borders);
        JPanel questions = new JPanel(grid);

        for (int x = 0; x < carLabel.length; x++) {
            questions.add(carLabel[x]);
            questions.add(new JLabel(carInfoLabel));
            questions.add(carInfo[x]);
        }

        JButton submit = new JButton("Submit");
        submit.addActionListener(this);

        JLabel instructions = new JLabel(
            "<html><p>Please make sure all your information is correct before submitting and moving onto the next form. If you don't own a car, please enter 0 for the fields.</p></html>");
        instructions.setFont(new Font("Calibri", Font.BOLD, 18));

        maintenance = new JRadioButton("Yes, I do maintenance.");
        noMaintenance = new JRadioButton("No maintenance.");
        maintenanceLabel = new JLabel(
            "<html><p>Do you perform regular maintenance on the vehicle?</p></html>");
        checkMaintenance = new ButtonGroup();
        checkMaintenance.add(maintenance);
        checkMaintenance.add(noMaintenance);

        questions.add(maintenanceLabel);
        questions.add(maintenance);
        questions.add(noMaintenance);

        panel.add(instructions, BorderLayout.NORTH);
        panel.add(submit, BorderLayout.SOUTH);

        panel.add(questions, BorderLayout.CENTER);
        this.add(panel);
        this.setSize(500, 500);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (validateData()) {
            int[] temp = new int[5];
            for (int x = 0; x < carInfo.length; x++) {
                temp[x] = Integer.parseInt(carInfo[x].getText());
            }
            EmissionsData.setVehicles(temp);
            EmissionsData.setMaintenance(maintenance.isSelected());
            EmissionsData.setVehicleEmissions();
            this.dispatchEvent(new WindowEvent(this,
                WindowEvent.WINDOW_CLOSING));
            new HomeEnergyForm();
        }
    }


    public boolean validateData() {
        String errors = "";
        for (int x = 0; x < 5; x++) {
            int num;
            try {
                num = Integer.parseInt(this.carInfo[x].getText());
                if (num < 0 || num > 11000) {
                    errors += carLabel[x].getText() + "\n";
                }
            }
            catch (NumberFormatException e) {
                errors += carLabel[x].getText() + "\n";
            }
        }
        if ((!maintenance.isSelected() && !noMaintenance.isSelected())) {
            errors += maintenanceLabel.getText() + "\n";
        }
        if (errors.length() > 0) {
            JOptionPane.showMessageDialog(this, EmissionsData.ERROR_MESSAGE
                + errors);
        }
        return errors.length() == 0;
    }

}
