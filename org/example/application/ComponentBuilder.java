package org.example.application;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class ComponentBuilder {
	
	public VBox getPane() {
		VBox defaultGridPane = new VBox();
		defaultGridPane.setMinSize(200.0, 150.0);
		return defaultGridPane;
	}
	
	public Button getButton(String nameOfButton) {
		Button defaultButton = new Button(nameOfButton);
		defaultButton.setMinSize(75.0, 30.0);
		return defaultButton;
	}
	
	public TextField getTextField() {
		TextField defaultTextField = new TextField();
		defaultTextField.setMinSize(100.0, 30.0);
		return defaultTextField;
	}
	
	public ComboBox<String> getComboBox() {
		ComboBox<String> defaultComboBox = new ComboBox<>();
		defaultComboBox.setMinSize(150.0, 30.0);
		return defaultComboBox;
	}
	
	public RadioButton getRadioButton(String nameOfButton) {
		RadioButton defaultRadioButton = new RadioButton(nameOfButton);
		defaultRadioButton.setMinSize(25.0, 25.0);
		return defaultRadioButton;
	}
	
	public ToggleGroup getToggleGroup(RadioButton ...buttons) {
		ToggleGroup defaultToggleGroup = new ToggleGroup();
		for(RadioButton button : buttons) {
			button.setToggleGroup(defaultToggleGroup);
		}
		return defaultToggleGroup;
	}
	
	public CheckBox getCheckBox(String nameOfCheckBox) {
		CheckBox defaultCheckBox = new CheckBox(nameOfCheckBox);
		defaultCheckBox.setMinSize(25.0, 25.0);
		return defaultCheckBox;
	}
	
	public TableView<Cell> getTableView() {
		TableView<Cell> defaultTable = new TableView<>();
		TableColumn<Cell, String> firstColumn = new TableColumn<>("First");
		TableColumn<Cell, String> secondColumn = new TableColumn<>("Second");
		defaultTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		defaultTable.getColumns().addAll(firstColumn, secondColumn);
		defaultTable.setMinSize(150.0, 50.0);
		return defaultTable;
	}
}