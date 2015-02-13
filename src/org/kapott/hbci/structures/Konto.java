/*  $Id: Konto.java,v 1.1 2011/05/04 22:37:49 willuhn Exp $

    This file is part of HBCI4Java
    Copyright (C) 2001-2008  Stefan Palme

    HBCI4Java is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    HBCI4Java is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
package org.kapott.hbci.structures;

import org.kapott.hbci.manager.HBCIUtils;
import java.io.Serializable;
import java.util.List;
/**
 * Kontoverbindung. Diese Klasse repr�sentiert eine einzelne Kontoverbindung
 * eines Kunden bei einer Bank.
 */
public class Konto implements Serializable {
    /**
     * L�nderkennzeichen des Kontos. Normalerweise ist hier der Wert
     * <code>DE</code> f�r Deutschland einzustellen.
     */
    public String country;
    /**
     * Bankleitzahl der kontof�hrenden Bank
     */
    public String blz;
    /**
     * Kontonummer des Kontos
     */
    public String number;
    /**
     * Unterkontomerkmal des Kontos, kann <code>null</code> sein
     */
    public String subnumber;
    /**
     * Kontoart (Girokonto, Sparkonto, Festgeldkonto, Kreditkartenkonto, etc.)
     * laut Segmentversion 5,6 von HIUPD. Wird bspw. bei DeuBa-Konten ben�tigt
     * da dort verschiedene Konten genau die gleiche Kontonummer haben bzw. sich
     * nur in der Kontoart unterscheiden
     */
    public String acctype;
    /**
     * Name (Typ) des Kontos. Dieses Feld ist nur f�r Konten verf�gbar, auf die
     * der Anwender via HBCI Zugriff hat. F�r alle anderen Konten ist dieser
     * Wert <code>null</code>
     */
    public String type;
    /**
     * W�hrung des Kontos. Hier ist in der Regel <code>EUR</code> f�r EURO
     * gespeichert.
     */
    public String curr;
    /**
     * Kreditinstitusseitiger Kundenname. Dieser Wert gibt an, unter welcher
     * Kunden-ID ein Bankkunde Zugriff auf dieses Konto hat. Dieser Wert ist nur
     * f�r Konten verf�gbar, auf die der Anwender Zugriff via HBCI hat, f�r alle
     * anderen Konten ist dieser Wert <code>null</code>. Falls eine Bank
     * Informationen �ber alle Konten bereitstellt, auf die ein Nutzer via HBCI
     * Zugriff hat, so kann dieses Feld beim Hinzuf�gen von Auftr�gen via {@link
     * org.kapott.hbci.GV.HBCIJob#addToQueue(String)} ausgewertet werden.
     */
    public String customerid;
    /**
     * Name des Kontoinhabers. Hier wird bei eigenen Konten der Inhabername
     * eingestellt, wie er von der Bank bereitgestellt wird. Bei fremden Konten
     * (z.B. bei den Konten, die als Gegenkonten auf einem Kontoauszug
     * erscheinen) wird hier der Name eingestellt, wie er in den Auftragsdaten
     * von der Bank gef�hrt wird.
     */
    public String name;
    /**
     * Name des Kontoinhabers (Fortsetzung) (optional).
     */
    public String name2;
    public Limit limit;
    public List allowedGVs;
    /**
     * BIC des Kontos
     */
    public String bic;
    /**
     * IBAN des Kontos
     */
    public String iban;

    /**
     * Anlegen eines neuen Konto-Objektes. Die W�hrung wird auf <code>EUR</code>
     * voreingestellt
     */
    public Konto () {
        this.curr = "EUR";
    }

    /**
     * Anlegen eines neuen Konto-Objektes. Die W�hrung wird auf <code>EUR</code>
     * voreingestellt. Es werden BLZ und Kontonummer angegeben. Die
     * L�nderkennung wird auf <code>DE</code> voreingestellt.
     *
     * @param blz Bankleitzahl der kontof�hrenden Bank
     * @param number Kontonummer des Kontos
     */
    public Konto (String blz, String number) {
        this("DE", blz, number);
    }

    /**
     * Anlegen eines neuen Konto-Objektes. Die W�hrung wird auf <code>EUR</code>
     * voreingestellt. Es werden BLZ, L�nderkennung und Kontonummer angegeben.
     *
     * @param country die L�nderkennung des kontof�hrenden Institutes
     * (normalerweise <code>DE</code>)
     * @param blz Bankleitzahl der kontof�hrenden Bank
     * @param number Kontonummer des Kontos
     */
    public Konto (String country, String blz, String number) {
        this(country, blz, number, null);
    }

    // TODO: doku fehlt
    public Konto (String country, String blz, String number, String subnumber) {
        this();
        this.country = country;
        this.blz = blz;
        this.number = number;
        this.subnumber = subnumber;
    }

    /**
     * Umwandeln der Kontoinformationen in einen String.
     *
     * @return Stringdarstellung der Kontoverbindung
     */
    public String toString () {
        StringBuffer ret = new StringBuffer();
        if (type != null) {
            ret.append(type + " ");
        }
        if (name != null) {
            ret.append(name + " ");
        }
        if (name2 != null) {
            ret.append(name2 + " ");
        }
        if (number != null) {
            ret.append(number);
        }
        if (subnumber != null) {
            ret.append("/" + subnumber);
        }
        ret.append(" ");
        if (blz != null) {
            ret.append(
                    "BLZ " + blz + " (" + HBCIUtils.getNameForBLZ(blz) + ") ");
        }
        if (bic != null) {
            ret.append("BIC " + bic + " ");
        }
        if (iban != null) {
            ret.append("IBAN " + iban + " ");
        }
        if (country != null) {
            ret.append("[" + country + "] ");
        }
        if (curr != null) {
            ret.append("(" + curr + ")");
        }
        return ret.toString();
    }

    /**
     * �berpr�fen der Kontonummer anhand des Pr�fzifferverfahrens, welche f�r
     * die Bank mit der Bankleitzahl <code>blz</code> gilt. Der Aufruf dieser
     * Methode setzt voraus, dass in diesem Kontoobjekt bereits <code>blz</code>
     * und <code>number</code> gesetzt sind. Diese Werte werden der Methode
     * {@link org.kapott.hbci.manager.HBCIUtils#checkAccountCRC(String, String)}
     * zur �berpr�fung �bergeben.
     *
     * @return es wird nur dann <code>false</code> zur�ckgegeben, wenn das
     * Pr�fzifferverfahren f�r die jeweilige Bank implementiert ist und die
     * Pr�fung der Kontonummer einen Fehler ergibt. In jedem anderen Fall wird
     * <code>true</code> zur�ckgegeben
     */
    public boolean checkCRC () {
        return HBCIUtils.checkAccountCRC(blz, number);
    }

    // TODO: doku fehlt
    public boolean checkIBAN () {
        return HBCIUtils.checkIBANCRC(iban);
    }

    public boolean equals (Object o) {
        boolean ret;
        if (o instanceof Konto) {
            Konto acc = (Konto) o;
            ret = true;
            // TODO: wenn this.blz==null und acc.blz!=null ist gibts ne exception
            ret &= (this.blz == null && acc.blz == null || this.blz
                    .equals(acc.blz));
            ret &= (this.country == null && acc.country == null || this.country
                    .equals(acc.country));
            ret &= (this.number == null && acc.number == null || this.number
                    .equals(acc.number));
            ret &=
                    (this.subnumber == null && acc.subnumber == null || this.subnumber
                            .equals(acc.subnumber));
            ret &= (this.curr == null && acc.curr == null || this.curr
                    .equals(acc.curr));
            ret &=
                    (this.customerid == null && acc.customerid == null || this.customerid
                            .equals(acc.customerid));
            ret &= (this.name == null && acc.name == null || this.name
                    .equals(acc.name));
            ret &= (this.name2 == null && acc.name2 == null || this.name2
                    .equals(acc.name2));
            ret &= (this.type == null && acc.type == null || this.type
                    .equals(acc.type));
            ret &= (this.bic == null && acc.bic == null || this.bic
                    .equals(acc.bic));
        } else {
            ret = super.equals(o);
        }
        return ret;
    }

    /**
     * Gibt <code>true</code> zur�ck, wenn sich dieses Konto f�r SEPA-GVs
     * verwenden l�sst
     */
    public boolean isSEPAAccount () {
        return (bic != null && iban != null && bic.length() != 0 && iban
                .length() != 0);
    }
}
