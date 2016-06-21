package de.uni_hamburg.informatik.swt.se2.kino.barzahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;

public class BarZahlWerkzeug
{

    private BarZahlWerkzeugUI _barzahlUi;
    private Vorstellung _vorstellung;
    private int _bezahlterBetrag;

    public BarZahlWerkzeug(Vorstellung vorstellung)
    {
        _vorstellung = vorstellung;
        createUI();
    }

    private int berechneDifferenz()
    {
        return _vorstellung.getPreis() - _bezahlterBetrag;
    }

    private void createUI()
    {
        _barzahlUi = new BarZahlWerkzeugUI(0);
    }

    public void fuehreBarZahlungDurch(int bezahlterBetrag)
    {
        _bezahlterBetrag += bezahlterBetrag;
        if (berechneDifferenz() == 0)
        {

        }
        //TODO berechneDifferenz
    }

    private boolean istBetragGueltig()
    {
        int betrag = _barzahlUi.getBetrag();
        if (betrag == 0)
        {
            return false;
        }
        //TODO betragString type int
        return true;
    }

    private boolean pruefeIstAllesBezahlt()
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
                    fuehreBarZahlungDurch(_barzahlUi.getBetrag());
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
    }

}
