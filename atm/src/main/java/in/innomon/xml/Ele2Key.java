/*
* Copyright (c) 2016, BON BIZ IT Services Pvt LTD.
*
* The Universal Permissive License (UPL), Version 1.0
* 
* Subject to the condition set forth below, permission is hereby granted to any person obtaining a copy of this software, associated documentation and/or data (collectively the "Software"), free of charge and under any and all copyright rights in the Software, and any and all patent rights owned or freely licensable by each licensor hereunder covering either (i) the unmodified Software as contributed to or provided by such licensor, or (ii) the Larger Works (as defined below), to deal in both

* (a) the Software, and

* (b) any piece of software and/or hardware listed in the lrgrwrks.txt file if one is included with the Software (each a “Larger Work” to which the Software is contributed by such licensors),
* 
* without restriction, including without limitation the rights to copy, create derivative works of, display, perform, and distribute the Software and make, use, sell, offer for sale, import, export, have made, and have sold the Software and the Larger Work(s), and to sublicense the foregoing rights on either these or other terms.
* 
* This license is subject to the following condition:
* 
* The above copyright notice and either this complete permission notice or at a minimum a reference to the UPL must be included in all copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
* 
* Author: Ashish Banerjee, tech@innomon.in
*/

package in.innomon.xml;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ashish
 */
public class Ele2Key implements ElementToKey{
    private String nameSpace = null;
    transient Properties ele2keyMap = new Properties();
    private String loggerName = "UPAY";

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }
 
    public void setCol2BeanMap(Properties map) {
        ele2keyMap = map;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public void setCol2BeanMapFile(String flname) {
        try {
            try (FileInputStream fis = new FileInputStream(flname)) {
                ele2keyMap.load(fis);
                fis.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(loggerName).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getKey(String elementName, String ns) {
        String ret = null;
             
        if(ns != null && nameSpace != null && ns.equals(nameSpace) ) {
            ret = ele2keyMap.getProperty(elementName);
        }   

       // Logger.getLogger(loggerName).log(Level.FINEST, " ns[{0}], elementName[{1}], key[{2}]", new Object[]{ns, elementName, ret});
            
        return ret;    
    }

}
