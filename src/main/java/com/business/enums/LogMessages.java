package com.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogMessages {
    // Find All
    CAR_FIND_ALL_START("Fetching all cars."),
    CAR_FIND_ALL_SUCCESS("Successfully fetched cars. Count = {}."),
    CAR_FIND_ALL_ERROR("Error fetching all cars!"),

    // Find By ID
    CAR_FIND_BY_ID_START("Fetching car with ID {}."),
    CAR_FIND_BY_ID_SUCCESS("Successfully fetched car with ID {}."),
    CAR_NOT_FOUND("Car with ID {} not found."),
    ERROR_FETCHING_CAR("Error fetching car with ID {}!"),

    // Save
    CAR_SAVE_START("Saving new car: {} {}."),
    CAR_SAVE_SUCCESS("Car saved successfully."),
    CAR_SAVE_ERROR("Error saving new car: {} {}!"),

    // Update
    CAR_UPDATE_START("Updating car with ID {}."),
    CAR_UPDATE_SUCCESS("Car with ID {} updated successfully."),
    CAR_UPDATE_ERROR("Error updating car with ID {}!"),

    // Delete
    CAR_DELETE_START("Deleting car with ID {}."),
    CAR_DELETE_SUCCESS("Car with ID {} deleted successfully."),
    CAR_DELETE_ERROR("Error deleting car with ID {}!");

    private final String value;
}
