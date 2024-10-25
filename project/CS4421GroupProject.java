import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class CS4421GroupProject {

    public static void menu() throws Exception { // Danny's function
        Scanner input = new Scanner(System.in);

        // Test code for pciNamer
        // String vendorName = TheProject.pciNamer("8086", "0180").get(0);
        // String deviceName = TheProject.pciNamer("8086", "0180").get(1);
        // System.out.printf("Vendor: %s%nDevice: %s", vendorName, deviceName);

        while (true) {
            System.out.println("Main Menu");
            System.out.println("1) CPU Info");
            System.out.println("2) Disk Info");
            System.out.println("3) PCI Info");
            System.out.println("4) System Info");
            System.out.println("5) USB Info");
            System.out.println("6) Exit");
            System.out.print("Enter a number: ");

            int number = input.nextInt();

            // Based on the user's choice, call the corresponding submenu.
            switch (number) {
                case 1:
                    handleInput("CPU_Info");
                    break;
                case 2:
                    handleInput("Disk_Info");
                    break;
                case 3:
                    handleInput("PCI_Info");
                    break;
                case 4:
                    handleInput("System_Info");
                    break;
                case 5:
                    handleInput("USB_Info");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return; // Exit the program
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // function to handle the submenu logic
    public static void handleInput(String UserInput) {
        Scanner input = new Scanner(System.in);

        switch (UserInput) {
            case "CPU_Info":
                System.out.println("\nCPU Information Menu:");
                System.out.println("1) View CPU Model");
                System.out.println("2) View CPU Speed");
                System.out.println("3) Back to Main Menu");
                break;
            case "Disk_Info":
                System.out.println("\nDisk Information Menu:");
                System.out.println("1) View Disk Capacity");
                System.out.println("2) View Disk Usage");
                System.out.println("3) Back to Main Menu");
                break;
            case "PCI_Info":
                System.out.println("\nPCI Information Menu:");
                System.out.println("1) View PCI Devices");
                System.out.println("2) View PCI Bus Info");
                System.out.println("3) Back to Main Menu");
                break;
            case "System_Info":
                System.out.println("\nSystem Information Menu:");
                System.out.println("1) View OS Version");
                System.out.println("2) View System Uptime");
                System.out.println("3) Back to Main Menu");
                break;
            case "USB_Info":
                System.out.println("\nUSB Information Menu:");
                System.out.println("1) View USB Devices");
                System.out.println("2) View USB Controller Info");
                System.out.println("3) Back to Main Menu");
                break;
            default:
                System.out.println("Invalid submenu option.");
        }

        System.out.print("Enter a number: ");
        int submenuChoice = input.nextInt();

        System.out.print(UserInput + submenuChoice);

        // Process submenu options
        if (submenuChoice == 3) {
            return; // Go back to the main menu
        } else {
            System.out.println("Displaying details for option " + submenuChoice);
            // You can add more specific functionality here for each option
        }
    }

    public static List<String> pciNamer(String vendorID, String deviceID) throws Exception {
        /*
         * This function takes two inputs for vendor ID and device ID, then returns a
         * string list
         * containing the actual data, in the form of ["Vendor Name", "Device Name"]
         * This is achieved by comparing the results to a csv database of >50,000 PCI
         * devices.
         * Created by Skye Fitzpatrick
         */

        /*
         * TO CALL pciNamer
         * numbers to be replaced with output from Mark's pciInfo code
         * String vendorName = TheProject.pciNamer("8086", "0180").get(0);
         * String deviceName = TheProject.pciNamer("8086", "0180").get(1);
         * System.out.printf("Vendor: %s%nDevice: %s", vendorName, deviceName);
         */

        // Read in data from the CSV file using Scanner
        Scanner readCsv = new Scanner(new File("./PCI-Lookup.csv"));

        /*
         * We need to create a 2-dimensional array because of the way the csv file is
         * formatted.
         * The CSV contains data in the form [vendorID+deviceID, vendor name, device
         * name], copied from https://pcilookup.com.
         * To analyse and get data from this, we need to use nested while loops as
         * below.
         */
        ArrayList<ArrayList<String>> pciData = new ArrayList<ArrayList<String>>();

        while (readCsv.hasNextLine()) { // while there are still rows to be read...
            ArrayList<String> rowData = new ArrayList<String>(); // create an empty list for each string in data row
            Scanner separateRowData = new Scanner(readCsv.nextLine()); // read data from the next row
            separateRowData.useDelimiter(","); // separate it using the comma delimiter
            while (separateRowData.hasNext()) {
                rowData.add(separateRowData.next()); // add it to the data list
            }
            pciData.add(rowData); // add to 2 dimensional array
        }

        // initialise variables for each dataset
        ArrayList<String> codesList = new ArrayList<String>();
        ArrayList<String> vendorsList = new ArrayList<String>();
        ArrayList<String> devicesList = new ArrayList<String>();

        // for loop increments for each item in pciData 2D array
        for (int i = 0; i < pciData.size(); i++) {
            codesList.add(pciData.get(i).get(0)); // first column of row i is a code
            vendorsList.add(pciData.get(i).get(1)); // second column of row i is a vendor name
            devicesList.add(pciData.get(i).get(2)); // third column of row i is a device name
        }

        String codeNeeded = vendorID.concat(deviceID); // combine the vendor and device ID into one string to form the
                                                       // "code"
        // using a try/catch block in case the code provided is not part of the database
        // list and runs an error
        try {
            int index = codesList.indexOf(codeNeeded); // find index of codeNeeded against original CSV
            ArrayList<String> result = new ArrayList<String>();
            result.add(vendorsList.get(index)); // use same index to find necessary vendor name
            result.add(devicesList.get(index)); // use same index to fine
            return result;
        } catch (Exception e) {
            // create and return an "error list" if the code doesn't run as expected
            List<String> error = new ArrayList<String>();
            error.add("Unknown Vendor!");
            error.add("Unknown Device!");
            return error;
        }
    }

    /* Bence's problem xx
    public static void getCPUOverTime() throws InterruptedException {
        // cpuInfo cpu = new cpuInfo();
        double startTime = System.currentTimeMillis();

        Graph graph = new Graph("Random Walk", "Pos", "Time", "Walk", new double[2], new double[2]);
        graph.displayGraph();

        ArrayList<ArrayList<Double>> returnValue = new ArrayList<>(2);
        returnValue.add(new ArrayList<>());
        returnValue.add(new ArrayList<>());

        int pos = 0;
        int max = 0;
        Random rand = new Random();
        int step;

        for (int counter = 0; counter < 100000; counter++) {
            double currentTime = (System.currentTimeMillis() - startTime) / 1000;

            boolean bool = rand.nextBoolean();
            if (bool)
                step = 1;
            else
                step = -1;
            pos += step;
            max = Math.max(pos, max);

            returnValue.get(0).add(currentTime);
            returnValue.get(1).add((double) pos);

            if (returnValue.get(0).size() > 1000) {
                returnValue.get(0).removeFirst();
                returnValue.get(1).removeFirst();
            }

            Thread.sleep(1);

            double[] returnX = returnValue.get(0).stream().mapToDouble(i -> i).toArray();
            double[] returnY = returnValue.get(1).stream().mapToDouble(i -> i).toArray();

            graph.updateGraph(returnX, returnY);
        }
    }
    */

    public static void main(String[] args) throws Exception, InterruptedException {
            CS4421GroupProject.menu();
    }
}