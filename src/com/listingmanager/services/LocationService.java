package com.listingmanager.services;

import com.google.api.services.mybusiness.v4.MyBusiness;
import com.google.api.services.mybusiness.v4.model.*;
import com.listingmanager.util.MyBusinessFactory;

import java.io.IOException;
import java.rmi.server.UID;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class LocationService {
    /**
     * Returns all locations for the specified account.
     * @param accountName The account for which to return locations.
     * @returns List A list of all locations for the specified account.
     */
    public static List<Location> listLocations(String accountName) throws Exception {
        MyBusiness.Accounts.Locations.List locationsList =
                MyBusinessFactory.getBusinessObject().accounts().locations().list(accountName);
        ListLocationsResponse response = locationsList.execute();
        List<Location> locations = response.getLocations();

        if (locations != null) {
            for (Location location : locations) {
                System.out.println(location.toPrettyString());
            }
        } else {
            System.out.printf("Account '%s' has no locations.", accountName);
        }
        return locations;
    }

    /**
     * Returns the specified location
     * @param locationName The name (resource path) of the location to retrieve.
     * @returns Location The requested location.
     */
    private static Location getLocationByName(String locationName) throws IOException, GeneralSecurityException {
        MyBusiness.Accounts.Locations.Get location =
                MyBusinessFactory.getBusinessObject().accounts().locations().get(locationName);

        Location response = location.execute();
        return response;
    }

    /**
     * Creates a new location.
     * @param accountName The name (resource path) of the account to create a
     *   location for.
     * @return Location The data for the new location.
     * @throws Exception
     */

    public static Location createLocation(String accountName,Location location) throws Exception {
        System.out.println("Creating Location");

        /*// Street address
        List addressLines = new ArrayList();
        addressLines.add("Level 5, 48 Pirrama Road");
        PostalAddress address = new PostalAddress()
                .setAddressLines(addressLines)
                .setLocality("Pyrmont")
                .setAdministrativeArea("NSW")
                .setRegionCode("AU")
                .setPostalCode("2009");

        // Business hours
        List periods = new ArrayList<>();
        List<String> days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");

        for (String day : days) {
            TimePeriod period = new TimePeriod()
                    .setOpenDay(day)
                    .setOpenTime("9:00")
                    .setCloseTime("17:00")
                    .setCloseDay(day);
            periods.add(period);
        }
        BusinessHours businessHours = new BusinessHours()
                .setPeriods(periods);
        Location location = new Location()
                .setAddress(address)
                .setRegularHours(businessHours)
                .setLocationName("Google Sydney")
                .setStoreCode("GOOG-SYD")
                .setPrimaryPhone("02 9374 4000")
                .setPrimaryCategory(new Category().setDisplayName("Software Company"))
                .setWebsiteUrl("https://www.google.com.au/");*/

        MyBusiness.Accounts.Locations.Create createLocation =
                MyBusinessFactory.getBusinessObject().accounts().locations().create(accountName, location);
        createLocation.setRequestId(UUID.randomUUID().toString());

        Location createdLocation = createLocation.execute();

        System.out.printf("Created Location:\n%s", createdLocation);
        return createdLocation;
    }

    /**
     * Deletes the specified location.
     * @param locationName The name (resource path) of the location to delete.
     * @throws Exception
     */
    public static void deleteLocation(String locationName) throws Exception {
        Location location = new Location();
        location.setName(locationName);
        MyBusiness.Accounts.Locations.Delete deleteLocation =
                MyBusinessFactory.getBusinessObject().accounts().locations().delete(locationName);
        deleteLocation.execute();
    }
}
