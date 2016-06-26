package de.uni_hamburg.informatik.swt.se2.kino.barzahlung;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf.PlatzVerkaufsWerkzeug;

/**
 * Werkzeug zur Bezahlung eines Betrages.
 *
 * @author SE2 Uebungsgruppe
 *
 */
public class BarZahlWerkzeug
{

    private BarZahlUI _barzahlUi;
    private Vorstellung _vorstellung;
    private int _bezahlterBetrag;
    private PlatzVerkaufsWerkzeug _platzVerkaufsWerkzeug;
    private int _preis;

    /**
     * Konstruktor für BarZahlung.
     *
     * @param vorstellung die Vorstellung in der wir Karten kaufen möchten.
     * @param platzVerkaufswerkeug Plätze die gekauft werden möchten und der zu zahlende Preis.
     * @param preis gesamter Preis der zu Zahlen ist.
     */
    public BarZahlWerkzeug(Vorstellung vorstellung,
            PlatzVerkaufsWerkzeug platzVerkaufsWerkzeug, int preis)
    {
        _vorstellung = vorstellung;
        _platzVerkaufsWerkzeug = platzVerkaufsWerkzeug;
        _preis = preis;
        createUI();
        registriereUIAktionen();
        _barzahlUi.setVisible(true);
    }

    /**
     * Berechnet die Differenz zwischen dem Preis und dem gezahlten Betrag.
     */
    private int berechneDifferenz()
    {
        return _preis - _bezahlterBetrag;
    }

    /**
     * Erstellt die BarzahlUI.
     */
    private void createUI()
    {
        _barzahlUi = new BarZahlUI(_preis);
    }

    /**
     * Fügt die Möglichkeit hinzu den Preis der ausgewählten Plätze zu bezahlen.
     *
     * @param betrag Der Betrag der gezahlt wird.
     */
    private void fuehreBarZahlungDurch(int betrag)
    {
        if (istAllesBezahlt())
        {
            _platzVerkaufsWerkzeug.verkaufePlaetze(_vorstellung);
            _barzahlUi.dispose();
        }
        if (berechneDifferenz() > 0)
        {
            _bezahlterBetrag += betrag;
        }
        else
        {
            _bezahlterBetrag -= betrag;
        }
        _barzahlUi.clearBetragField();
        _barzahlUi.aktualisiereRestBetrag(berechneDifferenz());
    }

    /**
     * Prüft ob der Preis vollständig gezahlt wurde.
     */
    private boolean istAllesBezahlt()
    {
        return berechneDifferenz() == 0;
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

                @Override
                public void keyPressed(KeyEvent e)
                {
                    //nothing to do here
                }

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

                @Override
                public void keyTyped(KeyEvent e)
                {
                    //nothing to do here
                }

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
