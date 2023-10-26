package com.sibme.CoreUtilties;

import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class CoreManipulation {


    public String spiltStringByDelimiterUsingIndex(String actualString, String delimiter, int index) {
        String resultString = null;

        if (actualString.contains(delimiter)) {
            String[] parts = actualString.split(Pattern.quote(delimiter));
            if (index < parts.length) {
                resultString = parts[index].trim();
                return resultString;
            } else {
                // Handle the case where the index is out of bounds
                return null;
            }
        } else {
            return actualString;
        }
    }

    public List<String> splitTextUsingDelimiterToArray(String text, String delimiter) {
        List<String> values = new ArrayList<>();

        if (text != null && text.contains(delimiter)) {
            String[] splitArray = text.split(delimiter);
            for (String value : splitArray) {
                values.add(value.trim());  // Trim each element before adding to the list
            }
        } else {
            values.add(text);
        }

        return values;
    }

    public List<String> gettextFromElementAsList(List <WebElement> locator){
        List<String> values = new ArrayList<>();
        for (WebElement element : locator) {
            values.add(element.getText().trim());
        }
        return values;
    }

    public static String getDateUsingStringInNumericForm(String beforeOrAfter, String input, String format ) {
        LocalDate currentDate = LocalDate.now();

        if (input.equalsIgnoreCase("TodayDate")) {
            // Return today's date in the specified format
            return currentDate.format(DateTimeFormatter.ofPattern(format));
        } else if (input.matches("\\d+years?")) {
            // Parse the number of years from the input
            int yearsToAdd = Integer.parseInt(input.replaceAll("\\D", ""));

            // Calculate and return the date before or after adding the years in the specified format
            LocalDate resultDate = beforeOrAfter.equalsIgnoreCase("Before") ?
                    currentDate.minusYears(yearsToAdd) :
                    currentDate.plusYears(yearsToAdd);
            return resultDate.format(DateTimeFormatter.ofPattern(format));
        } else if (input.matches("\\d+months?")) {
            // Parse the number of months from the input
            int monthsToAdd = Integer.parseInt(input.replaceAll("\\D", ""));

            // Calculate and return the date before or after adding the months in the specified format
            LocalDate resultDate = beforeOrAfter.equalsIgnoreCase("Before") ?
                    currentDate.minusMonths(monthsToAdd) :
                    currentDate.plusMonths(monthsToAdd);
            return resultDate.format(DateTimeFormatter.ofPattern(format));
        } else if (input.matches("\\d+days?")) {
            // Parse the number of days from the input
            int daysToAdd = Integer.parseInt(input.replaceAll("\\D", ""));

            // Calculate and return the date before or after adding the days in the specified format
            LocalDate resultDate = beforeOrAfter.equalsIgnoreCase("Before") ?
                    currentDate.minusDays(daysToAdd) :
                    currentDate.plusDays(daysToAdd);
            return resultDate.format(DateTimeFormatter.ofPattern(format));
        } else {
            // Invalid input
            throw new IllegalArgumentException("Invalid input: " + input);
        }
    }


    public String getDateUsingStringInStringForm(String beforeOrAfter, String input, String format) {
        LocalDate currentDate = LocalDate.now();

        if (input.equalsIgnoreCase("TodayDate")) {
            // Return today's date in the specified format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
            return currentDate.format(formatter);
        } else if (input.matches("\\d+years?")) {
            // Parse the number of years from the input
            int yearsToAdd = Integer.parseInt(input.replaceAll("\\D", ""));

            // Calculate and return the date before or after adding the years in the specified format
            LocalDate resultDate = beforeOrAfter.equalsIgnoreCase("Before") ?
                    currentDate.minusYears(yearsToAdd) :
                    currentDate.plusYears(yearsToAdd);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
            return resultDate.format(formatter);
        } else if (input.matches("\\d+months?")) {
            // Parse the number of months from the input
            int monthsToAdd = Integer.parseInt(input.replaceAll("\\D", ""));

            // Calculate and return the date before or after adding the months in the specified format
            LocalDate resultDate = beforeOrAfter.equalsIgnoreCase("Before") ?
                    currentDate.minusMonths(monthsToAdd) :
                    currentDate.plusMonths(monthsToAdd);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
            return resultDate.format(formatter);
        } else if (input.matches("\\d+days?")) {
            // Parse the number of days from the input
            int daysToAdd = Integer.parseInt(input.replaceAll("\\D", ""));

            // Calculate and return the date before or after adding the days in the specified format
            LocalDate resultDate = beforeOrAfter.equalsIgnoreCase("Before") ?
                    currentDate.minusDays(daysToAdd) :
                    currentDate.plusDays(daysToAdd);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
            return resultDate.format(formatter);
        } else {
            // Invalid input
            throw new IllegalArgumentException("Invalid input: " + input);
        }

    }

}
