package io.github.rubywha2.onboarding.util;
import io.github.rubywha2.onboarding.model.Log;

import java.io.*;

public class CSVLogger {
    private static final String FILE_PATH = "logs/login_logs.csv";

    /**
     * Check if the CSV file exists.
     * Returns true if yes, false otherwise.
     */
    public static boolean csvExists() {
        File file = new File(FILE_PATH);
        return file.exists();
    }

    /**
     * Append a new row to the CSV file.
     * Assumes file already exists.
     */
    public static void append(Log newLog, String status) {
        if (!csvExists()) {
            System.err.println("CSV file does not exist at: " + FILE_PATH);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String row = String.join(",", newLog.toString(), status);
            writer.write(row);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }

    /**
     * Print the entire CSV content to the console.
     */
    public static void printLogs() {
        if (!csvExists()) {
            System.err.println("CSV file does not exist at: " + FILE_PATH);
            return;
        }

        System.out.println("\n --- CSV Contents ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println(" Error reading CSV: " + e.getMessage());
        }
    }
}
