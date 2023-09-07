package com.github.matthewdesouza.interestcalculator;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controller for the calculator.fxml file. Hungarian Notation is used to differentiate between
 * gui components and their respective values in {@link #calculateLoanPayment()}.
 */
public class LoanCalculatorController {

    @FXML
    private TextField tfInterestRate;

    @FXML
    private TextField tfNumberOfYears;

    @FXML
    private TextField tfLoanAmount;

    @FXML
    private TextField tfMonthlyPayment;

    @FXML
    private TextField tfTotalPayment;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnQuit;

    @FXML
    private Button btnCalculate;

    /**
     * Called on initialization of the scene. Event-driven to launch the respective method from a {@link Button} press.
     */
    @FXML
    public void initialize() {
        btnCalculate.setOnAction(x -> calculateLoanPayment());
        btnQuit.setOnAction(x -> System.exit(0));
        btnReset.setOnAction(x -> resetFields());
    }

    /**
     * Calculates the loan payment from supplied values in the {@link TextField}s. Catches
     * {@link NumberFormatException} if values are not correctly supplied. Provides an {@link Alert} should the
     * exception be caught.
     */
    private void calculateLoanPayment() {
        try {
            double interestRate = Double.parseDouble(tfInterestRate.getText()) / 100;
            double monthlyInterestRate = interestRate / 12;
            int numberOfYears = Integer.parseInt(tfNumberOfYears.getText());
            double loanAmount = Double.parseDouble(tfLoanAmount.getText());

            double monthlyPayment = (loanAmount * monthlyInterestRate) /
                    (1 - Math.pow(1 + monthlyInterestRate, -numberOfYears * 12));
            double totalPayment = monthlyPayment * numberOfYears * 12;

            tfMonthlyPayment.setText(String.format("$%.2f", monthlyPayment));
            tfTotalPayment.setText(String.format("$%.2f", totalPayment));
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid numbers for all fields.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Resets the gui fields.
     */
    private void resetFields() {
        tfInterestRate.clear();
        tfNumberOfYears.clear();
        tfLoanAmount.clear();
        tfMonthlyPayment.clear();
        tfTotalPayment.clear();
    }

    /**
     * Creates an {@link Alert} to be displayed. The alert is of the type specified.
     *
     * @param title      Title of the window launched.
     * @param content    Text to be displayed within the window.
     * @param ALERT_TYPE Type of alert to be displayed, as defined by the {@link javafx.scene.control.Alert.AlertType} enum.
     */
    private void showAlert(String title, String content, Alert.AlertType ALERT_TYPE) {
        Alert alert = new Alert(ALERT_TYPE);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
