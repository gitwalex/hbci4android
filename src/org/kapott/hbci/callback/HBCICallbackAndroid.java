/**
 * 
 */
package org.kapott.hbci.callback;

import org.kapott.hbci.callback.AbstractHBCICallback;
import org.w3c.dom.Document;

/**
 * @author alex
 *
 */
public abstract class HBCICallbackAndroid extends AbstractHBCICallback {

    
    public abstract Document getHBCISpezifikation(String hbciVersion);

}
