package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import de.uni_hamburg.informatik.swt.se2.kino.barzahlung.BarZahlWerkzeug;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Platz;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kinosaal;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;

/**
 * Mit diesem Werkzeug können Plätze verkauft und storniert werden. Es arbeitet
 * auf einer Vorstellung als Material. Mit ihm kann angezeigt werden, welche
 * Plätze schon verkauft und welche noch frei sind.
 *
 * Dieses Werkzeug ist ein eingebettetes Subwerkzeug.
 *
 * @author SE2-Team
 * @version SoSe 2016
 */
public class PlatzVerkaufsWerkzeug
{
    // Die aktuelle Vorstellung, deren Plätze angezeigt werden. Kann null sein.
    private Vorstellung _vorstellung;

    private PlatzVerkaufsWerkzeugUI _ui;
    private int _preis;

    /**
     * Initialisiert das PlatzVerkaufsWerkzeug.
     */
    public PlatzVerkaufsWerkzeug()
    {
        _ui = new PlatzVerkaufsWerkzeugUI();
        registriereUIAktionen();
        // Am Anfang wird keine Vorstellung angezeigt:
        setVorstellung(null);
    }

    /**
     * Aktualisiert den Platzplan basierend auf der ausgwählten Vorstellung.
     */
    private void aktualisierePlatzplan()
    {
        if (_vorstellung != null)
        {
            Kinosaal saal = _vorstellung.getKinosaal();
            initialisierePlatzplan(saal.getAnzahlReihen(),
                    saal.getAnzahlSitzeProReihe());
            markiereNichtVerkaufbarePlaetze(saal.getPlaetze());
        }
        else
        {
            initialisierePlatzplan(0, 0);
        }
    }

    /**
     * Aktualisiert den anzuzeigenden Gesamtpreis
     */
    private void aktualisierePreisanzeige(Set<Platz> plaetze)
    {
        if (istVerkaufenMoeglich(plaetze))
        {
            _preis = _vorstellung.getPreisFuerPlaetze(plaetze);
            _ui.getPreisLabel()
                .setText("Gesamtpreis: " + _preis + " Eurocent");
        }
        else if (istStornierenMoeglich(plaetze))
        {
            _preis = _vorstellung.getPreisFuerPlaetze(plaetze);
            _ui.getPreisLabel()
                .setText("Gesamtstorno: " + _preis + " Eurocent");
        }
        else if (!plaetze.isEmpty())
        {
            _ui.getPreisLabel()
                .setText("Verkauf und Storno nicht gleichzeitig möglich!");
        }
        else
        {
            _ui.getPreisLabel()
                .setText("Gesamtpreis: 0 Eurocent");
        }
    }

    /**
     * Startet die Barzahlung.
     */
    private void fuehreBarzahlungDurch()
    {
        new BarZahlWerkzeug(_vorstellung, this, _preis);
    }

    /**
     * Gibt das Panel dieses Subwerkzeugs zurück. Das Panel sollte von einem
     * Kontextwerkzeug eingebettet werden.
     *
     * @ensure result != null
     */
    public JPanel getUIPanel()
    {
        return _ui.getUIPanel();
    }

    /**
     * Setzt am Platzplan die Anzahl der Reihen und der Sitze.
     *
     * @param saal Ein Saal mit dem der Platzplan initialisiert wird.
     */
    private void initialisierePlatzplan(int reihen, int sitzeProReihe)
    {
        _ui.getPlatzplan()
            .setAnzahlPlaetze(reihen, sitzeProReihe);
    }

    /**
     * Prüft, ob die angegebenen Plätze alle storniert werden können.
     */
    private boolean istStornierenMoeglich(Set<Platz> plaetze)
    {
        return !plaetze.isEmpty() && _vorstellung.sindStornierbar(plaetze);
    }

    /**
     * Prüft, ob die angegebenen Plätze alle verkauft werden können.
     */
    private boolean istVerkaufenMoeglich(Set<Platz> plaetze)
    {
        return !plaetze.isEmpty() && _vorstellung.sindVerkaufbar(plaetze);
    }

    /**
     * Markiert alle nicht verkaufbaren Plätze im Platzplan als verkauft.
     *
     * @param plaetze Eine Liste mit allen Plaetzen im Saal.
     */
    private void markiereNichtVerkaufbarePlaetze(List<Platz> plaetze)
    {
        for (Platz platz : plaetze)
        {
            if (!_vorstellung.istVerkaufbar(platz))
            {
                _ui.getPlatzplan()
                    .markierePlatzAlsVerkauft(platz);
            }
        }
    }

    /**
     * Reagiert darauf, dass sich die Menge der ausgewählten Plätze geändert
     * hat.
     *
     * @param plaetze die jetzt ausgewählten Plätze.
     */
    private void reagiereAufNeuePlatzAuswahl(Set<Platz> plaetze)
    {
        _ui.getVerkaufenButton()
            .setEnabled(istVerkaufenMoeglich(plaetze));
        _ui.getStornierenButton()
            .setEnabled(istStornierenMoeglich(plaetze));
        aktualisierePreisanzeige(plaetze);
    }

    /**
     * Fügt der UI die Funktionalität hinzu mit entsprechenden Listenern.
     */
    private void registriereUIAktionen()
    {
        _ui.getVerkaufenButton()
            .addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    fuehreBarzahlungDurch();
                }
            });

        _ui.getStornierenButton()
            .addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    stornierePlaetze(_vorstellung);
                }
            });

        _ui.getPlatzplan()
            .addPlatzSelectionListener(new PlatzSelectionListener()
            {
                @Override
                public void auswahlGeaendert(PlatzSelectionEvent event)
                {
                    reagiereAufNeuePlatzAuswahl(event.getAusgewaehltePlaetze());
                }
            });
    }

    /**
     * Setzt die Vorstellung. Sie ist das Material dieses Werkzeugs. Wenn die
     * Vorstellung gesetzt wird, muss die Anzeige aktualisiert werden. Die
     * Vorstellung darf auch null sein.
     */
    public void setVorstellung(Vorstellung vorstellung)
    {
        _vorstellung = vorstellung;
        aktualisierePlatzplan();
    }

    /**
     * Storniert die ausgewählten Plaetze.
     */
    private void stornierePlaetze(Vorstellung vorstellung)
    {
        Set<Platz> plaetze = _ui.getPlatzplan()
            .getAusgewaehltePlaetze();
        vorstellung.stornierePlaetze(plaetze);
        aktualisierePlatzplan();
    }

    /**
     * Verkauft die ausgewählten Plaetze.
     */
    public void verkaufePlaetze(Vorstellung vorstellung)
    {
        Set<Platz> plaetze = _ui.getPlatzplan()
            .getAusgewaehltePlaetze();
        vorstellung.verkaufePlaetze(plaetze);
        aktualisierePlatzplan();
    }
}
