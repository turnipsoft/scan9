package scan9.scanners

/**
 * Resultat af en scanning
 */
class ScanResultat {
    List<Problem> problemer = []

    boolean harProblemer() {
        return problemer.size()>0
    }

}
