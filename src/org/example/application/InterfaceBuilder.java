package org.example.application;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class InterfaceBuilder {
	
	private Stage stage;
	
	private Scene scene;
	
	private Pane mainPane;
	
	private ComponentBuilder builder;
    
	public InterfaceBuilder(Stage stage) {
		this.stage = stage;
		mainPane = new HBox();
		scene = new Scene(mainPane);
		builder = new ComponentBuilder();
	}
	
	public void build() {
		buildFirstPane();
		buildSecondPane();
		buildThirdPane();
		buildFourthPane();
		buildFifthPane();
		configureStage();
	}
		
	private void buildFirstPane() {
		TextField field = builder.getTextField();
		Button add = builder.getButton("Add");
		ComboBox<String> items = builder.getComboBox();
		addToMainPane(field, add, items);
		configurePane(field, add, items);
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
	
	private void buildSecondPane() {
		Button apply = builder.getButton("Apply");
		Button exchange = builder.getButton("Exchange");
		TextField field = builder.getTextField();
		addToMainPane(field, apply, exchange);
		configurePane(field, apply, exchange);
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
	
	private void buildThirdPane() {
		TextField field = builder.getTextField();
		Button choose = builder.getButton("Choose");
		RadioButton one = builder.getRadioButton("1");
		RadioButton two = builder.getRadioButton("2");
		RadioButton three = builder.getRadioButton("3");
		builder.getToggleGroup(one, two, three);
		addToMainPane(field, choose, one, two, three);
		configurePane(field, choose, one, two, three);
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
	
	private void buildFourthPane() {
		TextField field = builder.getTextField();
		Button choose = builder.getButton("Choose");
		CheckBox one = builder.getCheckBox("1");
		CheckBox two = builder.getCheckBox("2");
		CheckBox three = builder.getCheckBox("3");
		addToMainPane(field, choose, one, two, three);
		configurePane(field, choose, one, two, three);
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
			button.setSelected(!button.isSelected());
		} else {
			showMessage("There is no such CheckBox");
		}
	}
	
	private void buildFifthPane() {
		TextField field = builder.getTextField();
		TableView<Cell> table = builder.getTableView();
		Button add = builder.getButton("Add");
		Button left = builder.getButton("Left");
		Button right = builder.getButton("Right");
		addToMainPane(field, add, left, right, table);
		configurePane(field, table, add, left, right);
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
			if(index > -1) {
				table.getItems().get(index).toLeft();
				table.refresh();
			}
		});
		right.setOnAction((e) -> {
		int index = table.getSelectionModel().getSelectedIndex();
			if(index > -1) {
				table.getItems().get(index).toRight();
				table.refresh();
			}
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
	
	private void addToMainPane(Node ...nodes) {
		Pane localPane = builder.getPane();
		localPane.getChildren().addAll(nodes);
		mainPane.getChildren().add(localPane);
	}
	
	private void configureStage() {
		stage.setScene(scene);
		stage.setTitle("Lab 1");
	}
	
	public void showMessage(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setContentText(message);
		alert.showAndWait();
	}
}