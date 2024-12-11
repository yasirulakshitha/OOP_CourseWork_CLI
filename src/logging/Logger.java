package logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    private final static String LOG_FILE = "resource/log.txt";


      //Logs a message with a timestamp and writes it to the log file.
    public static void log(String message){

        // Create a timestamp for the log entry
        String timeStampMessage = LocalDateTime.now()+": "+message;

        // Print the log message to the console
        System.out.println(timeStampMessage);

        // Create the log file if it doesn't exist
        File file = new File(LOG_FILE);
        if (!file.exists()) {
            file.getParentFile().mkdirs();// Create directories if they don't exist
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e); // Handle file creation error
            }
        }

        // Write the log message to the log file
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(timeStampMessage); // Write the timestamped message
            bw.newLine();
        }catch (IOException e) { // Print error if writing to the file fails
            e.printStackTrace();
        }
    }


}
