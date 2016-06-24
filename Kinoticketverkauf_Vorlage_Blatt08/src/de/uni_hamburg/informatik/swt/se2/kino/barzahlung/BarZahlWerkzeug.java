package de.uni_hamburg.informatik.swt.se2.kino.barzahlung;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf.PlatzVerkaufsWerkzeug;

public class BarZahlWerkzeug
{

    private BarZahlWerkzeugUI _barzahlUi;
    private Vorstellung _vorstellung;
    private int _bezahlterBetrag;
    private PlatzVerkaufsWerkzeug _platzVerkaufsWerkzeug;
    private int _preis;

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

    private int berechneDifferenz()
    {
        return _preis - _bezahlterBetrag;
    }

    private void createUI()
    {
        _barzahlUi = new BarZahlWerkzeugUI(_preis);
    }

    private void fuehreBarZahlungDurch(int betrag)
    {
        if (istAllesBezahlt())
        {
            _platzVerkaufsWerkzeug.verkaufePlaetze(_vorstellung);
            _barzahlUi.dispose();
        }
        _bezahlterBetrag += betrag;
        _barzahlUi.clearBetragField();
        _barzahlUi.aktualisiereRestBetrag(berechneDifferenz());
    }

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
            .getDocument()
            .addDocumentListener(new DocumentListener()
            {
                @Override
                public void changedUpdate(DocumentEvent e)
                {
                    validate();
                }

                @Override
                public void insertUpdate(DocumentEvent e)
                {
                    validate();
                }

                private boolean istBetragGueltig()
                {
                    if (berechneDifferenz() != 0 && _barzahlUi.getBetragField()
                        .getText()
                        .length() == 0 && _barzahlUi.getBetragAsInt() == 0)
                    {
                        return false;
                    }
                    return true;
                }

                @Override
                public void removeUpdate(DocumentEvent e)
                {
                    validate();
                }

                public void validate()
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
                }
            });
    }

}
