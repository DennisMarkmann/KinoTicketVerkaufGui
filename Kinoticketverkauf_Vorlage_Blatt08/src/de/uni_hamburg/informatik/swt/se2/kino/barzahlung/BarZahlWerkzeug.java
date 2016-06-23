package de.uni_hamburg.informatik.swt.se2.kino.barzahlung;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
        _barzahlUi = new BarZahlWerkzeugUI(0);
    }

    private void fuehreBarZahlungDurch(String bezahlterBetrag)
    {
        if (istAllesBezahlt())
        {
            //TODO eigentlicher Verkauf
        }
        //_bezahlterBetrag += bezahlterBetrag;
        //TODO berechneDifferenz
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
                    fuehreBarZahlungDurch(_barzahlUi.getBetragText());
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
                    if (_barzahlUi.getBetragField()
                        .getText()
                        .length() > 0 && _barzahlUi.getBetragAsInt() != 0)
                    {
                        return true;
                    }
                    return false;
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
