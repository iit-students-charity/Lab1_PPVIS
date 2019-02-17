package org.example.application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ComponentBuilder {
	
	public VBox getPane() {
		VBox defaultGridPane = new VBox();
		defaultGridPane.setMinSize(200.0, 150.0);
		return defaultGridPane;
	}
	
	public Button getButton(String nameOfButton) {
		Button defaultButton = new Button(nameOfButton);
		defaultButton.setMinSize(100.0, 45.0);
		defaultButton.setMaxSize(100.0, 45.0);
		defaultButton.setContentDisplay(ContentDisplay.CENTER);
		defaultButton.setTextFill(Color.GOLD);
		defaultButton.setFont(Font.font(16));
		String pathToImage = "E:\\Repository\\JavaWeb\\Lab1\\"
							 + "bin\\resources\\img\\eight.jpg";
		setImage(defaultButton, pathToImage);
		return defaultButton;
	}
	
	private void setImage(Button button, String pathToImage) {
		try(InputStream input = new FileInputStream(pathToImage)) {
			ImageView image = new ImageView(new Image(input));
			image.setFitWidth(100.0);
		    image.setFitHeight(45.0);
		    button.setGraphic(image);
		}catch(IOException e) {
			e.printStackTrace();
		}
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
		defaultTable.getColumns().add(firstColumn);
		defaultTable.getColumns().add(secondColumn);
		defaultTable.setMinSize(150.0, 50.0);
		return defaultTable;
	}
	
}