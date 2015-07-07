package scan9.scanners.cpr

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import scan9.scanners.ScanResultat

/**
 * Created by shartvig on 07/07/15.
 */
class CprScanner implements scan9.scanners.Scanner {

    Logger log = LoggerFactory.getLogger(CprScanner.class)

    @Override
    ScanResultat scan(InputStream inputStream) {
        int tegn

        boolean talFundet = false
        boolean mellemrumFundet = false

        byte[] kontekst = new byte[100]

        StringBuffer talraekke = new StringBuffer()

        while ((tegn = inputStream.read()) != -1) {
            if (erMellemrum(tegn)) {
                // ignore
            } else if (erTal(tegn)) {
                talFundet = true
                talraekke.append(new Character((char)tegn))
            } else if (erBindestreg(tegn)) {
                //ignore
            } else {
                if (talFundet && talraekke.length()==10) {
                    log.debug("potentielt cpr fundet")
                }
                talraekke = new StringBuffer()
                talFundet = false
            }
        }
    }


    boolean erTal(int tegn) {
        char c = tegn
        if (c>='0' && c<='9') {
            return true
        }
    }

    boolean erMellemrum(int tegn) {
        char c = tegn
        if (c==' ') {
            return true
        }
    }

    boolean erBindestreg(int tegn) {
        char c = tegn
        if (c>='-') {
            return true
        }
    }
}
