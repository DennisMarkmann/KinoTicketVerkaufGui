package de.uni_hamburg.informatik.swt.se2.kino.barzahlung;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;

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
    private FlowLayout _layout;

    BarZahlWerkzeugUI(int preis)
    {

        this.setSize(300, 120);
        _layout = new FlowLayout();
        _preisLabel = new JLabel("Preis: " + preis);
        _betragField = new JTextField(20);
        _restLabel = new JLabel("Restbetrag: " + preis);
        _okayButton = new JButton("Okay");
        _okayButton.setEnabled(false);
        _abbrechenButton = new JButton("Abbrechen");
        this.setLocationRelativeTo(null);
        this.setLayout(_layout);
        this.add(_preisLabel);
        this.add(_restLabel);
        this.add(_betragField);
        this.add(_okayButton);
        this.add(_abbrechenButton);
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
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
        String input = _betragField.getText();
        if (this.isInteger(input))
        {
            int foo = Integer.parseInt(input);
            return foo;
        }

        return 0;
    }

    protected JTextField getBetragField()
    {
        return _betragField;
    }

    protected String getBetragText()
    {
        return _betragField.getText();
    }

    protected JButton getOkayButton()
    {
        return _okayButton;
    }

    private boolean isInteger(String s)
    {

        try
        {
            Integer.parseInt(s);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        catch (NullPointerException e)
        {
            return false;
        }
        // only got here if we didn't return false
        //stackoverflow.com
        return true;
    }
}
