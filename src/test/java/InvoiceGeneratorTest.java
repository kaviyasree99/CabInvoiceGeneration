import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class InvoiceGeneratorTest {
    static InvoiceGenerator invoiceGenerator;

    @BeforeClass
    public static void Obj() {
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calaculateFare(distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calaculateFare(distance, time);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = { new Ride(2.0, 5), new Ride(0.1, 1) };
        InvoiceSummary summary = invoiceGenerator.calaculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndRide_ShouldReturnInvoiceSummary() {
        String userId = "abc.com";
        Ride[] rides = { new Ride(2.0, 5), new Ride(0.1, 1) };
        invoiceGenerator.addRides(userId, rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturn_MultipleInvoiceSummary() {
        String userId = "abc.com";
        Ride[] rides = { new Ride(2.0, 5, InvoiceGenerator.RideMode.NORMAL), new Ride(0.1, 1, InvoiceGenerator.RideMode.NORMAL) };
        invoiceGenerator.addRides(userId, rides);
        Ride[] rides1 = { new Ride(2.0, 5, InvoiceGenerator.RideMode.PREMIUM), new Ride(0.1, 1, InvoiceGenerator.RideMode.PREMIUM) };
        invoiceGenerator.addRides(userId, rides1);
        Ride[] rides2 = { new Ride(2.0, 5, InvoiceGenerator.RideMode.NORMAL), new Ride(0.1, 1, InvoiceGenerator.RideMode.PREMIUM) };
        invoiceGenerator.addRides(userId, rides2);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(6, 125);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }
}