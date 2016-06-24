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
        registriereUIAktionen();
        _barzahlUi.setVisible(true);
    }

    private int berechneDifferenz()
    {
        return _vorstellung.getPreis() - _bezahlterBetrag;
    }

    private void createUI()
    {
        _barzahlUi = new BarZahlWerkzeugUI(_vorstellung.getPreis());
    }

    private void fuehreBarZahlungDurch(int betrag)
    {
        berechneDifferenz();
        _barzahlUi.changeOkayButtonState(istAllesBezahlt());
    }

    private boolean istAllesBezahlt()
    {
        return berechneDifferenz() == 0;
    }

    /**
     * Fügt der UI die Funktionalität hinzu mit entsprechenden Listenern.
     */
    public void registriereUIAktionen()
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

    }
}
