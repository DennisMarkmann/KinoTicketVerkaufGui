package de.uni_hamburg.informatik.swt.se2.kino.barzahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;

public class BarZahlWerkzeug
{

    private BarZahlWerkzeugUI _barzahlUi;
    private Vorstellung _vorstellung;
    private int _preis;

    public BarZahlWerkzeug(Vorstellung vorstellung)
    {
        createUI();
        _vorstellung = vorstellung;
    }

    private int berechneDifferenz(int bezahlterBetrag)
    {
        return bezahlterBetrag - _preis;

    }

    private void createUI()
    {
        _barzahlUi = new BarZahlWerkzeugUI(0);
    }

    public void fuehreBarZahlungDurch()
    {
        //TODO
        istBetragGueltig();
    }

    private boolean istBetragGueltig()
    {
        return false;
        //TODO betrag kleiner Preis, dateityp korrekt, feld nicht leer
    }

    private boolean pruefeAllesBezahlt()
    {
        return false;

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
                    //TODO
                }
            });

        _barzahlUi.getAbbrechenButton()
            .addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    //TODO
                }
            });
    }

}
