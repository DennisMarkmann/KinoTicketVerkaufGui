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

    public BarZahlWerkzeugUI(int preis)
    {
        _preisLabel = new JLabel();
        _betragField = new JTextField();
        _restLabel = new JLabel();
        _okayButton = new JButton();
        _abbrechenButton = new JButton();

        this.setSize(500, 500);
        this.setVisible(true);

        //TODO
    }

    void aktualisiereRestBetrag()
    {

    }

    public JButton getAbbrechenButton()
    {
        return _abbrechenButton;
    }

    String getBetrag()
    {
        return _betragField.getText();
    }

    public JButton getOkayButton()
    {
        return _okayButton;
    }

}
