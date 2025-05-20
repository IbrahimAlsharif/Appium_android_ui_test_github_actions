package configs.testdata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class TestData {
    private JsonNode testData;

    public TestData(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            testData = objectMapper.readTree(new File("src/test/java/configs/testdata/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public JsonNode getUserData(String userType) {
        return testData.get("users").get(userType);
    }

    public JsonNode getHostData(String userType) {
        return testData.get("users").get(userType);
    }

    public JsonNode getBaseUrl() {
        return testData.get("base-url").get("staging").get("arabic");
    }

    public JsonNode getPaymentData() {
        return testData.get("payment");
    }

    public JsonNode getPropertyData() {
        return testData.get("properties");
    }

    public JsonNode getReservationData() {
        return testData.get("reservations");
    }

    public JsonNode getFilterData() {
        return testData.get("Filters");
    }

    public JsonNode getHostData() {
        return testData.get("host");
    }

    public JsonNode getLoginData() {
        return testData.get("login");
    }

    public JsonNode getReservationDetailsAsHost() {
        return testData.get("reservationDetailsAsHost");
    }

    public JsonNode getGuestInvoice() {
        return testData.get("reservationDetailsAsHost").get("reservationDetailsPopup").get("guestFinancialCollection");
    }

    public JsonNode getHostInvoice() {
        return testData.get("reservationDetailsAsHost").get("reservationDetailsPopup").get("hostFinancialCollection");
    }

    // Returns check-in and check-out dates: check-in is today + 3 days, check-out is next day
    public LocalDate[] getCheckInAndCheckOutDates() {
        LocalDate checkIn = LocalDate.now().plusDays(5);
        LocalDate checkOut = checkIn.plusDays(1);
        return new LocalDate[]{checkIn, checkOut};
    }

    //return the key exist in test data
    public String getCheckInDayWithPriceType(String priceType) {
        LocalDate checkInDate = getCheckInAndCheckOutDates()[0];
        String checkInDay = checkInDate.getDayOfWeek().name().toLowerCase() + priceType; //fridayNightPrice
        return checkInDay;
    }

}

