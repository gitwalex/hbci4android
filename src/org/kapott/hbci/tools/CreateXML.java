/**
 *
 */
package org.kapott.hbci.tools;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
public class CreateXML {
    CreateXML () {
        FileOutputStream fop = null;
        File file;
        try {
            ClassLoader cl = getClass().getClassLoader();
            String filename = "hbci-300";
            InputStream syntaxStream =
                    cl.getResourceAsStream(filename + ".xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setIgnoringComments(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            dbf.setValidating(true);
            Document syntax;
            syntax = db.parse(syntaxStream);
            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(syntax);
            StreamResult result = new StreamResult(new StringWriter());
            transformer.transform(source, result);
            file = new File("/users/alex/" + filename + "android.xml");
            fop = new FileOutputStream(file);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // get the content in bytes
            String xmlString = result.getWriter().toString();
            System.out.println(xmlString);
            byte[] contentInBytes = xmlString.getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param args
     * @throws TransformerException
     * @throws ParserConfigurationException
     * @throws IOException
     */
    public static void main (String[] args) throws TransformerException, ParserConfigurationException, IOException {
        // TODO Auto-generated method stub
        new CreateXML();
    }
}