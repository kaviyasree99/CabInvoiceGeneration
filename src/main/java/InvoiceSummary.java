
public class InvoiceSummary {

    private final int numOfRides;
    private final double totalFare;
    private final double avgFare;

    public InvoiceSummary(int numOfRides, double totalFare) {
        this.numOfRides = numOfRides;
        this.totalFare = totalFare;
        this.avgFare = this.totalFare / this.numOfRides;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        InvoiceSummary other = (InvoiceSummary) obj;
        return numOfRides == other.numOfRides && Double.compare(other.totalFare, totalFare) == 0
                && Double.compare(other.avgFare, avgFare) == 0;
    }
}