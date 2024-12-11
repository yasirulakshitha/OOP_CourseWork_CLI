package configuration;

import com.google.gson.Gson;
import logging.Logger;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Configurations {

    private int totalTickets;
    private int releaseRate;
    private int retrievalRate;
    private int maxTicketPoolSize;

    //These fields are used during runtime but won't be saved to the JSON file
    private transient int numberOfVendors;
    private transient int numberOfCustomers;

    //Constructor with only the 4 configurations to be saved
    public Configurations(int totalTickets, int releaseRate, int retrievalRate, int maxTicketPoolSize) {
        this.totalTickets = totalTickets;
        this.releaseRate = releaseRate;
        this.retrievalRate = retrievalRate;
        this.maxTicketPoolSize = maxTicketPoolSize;
//        this.numberOfVendors = numberOfVendors;
//        this.numberOfCustomers = numberOfCustomers;
    }

    // Getters and setters for all the fields
    public int getTotalTickets() {
        return totalTickets;
    }

    public int getReleaseRate() {
        return releaseRate;
    }

    public int getRetrievalRate() {
        return retrievalRate;
    }

    public int getMaxTicketPoolSize() {
        return maxTicketPoolSize;
    }

    public int getNumberOfVendors() {
        return numberOfVendors;
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public void setNumberOfVendors(int numberOfVendors) {
        this.numberOfVendors = numberOfVendors;
    }

    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }


    //Saves the current configuration to a JSON file.
    //configurations the configuration object to save
    //fileName the name of the file to save the configuration to




    public static void saveConfiguration(Configurations configurations, String fileName) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(configurations, writer);
            Logger.log("Configurations saved to " + fileName);
        } catch (IOException e) {
            Logger.log("Error saving configurations: " + e.getMessage());
        }
    }

      //Loads the configuration from a JSON file.
      // fileName the name of the file to load the configuration from
      // the loaded configuration, or null if there was an error



    public static Configurations loadSavedConfiguration(String fileName) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, Configurations.class);
        } catch (IOException e) {
            Logger.log("Error loading configurations: " + e.getMessage());
            return null;
        }
    }
}
