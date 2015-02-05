/*  $Id: HBCICallback.java,v 1.2 2011/05/09 15:07:02 willuhn Exp $

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
package org.kapott.hbci.callback;

import org.kapott.hbci.passport.HBCIPassport;
import org.w3c.dom.Document;
import java.util.Date;
import java.util.Properties;
/**
 * <p>Schnittstelle, die eine Callback-Klasse implementieren muss. Beim
 * Initialisieren von <em>HBCI4Java</em> ({@link org.kapott.hbci.manager.HBCIUtils#init(Properties,
 * org.kapott.hbci.callback.HBCICallback)}) muss ein Callback-Objekt angegeben
 * werden. Die Klasse dieses Objektes muss die HBCICallback-Schnittstelle
 * implementieren. Der HBCI-Kernel ruft in bestimmten Situationen Methoden
 * dieser Klasse auf. Das ist z.B. dann der Fall, wenn eine bestimmte Aktion
 * (Einlegen der Chipkarte) oder Eingabe (Passwort) vom Anwender erwartet wird.
 * Au�erdem werden auf diesem Weg Informationen an den Anwender weitergegeben
 * (Mitteilungen des Kreditinstitutes bei der Dialoginitialisierung).</p> <p>Ein
 * Anwendungsentwickler muss die Methoden dieser Schnittstelle also geeignet
 * implementieren, um bei jeder m�glichen Ursache f�r den Aufruf einer der
 * Callback-Methoden sinnvoll zu reagieren. Dabei m�ssen nicht immer tats�chlich
 * alle Anfragen an den Anwender weitergegeben werden. Ist z.B. das Passwort f�r
 * die Schl�sseldatei eines Passports bereits bekannt, so kann die entsprechende
 * Methode dieses Passwort direkt zur�ckgeben, ohne den Anwender erneut danach
 * zu fragen. </p>
 */
public interface HBCICallback extends HBCICallbackKonstanten {
    /**
     * Wird aufgerufen, wenn der HBCI-Kernel eine Log-Ausgabe erzeugt.
     * <em>HBCI4Java</em> gibt Logging-Ausgaben nicht selbst auf irgendeinem
     * Device aus, sondern sendet diese mit Hilfe der Methode
     * <code>log(...)</code> an die Anwendung. Diese muss selbst entscheiden,
     * was mit der Information geschehen soll (einfach ignorieren, abspeichern,
     * dem Nutzer anzeigen, ...).
     *
     * @param msg die eigentliche Text-Meldung des HBCI-Kernels
     * @param level Loglevel, welcher die "Wichtigkeit" dieser Meldung angibt.
     * Die m�glichen Werte daf�r sind in {@link org.kapott.hbci.manager.HBCIUtils}
     * definiert und lauten <ul> <li><code>LOG_CHIPCARD</code></li>
     * <li><code>LOG_DEBUG</code></li> <li><code>LOG_INFO</code></li>
     * <li><code>LOG_WARN</code></li> <li><code>LOG_ERR</code></li> </ul>
     * @param date Zeitpunkt, zu dem die Logausgabe generiert wurde
     * @param trace ein <code>StackTrace</code>-Element, welches die Stelle im
     * Code beschreibt, an der die Logausgabe erzeugt wurde (kann benutzt
     * werden, um die Klasse, Methode, Zeilennummer etc. des Aufrufes zu
     * ermitteln)
     */
    public void log (String msg, int level, Date date, StackTraceElement trace);

    /**
     * Wird vom HBCI-Kernel aufgerufen, wenn die Interaktion mit der Anwendung
     * erforderlich ist. In bestimmten Situationen ben�tigt der HBCI-Kernel
     * zus�tzliche Daten bzw. muss auf die Ausf�hrung einer Aktion des Nutzers
     * warten. Dann wird diese Methode aufgerufen. Dabei wird ein Code
     * (<code>reason</code>) �bergeben, der anzeigt, welche Ursache dieser
     * Callbackaufruf hat, d.h. welche Daten oder Aktionen erwartet werden.
     * Falls Daten erwartet werden (z.B. ein Passwort, eine Benutzerkennung,
     * ...), so ist legt der Parameter <code>datatype</code> fest, wie diese
     * Daten erwartet werden. Die eigentlichen Daten muss die Anwendung im
     * Objekt <code>retData</code> ablegen (keinen neuen StringBuffer erzeugen,
     * sondern den Inhalt von <code>retData</code> �berschreiben!). Bei einigen
     * Callbacks �bergibt <em>HBCI4Java</em> einen vorgeschlagenen default-Wert
     * f�r die Nutzereingabe im <em>retData</em>-Objekt. Diese Tatsache ist
     * besonders bei der Auswertung des Callbacks {@link #HAVE_CRC_ERROR} zu
     * beachten!
     *
     * @param passport enth�lt das Passport-Objekt, bei dessen Benutzung der
     * Callback erzeugt wurde. Falls also in einer Anwendung mehrere
     * Passport-Objekte gleichzeitig benutzt werden, so kann anhand dieses
     * Parameters festgestellt werden, welches Passport (und damit welches
     * HBCIHandle) <em>HBCI4Java</em> gerade benutzt.
     * @param reason gibt den Grund f�r diesen Aufruf an. Dieser Parameter kann
     * alle Werte annehmen, die als "Ursache des Callback-Aufrufes" in der
     * Dokumentation aufgef�hrt sind. Je nach Wert dieses Parameters werden vom
     * Nutzer Aktionen oder Eingaben erwartet.
     * @param msg ein Hinweistext, der den Grund des Callbacks n�her beschreibt.
     * Dieser Parameter muss nicht ausgewertet werden, der Parameter
     * <code>reason</code> ist bereits eindeutig. Er dient nur dazu, bei
     * Anwendungen, die nicht f�r jeden Ursache des Callback-Aufrufes einen
     * eigenen Hinweistext bereitstellen wollen, eine Art default-Wert f�r den
     * anzuzeigenden Text bereitzustellen.
     * @param datatype legt fest, welchen Datentyp die vom HBCI-Kernel
     * erwarteten Antwortdaten haben m�ssen. Ist dieser Wert gleich
     * <code>TYPE_NONE</code>, so werden keine Antwortdaten (also keine
     * Nutzereingabe) erwartet, bei <code>TYPE_SECRET</code> und
     * <code>TYPE_TEXT</code> wird ein normaler String erwartet.<br/> Der
     * Unterschied zwischen beiden ist der, dass bei <code>TYPE_SECRET</code>
     * sensible Daten (Passw�rter usw.) eingegeben werden sollen, so dass die
     * Eingaberoutine evtl. anders arbeiten muss (z.B. Sternchen anstatt dem
     * eingegebenen Text darstellen).
     * @param retData In diesem StringBuffer-Objekt m�ssen die Antwortdaten
     * abgelegt werden. Beim Aufruf der Callback-Methode von <em>HBCI4Java</em>
     * wird dieser StringBuffer u.U. mit einem vorgeschlagenen default-Wert f�r
     * die Nutzereingabe gef�llt.
     */
    public void callback (HBCIPassport passport, int reason, String msg, int datatype, StringBuffer retData);

    /**
     * Wird vom HBCI-Kernel aufgerufen, um einen bestimmten Status der
     * Abarbeitung bekanntzugeben.
     *
     * @param passport gibt an, welches Passport (und damit welches HBCIHandle)
     * benutzt wurde, als der Callback erzeugt wurde (siehe auch {@link
     * #callback(org.kapott.hbci.passport.HBCIPassport, int, String, int,
     * StringBuffer)}).
     * @param statusTag gibt an, welche Stufe der Abarbeitung gerade erreicht
     * wurde (alle oben beschriebenen Konstanten, die mit <code>STATUS_</code>
     * beginnen)
     * @param o ein Array aus Objekten, das zus�tzliche Informationen zum
     * jeweiligen Status enth�lt. In den meisten F�llen handelt es sich um einen
     * String, der zus�tzliche Informationen im Klartext enth�lt. Welche
     * Informationen das jeweils sind, ist der Beschreibung zu den einzelnen
     * <code>STATUS_*</code>-Tag-Konstanten zu entnehmen.
     */
    public void status (HBCIPassport passport, int statusTag, Object[] o);

    /**
     * Kurzform f�r {@link #status(HBCIPassport, int, Object[])} f�r den Fall,
     * dass das <code>Object[]</code> nur ein einziges Objekt enth�lt
     */
    public void status (HBCIPassport passport, int statusTag, Object o);

    /**
     * <p>Legt fest, ob ein Callback asynchron oder �ber den
     * threaded-callback-Mechanismus behandelt werden soll. Im "Normalfall" gibt
     * diese Methode <code>false</code> zur�ck, womit die asynchrone
     * Callback-Behandlung aktiviert wird. F�r bestimmte Anwendungsf�lle ist
     * jedoch eine synchrone Callback-Behandlung sinnvoll. Dazu muss zun�chst
     * das zu verwendende Callback-Objekt in einer Instanz der Klasse {link
     * org.kapott.hbci.callback.HBCICallbackThreaded HBCICallbackThreaded}
     * gekapselt werden. Au�erdem muss diese Methode so �berschrieben werden,
     * dass sie f�r alle Callbacks, die synchron behandelt werden sollen,
     * <code>true</code> zur�ckgibt.</p> <p>Die �bergebenen Parameter
     * entsprechen denen der Methode {@link #callback(HBCIPassport, int, String,
     * int, StringBuffer)}. Der R�ckgabewert gibt ab, ob dieser Callback
     * synchron (<code>true</code>) oder asynchron (<code>false</code>)
     * behandelt werden soll.</p> <p>Mehr Informationen dazu in der Datei
     * <code>README.ThreadedCallbacks</code>.</p>
     */
    public boolean useThreadedCallback (HBCIPassport passport, int reason, String msg, int datatype, StringBuffer retData);

    public Document getHBCISpezifikation (String hbciVersion);
}

