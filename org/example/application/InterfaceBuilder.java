package org.example.application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class InterfaceBuilder {
	
	private Stage stage;
	
	private Scene scene;
	
	private Pane mainPane;
	
	private ComponentBuilder builder;
	
	private ActionController controller;
    
	public InterfaceBuilder(Stage stage) {
		this.stage = stage;
		mainPane = new HBox();
		scene = new Scene(mainPane);
		builder = new ComponentBuilder();
		controller = new ActionController();
	}
	
	public void build() {
		buildFirstPane();
		buildSecondPane();
		buildThirdPane();
		buildFourthPane();
		buildFifthPane();
		configureStage();
	}
	
	private void configureStage() {
		stage.setScene(scene);
		stage.setTitle("Lab 1");
        stage.setWidth(1100);
        stage.setHeight(600);
        stage.show();
	}
	
	private void buildFirstPane() {
		TextField field = builder.getTextField();
		Button add = builder.getButton("Add");
		ComboBox<String> items = builder.getComboBox();
		Pane localPane = builder.getGridPane();
		localPane.getChildren().addAll(field, add, items);
		mainPane.getChildren().addAll(localPane);
		controller.configurePane(field, add, items);
	}
	
	private void buildSecondPane() {
		Button apply = builder.getButton("Apply");
		Button exchange = builder.getButton("Exchange");
		TextField field = builder.getTextField();
		Pane localPane = builder.getGridPane();
		localPane.getChildren().addAll(field, apply, exchange);
		mainPane.getChildren().addAll(localPane);
		controller.configurePane(field, apply, exchange);
	}
	
	private void buildThirdPane() {
		TextField field = builder.getTextField();
		Button choose = builder.getButton("Choose");
		RadioButton one = builder.getRadioButton("1");
		RadioButton two = builder.getRadioButton("2");
		RadioButton three = builder.getRadioButton("3");
		ToggleGroup group = builder.getToggleGroup(one, two, three);
		Pane localPane = builder.getGridPane();
		localPane.getChildren().addAll(field, choose, one, two, three);
		mainPane.getChildren().addAll(localPane);
		controller.configurePane(field, choose, one, two, three);
	}
	
	private void buildFourthPane() {
		TextField field = builder.getTextField();
		Button choose = builder.getButton("Choose");
		CheckBox one = builder.getCheckBox("1");
		CheckBox two = builder.getCheckBox("2");
		CheckBox three = builder.getCheckBox("3");
		Pane localPane = builder.getGridPane();
		localPane.getChildren().addAll(field, choose, one, two, three);
		mainPane.getChildren().addAll(localPane);
		controller.configurePane(field, choose, one, two, three);
	}
	
	private void buildFifthPane() {
		TextField field = builder.getTextField();
		TableView<Cell> table = builder.getTableView();
		Button add = builder.getButton("Add");
		Button left = builder.getButton("Left");
		Button right = builder.getButton("Right");
		Pane localPane = builder.getGridPane();
		localPane.getChildren().addAll(field, add, left, right, table);
		mainPane.getChildren().addAll(localPane);
		controller.configurePane(field, table, add, left, right);
	}
	
}
