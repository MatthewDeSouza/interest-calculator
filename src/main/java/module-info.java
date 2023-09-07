module com.github.matthewdesouza.interestcalculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.github.matthewdesouza.interestcalculator to javafx.fxml;
    exports com.github.matthewdesouza.interestcalculator;
}