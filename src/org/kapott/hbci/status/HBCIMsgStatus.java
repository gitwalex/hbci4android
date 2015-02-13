
/*  $Id: HBCIMsgStatus.java,v 1.1 2011/05/04 22:38:02 willuhn Exp $

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

package org.kapott.hbci.status;

import org.kapott.hbci.manager.HBCIUtilsInternal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/** <p>Enth�lt alle Status-Informationen zu genau einem Nachrichtenaustausch.
    Es ist zu beachten, dass in einer Nachricht Informationen zu
    <em>mehreren</em> Gesch�ftsvorf�llen enthalten sein k�nnen, wenn die
    gesendete Nachricht mehrere Auftr�ge enthalten hat.
    </p><p>
    Die direkte Auswertung
    der Felder dieser Klasse wird nicht empfohlen, statt dessen sollten nur
    die Methoden benutzt werden, die den prinzipiellen Status (OK oder nicht OK)
    sowie die eigentlichen Fehler-Informationen zur�ckgeben. </p>*/
public final class HBCIMsgStatus implements Serializable {
    /** Globale Status-Informationen. Das sind Informationen, die die
        Nachricht als ganzes betreffen (z.B. wenn die Nachricht nicht signiert
        oder verschl�sselt war, oder wenn sie nicht dekodiert werden konnte etc.) */
    public HBCIStatus globStatus;
    /** Status-Informationen, die einzelne Segmente der Nachricht betreffen.
        Hier werden alle R�ckgabecodes gespeichert, die sich konkret auf
        einzelne Segmente der gesendeten Nachricht beziehen. */
    public HBCIStatus segStatus;
    
    private Properties data;
    
    public HBCIMsgStatus()
    {
        this.globStatus=new HBCIStatus();
        this.segStatus=new HBCIStatus();
        this.data=new Properties();
    }
    
    /** Wird von der <em>HBCI4Java</em>-Dialog-Engine aufgerufen */
    public void addException(Exception e)
    {
        globStatus.addException(e);
    }
    
    /** Wird von der <em>HBCI4Java</em>-Dialog-Engine aufgerufen */
    public void setData(Properties data)
    {
        this.data=data;
        extractStatusData();
    }
    
    /** Wird von der <em>HBCI4Java</em>-Dialog-Engine aufgerufen */
    public void addData(Properties _data)
    {
        this.data.putAll(_data);
        extractStatusData();
    }
     
    private void extractStatusData()
    {
        this.globStatus=new HBCIStatus();
        this.segStatus=new HBCIStatus();

        // globale return-codes extrahieren
        for (int i=0;true;i++) {
            HBCIRetVal rv=null;
            try {
                rv=new HBCIRetVal(data,
                                  HBCIUtilsInternal.withCounter("RetGlob.RetVal",i));
            } catch (Exception e) {
                break;
            }
            
            globStatus.addRetVal(rv);
        }
        
        // segment-codes extrahieren
        for (int i=0;true;i++) {
            String segheader=HBCIUtilsInternal.withCounter("RetSeg",i);
            String segref=data.getProperty(segheader+".SegHead.ref");
            if (segref==null) {
                break;
            }
            
            for (int j=0;true;j++) {
                HBCIRetVal rv=null;
                try {
                    rv=new HBCIRetVal(data,
                                      HBCIUtilsInternal.withCounter(segheader+".RetVal",j),
                                      segref);
                } catch (Exception e) {
                    break;
                }
                
                segStatus.addRetVal(rv);
            }
        }
    }
    
    /** <p>Gibt den eigentlichen Inhalt sowohl der gesendeten wie auch der
        empfangenen Nachricht zur�ck. Die <em>keys</em> des Property-Objektes
        enthalten die Lowlevelnamen der Datenelemente, die dazugeh�rigen
        <em>values</em> enthalten jeweils den Wert des entsprechenden Datenelementes.
        Die Bezeichnungen der Datenelemente der <em>gesendeten</em> Nachricht tragen
        zur Unterscheidung mit den Datenelementen der empfangenen Nachricht das
        Prefix "<code>orig_</code>".</p> */
    public Properties getData()
    {
        return data;
    }
    
    /** Gibt zur�ck, ob bei der Ausf�hrung eines Nachrichtenaustauschs Exceptions
        aufgetreten sind. Diese Exceptions k�nnen entweder beim Erzeugen bzw.
        Versenden der Kundennachricht oder aber beim Empfangen und Auswerten
        der Institutsnachricht aufgetreten sein.
        @return <code>true</code>, wenn Exceptions aufgetreten sind, sonst
                <code>false</code>*/
    public boolean hasExceptions()
    {
        return globStatus.hasExceptions();
    }
    
    /** Gibt die Exceptions zur�ck, ob bei der Ausf�hrung eines 
        Nachrichtenaustauschs aufgetreten sind. Diese Exceptions k�nnen entweder 
        beim Erzeugen bzw. Versenden der Kundennachricht oder aber beim Empfangen 
        und Auswerten der Institutsnachricht aufgetreten sein.
        @return Array mit aufgetretenen Exceptions, ist niemals <code>null</code>,
                 kann aber die L�nge 0 haben */
    public Exception[] getExceptions()
    {
        return globStatus.getExceptions();
    }

    /** Gibt zur�ck, ob ein Nachrichtenaustausch erfolgreich durchgef�hrt wurde. Das
        ist dann der Fall, wenn bei der Abarbeitung keine Exceptions aufgetreten
        sind und die Antwortnachricht eine Erfolgsmeldung oder zumindest
        nur Warnungen (aber keine Fehlermeldung) enth�lt.
        @return <code>true</code>, wenn die Nachricht erfolgreich abgearbeitet
                wurde, sonst <code>false</code> */
    public boolean isOK()
    {
        // wenn bei einer CustomMsg mit mehreren GVs EINER fehlschlug,
        // dann ist auch isOK()==false. Ist das so gewollt?
        return globStatus.getStatusCode()==HBCIStatus.STATUS_OK;
    }
    
    /** Gibt einen String zur�ck, der alle aufgetretenen Fehler bei der 
        Durchf�hrung des Nachrichtenaustauschs beschreibt. Dieser String besteht aus
        allen Exception-Meldungen sowie allen evtl. empfangenen Fehlermeldungen.
        Die Meldungen werden aus den einzelnen 
        {@link org.kapott.hbci.status.HBCIStatus}-Objekten durch 
        Aufruf der Methode {@link org.kapott.hbci.status.HBCIStatus#getErrorString()}
        erzeugt. 
        @return String mit allen aufgetretenen Fehlermeldungen */
    public String getErrorString()
    {
        StringBuffer ret=new StringBuffer();
        
        ret.append(globStatus.getErrorString());
        ret.append(System.getProperty("line.separator"));
        ret.append(segStatus.getErrorString());
        
        return ret.toString().trim();
    }
    
    /** Fasst alle Status-Informationen zu einem Nachrichtenaustausch in einem einzigen
        String zusammen und gibt diesen zur�ck. Dazu geh�ren alle evtl. 
        aufgetretenen Exception-Meldungen, alle Fehlermeldungen, Warnungen sowie
        Erfolgsmeldungen. Die Meldungen werden aus den einzelnen 
        {@link org.kapott.hbci.status.HBCIStatus}-Objekten durch 
        Aufruf der Methode {@link org.kapott.hbci.status.HBCIStatus#toString()}
        erzeugt. 
        @return einen String, der alle Status-Informationen zu einer Nachricht enth�lt */
    public String toString()
    {
        StringBuffer ret=new StringBuffer();
        ret.append(globStatus.toString());
        ret.append(System.getProperty("line.separator"));
        ret.append(segStatus.toString());
        return ret.toString().trim();
    }
    
    /** 
     * Gibt zur�ck, ob der Fehler "PIN ung�ltig" zur�ckgemeldet wurde
     * @return <code>true</code> oder <code>false</code> */
    public boolean isInvalidPIN()
    {
        boolean result=false;
        
        List<HBCIRetVal> retvals=new ArrayList<HBCIRetVal>(Arrays.asList(globStatus.getErrors()));
        retvals.addAll(new ArrayList<HBCIRetVal>(Arrays.asList(segStatus.getErrors())));
        
        for (Iterator<HBCIRetVal> i=retvals.iterator(); i.hasNext();) {
            HBCIRetVal ret=i.next();
            
            if (ret.code.equals("9942") ||      // PIN falsch (konkret)
                    ret.code.equals("9340"))    // Signatur falsch (generisch)
            {
                result=true;
                break;
            }
        }
        
        return result;
    }
}
