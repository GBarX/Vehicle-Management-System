import java.util.Scanner;

public class TestClass_29 {
    public static void main(String[] args) {    
        Scanner scanner = new Scanner(System.in);    
        VehiclePark park = new VehiclePark();
        MenuHandler_29 menu = new MenuHandler_29(park);

        Vehicle_29 car1 = new Car_29("34NF1234", 4, "Sports");
        Vehicle_29 car2 = new Car_29("06P5678", 4, "SUV");
        Vehicle_29 car3 = new Car_29("01AYN04", 4, "Station Wagon");
        Vehicle_29 car4 = new Car_29("34AYN8411", 4, "SUV");
        Vehicle_29 truck1 = new Truck_29("23ZRF1234", 6, 1000, "Small Truck");
        Vehicle_29 truck2 = new Truck_29("16ZRF5678", 10, 2000, "Transporter");

        car1.setDailyFee(100);
        car2.setDailyFee(150);
        car3.setDailyFee(120);
        car4.setDailyFee(130);
        truck1.setDailyFee(200);
        truck2.setDailyFee(250);

        park.addVehicle(car1);
        park.addVehicle(car2);
        park.addVehicle(car3);
        park.addVehicle(car4);
        park.addVehicle(truck1);
        park.addVehicle(truck2);



        //Starts
        menu.start();
    }
}
