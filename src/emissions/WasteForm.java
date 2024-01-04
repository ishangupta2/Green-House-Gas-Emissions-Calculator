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

@SuppressWarnings("serial")
public class WasteForm extends JFrame implements ActionListener {

    ButtonGroup steel, plastic, glass, newspaper, magazine;
    JRadioButton steelYes, steelNo, plasticYes, plasticNo, glassYes, glassNo,
        newspaperYes, newspaperNo, magazineYes, magazineNo;
    JLabel steelLabel, plasticLabel, glassLabel, newspaperLabel, magazineLabel;


    public WasteForm() {
        GridLayout grid = new GridLayout(5, 3);
        BorderLayout borders = new BorderLayout();

        JPanel panel = new JPanel(borders);
        JPanel questions = new JPanel(grid);

        JButton submit = new JButton("Submit");
        submit.addActionListener(this);

        steelLabel = new JLabel(
            "<html><p>Do you recycle aluminum and steel cans?</html></p>");
        steel = new ButtonGroup();
        steelYes = new JRadioButton("Yes");
        steelNo = new JRadioButton("No");
        steel.add(steelNo);
        steel.add(steelYes);

        plasticLabel = new JLabel(
            "<html><p>Do you recycle plastic?</html></p>");
        plastic = new ButtonGroup();
        plasticYes = new JRadioButton("Yes");
        plasticNo = new JRadioButton("No");
        plastic.add(plasticNo);
        plastic.add(plasticYes);

        glassLabel = new JLabel("<html><p>Do you recycle glass?</html></p>");
        glass = new ButtonGroup();
        glassYes = new JRadioButton("Yes");
        glassNo = new JRadioButton("No");
        glass.add(glassNo);
        glass.add(glassYes);

        newspaperLabel = new JLabel(
            "<html><p>Do you recycle newspaper?</html></p>");
        newspaper = new ButtonGroup();
        newspaperYes = new JRadioButton("Yes");
        newspaperNo = new JRadioButton("No");
        newspaper.add(newspaperNo);
        newspaper.add(newspaperYes);

        magazineLabel = new JLabel(
            "<html><p>Do you recycle magazines?</html></p>");
        magazine = new ButtonGroup();
        magazineYes = new JRadioButton("Yes");
        magazineNo = new JRadioButton("No");
        magazine.add(magazineNo);
        magazine.add(magazineYes);

        questions.add(steelLabel);
        questions.add(steelYes);
        questions.add(steelNo);

        questions.add(plasticLabel);
        questions.add(plasticYes);
        questions.add(plasticNo);

        questions.add(glassLabel);
        questions.add(glassYes);
        questions.add(glassNo);

        questions.add(newspaperLabel);
        questions.add(newspaperYes);
        questions.add(newspaperNo);

        questions.add(magazineLabel);
        questions.add(magazineYes);
        questions.add(magazineNo);

        panel.add(EmissionsData.INSTRUCTIONS, BorderLayout.NORTH);
        panel.add(submit, BorderLayout.SOUTH);

        panel.add(questions, BorderLayout.CENTER);
        this.add(panel);
        this.setSize(500, 500);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (validateData()) {
            this.dispatchEvent(new WindowEvent(this,
                WindowEvent.WINDOW_CLOSING));
            new EmissionsForm();
        }
    }


    public boolean validateData() {
        String errors = "";

        if (!steelYes.isSelected() && !steelNo.isSelected()) {
            errors += steelLabel.getText() + "\n";
        }

        if (!plasticYes.isSelected() && !plasticNo.isSelected()) {
            errors += plasticLabel.getText() + "\n";
        }
        if (!glassYes.isSelected() && !glassNo.isSelected()) {
            errors += glassLabel.getText() + "\n";
        }
        if (!newspaperYes.isSelected() && !newspaperNo.isSelected()) {
            errors += newspaperLabel.getText() + "\n";
        }
        if (!magazineYes.isSelected() && !magazineNo.isSelected()) {
            errors += magazineLabel.getText() + "\n";
        }

        if (errors.length() > 0) {
            JOptionPane.showMessageDialog(this, EmissionsData.ERROR_MESSAGE
                + errors);
        }
        else {
            EmissionsData.setWasteValues(steelYes.isSelected(), plasticYes
                .isSelected(), glassYes.isSelected(), newspaperYes.isSelected(),
                magazineYes.isSelected());
        }
        return errors.length() == 0;
    }

}
