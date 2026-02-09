import java.io.Serializable;
import java.util.ArrayList;

public class VehiclePark implements Serializable{
    private ArrayList<Vehicle_29> vehicles = new ArrayList<>();
    private Vehicle_29 vehicle;
    private String rentedBy;

    public void displayVehhicles(){
        if(vehicles.isEmpty()){
            System.out.println("No vehicles in park.");
        }
        else{
            System.out.println("Vehicles in the park:");
            for(int i = 0; i < vehicles.size() ; i++){
                Vehicle_29 v = vehicles.get(i);
                System.out.println("Plate Number: " + v.getPlateNumber() + ", Number of Tires: " + v.getNumberOfTires() + ", Daily Fee: " + v.getDailyFee() + " TL");
            }
        }
    }

    public void displayAvailableVehicles() {
    if (vehicles.isEmpty()) {
        System.out.println("No vehicles available in the park.");
    } else {
        System.out.println("Available vehicles in the park:");
        for (int i = 0; i < vehicles.size(); i++) {
            if(vehicles.get(i).getRentable()) {
                Vehicle_29 v = vehicles.get(i);
                System.out.println("Plate Number: " + v.getPlateNumber() + ", Number of Tires: " + v.getNumberOfTires() + ", Daily Fee: " + v.getDailyFee() + " TL");
            }
        }
    }
}


    public void addVehicle(Vehicle_29 newVehicle) {
        for (int i = 0; i < vehicles.size(); i++) {
            if(vehicles.get(i).getPlateNumber().equals(newVehicle.getPlateNumber())){
                System.out.println("Vehicle with plate number " + newVehicle + " already exists in the park.");
                return;
            }
        }
        
        vehicles.add(newVehicle);
        System.out.println("Vehicle with plate number " + newVehicle + " has been added to the park.");
    }

    public void removeVehicle(Vehicle_29 vehicleToRemove) {
    for (int i = 0; i < vehicles.size(); i++) {
        if (vehicles.get(i).getPlateNumber().equals(vehicleToRemove.getPlateNumber())) {
            vehicles.remove(i);
            System.out.println("Vehicle with plate number " + vehicleToRemove + " has been removed from the park.");
            return;
        }
    }
    System.out.println("Vehicle with plate number " + vehicleToRemove + " not found in the park.");
    
}

    public void bookVehicle(Vehicle_29 vehicleToBook, int numberOfDays) {
    for (int i = 0; i < vehicles.size(); i++) {
        if (vehicles.get(i).getPlateNumber().equals(vehicleToBook.getPlateNumber())) {
            try {
                String message = vehicleToBook.rentMe(numberOfDays);
                System.out.println(message);
            } catch (Vehicle_29.SorryWeDontHaveThisVehicleException e) {
                System.out.println("Booking failed: " + e.getMessage());
            }
            return;
        }
    }
    System.out.println("Vehicle with plate number " + vehicleToBook.getPlateNumber() + " not found in the park.");
}
    public void cancelBooking(Vehicle_29 vehicleToCancel, String dateStr) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getPlateNumber().equals(vehicleToCancel.getPlateNumber())) {
                try {
                    vehicleToCancel.cancelMe(dateStr);
                    System.out.println("Booking for vehicle with plate number " + vehicleToCancel.getPlateNumber() + " has been cancelled.");
                } catch (Vehicle_29.NoCancellationYouMustPayException e) {
                    System.out.println("Error! " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Vehicle with plate number " + vehicleToCancel.getPlateNumber() + " not found in the park.");
    }

    public void rentVehicle(Vehicle_29 vehicleToRent, int numberOfDays) {
        
            for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getPlateNumber().equals(vehicleToRent.getPlateNumber())) {
                try {
                    String msg = vehicleToRent.rentMe(numberOfDays);
                    System.out.println(msg);
                } catch (Vehicle_29.SorryWeDontHaveThisVehicleException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                return;
            }
        }
        
        
    }

    public void dropVehicle(Vehicle_29 vehicleToDrop, String plateNumber) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getPlateNumber().equals(plateNumber)) {
                vehicleToDrop.dropMe(plateNumber);
                return;
            }
        }
        System.out.println("Vehicle with plate number " + plateNumber + " not found in the park.");
    }
    public void loadVehicle(Vehicle_29 vehicleToLoad, int loadingAmount, int loadingCapacity) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getPlateNumber().equals(vehicleToLoad.getPlateNumber())) {
                try {
                    vehicleToLoad.loadMe(loadingAmount, loadingCapacity);
                } catch (Vehicle_29.OverWeightException e) {
                    System.out.println("Error! " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Vehicle with plate number " + vehicleToLoad.getPlateNumber() + " not found in the park.");
    }

    public void dailyReport(Vehicle_29 vehicle){
        System.out.println("A person with name: " + rentedBy + " has rented this vehicle.");
        System.out.println("Daily Report for Vehicle with Plate Number: " + vehicle.getPlateNumber());
        System.out.println("Total Fee: " + vehicle.getTotalFee() + " TL");
        System.out.println("Number of Days Rented: " + vehicle.getNumberOfDays());
        System.out.println("Daily Fee: " + vehicle.getDailyFee() + " TL");
        System.out.println(" A person with name: "  + " has rented this vehicle.");
    }

    public ArrayList<Vehicle_29> getVehicles(){
        return vehicles;
    }

    public void setRentedBy(String name){
        this.rentedBy = name;
    }

    public String getRentedBy(){
        return this.rentedBy;
    }

    public void displayAvailableCars(String Date) {
        boolean found = false;
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle_29 v = vehicles.get(i);
            if (v.getTypeName().equals("Car") && v.getRentable()) {
                System.out.println("Plate Number: " + v.getPlateNumber() + ", Number of Tires: " + v.getNumberOfTires() + ", Daily Fee: " + v.getDailyFee() + " TL");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available cars in the park.");
        }
    }

    public void displayAvailableTrucks(String Date) {
        boolean found = false;
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle_29 v = vehicles.get(i);
            if (v.getTypeName().equals("Truck") && v.getRentable()) {
                System.out.println("Plate Number: " + v.getPlateNumber() + ", Number of Tires: " + v.getNumberOfTires() + ", Daily Fee: " + v.getDailyFee() + " TL");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available trucks in the park.");
        }
    }
}