package com.business.frontend;

import com.business.model.Car;
import com.business.service.CarService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.Objects;

import static com.business.enums.Constants.ADD_CAR_DIALOG;
import static com.business.enums.Constants.CANCEL;
import static com.business.enums.Constants.CLICK_COUNT;
import static com.business.enums.Constants.COLOR_FIELD;
import static com.business.enums.Constants.CONFIRM;
import static com.business.enums.Constants.DAILY_PRICE_FIELD;
import static com.business.enums.Constants.DELETE;
import static com.business.enums.Constants.DELETE_CAR_CONFIRM;
import static com.business.enums.Constants.DIALOG_HEIGHT;
import static com.business.enums.Constants.DIALOG_WIDTH;
import static com.business.enums.Constants.DIMENSION_HEIGHT;
import static com.business.enums.Constants.DIMENSION_WIDTH;
import static com.business.enums.Constants.EDIT_CAR_DIALOG;
import static com.business.enums.Constants.EMPTY_COLOR;
import static com.business.enums.Constants.EMPTY_MAKER;
import static com.business.enums.Constants.EMPTY_MODEL;
import static com.business.enums.Constants.EMPTY_PLATE;
import static com.business.enums.Constants.EMPTY_PRICE;
import static com.business.enums.Constants.EMPTY_STRING;
import static com.business.enums.Constants.EMPTY_YEAR;
import static com.business.enums.Constants.ERROR_PREFIX;
import static com.business.enums.Constants.FONT_ARIAL;
import static com.business.enums.Constants.FONT_SIZE;
import static com.business.enums.Constants.HEIGHT_SIZE;
import static com.business.enums.Constants.INPUT_ERROR;
import static com.business.enums.Constants.INVALID_PRICE;
import static com.business.enums.Constants.INVALID_YEAR;
import static com.business.enums.Constants.IS_RENTED_CHECKBOX;
import static com.business.enums.Constants.MAKER_FIELD;
import static com.business.enums.Constants.MIN_CAR_YEAR;
import static com.business.enums.Constants.MODEL_FIELD;
import static com.business.enums.Constants.NO_OPTION;
import static com.business.enums.Constants.PANEL_COLS;
import static com.business.enums.Constants.PANEL_HGAP;
import static com.business.enums.Constants.PANEL_VGAP;
import static com.business.enums.Constants.PLATE_FIELD;
import static com.business.enums.Constants.PRICE_FORMAT;
import static com.business.enums.Constants.PRICE_LESS_THAN_ZERO;
import static com.business.enums.Constants.SAVE;
import static com.business.enums.Constants.STATUS_FIELD;
import static com.business.enums.Constants.TABLE_COLOR;
import static com.business.enums.Constants.TABLE_ID;
import static com.business.enums.Constants.TABLE_MAKER;
import static com.business.enums.Constants.TABLE_MODEL;
import static com.business.enums.Constants.TABLE_PLATE;
import static com.business.enums.Constants.TABLE_PRICE;
import static com.business.enums.Constants.TABLE_RENTED;
import static com.business.enums.Constants.TABLE_YEAR;
import static com.business.enums.Constants.TEXT_FIELDS_COLUMNS;
import static com.business.enums.Constants.TITLE;
import static com.business.enums.Constants.WIDTH_SIZE;
import static com.business.enums.Constants.YEAR_FIELD;
import static com.business.enums.Constants.YEAR_OUT_OF_RANGE;
import static com.business.enums.Constants.YES_OPTION;
import static com.business.enums.Constants.ZERO;

public class CarRental extends JFrame {

    private final CarService carService;
    private final DefaultTableModel tableModel;
    private final JTable table;

    public CarRental() {
        this.carService = new CarService();

        setTitle(TITLE.getValue());
        setSize(WIDTH_SIZE.getNumberValue(), HEIGHT_SIZE.getNumberValue());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new String[]{
                TABLE_ID.getValue(), TABLE_MAKER.getValue(), TABLE_MODEL.getValue(), TABLE_YEAR.getValue(),
                TABLE_COLOR.getValue(), TABLE_PLATE.getValue(), TABLE_PRICE.getValue(), TABLE_RENTED.getValue()
        }, ZERO.getNumberValue()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        table = new JTable(tableModel);
        table.getColumnModel().getColumn(ZERO.getNumberValue()).setMinWidth(ZERO.getNumberValue());
        table.getColumnModel().getColumn(ZERO.getNumberValue()).setMaxWidth(ZERO.getNumberValue());
        table.getColumnModel().getColumn(ZERO.getNumberValue()).setPreferredWidth(ZERO.getNumberValue());
        table.setRowSelectionAllowed(true);
        table.setFocusable(false);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == CLICK_COUNT.getNumberValue()) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row >= ZERO.getNumberValue()) {
                        long carId = Long.parseLong(table.getValueAt(row, ZERO.getNumberValue()).toString());
                        Car car = carService.findById(carId);
                        if (car != null) {
                            openCarDialog(car);
                        }
                    }
                }
            }
        });

        loadCars();

        JButton btnAdd = new JButton(ADD_CAR_DIALOG.getValue());
        btnAdd.setFont(new Font(FONT_ARIAL.getValue(), Font.PLAIN, FONT_SIZE.getNumberValue()));
        btnAdd.setPreferredSize(new Dimension(DIMENSION_WIDTH.getNumberValue(), DIMENSION_HEIGHT.getNumberValue()));
        btnAdd.addActionListener(e -> openCarDialog(null));

        JPanel bottom = new JPanel();
        bottom.add(btnAdd);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    private void loadCars() {
        tableModel.setRowCount(ZERO.getNumberValue());
        List<Car> cars = carService.findAll();
        for (Car car : cars) {
            tableModel.addRow(new Object[]{
                    car.getId(),
                    car.getMaker(),
                    car.getModel(),
                    car.getYear(),
                    car.getColor(),
                    car.getLicensePlate(),
                    String.format(PRICE_FORMAT.getValue(), car.getDailyPrice()),
                    car.getIsRented() ? YES_OPTION.getValue() : NO_OPTION.getValue()
            });
        }
    }

    private void openCarDialog(Car car) {
        JDialog dialog = new JDialog(this, true);
        dialog.setTitle(car == null ? ADD_CAR_DIALOG.getValue() : EDIT_CAR_DIALOG.getValue());
        dialog.setSize(DIALOG_WIDTH.getNumberValue(), DIALOG_HEIGHT.getNumberValue());
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JTextField tfMaker = new JTextField(TEXT_FIELDS_COLUMNS.getNumberValue());
        JTextField tfModel = new JTextField(TEXT_FIELDS_COLUMNS.getNumberValue());
        JTextField tfYear = new JTextField(TEXT_FIELDS_COLUMNS.getNumberValue());
        JTextField tfColor = new JTextField(TEXT_FIELDS_COLUMNS.getNumberValue());
        JTextField tfPlate = new JTextField(TEXT_FIELDS_COLUMNS.getNumberValue());
        JTextField tfPrice = new JTextField(TEXT_FIELDS_COLUMNS.getNumberValue());
        JCheckBox cbRented = new JCheckBox(IS_RENTED_CHECKBOX.getValue());

        if (car != null) {
            tfMaker.setText(car.getMaker());
            tfModel.setText(car.getModel());
            tfYear.setText(car.getYear() == null ? EMPTY_STRING.getValue() : car.getYear().toString());
            tfColor.setText(car.getColor());
            tfPlate.setText(car.getLicensePlate());
            tfPrice.setText(car.getDailyPrice() == null ? EMPTY_STRING.getValue() :
                    String.format(PRICE_FORMAT.getValue(), car.getDailyPrice()));
            cbRented.setSelected(car.getIsRented());
        }

        JPanel form = new JPanel(new GridLayout(ZERO.getNumberValue(), PANEL_COLS.getNumberValue(),
                PANEL_HGAP.getNumberValue(), PANEL_VGAP.getNumberValue()));
        form.add(new JLabel(MAKER_FIELD.getValue()));
        form.add(tfMaker);
        form.add(new JLabel(MODEL_FIELD.getValue()));
        form.add(tfModel);
        form.add(new JLabel(YEAR_FIELD.getValue()));
        form.add(tfYear);
        form.add(new JLabel(COLOR_FIELD.getValue()));
        form.add(tfColor);
        form.add(new JLabel(PLATE_FIELD.getValue()));
        form.add(tfPlate);
        form.add(new JLabel(DAILY_PRICE_FIELD.getValue()));
        form.add(tfPrice);
        form.add(new JLabel(STATUS_FIELD.getValue()));
        form.add(cbRented);

        dialog.add(form, BorderLayout.CENTER);

        JButton btnSave = new JButton(SAVE.getValue());
        JButton btnDelete = new JButton(DELETE.getValue());
        JButton btnCancel = new JButton(CANCEL.getValue());

        if (car == null) btnDelete.setEnabled(false);

        btnSave.addActionListener(e -> {
            try {
                String maker = tfMaker.getText().trim().toUpperCase();
                String model = tfModel.getText().trim().toUpperCase();
                String yearText = tfYear.getText().trim();
                String color = tfColor.getText().trim().toUpperCase();
                String plate = tfPlate.getText().trim().toUpperCase();
                String priceText = tfPrice.getText().trim();

                boolean rented = cbRented.isSelected();

                if (maker.isEmpty()) {
                    throw new IllegalArgumentException(EMPTY_MAKER.getValue());
                } else if (model.isEmpty()) {
                    throw new IllegalArgumentException(EMPTY_MODEL.getValue());
                } else if (yearText.isEmpty()) {
                    throw new IllegalArgumentException(EMPTY_YEAR.getValue());
                } else if (color.isEmpty()) {
                    throw new IllegalArgumentException(EMPTY_COLOR.getValue());
                } else if (plate.isEmpty()) {
                    throw new IllegalArgumentException(EMPTY_PLATE.getValue());
                } else if (priceText.isEmpty()) {
                    throw new IllegalArgumentException(EMPTY_PRICE.getValue());
                }

                int year;
                try {
                    year = Integer.parseInt(yearText);
                    if (year < MIN_CAR_YEAR.getNumberValue() || year > Year.now().getValue()) {
                        throw new IllegalArgumentException(String.format(YEAR_OUT_OF_RANGE.getValue(), Year.now().getValue()));
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException(INVALID_YEAR.getValue());
                }

                BigDecimal price;
                try {
                    price = new BigDecimal(priceText);
                    if (price.compareTo(BigDecimal.ZERO) <= ZERO.getNumberValue()) {
                        throw new IllegalArgumentException(PRICE_LESS_THAN_ZERO.getValue());
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException(INVALID_PRICE.getValue());
                }

                if (car == null) {
                    Car newCar = new Car(null, maker, model, year, color, plate, price, rented);
                    carService.save(newCar);
                } else {
                    car.setMaker(maker);
                    car.setModel(model);
                    car.setYear(year);
                    car.setColor(color);
                    car.setLicensePlate(plate);
                    car.setDailyPrice(price);
                    car.setIsRented(rented);
                    carService.update(car);
                }

                loadCars();
                dialog.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog,
                        ERROR_PREFIX.getValue() + ex.getMessage(),
                        INPUT_ERROR.getValue(),
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnDelete.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(dialog, DELETE_CAR_CONFIRM.getValue(),
                    CONFIRM.getValue(), JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION) {

                carService.delete(Objects.requireNonNull(car).getId());
                loadCars();
                dialog.dispose();
            }
        });

        btnCancel.addActionListener(e -> dialog.dispose());

        JPanel buttons = new JPanel();
        buttons.add(btnSave);
        buttons.add(btnDelete);
        buttons.add(btnCancel);

        dialog.add(buttons, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}
