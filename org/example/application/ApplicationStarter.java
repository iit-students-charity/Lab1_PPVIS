package org.example.application;

import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationStarter extends Application{
	
	public static void main(String[] args) {
        Application.launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		InterfaceBuilder builder = new InterfaceBuilder(primaryStage);
		builder.build();
	}

}
