package de.uni_hamburg.informatik.swt.se2.kino.barzahlung;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class BarZahlWerkzeugUI extends JFrame
{
    private static final long serialVersionUID = 4057874294977034472L;

    private JLabel _preisLabel;
    private JTextField _betragField;

    private JLabel _restLabel;
    private JButton _okayButton;
    private JButton _abbrechenButton;

    BarZahlWerkzeugUI(int preis)
    {

        _preisLabel = new JLabel("" + preis);
        _betragField = new JTextField();
        _restLabel = new JLabel("" + preis);
        _okayButton = new JButton("Okay");
        _okayButton.setEnabled(false);
        _abbrechenButton = new JButton("Abbrechen");
    }

    void aktualisiereRestBetrag(int restBetrag)
    {
        this._restLabel.setText("" + restBetrag);
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

    public int getBetragAsInt()
    {
        // TODO Auto-generated method stub
        return 0;
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
