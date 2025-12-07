package com.bussiness.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Constants {
    //Database
    HOST("localhost"),
    PORT("1433"),
    DATABASE("car_rental"),
    USER("SA"),
    PASSWORD("Password12345"),
    URL("jdbc:sqlserver://%s:%s;databaseName=%s;encrypt=false;"),
    ID("ID"),
    MAKER("MAKER"),
    MODEL("MODEL"),
    YEAR("YEAR"),
    COLOR("COLOR"),
    LICENSE_PLATE("LICENSE_PLATE"),
    DAILY_PRICE("DAILY_PRICE"),
    IS_RENTED("IS_RENTED"),
    FIND_ALL_QUERY("SELECT ID, MAKER, MODEL, YEAR, COLOR, LICENSE_PLATE, DAILY_PRICE, IS_RENTED FROM CARS ORDER BY ID"),
    FIND_BY_ID_QUERY("SELECT ID, MAKER, MODEL, YEAR, COLOR, LICENSE_PLATE, DAILY_PRICE, IS_RENTED FROM CARS WHERE ID = ?"),
    SAVE_QUERY("INSERT INTO CARS (MAKER, MODEL, YEAR, COLOR, LICENSE_PLATE, DAILY_PRICE, IS_RENTED) VALUES (?, ?, ?, ?, ?, ?, ?)"),
    UPDATE_QUERY("UPDATE CARS SET MAKER = ?, MODEL = ?, YEAR = ?, COLOR = ?, LICENSE_PLATE = ?, DAILY_PRICE = ?, IS_RENTED = ? WHERE ID = ?"),
    DELETE_QUERY("DELETE FROM CARS WHERE ID = ?"),

    //Frontend
    TITLE("Car Rental Management"),
    ADD_CAR("Add Car"),
    ADD_CAR_DIALOG("Add Car"),
    EDIT_CAR_DIALOG("Edit Car"),
    TABLE_ID("ID"),
    TABLE_MAKER("Maker"),
    TABLE_MODEL("Model"),
    TABLE_YEAR("Year"),
    TABLE_COLOR("Color"),
    TABLE_PLATE("Plate"),
    TABLE_PRICE("Price"),
    TABLE_RENTED("Rented"),
    STATUS("Status"),
    MAKER_FIELD("Maker:"),
    MODEL_FIELD("Model:"),
    YEAR_FIELD("Year:"),
    COLOR_FIELD("Color:"),
    PLATE_FIELD("Plate:"),
    DAILY_PRICE_FIELD("Daily Price:"),
    STATUS_FIELD("Status:"),
    SAVE("Save"),
    DELETE("Delete"),
    CANCEL("Cancel"),
    INPUT_ERROR("Input Error"),
    ERROR_PREFIX("Error: "),
    DELETE_CAR_CONFIRM("Delete car?"),
    CONFIRM("Confirm"),
    YES_OPTION("YES"),
    NO_OPTION("NO"),
    FONT_ARIAL("Arial"),
    PRICE_FORMAT("%.2f"),
    IS_RENTED_CHECKBOX("Is Rented?"),
    EMPTY_STRING(""),
    EMPTY_MAKER("Maker field cannot be empty"),
    EMPTY_MODEL("Model field cannot be empty"),
    EMPTY_YEAR("Year field cannot be empty"),
    EMPTY_COLOR("Color field cannot be empty"),
    EMPTY_PLATE("License Plate field cannot be empty"),
    EMPTY_PRICE("Price field cannot be empty"),
    INVALID_YEAR("Year must be a valid number"),
    YEAR_OUT_OF_RANGE("Year must be between 1900 and %d"),
    INVALID_PRICE("Daily price must be a valid number"),
    PRICE_LESS_THAN_ZERO("Daily price must be greater than 0"),
    WIDTH_SIZE("800"),
    HEIGHT_SIZE("400"),
    CLICK_COUNT("2"),
    ZERO("0"),
    FONT_SIZE("12"),
    DIMENSION_WIDTH("90"),
    DIMENSION_HEIGHT("25"),
    DIALOG_WIDTH("350"),
    DIALOG_HEIGHT("350"),
    TEXT_FIELDS_COLUMNS("15"),
    PANEL_COLS("2"),
    PANEL_HGAP("5"),
    PANEL_VGAP("5"),
    MIN_CAR_YEAR("1900");

    private final String value;

    public int getNumberValue() {
        return Integer.parseInt(this.value);
    }
}
