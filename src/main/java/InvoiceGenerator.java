public class InvoiceGenerator {
    private static final double MIN_COST_PER_KM = 10.0;
    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_FARE = 5.0;

    public final RideRepository rideRepository;

    public enum RideMode {
        NORMAL(10.0, 1, 5.0), PREMIUM(15.0, 2, 20.0);

        private double MIN_COST_PER_KM;
        private int COST_PER_TIME;
        private double MINIMUM_FARE;

        RideMode(double MIN_COST_PER_KM, int COST_PER_TIME, double MINIMUM_FARE) {
            this.MIN_COST_PER_KM = MIN_COST_PER_KM;
            this.COST_PER_TIME = COST_PER_TIME;
            this.MINIMUM_FARE = MINIMUM_FARE;
        }
    }

    public InvoiceGenerator() {
        this.rideRepository = new RideRepository();
    }

    public double calaculateFare(double distance, int time) {
        double totalFare = distance * MIN_COST_PER_KM + time * COST_PER_TIME;
        return (totalFare < MINIMUM_FARE) ? MINIMUM_FARE : totalFare;
    }

    public InvoiceSummary calaculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calaculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calaculateFare(rideRepository.getRides(userId));
    }
}