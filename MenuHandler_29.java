import java.io.*;
import java.util.Scanner;

public class MenuHandler_29 implements Serializable{
    private VehiclePark park;
    private final Scanner sc = new Scanner(System.in);
    private Vehicle_29 v;
    private Truck_29 truck;
    
    
    public MenuHandler_29(VehiclePark park) {
        this.park = park;
    }

    public void start(){
        loadPark();
        System.out.println("Welcome to the Vehicle System! ");
        System.out.println("Login as (1 - Admin, 2 - User): ");
        int role = sc.nextInt();
        if(role == 1){
            System.out.println("Please enter your choice: ");
            int choice = 0;
            while(choice != 9 ){
                System.out.println("--- Vehicle Rental Menu ---");
                System.out.println("1. Display all vehicles");
                System.out.println("2. Displays available vehicles");
                System.out.println("3. Add a new vehicle");
                System.out.println("4. Remove Vehicle");
                System.out.println("5. Daily Reports");
                System.out.println("6. Quit");
                System.out.println("--------------------");
                System.out.println("Please enter the input: ");
                choice = sc.nextInt();

                switch(choice){
                case 1:
                    park.displayVehhicles();
                    break;
                case 2:
                    park.displayAvailableVehicles();
                    break;
                case 3:
                    addVehicle();
                    break;
                case 4:
                    removeVehicle();
                    break;
                case 5:
                    dailyReport();
                    break;
                case 6:
                    System.exit(0);
            }
            }
            
        }
        else if(role == 2){
            System.out.println("Please enter your choice: ");
            int choice = 0;
            
            while(choice != 9){
                System.out.println("--- Vehicle Rental Menu ---");
                System.out.println("1. Display all vehicles"); //id,plate no, vehicle type, available or not now
                System.out.println("2. Displays available vehicles"); //ask date
                System.out.println("3. Display available vehicles by type"); //asks date, vehicle type
                System.out.println("4. Book a vehicle"); //ask required input
                System.out.println("5. Cancel my booking"); //asks r.i
                System.out.println("6. Rent a vehicle"); //ask r.i
                System.out.println("7. Drop a vehicle"); //ask r.i
                System.out.println("8. Load a vehicle"); //ask r.i
                System.out.println("9. Quit");
                System.out.println("Please enter the input: ");
                choice = sc.nextInt();

                switch(choice){
                case 1:
                    park.displayVehhicles();
                    break;
                case 2:
                    park.displayAvailableVehicles();
                    break;
                case 3:
                    displayAvailableVehiclesByType();
                    break;
                case 4:
                    bookVehicle();
                    break;
                case 5:
                    cancelBooking();
                    break;
                case 6: 
                    rentVehicle();
                    break;
                case 7:
                    dropVehicle();
                    break;
                case 8:
                    loadVehicle();
                    break;
                default:
                    System.out.println("Please enter a invalid number.");
            }
            }
        }
    }

    private void savePark(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("park.dat"))) {
            oos.writeObject(park);
            System.out.println("Park saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving park: " + e.getMessage());
        }
    }

    private void loadPark(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("park.dat"))){
            VehiclePark loadPark = (VehiclePark) ois.readObject();
            park.getVehicles().addAll(loadPark.getVehicles());
            System.out.println("Vehicle park loaded successfully.");
        }catch(Exception e){
            System.out.println("Error loading vehicle park: " + e.getMessage());
        }
    }

    private void addVehicle(){
        sc.nextLine(); 
    System.out.print("Enter vehicle type (Car/Truck): ");

    String type = sc.nextLine();

    if(type.equals("Car")){
        System.out.print("Enter plate number: ");
        String plate = sc.nextLine();

        System.out.print("Enter number of tires: ");
        int tires = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter car type (SUV, Sports, Station Wagon): ");
        String carType = sc.nextLine();

        v = new Car_29(plate, tires, carType);
        park.addVehicle(v);
        System.out.println("Car successfully added! Type: " + carType + ", Plate: " + plate + " Please enter the daily fee of this car: ");
        int dailyFee = sc.nextInt();
        this.v.setDailyFee(dailyFee);
        System.out.println("Daily fee of this car set to: " + dailyFee);

    } else if(type.equals("Truck")){
        System.out.print("Enter plate number: ");
        String plate = sc.nextLine();

        System.out.print("Enter number of tires: ");
        int tires = sc.nextInt();

        System.out.print("Enter loading capacity: ");
        int capacity = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter truck type (Small Truck, Transport Truck): ");
        String truckType = sc.nextLine();

        truck = new Truck_29(plate, tires, capacity, truckType);
        park.addVehicle(truck);
        System.out.println("Truck successfully added! Type: " + truckType + ", Plate: " + plate + " Please enter the daily fee of this truck: ");
        int dailyFee = sc.nextInt();
        this.truck.setDailyFee(dailyFee);
        System.out.println("Daily fee of this car set to: " + dailyFee);
    }

    savePark();
}


    private void removeVehicle(){
    sc.nextLine();
    System.out.println("Enter plate number of vehicle to remove: ");
    String plate = sc.nextLine();
    Vehicle_29 vehicleToRemove = findVehicleByPlate(plate);

    if (vehicleToRemove != null) {
        park.removeVehicle(vehicleToRemove);
        savePark();
        System.out.println("Vehicle removed from the list!");
    } else {
        System.out.println("Vehicle not found.");
    }
}


    private void bookVehicle(){
        sc.nextLine();
        System.out.println("Enter plate number to book: ");
        String plate = sc.nextLine();
        Vehicle_29 found = findVehicleByPlate(plate);
        if(found != null){
            System.out.println("Enter start date (dd/MM/yyyy): ");
            String startDate = sc.nextLine();
            System.out.println("Enter end date (dd/MM/yyyy): ");
            String endDate = sc.nextLine();
            try {
                found.bookMe(startDate, endDate);
            } catch (Exception e) {
                System.out.println("Error booking vehicle: " + e.getMessage());
            }
        } else {
            System.out.println("Vehicle not found.");
        }
    } 

    private void rentVehicle(){
        sc.nextLine();
        System.out.println("Enter plate number to rent: ");
        String plate = sc.nextLine();
        Vehicle_29 found = findVehicleByPlate(plate);
        if(found != null){
            System.out.println("Enter number of days to rent: ");
            int days = sc.nextInt();
            try {
                park.rentVehicle(found, days);
            } catch (Exception e) {
                System.out.println("Error renting vehicle: " + e.getMessage());
            }
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private void cancelBooking(){
        sc.nextLine(); 
        System.out.println("Enter plate number to cancel booking: ");
        String plate = sc.nextLine();
        Vehicle_29 found = findVehicleByPlate(plate);
        if(found != null){
            System.out.println("Enter cancellation date (dd/MM/yyyy): ");
            String cancelDate = sc.nextLine();
            try {
                found.cancelMe(cancelDate);
            } catch (Vehicle_29.NoCancellationYouMustPayException e) {
                System.out.println("Error cancelling booking: " + e.getMessage());
            }
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private void loadVehicle(){
        sc.nextLine();
        System.out.println("Enter plate number to load: ");
        String plate = sc.nextLine();
        Vehicle_29 found = findVehicleByPlate(plate); 
        if(found != null){
                System.out.println("Enter loading amount: ");
                int amount = sc.nextInt();
                sc.nextLine();
            try {
                park.loadVehicle(found, amount, this.truck.getLoadingCapacity());
            } catch (Exception e) {
                System.out.println("Error loading vehicle: " + e.getMessage());
            }
        }
        System.out.println("Vehicle not found.");
    }

    private void dropVehicle(){
        sc.nextLine();
        System.out.print("Enter plate number to drop: ");
        String plate = sc.nextLine();
        Vehicle_29 found = findVehicleByPlate(plate);
        if (found != null){
            park.dropVehicle(found, plate);
            System.out.println("Vehicle with plate " + plate + " has been dropped off.");
        } 
        else{
            System.out.println("Vehicle not found.");
        }
    }

    private void dailyReport(){
        sc.nextLine(); 
        System.out.println("Enter plate number: ");
        String plate = sc.nextLine();
        Vehicle_29 found = findVehicleByPlate(plate);
        if(found != null){
            System.out.println("Daily report for vehicle with plate " + plate + ":");
            park.dailyReport(found);
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private Vehicle_29 findVehicleByPlate(String plate){
        for (int i = 0; i < park.getVehicles().size(); i++) {
            Vehicle_29 current = park.getVehicles().get(i);
            if(current.getPlateNumber().equals(plate)){
                return current;
            }
        }
        return null;
    }

    private void displayAvailableVehiclesByType() {
        sc.nextLine();
        System.out.println("Enter vehicle type (Car/Truck): ");
        String type = sc.nextLine();
        System.out.println("Enter date (dd/MM/yyyy): ");
        String date = sc.nextLine();
        
        if (type.equals("Car")) {
            park.displayAvailableCars(date);
        } else if (type.equalsIgnoreCase("Truck")) {
            park.displayAvailableTrucks(date);
        } else {
            System.out.println("Invalid vehicle type.");
        }
    }
}