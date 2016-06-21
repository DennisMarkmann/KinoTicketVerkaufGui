package de.uni_hamburg.informatik.swt.se2.kino.barzahlung;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BarZahlWerkzeugUI extends JFrame
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
        _okayButton = new JButton();
        _abbrechenButton = new JButton();

        this.setSize(500, 500);
        this.setVisible(true);

        //TODO create UI
    }

    void aktualisiereRestBetrag(int restBetrag)
    {
        this._restLabel.setText("" + restBetrag);
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

    String getBetragText()
    {
        return _betragField.getText();
    }

    JButton getOkayButton()
    {
        return _okayButton;
    }
}
