import java.text.SimpleDateFormat;
import java.util.Date;

public class Truck_29 extends Vehicle_29 implements Bookable{
    private int loadingCapacity;
    private String truckType;
    private String bookingStartDateStr;
    private String bookingEndDateStr;
    private Vehicle_29 vehicle;

    public Truck_29(String plateNumber, int numberOfTires, int loadingCapacity, String truckType) {
        super(plateNumber, numberOfTires);
        this.truckType = truckType;
        
    }

    public int getLoadingCapacity() {
        return loadingCapacity;
    }

    public void setLoadingCapacity(int loadingCapacity) {
        this.loadingCapacity = loadingCapacity;
    }

    @Override
    public String getVehicleInfo() {
        return "Truck Info → Plate: " + getPlateNumber() + ", Type: " + truckType + ", Tires: " + getNumberOfTires() + ", Fee: " + getDailyFee();
    }

    @Override
    public void bookMe(String startDate, String endDate) throws Exception {
        //? checked exception 
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);

        int numberOfDays = this.vehicle.getNumberOfDays();
        String plateNumber = this.vehicle.getPlateNumber();

        if (end.before(start)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }

        this.bookingStartDateStr = startDate;
        this.bookingEndDateStr = endDate;

        long diffMs = end.getTime() - start.getTime();
        numberOfDays = (int) (diffMs / (1000 * 60 * 60 * 24));

        System.out.println("Vehicle with plate " + plateNumber + " booked from " + startDate + " to " + endDate + " (" + numberOfDays + " days).");
    }

    @Override
    public void cancelMe(String cancelDate) throws NoCancellationYouMustPayException {
        try {
            if (bookingStartDateStr == null) {
                System.out.println("No booking exists to cancel.");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date cancel = sdf.parse(cancelDate);
            Date start = sdf.parse(bookingStartDateStr);

            if (cancel.before(start)) {
                System.out.println("Booking cancelled successfully before rental start.");
            } else {
                throw new NoCancellationYouMustPayException("Rental has started or passed. You must pay!");
            }

        } catch (Exception e) {
            throw new NoCancellationYouMustPayException("An error occurred while cancelling the booking: " + e.getMessage());
        }
    }

    @Override
    public String getTypeName(){
        return "Truck";
    }

}
