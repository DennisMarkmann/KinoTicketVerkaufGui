package de.uni_hamburg.informatik.swt.se2.kino.barzahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;

public class BarZahlWerkzeug
{

    private BarZahlWerkzeugUI _barzahlUi;
    private Vorstellung _vorstellung;

    public BarZahlWerkzeug(Vorstellung _vorstellung)
    {
        createUI();
        _vorstellung = _vorstellung;
    }

    private void berechneDifferenz(int bezahlterBetrag)
    {

    }

    private void createUI()
    {
        _barzahlUi = new BarZahlWerkzeugUI(0);
    }

    public void fuehreBarZahlungDurch()
    {
        istBetragGueltig();
    }

    private void istBetragGueltig()
    {
        //TODO betrag kleiner Preis, dateityp korrekt, feld nicht leer
    }

    private void pruefeAllesBezahlt()
    {

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
