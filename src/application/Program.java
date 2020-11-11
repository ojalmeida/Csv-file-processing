package application;

import entities.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the path: ");
        String pathStr = sc.nextLine();

        File filePath = new File(pathStr); // In path
        File[] files = filePath.listFiles(File::isFile); ///Gets all the directories
        String outPath = pathStr + "\\summary"; // Out path
        boolean sucess = new File(outPath).mkdir(); // Creates the directory for the out data

        try {
            for (File f : files) { // Access each file and create a summarized correspondent on the out path typed
                System.out.println();
                String outPathFileName = outPath + "\\Summary_Of_" + f.getName();
                System.out.println("Summarized " + f.getName() + " ready");
                ArrayList<Product> productArrayList = new ArrayList<>(); // Product arrays of the current file

                try (BufferedReader reader = new BufferedReader(new FileReader(f.getPath()))) {

                    ArrayList<String> lines = new ArrayList<>(); // Read all the lines and save it on an Array
                    String line = reader.readLine();
                    System.out.printf("%d line(s) processed: %n", files.length - 1);
                    while (line != null) {
                        for (int i=1; i <=files.length - 1; i++) {
                            System.out.printf("Line (%d) - %s%n", i, line);

                            lines.add(line);
                            line = reader.readLine();
                        }
                    }
                    for (String s : lines) { /// reads, splits, writes each line on the output file

                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outPathFileName))) {

                            String[] sepStrVect = s.split(";|;\\s"); // Split de attributes that will be used on the instantiation
                            String name = sepStrVect[0];
                            double price = Double.parseDouble(sepStrVect[1]);
                            int quantity = Integer.parseInt(sepStrVect[2]);

                            Product p = new Product(name, price, quantity); // Instantiation
                            productArrayList.add(p);

                            for (Product p2 : productArrayList) {

                                writer.append(p2.toString());
                                writer.newLine();
                            }
                        }
                    }
                }
            }
        } catch (IOException | NullPointerException e) {
            e.getMessage();
        }
    }
}
