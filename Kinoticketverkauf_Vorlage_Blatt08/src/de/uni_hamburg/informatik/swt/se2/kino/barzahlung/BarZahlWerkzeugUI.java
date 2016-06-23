package de.uni_hamburg.informatik.swt.se2.kino.barzahlung;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

class BarZahlWerkzeugUI extends JDialog
{
    private static final long serialVersionUID = 4057874294977034472L;

    private JLabel _preisLabelDesc;
    private JLabel _preisLabel;
    private JTextField _betragField;
    private JLabel _betragLabelDesc;

    private JLabel _restLabelDesc;
    private JLabel _restLabel;
    private JButton _okayButton;
    private JButton _abbrechenButton;
    private final GridBagConstraints _gridBagConstraints = new GridBagConstraints();

    BarZahlWerkzeugUI(int preis)
    {
        this.setSize(300, 200);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        _gridBagConstraints.fill = GridBagConstraints.BOTH;
        _gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        _gridBagConstraints.weightx = 2;

        _preisLabelDesc = new JLabel("Preis:");
        _preisLabel = new JLabel("" + preis);
        _betragLabelDesc = new JLabel("Betrag:");
        _betragField = new JTextField();
        _restLabelDesc = new JLabel("Rest:");
        _restLabel = new JLabel("" + preis);
        _okayButton = new JButton("Okay");
        _okayButton.setEnabled(false);
        _abbrechenButton = new JButton("Abbrechen");

        addComponent(_preisLabelDesc, 0, 0);
        addComponent(_preisLabel, 1, 0);
        addComponent(_betragLabelDesc, 0, 1);
        addComponent(_betragField, 1, 1);
        addComponent(_restLabelDesc, 0, 2);
        addComponent(_restLabel, 1, 2);
        addComponent(_okayButton, 0, 3);
        addComponent(_abbrechenButton, 1, 3);

    }

    private void addComponent(final Component component, final int gridxValue,
            final int gridyValue)
    {
        _gridBagConstraints.gridx = gridxValue;
        _gridBagConstraints.gridy = gridyValue;
        this.add(component, _gridBagConstraints);
    }

    void aktualisiereRestBetrag(int restBetrag)
    {
        this._restLabelDesc.setText("" + restBetrag);
    }

    void changeBetragFieldColor(Color color)
    {
        _betragField.setBackground(color);
    }

    void changeOkayButtonState(boolean enabled)
    {
        _okayButton.setEnabled(enabled);
    }

    void clearBetragField()
    {
        this._betragField.setText("");
    }

    JButton getAbbrechenButton()
    {
        return _abbrechenButton;
    }

    int getBetragAsInt()
    {
        int betrag = 0;
        try
        {
            betrag = Integer.parseInt(getBetragText());
            return betrag;
        }
        catch (NumberFormatException e)
        {
            return betrag;
        }
    }

    JTextField getBetragField()
    {
        return _betragField;
    }

    String getBetragText()
    {
        return _betragField.getText();
    }

    JButton getOkayButton()
    {
        return _okayButton;
    }
}
