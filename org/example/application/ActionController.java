package org.example.application;

import javafx.collections.ObservableList;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ActionController {
	
	public void showMessage(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	public void configurePane(TextField field, Button add, 
							  ComboBox<String> items) {
		add.setOnAction((e) -> {
			doAction(items, field.getText());
			field.clear();
		});
	}
	
	private void doAction(ComboBox<String> items, String value) {
		if(!items.getItems().contains(value)) {
		    items.getItems().add(value);
		} else {
			showMessage("Value " + value + " is already added");
		}
	}
	
	public void configurePane(TextField field, Button apply, Button exchange) {
		apply.setOnAction((e) -> {
			exchange.setText(field.getText());
			field.clear();
		});
		exchange.setOnAction((e) -> {
			String value = exchange.getText();
			exchange.setText(apply.getText());
			apply.setText(value);
			field.clear();
		});
	}
	
	public void configurePane(TextField field, Button choose, 
							  RadioButton ...buttons) {
		choose.setOnAction((e) -> {
			doAction(field.getText(), buttons);
			field.clear();
		});
	}
	
	private void doAction(String value, RadioButton ...buttons) {
		RadioButton button = (RadioButton) getLabeledByText(value, buttons);
		if(button != null) {
			button.setSelected(true);
		} else {
			showMessage("There is no such RadioButton");
		}
	}
	
	private Labeled getLabeledByText(String value, Labeled ...nodes) {
		Labeled result = null;
		for(Labeled node: nodes) {
			if(value.equals(node.getText())) {
				result = node;
				break;
			}
		}
		return result;
	}
	
	public void configurePane(TextField field, Button choose, 
							  CheckBox ...boxes) {
		choose.setOnAction((e) -> {
			doAction(field.getText(), boxes);
			field.clear();
		});
	}
	
	private void doAction(String value, CheckBox ...boxes) {
		CheckBox button = (CheckBox) getLabeledByText(value, boxes);
		if(button != null) {
			button.setSelected(true);
		} else {
			showMessage("There is no such CheckBox");
		}
	}
	
	public void configurePane(TextField field, TableView<Cell> table, 
							  Button add, Button left, Button right) {
		setCellFactory(table, 0, "left");
		setCellFactory(table, 1, "right");
		add.setOnAction((e) -> {
			addValue(field.getText(), table);
			field.clear();
		});
		left.setOnAction((e) -> {
	        int index = table.getSelectionModel().getSelectedIndex();
	        table.getItems().get(index).toLeft();
	        table.refresh();
		});
		right.setOnAction((e) -> {
			int index = table.getSelectionModel().getSelectedIndex();
	        table.getItems().get(index).toRight();
	        table.refresh();
		});
	}
	
	private void setCellFactory(TableView<Cell> table, int index, 
								String factory) {
		TableColumn<Cell, ?> column = table.getColumns().get(index);
		column.setCellValueFactory(new PropertyValueFactory<>(factory));
	}
	
	private void addValue(String value, TableView<Cell> table) {
		ObservableList<Cell> data = table.getItems();
		data.add(new Cell(value));
		table.setItems(data);
	}
}