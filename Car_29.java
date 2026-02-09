import java.text.SimpleDateFormat;
import java.util.Date;

public class Car_29 extends Vehicle_29 implements Bookable{
    private String color;
    private int seatingCapacity, numOfDoors;
    private String carType;
    private boolean horsePower = false;
    private int loadingCapacity;
    private Vehicle_29 vehicle;
    private String bookingStartDateStr;
    private String bookingEndDateStr;

    public Car_29(String plateNumber, int numberOfTires, String carType) {
        super(plateNumber, numberOfTires);
        this.carType = carType;
    }

    public int getType(){
        int type = 0;
        switch(carType){
            case "Sports":
            setDailyFee(type * 100);
            loadingCapacity = 10;
            horsePower = true;
            seatingCapacity = 5;
            type = 1;
                return type;
            case "Station Wagon":
            setDailyFee(type * 50);
            loadingCapacity = 70;
            seatingCapacity = 6;
            type = 2;
                return type;
            case "SUV":
            setDailyFee(type * 75);
            loadingCapacity = 5;
            seatingCapacity = 5;
            type = 3;
                return type;
            default:
            type = 0;
                return type;
        }
    }

    @Override
    public String getVehicleInfo() {
        return "Car Info → Plate: " + getPlateNumber() + ", Type: " + carType + ", Tires: " + getNumberOfTires() + ", Fee: " + getDailyFee();
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

        } catch (NoCancellationYouMustPayException e) {
            throw e;
        } catch (Exception e) {
            System.out.println("Cancel failed due to error: " + e.getMessage());
        }
    }

    @Override
    public String getTypeName(){
        return "Car";
    }
}