package emissions;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class BasicsForm extends JFrame implements ActionListener {

    JTextField numPeople, zipCode;
    JComboBox<String> heating;
    JLabel numPeopleLabel, zipCodeLabel, heatingLabel;


    public BasicsForm() {
        super("Household Information");
        GridLayout grid = new GridLayout(3, 2);
        BorderLayout borders = new BorderLayout();

        JPanel panel = new JPanel(borders);

        JPanel questions = new JPanel(grid);

        numPeopleLabel = new JLabel(
            "<html><p>How many people live in your home?</html</p>");
        zipCodeLabel = new JLabel("<html><p>What is your zip code?</html</p>");
        heatingLabel = new JLabel(
            "<html><p>What is your household's primary heating source?</html</p>");

        numPeople = new JTextField(3);
        zipCode = new JTextField(5);

        heating = new JComboBox<String>(EmissionsData.HEATING_CHOICES);
        heating.setVisible(true);

        JButton submit = new JButton("Submit");
        submit.addActionListener(this);

        EmissionsData.INSTRUCTIONS.setFont(new Font("Calibri", Font.BOLD, 18));

        panel.add(EmissionsData.INSTRUCTIONS, BorderLayout.NORTH);
        panel.add(submit, BorderLayout.SOUTH);

        questions.add(numPeopleLabel);
        questions.add(numPeople);
        questions.add(zipCodeLabel);
        questions.add(zipCode);
        questions.add(heatingLabel);
        questions.add(heating);

        panel.add(questions, BorderLayout.CENTER);
        this.add(panel);
        this.setSize(500, 300);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (validateData()) {
            EmissionsData.setNumPeople(Integer.parseInt(numPeople.getText()));
            EmissionsData.setHeating(heating.getSelectedIndex());
            this.dispatchEvent(new WindowEvent(this,
                WindowEvent.WINDOW_CLOSING));
            new VehicleForm();
        }
    }


    public boolean validateData() {
        String errors = "";

        String zip = zipCode.getText();
        String people = numPeople.getText();

        if (zip.length() == 5) {
            try {
                Double.parseDouble(zip);
                EmissionsData.setEFactorValue(zip);
            }
            catch (NumberFormatException e) {
                errors += zipCodeLabel.getText() + "\n";
            }
        }
        else {
            errors += zipCodeLabel.getText() + "\n";
        }
        try {
            int num = Integer.parseInt(people);
            if (num <= 0 || num > 100) {
                errors += numPeopleLabel.getText() + "\n";
            }
        }
        catch (NumberFormatException e) {
            errors += numPeopleLabel.getText() + "\n";
        }

        if (errors.length() > 0) {
            JOptionPane.showMessageDialog(this, EmissionsData.ERROR_MESSAGE
                + errors);
        }

        return errors.length() == 0;
    }

}
