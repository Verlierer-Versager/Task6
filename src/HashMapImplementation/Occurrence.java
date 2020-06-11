package HashMapImplementation;

public class Occurrence implements Comparable <Occurrence>{
    private String chars;
    private double occurrence = 0;

    public Occurrence(String chars, double occurrence) {
        this.chars=chars;
        this.occurrence=occurrence;
    }

    public double getOccurrence() {
        return occurrence;
    }

    public String getChars() {
        return chars;
    }

    public void setOccurrence(double occurrence) {
        this.occurrence = occurrence;
    }
     @Override
    public int compareTo(Occurrence o) {
        return (int) (o.getOccurrence()-this.occurrence);
    }
}
