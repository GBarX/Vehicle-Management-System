import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Vehicle_29 implements Bookable, Serializable{
    private static int id;
    private int isd;
    private final String plateNumber;
    private final int numberOfTires;
    private int dailyFee;
    private int numberOfDays;
    private boolean canCancel = true;
    private boolean rentable = true;
    

    public Vehicle_29(String plateNumber, int numberOfTires) {
        this.plateNumber = plateNumber;
        this.numberOfTires = numberOfTires;
        this.isd = id++;
    } 

    @Override
    public void bookMe(String startDate, String endDate) throws Exception{
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);

            long diffMs = end.getTime() - start.getTime();
            this.numberOfDays = (int) (diffMs / (1000 * 60 * 60 * 24));
            System.out.println("Vehicle with plate " + plateNumber + " booked from " + startDate + " to " + endDate + " (" + numberOfDays + " days)."); 
        } catch (Exception e) {
            System.out.println("Error in booking: " + e.getMessage()); 
        }
    }

     public boolean canCancel(String bookingDateStr) throws IllegalArgumentException {
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date bookingDate = sdf.parse(bookingDateStr);
        Date currentDate = sdf.parse(sdf.format(new Date()));

        long diff = bookingDate.getTime() - currentDate.getTime();
        long daysDiff = diff / (1000 * 60 * 60 * 24);

        if (daysDiff > 0) {
            return true; 
        } else if (daysDiff == 0) {
            return false; 
        } else {
            throw new IllegalArgumentException("You cannot cancel a booking that is in the past.");
        }

    } catch (Exception e) {
        throw new IllegalArgumentException("Invalid date format. Please use dd/MM/yyyy.");
    }
}


    public class NoCancellationYouMustPayException extends Exception {
        public NoCancellationYouMustPayException(String message) {
            super(message);
        }
    }

    public void cancelMe(String bookingDateStr) throws NoCancellationYouMustPayException {
    try {
        if (canCancel(bookingDateStr)) {
            System.out.println("✅ Booking for vehicle with plate number " + plateNumber + " has been cancelled.");
        } else {
            throw new NoCancellationYouMustPayException("You cannot cancel this booking. You must pay for the rental.");
        }
    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}

public class SorryWeDontHaveThisVehicleException extends Exception{
    public SorryWeDontHaveThisVehicleException(String message){
        super(message);
    }
}


    public String rentMe(int numberOfDays) throws SorryWeDontHaveThisVehicleException {
    this.numberOfDays = numberOfDays;
    if (rentable) {
        rentable = false;
        return "Vehicle with plate number " + plateNumber + " has been rented for " + numberOfDays + " days.";
    } else {
        throw new SorryWeDontHaveThisVehicleException("This vehicle is not available right now.");
    }
}


    public void dropMe(String plateNumber){
        if(this.plateNumber.equals(plateNumber)){
            System.out.println("Vehicle with plate number " + plateNumber + " has been dropped off. Price is: " + getTotalFee() + " TL.");
            rentable = true;
        } else {
            System.out.println("Vehicle with plate number " + plateNumber + " not found.");
        }
    }

    public class OverWeightException extends RuntimeException {
        public OverWeightException(String message) {
            super(message);
        }
    }

    public void loadMe(int loadingAmount, int loadingCapacity) {
        if(loadingAmount > loadingCapacity){
            throw new OverWeightException("Loading amount exceeds the vehicle's capacity.");
        }
        else{
            System.out.println("Vehicle with plate number " + plateNumber + " has been loaded with " + loadingAmount + " kg.");
        }
    }

    public String getTypeName(){
        return "Generic Vehicle";
    }

    

    // Getters

    public int getTotalFee(){
        return numberOfDays * dailyFee;
    }
    public int getId(){
        return isd;
    }

    public String getPlateNumber() {
        return plateNumber;
    }
    public int getNumberOfTires() {
        return numberOfTires;
    }
    public int getDailyFee() {
        return dailyFee;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public boolean getRentable(){
        return rentable;
    }

    // Setters

    public void setDailyFee(int dailyFee) {
        this.dailyFee = dailyFee;
    }

    // Abstract method
    public abstract String getVehicleInfo();

    
}
