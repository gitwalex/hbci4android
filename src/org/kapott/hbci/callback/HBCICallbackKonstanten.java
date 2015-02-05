package org.kapott.hbci.callback;

/**
 * Created by alex on 04.02.2015.
 */
public interface HBCICallbackKonstanten {
    /**
     * Ursache des Callback-Aufrufes: Chipkarte ben�tigt (im
     * Chipkartenterminal). Dieser Callback tritt auf, wenn der HBCI-Kernel auf
     * das Einlegen der HBCI-Chipkarte in den Chipkartenleser wartet. Als
     * Reaktion auf diesen Callback darf nur eine entsprechende Aufforderung
     * o.�. angezeigt werden, die Callback-Methode muss anschlie�end sofort
     * beendet werden. Das eigentliche "Warten" auf die Chipkarte sowie das
     * Erkennen, dass eine Chipkarte eingelegt wurde, wird von
     * <em>HBCI4Java</em> �bernommen. Ist das Einlegen der Chipkarte
     * abgeschlossen, so wird ein weiterer Callback mit dem Code
     * <code>HAVE_CHIPCARD</code> erzeugt.
     */
    public final static int NEED_CHIPCARD = 2;
    /**
     * Ursache des Callback-Aufrufes: PIN-Eingabe am Chipkartenterminal
     * erwartet. Dieser Callback zeigt an, dass der Anwender jetzt die HBCI-PIN
     * am Chipkartenterminal eingeben muss. Hier gilt das gleiche wie beim Code
     * <code>NEED_CHIPCARD</code>: Die Callback-Methode darf hier nur eine
     * entsprechende Meldung o.�. anzeigen und muss dann sofort zur�ckkehren --
     * <em>HBCI4Java</em> erledigt die eigentliche Entgegennahme der PIN. Wurde
     * die PIN eingegeben (oder die Eingabe abgebrochen), so wird ein weiterer
     * Callback-Aufruf mit dem Code <code>HAVE_HARDPIN</code> erzeugt.
     */
    public final static int NEED_HARDPIN = 3;
    /**
     * Ursache des Callback-Aufrufes: PIN-Eingabe �ber Computer-Tastatur
     * ben�tigt. Alternativ zum Callback <code>NEED_HARDPIN</code> kann dieser
     * Callback auftreten, wenn die direkte PIN-Eingabe am Chipkartenterminal
     * nicht m�glich oder deaktiviert ist. In diesem Fall muss die PIN
     * "softwarem��ig" eingegeben werden, d.h. der Anwender gibt die PIN �ber
     * die PC-Tastatur ein, welche �ber diesen Callback-Aufruf an den
     * HBCI-Kernel �bergeben wird. Der Kernel �bermittelt die PIN anschlie�end
     * zur Verifikation an die Chipkarte. In diesem Falle gibt es keinen
     * weiteren Callback-Aufruf, wenn die PIN-Verifikation abgeschlossen ist!
     */
    public final static int NEED_SOFTPIN = 4;
    /**
     * Ursache des Callback-Aufrufes: PIN-Eingabe �ber Chipkartenterminal
     * abgeschlossen. Dieser Callback tritt auf, wenn die direkte PIN-Eingabe am
     * Chipkartenleser abgeschlossen (oder abgebrochen) ist. Dieser Aufruf kann
     * dazu genutzt werden, evtl. angezeigte Meldungsfenster ("Bitte jetzt PIN
     * eingeben") wieder zu schlie�en.
     */
    public final static int HAVE_HARDPIN = 5;
    /**
     * Ursache des Callback-Aufrufes: Chipkarte wurde in Chipkartenterminal
     * eingelegt. Dieser Callback tritt auf, wenn das Einlegen der Chipkarte in
     * den Chipkartenleser abgeschlossen (oder abgebrochen) ist. Dieser Aufruf
     * kann dazu genutzt werden, evtl. angezeigte Meldungsfenster ("Bitte jetzt
     * Karte einlegen einlegen") wieder zu schlie�en.
     */
    public final static int HAVE_CHIPCARD = 6;
    /**
     * Ursache des Callback-Aufrufes: L�nderkennzeichen der Bankverbindung
     * ben�tigt. Der Kernel ben�tigt f�r ein neu zu erstellendes Passport-Medium
     * das L�nderkennzeichen der Bank, f�r die dieses Passport benutzt werden
     * soll. Da es sich i.d.R. um deutsche Banken handelt, kann die
     * Callback-Routine hier immer "DE" zur�ckgeben, anstatt tats�chlich auf
     * eine Nutzereingabe zu warten.
     */
    public final static int NEED_COUNTRY = 7;
    /**
     * Ursache des Callback-Aufrufes: Bankleitzahl der Bank ben�tigt. F�r ein
     * neu zu erstellendes Passport-Medium wird die Bankleitzahl der Bank
     * ben�tigt, f�r die dieses Passport verwendet werden soll.
     */
    public final static int NEED_BLZ = 8;
    /**
     * Ursache des Callback-Aufrufes: Netzwerkadresse des HBCI-Servers ben�tigt.
     * Es wird die Hostadresse ben�tigt, unter welcher der HBCI-Server der Bank
     * zu erreichen ist. Dieses Callback tritt nur auf, wenn der Kernel ein
     * neues Passport-Medium erzeugt. Bei RDH- bzw. DDV-Passports wird hier eine
     * IP-Adresse oder ein vollst�ndiger Hostname erwartet. F�r
     * PIN/TAN-Passports wird hier die URL erwartet, unter der der
     * HBCI-PIN/TAN-Handler auf entsprechende HTTPS-Requests reagiert. Dabei
     * muss das Prefix "<code>https://</code>" weggelassen werden (also
     * beispielsweise "<code>www.hbci-kernel.de/pintan/PinTanServlet</code>").
     */
    public final static int NEED_HOST = 9;
    /**
     * Ursache des Callback-Aufrufes: TCP-Port, auf dem der HBCI-Server arbeitet
     * (3000), ben�tigt. Dieser Callback tritt nur auf, wenn ein neues
     * Passport-Medium vom Kernel erzeugt wird. Da die TCP-Portnummer f�r
     * HBCI-Server immer "3000" ist, kann dieser Wert direkt von der
     * Callback-Methode zur�ckgegeben werden, anstatt auf eine Nutzereingabe zu
     * warten.
     */
    public final static int NEED_PORT = 10;
    /**
     * Ursache des Callback-Aufrufes: Nutzerkennung f�r HBCI-Zugang ben�tigt.
     * Wird beim Anlegen eines neuen Passport-Mediums und manchmal beim
     * erstmaligen Benutzen einer DDV-Chipkarte erzeugt, wenn auf der Chipkarte
     * die Benutzerkennung noch nicht gespeichert ist.
     */
    public final static int NEED_USERID = 11;
    /**
     * Ursache des Callback-Aufrufes: Best�tigung f�r neue Instituts-Schl�ssel
     * ben�tigt (INI-Brief-Vergleich). Dieser Callback tritt nur bei Verwendung
     * des RDH-Verfahrens auf. Bei einer Dialoginitialisierung versucht
     * <em>HBCI4Java</em>, die �ffentlichen Schl�ssel des Kreditinstitutes zu
     * aktualisieren. Werden tats�chlich neue Schl�sseldaten empfangen (was
     * i.d.R. nur beim erstmaligen Initialisieren eines Passport-Mediums
     * auftritt), so m�ssen diese Schl�sseldaten vom Anwender verifiziert
     * werden. Dazu muss er die Schl�sseldaten, die <em>HBCI4Java</em> empfangen
     * hat, mit den Daten vergleichen, die die Bank in einem INI-Brief
     * mitgeteilt hat. Erst wenn dieser Vergleich positiv abl�uft, wird
     * <em>HBCI4Java</em> diese Schl�ssel f�r die Kommunikation mit der Bank
     * benutzen. <p>Beim Auftreten dieses Callbacks muss die Anwendung also die
     * gerade empfangenen Schl�sseldaten der Bank (�ffentlicher
     * Signier-/Chiffrierschl�ssel) geeignet anzeigen (Exponent, Modulus,
     * Hash-Wert) und den Anwender auffordern, diese Daten mit denen aus dem
     * INI-Brief zu vergleichen. Dieser Callback erwartet als R�ckgabedaten
     * einen Boolean-Wert (siehe { #TYPE_BOOLEAN}). Sind die Daten in Ordnung,
     * so muss die Callback-Methode einen leeren String in dem
     * R�ckgabedaten-StringBuffer zur�ckgeben, ansonsten f�llt sie den
     * StringBuffer mit einem beliebigen nichtleeren String (siehe dazu {link
     * #callback(org.kapott.hbci.passport.HBCIPassport, int, String, int,
     * StringBuffer)} und die Beschreibung des R�ckgabe-Datentyps {
     * #TYPE_BOOLEAN})).</p> <p>Da im Moment keine dokumentierten Methoden zur
     * Verf�gung stehen, um aus einem Passport die entsprechenden Schl�sseldaten
     * zum Anzeigen zu extrahieren, wird folgendes Vorgehen empfohlen: die
     * Anwendung erzeugt eine HBCICallback-Klasse, die von einer der bereits
     * vorhandenen Default-Implementationen ({ org.kapott.hbci.callback.HBCICallbackConsole},
     * { org.kapott.hbci.callback.HBCICallbackSwing}, ...) abgeleitet ist. Tritt
     * dieser Callback auf, so kann die Anwendung mit <code>super.callback(...)</code>
     * die bereits implementierte Version des entsprechenden Handlers aufrufen.
     * In diesen Default-Implementationen werden zur Zeit nicht dokumentierte
     * Passport-Funktionen benutzt, um die Schl�sseldaten zu extrahieren.</p>
     */
    public final static int NEED_NEW_INST_KEYS_ACK = 12;
    /**
     * Ursache des Callback-Aufrufes: neue Nutzerschl�ssel generiert (INI-Brief
     * erforderlich). Dieser Callback tritt nur bei Verwendung von RDH-Passports
     * auf. Wird ein RDH-Passport neu erstellt, so werden f�r den Bankkunden
     * neue Schl�ssel f�r die Signierung und Verschl�sselung der
     * HBCI-Nachrichten erzeugt. Die �ffentlichen Teile dieser Schl�ssel werden
     * von <em>HBCI4Java</em> an die Bank gesandt. Diese schaltet die neuen
     * Schl�ssel aber erst dann frei, wenn ihre Authentizit�t durch einen
     * INI-Brief best�tigt wird, den der Kunde erzeugen und ebenfalls an die
     * Bank senden muss (per Post oder Fax). <p>Nach der Schl�sselerzeugung und
     * dem erfolgreichen Versand der Schl�sseldaten erzeugt <em>HBCI4Java</em>
     * also diesen Callback. Die Anwendung muss in diesem Fall den Anwender
     * dar�ber informieren, dass seine neuen Schl�ssel erst dann freigeschaltet
     * werden, wenn er einen entsprechenden INI-Brief generiert und zur Bank
     * geschickt hat (und diese die Schl�sseldaten auf �bereinstimmung
     * verglichen hat). Zum Generieren eines INI-Briefes kann das Tool {
     * org.kapott.hbci.tools.INILetter} benutzt werden, was Teil von
     * <em>HBCI4Java</em> ist.</p> <p>Nachdem dieser Callback abgearbeitet
     * wurde, wirft <em>HBCI4Java</em> eine Exception (<code>NeedKeyAckException</code>)
     * und bricht damit die Ausf�hrung des aktuellen HBCI-Dialoges ab. Ein
     * HBCI-Dialog zum Ausf�hren von Gesch�ftsvorf�llen kann erst dann wieder
     * stattfinden, wenn die Bank die Schl�ssel freigeschaltet hat. Wird ein
     * HBCI-Dialog begonnen, obwohl die Bank die neuen Schl�ssel noch nicht
     * aktiviert hat, wird der HBCI-Server mit einer entsprechenden
     * Fehlermeldung beim Initialisieren des HBCI-Dialoges antworten.</p>
     */
    public final static int HAVE_NEW_MY_KEYS = 13;
    /**
     * Ursache des Callback-Aufrufes: Institutsnachricht erhalten. Tritt dieser
     * Callback auf, so enth�lt der <code>msg</code>-Parameter der
     * <code>callback</code>-Methode (siehe { #callback(org.kapott.hbci.passport.HBCIPassport,
     * int, String, int, StringBuffer)} einen String, den die Bank als
     * Kreditinstitutsnachricht an den Kunden gesandt hat. Diese Nachricht
     * sollte dem Anwender i.d.R. angezeigt werden. <em>HBCI4Java</em> erwartet
     * auf diesen Callback keine Antwortdaten.
     */
    public final static int HAVE_INST_MSG = 14;
    /**
     * Ursache des Callback-Aufrufes: Chipkarte soll aus Chipkartenterminal
     * entfernt werden. Dieser Callback wird zur Zeit noch nicht benutzt.
     */
    public final static int NEED_REMOVE_CHIPCARD = 15;
    /**
     * Ursache des Callback-Aufrufes: PIN f�r PIN/TAN-Verfahren ben�tigt. Dieser
     * Callback tritt nur bei Verwendung von PIN/TAN-Passports auf. Ben�tigt
     * <em>HBCI4Java</em> die PIN, um die digitale Signatur zu erzeugen, wird
     * sie �ber diesen Callback abgefragt.
     */
    public final static int NEED_PT_PIN = 16;
    /**
     * Ursache des Callback-Aufrufes: eine TAN f�r PIN/TAN-Verfahren ben�tigt.
     * Dieser Callback tritt nur bei Verwendung von PIN/TAN-Passports auf.
     * Ben�tigt <em>HBCI4Java</em> eine TAN, um eine digitale Signatur zu
     * erzeugen, wird sie �ber diesen Callback abgefragt.
     */
    public final static int NEED_PT_TAN = 17;
    /**
     * Ursache des Callback-Aufrufes: Kunden-ID f�r HBCI-Zugang ben�tigt. Dieser
     * Callback tritt nur beim Erzeugen eines neuen Passports auf.
     * <em>HBCI4Java</em> ben�tigt die Kunden-ID, die das Kreditinstitut dem
     * Bankkunden zugewiesen hat (steht meist in dem Brief mit den
     * Zugangsdaten). Hat eine Bank einem Kunden keine separate Kunden-ID
     * zugewiesen, so muss an dieser Stelle die Benutzer-Kennung (User-ID)
     * zur�ckgegeben werden.
     */
    public final static int NEED_CUSTOMERID = 18;
    /**
     * <p>Ursache des Callback-Aufrufes: Fehler beim Verifizieren einer
     * Kontonummer mit Hilfe des jeweiligen Pr�fzifferverfahrens. Tritt dieser
     * Callback auf, so hat <em>HBCI4Java</em> festgestellt, dass eine
     * verwendete Kontonummer den Pr�fziffercheck der dazugeh�rigen Bank nicht
     * bestanden hat. Der Anwender soll die M�glichkeit erhalten, die
     * Kontonummer und/oder Bankleitzahl zu korrigieren. Dazu wird ein String in
     * der Form "BLZ|KONTONUMMER" im Parameter <code>retData</code> der
     * <code>callback</code>-Methode �bergeben. Die Anwendung kann dem Anwender
     * also BLZ und Kontonummer anzeigen und diese evtl. �ndern lassen. Die neue
     * BLZ und Kontonummer muss im Ergebnis wieder in der o.g. Form in die
     * R�ckgabevariable <code>retData</code> eingetragen werden. Wurden BLZ oder
     * Kontonummer ge�ndert, so f�hrt <em>HBCI4Java</em> eine erneute Pr�fung
     * der Daten durch - schl�gt diese wieder fehl, so wird der Callback erneut
     * erzeugt, diesmal nat�rlich mit den neuen (vom Anwender eingegebenen)
     * Daten. Werden die Daten innerhalb der Callback-Methode nicht ge�ndert
     * (bleibt also der Inhalt von <code>retData</code> unver�ndert), so
     * �bernimmt <em>HBCI4Java</em> die Kontodaten trotz des fehlgeschlagenen
     * Pr�fziffern-Checks</p> <p>Die automatische �berpr�fung von Kontonummern
     * findet statt, wenn HBCI-Jobs mit Hilfe des Highlevel-Interfaces (siehe
     * dazu Paketbeschreibung von <code>org.kapott.hbci.GV</code>) erzeugt
     * werden. Beim Hinzuf�gen eines so erzeugten Jobs zur Menge der
     * auszuf�hrenden Auftr�ge ({ org.kapott.hbci.GV.HBCIJob#addToQueue()}) wird
     * die �berpr�fung f�r alle in diesem Job benutzten Kontonummern
     * durchgef�hrt. F�r jeden Pr�fzifferfehler, der dabei entdeckt wird, wird
     * dieser Callback erzeugt.<br/> Tritt beim �berpr�fen einer IBAN ein Fehler
     * auf, wird statt dessen { #HAVE_IBAN_ERROR} als Callback-Reason
     * verwendet.
     */
    public final static int HAVE_CRC_ERROR = 19;
    /**
     * <p>Ursache des Callback-Aufrufes: Es ist ein Fehler aufgetreten, der auf
     * Wunsch des Anwenders ignoriert werden kann. Durch Setzen bestimmter
     * Kernel-Parameter (siehe { org.kapott.hbci.manager.HBCIUtils#setParam(String,
     * String)}) kann festgelegt werden, dass beim Auftreten bestimmter Fehler
     * zur Laufzeit nicht sofort eine Exception geworfen wird, sondern dass
     * statt dessen erst dieser Callback erzeugt wird, welcher als
     * <code>msg</code> eine entsprechende Problembeschreibung enth�lt.
     * <em>HBCI4Java</em> erwartet einen boolschen R�ckgabewert, der beschreibt,
     * ob der Fehler ignoriert werden soll oder ob eine enstprechende Exception
     * erzeugt werden soll. Der Anwender kann den Fehler ignorieren, indem im
     * <code>retData</code> R�ckgabedaten-Objekt ein leerer String zur�ckgegeben
     * wird, oder er kann erzwingen, dass <em>HBCI4Java</em> tats�chlich
     * abbricht, indem ein nicht-leerer String im <code>retData</code>-Objekt
     * zur�ckgegen wird. Siehe dazu auch die Beschreibung des R�ckgabe-Datentyps
     * { #TYPE_BOOLEAN}.</p> <p>Das Ignorieren eines Fehlers kann dazu f�hren,
     * dass <em>HBCI4Java</em> sp�ter trotzdem eine Exception erzeugt, z.B. weil
     * der Fehler in einem bestimmten Submodul doch nicht einfach ignoriert
     * werden kann, oder es kann auch dazu f�hren, dass Auftr�ge von der Bank
     * nicht angenommen werden usw. Es wird aber in jedem Fall eine
     * entsprechende Fehlermeldung erzeugt.</p>
     */
    public final static int HAVE_ERROR = 20;
    /**
     * Ursache des Callback-Aufrufes: Passwort f�r das Einlesen der
     * Schl�sseldatei ben�tigt. Dieser Callback tritt beim Laden eines
     * Passport-Files auf, um nach dem Passwort f�r die Entschl�sselung zu
     * fragen.
     */
    public final static int NEED_PASSPHRASE_LOAD = 21;
    /**
     * Ursache des Callback-Aufrufes: Passwort f�r das Erzeugen der
     * Schl�sseldatei ben�tigt. Dieser Callback tritt beim Erzeugen eines neuen
     * Passport-Files bzw. beim �ndern der Passphrase f�r eine Schl�sseldatei
     * auf, um nach dem Passwort f�r die Verschl�sselung zu fragen.
     */
    public final static int NEED_PASSPHRASE_SAVE = 22;
    /**
     * <p>Ursache des Callback-Aufrufes: Auswahl eines Eintrages aus einer
     * SIZ-RDH-Datei ben�tigt. Dieser Callback tritt nur bei Verwendung der
     * Passport-Variante SIZRDHFile auf. In einer SIZ-RDH-Schl�sseldatei k�nnen
     * mehrere HBCI-Zugangsdatens�tze gespeichert sein. Wird eine solche Datei
     * mit mehreren Datens�tzen geladen, so wird dieser Callback erzeugt, um den
     * zu benutzenden Datensatz aus der Datei ausw�hlen zu k�nnen.</p> <p>Dazu
     * wird beim Aufruf der Callback-Routine im Parameter <code>retData</code>
     * ein String �bergeben, der aus Informationen �ber alle in der Datei
     * vorhandenen Zugangsdatens�tze besteht. Das Format dieses Strings ist
     * <code>&lt;ID&gt;;&lt;BLZ&gt;;&lt;USERID&gt;[|&lt;ID&gt;;&lt;BLZ&gt;;&lt;USERID&gt;...]</code>
     * Es werden also die verschiedenen Datens�tze durch "|" getrennt
     * dargestellt, wobei jeder einzelne Datensatz durch eine ID, die
     * Bankleitzahl und die UserID dieses Datensatzes repr�sentiert wird.</p>
     * <p>Dem Anwender m�ssen diese Daten in geeigneter Weise zur Auswahl
     * angezeigt werden. Die Callback-Routine muss schlie�lich die ID des vom
     * Anwender ausgew�hlten Eintrages im <code>retData</code>-R�ckgabedatenobjekt
     * zur�ckgeben.</p> <p>Beim Aufruf der Callback-Routine k�nnte
     * <code>retData</code> also folgendes enthalten: <code>0;09950003;Kunde-001|1;01234567;Kunde8|4;8765432;7364634564564</code>.
     * Der Anwender muss sich also zwischen den Datens�tzen
     * "09950003;Kunde-001", "01234567;Kunde8" und "8765432;7364634564564"
     * entscheiden. Je nach Auswahl muss in <code>retData</code> dann jeweils
     * "0", "1" oder "4" zur�ckgegeben werden.</p>
     */
    public final static int NEED_SIZENTRY_SELECT = 23;
    /**
     * <p>Ursache des Callback-Aufrufes: es wird eine Netz-Verbindung zum
     * HBCI-Server ben�tigt. Dieser Callback wird erzeugt, bevor
     * <em>HBCI4Java</em> eine Verbindung zum HBCI-Server aufbaut. Bei
     * Client-Anwendungen, die mit einer Dialup-Verbindung zum Internet
     * arbeiten, kann dieser Callback benutzt werden, um den Anwender zum
     * Aktivieren der Internet-Verbindung aufzufordern. Es werden keine
     * R�ckgabedaten erwartet. Sobald die Internet-Verbindung nicht mehr
     * ben�tigt wird, wird ein anderer Callback ({ #CLOSE_CONNECTION})
     * erzeugt.</p> <p>Dieses Callback-Paar wird immer dann erzeugt, wenn von
     * der aktuellen <em>HBCI4Java</em>-Verarbeitungsstufe tats�chlich eine
     * Verbindung zum Internet ben�tigt wird bzw. nicht mehr ({
     * #CLOSE_CONNECTION}) ben�tigt wird. U.U. werden allerdings mehrere solcher
     * Verarbeitungsstufen direkt hintereinander ausgef�hrt - das kann zur Folge
     * haben, dass auch diese Callback-Paare mehrmals direkt hintereinander
     * auftreten. Das tritt vor allem beim erstmaligen Initialiseren eines
     * Passports auf. Beim Aufruf von <code>new&nbsp;HBCIHandler(...)</code>
     * werden verschiedene Passport-Daten mit der Bank abgeglichen, dabei wird
     * u.U. mehrmals <code>NEED_CONNECTION</code>/<code>CLOSE_CONNECTION</code>
     * aufgerufen. Evtl. sollte der Callback-Handler der Anwendung in diesem
     * Fall also entsprechende Ma�nahmen treffen.</p>
     */
    public final static int NEED_CONNECTION = 24;
    /**
     * Ursache des Callback-Aufrufes: die Netzwerk-Verbindung zum HBCI-Server
     * wird nicht l�nger ben�tigt. Dieser Callback wird aufgerufen, sobald
     * <em>HBCI4Java</em> die Kommunikation mit dem HBCI-Server vorl�ufig
     * beendet hat. Dieser Callback kann zusammen mit dem Callback {
     * #NEED_CONNECTION} benutzt werden, um f�r Clients mit Dialup-Verbindungen
     * die Online-Zeiten zu optimieren. Bei diesem Callback werden keine
     * R�ckgabedaten erwartet
     */
    public final static int CLOSE_CONNECTION = 25;
    /**
     * <p>Ursache des Callback-Aufrufes: es wird die Bezeichnung des zu
     * verwendenden Datenfilters ben�tigt. M�gliche Filterbezeichnungen sind
     * "<code>None</code>" (kein Filter) und "<code>Base64</code>" (Daten
     * BASE64-kodieren). Die jeweilige Filterbezeichnung ist in
     * <code>retData</code> zur�ckzugeben. Dieser Callback tritt zur Zeit nur
     * bei Verwendung von PIN/TAN-Passports auf, weil hier nicht alle Banken
     * einheitlich mit der gleichen Art der Filterung arbeiten.</p>
     * <p>Normalweise wird bei PIN/TAN der <code>Base64</code>-Filter benutzt.
     * Wenn bei dessen Verwendung aber keine Antwortdaten von der Bank empfangen
     * werden, dann sollte die andere Variante (<code>None</code>) ausprobiert
     * werden.</p>
     */
    public final static int NEED_FILTER = 26;
    /**
     * <p>Ursache des Callbacks: bei Verwendung von HBCI-PIN/TAN muss eines der
     * unterst�tzten Verfahren ausgew�hlt werden. Seit FinTS-3.0 gibt es mehrere
     * Verfahren f�r PIN/TAN - das "normale" Einschrittverfahren sowie mehrere
     * Zweischritt-Verfahren. Unterst�tzt eine Bank mehr als ein Verfahren, so
     * wird dieser Callback erzeugt, damit der Anwender das zu verwendende
     * Verfahren ausw�hlen kann.</p> <p>Dazu wird in <code>retData</code> ein
     * String mit folgendem Format an die Callback-Methode �bergeben:
     * "<code>ID1:Beschreibung1|ID2:Beschreibung2...</code>". Jedes Token
     * "<code>ID:Beschreibung</code>" steht dabei f�r ein unterst�tztes
     * PIN/TAN-Verfahren. Die Callback-Methode muss die ID des vom Anwender
     * ausgew�hlten PIN/TAN-Verfahrens anschlie�end in <code>retData</code>
     * zur�ckgeben.</p>
     */
    public final static int NEED_PT_SECMECH = 27;
    /**
     * Ursache des Callbacks: es wird ein Nutzername f�r die Authentifizierung
     * am Proxy-Server ben�tigt. Wird f�r die HTTPS-Verbindungen bei
     * HBCI-PIN/TAN ein Proxy-Server verwendet, und verlangt dieser Proxy-Server
     * eine Authentifizierung, so wird �ber diesen Callback nach dem Nutzernamen
     * gefragt, falls dieser nicht schon durch den Kernel-Parameter
     * <code>client.passport.PinTan.proxyuser</code> gesetzt wurde
     */
    public final static int NEED_PROXY_USER = 28;
    /**
     * Ursache des Callbacks: es wird ein Passwort f�r die Authentifizierung am
     * Proxy-Server ben�tigt. Wird f�r die HTTPS-Verbindungen bei HBCI-PIN/TAN
     * ein Proxy-Server verwendet, und verlangt dieser Proxy-Server eine
     * Authentifizierung, so wird �ber diesen Callback nach dem Passwort
     * gefragt, falls dieses nicht schon durch den Kernel-Parameter
     * <code>client.passport.PinTan.proxypass</code> gesetzt wurde
     */
    public final static int NEED_PROXY_PASS = 29;
    /**
     * Ursache des Callbacks: beim �berpr�fen einer IBAN ist ein Fehler
     * aufgetreten. in <code>retData</code> wird die fehlerhafte IBAN �bergeben.
     * Der Nutzer sollte die IBAN korrieren. Die korrigierte IBAN sollte wieder
     * in <code>retData</code> zur�ckgegeben werden. Wird die IBAN nicht
     * ver�ndert, wird diese IBAN trotz des Fehlers verwendet. Wird eine
     * korrigierte IBAN zum Nutzer zur�ckgegeben, wird f�r diese erneut ein
     * Pr�fsummencheck ausgef�hrt. Schl�gt der wieder fehl, wird der Callback
     * erneut erzeugt. Das geht so lange, bis entweder der Pr�fsummencheck
     * erfolgreich war oder bis die IBAN vom Nutzer nicht ver�ndert wird. Siehe
     * dazu auch { #HAVE_CRC_ERROR}.
     */
    public final static int HAVE_IBAN_ERROR = 30;
    /**
     * Ursache des Callbacks: Kernel fragt um Erlaubnis, Daten an den
     * InfoPoint-Server zu senden. An bestimmten Punkten der HBCI-Kommunikation
     * sendet der HBCI-Kernel Daten �ber erfolgreich gelaufene Verbindungen an
     * den InfoPoint-Server (siehe Kernel-Parameter "<code>infoPoint.enabled</code>"
     * und Datei <em>README.InfoPoint</em>). Bei diesem Callback wird im
     * StringBuffer <code>retData</code> das XML-Document �bergeben, welches an
     * den InfoPoint-Server gesendet werden soll. Als Antwort wird ein
     * Boolean-Wert erwartet (siehe { #TYPE_BOOLEAN}). D�rfen die Daten gesendet
     * werden, ist von der Anwendung also ein leerer String in
     * <code>retData</code> zur�ckzugeben, ansonsten ein beliebiger nicht-leerer
     * String.
     */
    public final static int NEED_INFOPOINT_ACK = 31;
    /**
     * <p>Ursache des Callbacks: bei Verwendung von HBCI-PIN/TAN muss die
     * Bezeichnung des TAN-Mediums eingegeben werden. Bei smsTAN ist das z.Bsp.
     * der Alias-Name des Mobiltelefons, wie er bei der Bank hinterlegt wurde.
     * Dieser Name wird verwendet, damit die SMS mit der TAN an mehrere
     * Mobiltelefone schicken kann.
     */
    public final static int NEED_PT_TANMEDIA = 32;
    /**
     * <p>Ursache des Callbacks: falsche PIN eingegeben
     */
    public final static int WRONG_PIN = 40;
    /** <p>Ursache des Callbacks: Dialogantwort 3072 der GAD - UserID und CustomerID werden ausgetauscht */
    /**
     * <p>im Parameter retData stehen die neuen Daten im Format
     * UserID|CustomerID drin
     */
    public final static int USERID_CHANGED = 41;
    /**
     * erwarteter Datentyp der Antwort: keiner (keine Antwortdaten erwartet)
     */
    public final static int TYPE_NONE = 0;
    /**
     * erwarteter Datentyp der Antwort: geheimer Text (bei Eingabe nicht
     * anzeigen)
     */
    public final static int TYPE_SECRET = 1;
    /**
     * erwarteter Datentyp der Antwort: "normaler" Text
     */
    public final static int TYPE_TEXT = 2;
    /**
     * <p>erwarteter Datentyp der Antwort: ja/nein, true/false, weiter/abbrechen
     * oder �hnlich. Da das R�ckgabedatenobjekt immer ein
     * <code>StringBuffer</code> ist, wird hier folgende Kodierung verwendet:
     * die beiden m�glichen Werte f�r die Antwort (true/false, ja/nein,
     * weiter/abbrechen, usw.) werden dadurch unterschieden, dass f�r den einen
     * Wert ein <em>leerer</em> String zur�ckgegeben wird, f�r den anderen Wert
     * ein <em>nicht leerer</em> beliebiger String. Einige Callback-Reasons
     * k�nnen auch den Inhalt des nicht-leeren Strings auswerten. Eine genaue
     * Beschreibung der jeweilis m�glichen R�ckgabedaten befinden sich in der
     * Beschreibung der Callback-Reasons (<code>HAVE_*</code> bzw.
     * <code>NEED_*</code>), bei denen Boolean-Daten als R�ckgabewerte ben�tigt
     * werden.</p> <p>Siehe dazu auch die Hinweise in der Paketbeschreibung zum
     * Paket <code>org.kapott.hbci.callback</code>.</p>
     */
    public final static int TYPE_BOOLEAN = 3;
    /**
     * Kernel-Status: Erzeuge Auftrag zum Versenden. Als Zusatzinformation wird
     * bei diesem Callback das <code>HBCIJob</code>-Objekt des Auftrages
     * �bergeben, dessen Auftragsdaten gerade erzeugt werden.
     */
    public final static int STATUS_SEND_TASK = 1;
    /**
     * Kernel-Status: Auftrag gesendet. Tritt auf, wenn zu einem bestimmten Job
     * Auftragsdaten empfangen und ausgewertet wurden. Als Zusatzinformation
     * wird das <code>HBCIJob</code>-Objekt des jeweiligen Auftrages �bergeben.
     */
    public final static int STATUS_SEND_TASK_DONE = 2;
    /**
     * Kernel-Status: hole BPD. Kann nur w�hrend der Passport-Initialisierung ({
     * org.kapott.hbci.manager.HBCIHandler#HBCIHandler(String,
     * org.kapott.hbci.passport.HBCIPassport)}) auftreten und zeigt an, dass die
     * BPD von der Bank abgeholt werden m�ssen, weil sie noch nicht lokal
     * vorhanden sind. Es werden keine zus�tzlichen Informationen �bergeben.
     */
    public final static int STATUS_INST_BPD_INIT = 3;
    /**
     * Kernel-Status: BPD aktualisiert. Dieser Status-Callback tritt nach dem
     * expliziten Abholen der BPD ({ #STATUS_INST_BPD_INIT}) auf und kann auch
     * nach einer Dialog-Initialisierung auftreten, wenn dabei eine neue BPD vom
     * Kreditinstitut empfangen wurde. Als Zusatzinformation wird ein
     * <code>Properties</code>-Objekt mit den neuen BPD �bergeben.
     */
    public final static int STATUS_INST_BPD_INIT_DONE = 4;
    /**
     * Kernel-Status: hole Institutsschl�ssel. Dieser Status-Callback zeigt an,
     * dass <em>HBCI4Java</em> die �ffentlichen Schl�ssel des Kreditinstitutes
     * abholt. Dieser Callback kann nur beim Initialisieren eines Passportes
     * (siehe { org.kapott.hbci.manager.HBCIHandler#HBCIHandler(String,
     * org.kapott.hbci.passport.HBCIPassport)}) und bei Verwendung von RDH als
     * Sicherheitsverfahren auftreten. Es werden keine zus�tzlichen
     * Informationen �bergeben.
     */
    public final static int STATUS_INST_GET_KEYS = 5;
    /**
     * Kernel-Status: Institutsschl�ssel aktualisiert. Dieser Callback tritt
     * auf, wenn <em>HBCI4Java</em> neue �ffentliche Schl�ssel der Bank
     * empfangen hat. Dieser Callback kann nach dem expliziten Anfordern der
     * neuen Schl�ssel ({ #STATUS_INST_GET_KEYS}) oder nach einer
     * Dialog-Initialisierung auftreten, wenn das Kreditinstitut neue Schl�ssel
     * �bermittelt hat. Es werden keine zus�tzlichen Informationen �bergeben.
     */
    public final static int STATUS_INST_GET_KEYS_DONE = 6;
    /**
     * Kernel-Status: Sende Nutzerschl�ssel. Wird erzeugt, wenn
     * <em>HBCI4Java</em> neue Schl�ssel des Anwenders an die Bank versendet.
     * Das tritt beim erstmaligen Einrichten eines RDH-Passportes bzw. nach dem
     * manuellen Erzeugen neuer RDH-Schl�ssel auf. Es werden keine zus�tzlichen
     * Informationen �bergeben.
     */
    public final static int STATUS_SEND_KEYS = 7;
    /**
     * Kernel-Status: Nutzerschl�ssel gesendet. Dieser Callback zeigt an, dass
     * die RDH-Schl�ssel des Anwenders an die Bank versandt wurden. Der Erfolg
     * dieser Aktion kann nicht allein durch das Auftreten dieses Callbacks
     * angenommen werden! Es wird der Status des Nachrichtenaustauschs ({
     * org.kapott.hbci.status.HBCIMsgStatus}) als Zusatzinformation �bergeben.
     */
    public final static int STATUS_SEND_KEYS_DONE = 8;
    /**
     * Kernel-Status: aktualisiere System-ID. Dieser Status-Callback wird
     * erzeugt, wenn <em>HBCI4Java</em> die System-ID, die f�r das RDH-Verfahren
     * ben�tigt wird, synchronisiert. Der Callback kann nur beim Initialisieren
     * eines Passports (siehe { org.kapott.hbci.manager.HBCIHandler#HBCIHandler(String,
     * org.kapott.hbci.passport.HBCIPassport)}) auftreten. Es werden keine
     * Zusatzinformationen �bergeben.
     */
    public final static int STATUS_INIT_SYSID = 9;
    /**
     * Kernel-Status: System-ID aktualisiert. Dieser Callback tritt auf, wenn im
     * Zuge der Synchronisierung ({ #STATUS_INIT_SYSID}) eine System-ID
     * empfangen wurde. Als Zusatzinformation wird ein Array �bergeben, dessen
     * erstes Element die Statusinformation zu diesem Nachrichtenaustausch
     * darstellt ({ org.kapott.hbci.status.HBCIMsgStatus}) und dessen zweites
     * Element die neue System-ID ist.
     */
    public final static int STATUS_INIT_SYSID_DONE = 10;
    /**
     * Kernel-Status: hole UPD. Kann nur w�hrend der Passport-Initialisierung ({
     * org.kapott.hbci.manager.HBCIHandler#HBCIHandler(String,
     * org.kapott.hbci.passport.HBCIPassport)}) auftreten und zeigt an, dass die
     * UPD von der Bank abgeholt werden m�ssen, weil sie noch nicht lokal
     * vorhanden sind. Es werden keine zus�tzlichen Informationen �bergeben.
     */
    public final static int STATUS_INIT_UPD = 11;
    /**
     * Kernel-Status: UPD aktualisiert. Dieser Status-Callback tritt nach dem
     * expliziten Abholen der UPD ({ #STATUS_INIT_UPD}) auf und kann auch nach
     * einer Dialog-Initialisierung auftreten, wenn dabei eine neue UPD vom
     * Kreditinstitut empfangen wurde. Als Zusatzinformation wird ein
     * <code>Properties</code>-Objekt mit den neuen UPD �bergeben.
     */
    public final static int STATUS_INIT_UPD_DONE = 12;
    /**
     * Kernel-Status: sperre Nutzerschl�ssel. Dieser Status-Callback wird
     * erzeugt, wenn <em>HBCI4Java</em> einen Auftrag zur Sperrung der aktuellen
     * Nutzerschl�ssel generiert. Es werden keine Zusatzinformationen
     * �bergeben.
     */
    public final static int STATUS_LOCK_KEYS = 13;
    /**
     * Kernel-Status: Nutzerschl�ssel gesperrt. Dieser Callback tritt auf,
     * nachdem die Antwort auf die Nachricht "Sperren der Nutzerschl�ssel"
     * eingetroffen ist. Ein Auftreten dieses Callbacks ist keine Garantie
     * daf�r, dass die Schl�sselsperrung erfolgreich abgelaufen ist. Es wird der
     * Status des Nachrichtenaustauschs ({ org.kapott.hbci.status.HBCIMsgStatus})
     * als Zusatzinformation �bergeben.
     */
    public final static int STATUS_LOCK_KEYS_DONE = 14;
    /**
     * Kernel-Status: aktualisiere Signatur-ID. Dieser Status-Callback wird
     * erzeugt, wenn <em>HBCI4Java</em> die Signatur-ID, die f�r das
     * RDH-Verfahren ben�tigt wird, synchronisiert. Der Callback kann nur beim
     * Initialisieren eines Passports (siehe { org.kapott.hbci.manager.HBCIHandler#HBCIHandler(String,
     * org.kapott.hbci.passport.HBCIPassport)}) auftreten. Es werden keine
     * Zusatzinformationen �bergeben.
     */
    public final static int STATUS_INIT_SIGID = 15;
    /**
     * Kernel-Status: Signatur-ID aktualisiert. Dieser Callback tritt auf, wenn
     * im Zuge der Synchronisierung ({ #STATUS_INIT_SIGID}) eine Signatur-ID
     * empfangen wurde. Als Zusatzinformation wird ein Array �bergeben, dessen
     * erstes Element die Statusinformation zu diesem Nachrichtenaustausch
     * darstellt ({ org.kapott.hbci.status.HBCIMsgStatus}) und dessen zweites
     * Element die neue Signatur-ID (ein Long-Objekt) ist.
     */
    public final static int STATUS_INIT_SIGID_DONE = 16;
    /**
     * Kernel-Status: Starte Dialog-Initialisierung. Dieser Status-Callback
     * zeigt an, dass <em>HBCI4Java</em> eine Dialog-Initialisierung startet. Es
     * werden keine zus�tzlichen Informationen �bergeben.
     */
    public final static int STATUS_DIALOG_INIT = 17;
    /**
     * Kernel-Status: Dialog-Initialisierung ausgef�hrt. Dieser Callback tritt
     * nach dem Durchf�hren der Dialog-Initialisierung auf. Als
     * Zusatzinformation wird ein Array �bergeben, dessen erstes Element die
     * Statusinformation zu diesem Nachrichtenaustausch darstellt ({
     * org.kapott.hbci.status.HBCIMsgStatus}) und dessen zweites Element die
     * neue Dialog-ID ist.
     */
    public final static int STATUS_DIALOG_INIT_DONE = 18;
    /**
     * Kernel-Status: Beende Dialog. Wird ausgel�st, wenn <em>HBCI4Java</em> den
     * aktuellen Dialog beendet. Es werden keine zus�tzlichen Daten �bergeben.
     */
    public final static int STATUS_DIALOG_END = 19;
    /**
     * Kernel-Status: Dialog beendet. Wird ausgef�hrt, wenn der HBCI-Dialog
     * tats�chlich beendet ist. Es wird der Status des Nachrichtenaustauschs ({
     * org.kapott.hbci.status.HBCIMsgStatus}) als Zusatzinformation �bergeben.
     */
    public final static int STATUS_DIALOG_END_DONE = 20;
    /**
     * Kernel-Status: Erzeuge HBCI-Nachricht. Dieser Callback zeigt an, dass
     * <em>HBCI4Java</em> gerade eine HBCI-Nachricht erzeugt. Es wird der Name
     * der Nachricht als zus�tzliches Objekt �bergeben.
     */
    public final static int STATUS_MSG_CREATE = 21;
    /**
     * Kernel-Status: Signiere HBCI-Nachricht. Dieser Callback wird aufgerufen,
     * wenn <em>HBCI4Java</em> die ausgehende HBCI-Nachricht signiert. Es werden
     * keine zus�tzlichen Informationen �bergeben.
     */
    public final static int STATUS_MSG_SIGN = 22;
    /**
     * Kernel-Status: Verschl�ssele HBCI-Nachricht. Wird aufgerufen, wenn
     * <em>HBCI4Java</em> die ausgehende HBCI-Nachricht verschl�sselt. Es werden
     * keine zus�tzlichen Informationen �bergeben.
     */
    public final static int STATUS_MSG_CRYPT = 23;
    /**
     * Kernel-Status: Sende HBCI-Nachricht (bei diesem Callback ist das
     * <code>passport</code>-Objekt immer <code>null</code>). Wird aufgerufen,
     * wenn die erzeugte HBCI-Nachricht an den HBCI-Server versandt wird. Es
     * werden keine zus�tzlichen Informationen �bergeben.
     */
    public final static int STATUS_MSG_SEND = 24;
    /**
     * Kernel-Status: Entschl�ssele HBCI-Nachricht. Wird aufgerufen, wenn die
     * empfangene HBCI-Nachricht von <em>HBCI4Java</em> entschl�sselt wird. Es
     * werden keine zus�tzlichen Informationen �bergeben.
     */
    public final static int STATUS_MSG_DECRYPT = 25;
    /**
     * Kernel-Status: �berpr�fe digitale Signatur der Nachricht. Wird
     * aufgerufen, wenn <em>HBCI4Java</em> die digitale Signatur der empfangenen
     * Antwortnachricht �berpr�ft. Es werden keine zus�tzlichen Informationen
     * �bergeben.
     */
    public final static int STATUS_MSG_VERIFY = 26;
    /**
     * Kernel-Status: Empfange HBCI-Antwort-Nachricht (bei diesem Callback ist
     * das <code>passport</code>-Objekt immer <code>null</code>). Wird
     * aufgerufen, wenn die Antwort-HBCI-Nachricht vom HBCI-Server empfangen
     * wird. Es werden keine zus�tzlichen Informationen �bergeben.
     */
    public final static int STATUS_MSG_RECV = 27;
    /**
     * Kernel-Status: Parse HBCI-Antwort-Nachricht (bei diesem Callback ist das
     * <code>passport</code>-Objekt immer <code>null</code>). Wird aufgerufen,
     * wenn <em>HBCI4Java</em> versucht, die empfangene Nachricht zu parsen. Es
     * wird der Name der erwarteten Nachricht als zus�tzliche Information
     * �bergeben.
     */
    public final static int STATUS_MSG_PARSE = 28;
    /**
     * Kernel-Status: Der Kernel sendet Informationen �ber eine erfolgreiche
     * Dialog-Initialisierung an den InfoPoint-Server (siehe auch
     * <em>README.InfoPoint</em>). Als zus�tzlicher Parameter wird das
     * XML-Dokument (als String) �bergeben, welches an den InfoPoint-Server
     * gesendet wird.
     */
    public final static int STATUS_SEND_INFOPOINT_DATA = 29;
    /**
     * Wird aufgerufen unmittelbar bevor die HBCI-Nachricht an den Server
     * gesendet wird. Als zusaetzliche Information wird die zu sendende
     * Nachricht als String uebergeben. Sie kann dann z.Bsp. in einem Log
     * gesammelt werden, welches ausschliesslich (zusammen mit {
     * HBCICallback#STATUS_MSG_RAW_RECV}) die gesendeten und empfangenen rohen
     * HBCI-Nachrichten enthaelt. Sinnvoll zum Debuggen der Kommunikation mit
     * der Bank.
     */
    public final static int STATUS_MSG_RAW_SEND = 30;
    /**
     * Wird aufgerufen unmittelbar nachdem die HBCI-Nachricht vom Server
     * empfangen wurde. Als zusaetzliche Information wird die empfangene
     * Nachricht als String uebergeben. Sie kann dann z.Bsp. in einem Log
     * gesammelt werden, welches ausschliesslich (zusammen mit {
     * HBCICallback#STATUS_MSG_RAW_SEND}) die gesendeten und empfangenen rohen
     * HBCI-Nachrichten enthaelt. Sinnvoll zum Debuggen der Kommunikation mit
     * der Bank.
     */
    public final static int STATUS_MSG_RAW_RECV = 31;
}
