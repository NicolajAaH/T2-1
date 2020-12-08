package Energy.domain;

class EnergyLabel {
    // ATTRIBUTTER
    // intervaller for aktuelle energimærker nov 2020 baseret på 160m2 parcelhus med oliefyr
    private static final int A = 0;
    private static final int B = 11000;
    private static final int C = 14000;
    private static final int D = 22000;
    private static final int E = 29000;
    private static final int F = 36000;
    private static final int G = 46000;

    // METODER
    // returnerer en streng med energimærke baseret på det aktuelle forbrug
    // start = start forbrug, score = opnået besparelse
    public static String createEnergyLabel(int score, int start) {
        int mark = start - score;
        if (mark < B) {
            return "A";
        }
        if (mark < C) {
            return "B";
        }
        if (mark < D) {
            return "C";
        }
        if (mark < E) {
            return "D";
        }
        if (mark < F) {
            return "E";
        }
        if (mark < G) {
            return "F";
        }
        return "G";
    }
}
