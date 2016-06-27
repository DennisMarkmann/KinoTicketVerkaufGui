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

/**
 * UI Klasse zur Bezahlung eines Betrages.
 *
 * @author SE2 Uebungsgruppe
 *
 */
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

    /**
     * Konstruktor über den das UI erstellt wird.
     *
     * @param preis gesamter Preis der im Preislabel angezeigt werden soll.
     * @ensure preis > 0
     */

    BarZahlWerkzeugUI(int preis)
    {
        assert preis > 0 : "Vorbedingung verletzt: preis > 0";
        setLayoutOptions();
        initializeUiObjects(preis);
        addObjectsToFrame();
    }

    /**
     * Hilfsmethode zum Hinzufuegen einer UI Komponente an den gegebenen Koordinaten im Gridbag Layout.
     *
     * @param component die hinzuzufuegende Komponente (z.B. ein JButton)
     * @param gridxValue die x-Koordinate an der die Komponente platziert werden soll.
     * @param gridyValue die y-Koordinate an der die Komponente platziert werden soll.
     */

    private void addComponent(final Component component, final int gridxValue,
            final int gridyValue)
    {
        _gridBagConstraints.gridx = gridxValue;
        _gridBagConstraints.gridy = gridyValue;
        this.add(component, _gridBagConstraints);
    }

    /**
     * Mehtode die alle Objekte an ihre Position des UIs hinzufuegt.
     */
    private void addObjectsToFrame()
    {
        addComponent(_preisLabelDesc, 0, 0);
        addComponent(_preisLabel, 1, 0);
        addComponent(_restLabelDesc, 0, 1);
        addComponent(_restLabel, 1, 1);
        addComponent(_betragLabelDesc, 0, 2);
        addComponent(_betragField, 1, 2);
        addComponent(_okayButton, 0, 3);
        addComponent(_abbrechenButton, 1, 3);
    }

    /**
     * Ändert den angezeigten Wert des restLabels auf den uebergegeben Wert.
     *
     * @param restBetrag der noch zu zahlen ist und in dem Label angezeigt werden soll.
     */
    void aktualisiereRestBetrag(int restBetrag)
    {
        this._restLabel.setText(restBetrag + " Cent");
    }

    /**
     * Aendert die Hintergrundfarbe des Betrag Feldes auf die uebergebene.
     *
     * @param color die Hintergrundfarbe die das Betragsfeld annehmen soll.
     * @ensure color != null
     */
    void changeBetragFieldColor(Color color)
    {
        assert color != null : "Vorbedingung verletzt: color != null";
        _betragField.setBackground(color);
    }

    /**
     * Aktiviert oder deaktiviert den Okay Button.
     *
     * @param enabled true = enable, false = disable
     */
    void changeOkayButtonState(boolean enabled)
    {
        _okayButton.setEnabled(enabled);
    }

    /**
     * Resetted den angezeigten Wert des Betragfeldes.
     */
    void clearBetragField()
    {
        this._betragField.setText("");
    }

    /**
     * Uebergibt den abbrechen Button.
     *
     * @return abbrechen Button.
     */
    JButton getAbbrechenButton()
    {
        return _abbrechenButton;
    }

    /**
     * Methode zur Uebergabe des Wertes im Betrag Textfeld als Integer.
     *
     * @return Wert des Betrag Textfelds als Integer.
     */
    int getBetragAsInt()
    {
        int betrag = 0;
        try
        {
            betrag = Integer.parseInt(_betragField.getText());
            return betrag;
        }
        catch (NumberFormatException e)
        {
            return betrag;
        }
    }

    /**
     * Uebergibt das Betrags TextField.
     *
     * @return Betrags TextField.
     */

    JTextField getBetragField()
    {
        return _betragField;
    }

    /**
     * Uebergibt den okay Button.
     *
     * @return okay Button.
     */

    JButton getOkayButton()
    {
        return _okayButton;
    }

    /**
     * Initialisiert die UI Objekte.
     *
     * @param preis gesamter Preis der im Preislabel angezeigt werden soll.
     */

    private void initializeUiObjects(int preis)
    {
        _preisLabelDesc = new JLabel("Preis:");
        _preisLabel = new JLabel(preis + " Cent");
        _betragLabelDesc = new JLabel("Betrag:");
        _betragField = new JTextField();
        _restLabelDesc = new JLabel("Rest:");
        _restLabel = new JLabel(preis + " Cent");
        _okayButton = new JButton("Okay");
        _okayButton.setEnabled(false);
        _abbrechenButton = new JButton("Abbrechen");
    }

    /**
     * Setzt die Einstellungen fuer das UI Layout.
     */

    private void setLayoutOptions()
    {
        this.setTitle("Barzahlung");
        this.setSize(300, 200);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        _gridBagConstraints.fill = GridBagConstraints.BOTH;
        _gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        _gridBagConstraints.weightx = 2;
    }
}
