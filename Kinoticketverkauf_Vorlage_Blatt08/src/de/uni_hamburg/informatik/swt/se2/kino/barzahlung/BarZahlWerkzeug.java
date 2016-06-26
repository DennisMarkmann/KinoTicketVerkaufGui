package de.uni_hamburg.informatik.swt.se2.kino.barzahlung;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf.PlatzVerkaufsWerkzeug;

/**
 * Werkzeug zur Unterstuetzung bei der Bezahlung eines Betrages.
 *
 * @author SE2 Uebungsgruppe
 */

public class BarZahlWerkzeug
{

    private BarZahlUI _barzahlUi;
    private Vorstellung _vorstellung;
    private int _bezahlterBetrag;
    private PlatzVerkaufsWerkzeug _platzVerkaufsWerkzeug;
    private int _preis;

    /**
     * Konstruktor ueber den das dazu gehoerige UI erzeugt wird.
     *
     * @param vorstellung zu der die Plaetze verkauft werden sollen.
     * @param platzVerkaufsWerkzeug das die Plaetze verkaufen soll, sobald die Bezahlung abgeschlossen ist.
     * @param preis der insgesamt zu zahlende Preis.
     *
     * @ensure vorstellung != null
     * @ensure platzVerkaufsWerkzeug != null
     * @ensure preis > 0
     */

    public BarZahlWerkzeug(Vorstellung vorstellung,
            PlatzVerkaufsWerkzeug platzVerkaufsWerkzeug, int preis)
    {
        assert vorstellung != null : "Vorbedingung verletzt: vorstellung != null ";
        assert platzVerkaufsWerkzeug != null : "Vorbedingung verletzt: platzVerkaufsWerkzeug != null ";
        assert preis > 0 : "Vorbedingung verletzt: preis > 0";

        _vorstellung = vorstellung;
        _platzVerkaufsWerkzeug = platzVerkaufsWerkzeug;
        _preis = preis;
        createUI();
        registriereUIAktionen();
        _barzahlUi.setVisible(true);
    }

    /**
     * Aktualisiert den bereits bezahlten Betrag.
     *
     * @param betrag der bereits bezahlt wurde.
     */

    private void aktualisiereBezahlterBetrag(int betrag)
    {
        if (berechnePreisDifferenz() > 0)
        {
            _bezahlterBetrag += betrag;
        }
        else
        {
            _bezahlterBetrag -= betrag;
        }
    }

    /**
     * Methode zur Berechnung der Differenz zwischen dem Gesamtpreis und dem bereits bezahlten Betrag.
     *
     * @return die noch vorhandene Differenz.
     */

    private int berechnePreisDifferenz()
    {
        return _preis - _bezahlterBetrag;
    }

    /**
     * Erstellt das UI.
     */

    private void createUI()
    {
        _barzahlUi = new BarZahlUI(_preis);
    }

    /**
     * Methode zur Durchfuehrung der eigentlichen Bezahlung. Verkauft auch die Plätze sobald die Bezahlung komplett ist.
     *
     * @param betrag der bezahlt wurde.
     */

    private void fuehreBarZahlungDurch(int betrag)
    {
        if (istAllesBezahlt())
        {
            _platzVerkaufsWerkzeug.verkaufePlaetze(_vorstellung);
            _barzahlUi.dispose();
        }
        aktualisiereBezahlterBetrag(betrag);
        _barzahlUi.clearBetragField();
        _barzahlUi.aktualisiereRestBetrag(berechnePreisDifferenz());
    }

    /**
     * Prueft ob die Preis Differenz gleich 0 ist.
     *
     * @return boolean ist Differenz = 0
     */

    private boolean istAllesBezahlt()
    {
        return berechnePreisDifferenz() == 0;
    }

    /**
     * Fügt der UI die Funktionalität hinzu mit entsprechenden Listenern.
     */
    private void registriereUIAktionen()
    {
        _barzahlUi.getOkayButton()
            .addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    fuehreBarZahlungDurch(_barzahlUi.getBetragAsInt());
                }
            });

        _barzahlUi.getAbbrechenButton()
            .addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    _barzahlUi.dispose();
                }
            });

        _barzahlUi.getBetragField()
            .addKeyListener(new KeyListener()
            {

                /**
                 * Prueft ob der im Betragsfeld eingetragene Wert gueltig ist oder nicht.
                 *
                 * @return gueltig = true, ungueltig = false
                 */
                private boolean istBetragGueltig()
                {
                    if (istAllesBezahlt() || (_barzahlUi.getBetragField()
                        .getText()
                        .length() > 0 && _barzahlUi.getBetragAsInt() != 0))
                    {
                        return true;
                    }
                    return false;
                }

                /**
                 * Tut nichts.
                 */

                @Override
                public void keyPressed(KeyEvent e)
                {
                    //nothing to do here
                }

                /**
                 * Enter + Eintrag in Betragsfeld ist valide -> BarZahlung wird durchgeführt.
                 * Esc = UI wird geschlossen und der Bezahlvorgang abgebrochen.
                 *
                 */

                @Override
                public void keyReleased(KeyEvent e)
                {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER & validate())

                    {
                        fuehreBarZahlungDurch(_barzahlUi.getBetragAsInt());
                    }
                    else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                    {
                        _barzahlUi.dispose();
                    }
                }

                /**
                 * Tut nichts.
                 */

                @Override
                public void keyTyped(KeyEvent e)
                {
                    //nothing to do here
                }

                /**
                 * Prueft ob der Eintrag im Betragsfeld gueltig ist oder nicht. Faerbt das Feld rot wenn nicht, weiß wenn doch. Deaktiviert oder aktiviert den Okay Button entsprechend.
                 * @return
                 */
                private boolean validate()
                {
                    if (istBetragGueltig())
                    {
                        _barzahlUi.changeBetragFieldColor(Color.WHITE);
                    }
                    else
                    {
                        _barzahlUi.changeBetragFieldColor(Color.RED);

                    }
                    _barzahlUi.changeOkayButtonState(istBetragGueltig());
                    return istBetragGueltig();
                }
            });
    }
}
