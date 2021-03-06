package controller;

import java.util.Observer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
//import model.Edge;
import model.Graph;
import view.*;

public class Main extends Application {

	// model variable
	private Graph theGraph;			// the Graph
	// observer variable(s)
	private Observer currentView;
	private Observer addEdgeView;
	private Observer addVertexView;
	private Observer displayView;
	private Observer eulerView;
	// stage variables
	private BorderPane window;
	private MenuBar menuBar;
	// static variables 
	public static final int width = 800;
	public static final int height = 500;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// set stage title
		primaryStage.setTitle("Euler...Euler...");
		// create new window to put stuff in
		window = new BorderPane();
		// create new scene to put window in
		Scene scene = new Scene(window, width, height);
		// setup sub menus
		setupMenus();
		// set the menu bar to the top of the window
		window.setTop(menuBar);
		// create a new model
		theGraph = Graph.graph1();
		// create  view(s)
		addEdgeView = new AddEdgeView(theGraph);
		addVertexView = new AddVertexView(theGraph);
		displayView = new DisplayGraphView(theGraph);
		eulerView = new EulerView(theGraph);
		// set initial view
		setViewTo(eulerView);
		// set the stage scene
		primaryStage.setScene(scene);
		// show the stage
		primaryStage.show();
	}
	
	private void setupMenus() {
		// create menu items 
		MenuItem addEdgeMI = new MenuItem("Add Edge");
		MenuItem addVertexMI = new MenuItem("Add Vertex");
		MenuItem displayMI = new MenuItem("Display Graph");
		MenuItem eulerMI = new MenuItem("Find Euler Tour");
		MenuItem exitMI = new MenuItem("Exit");
		// create the menu
		Menu menu = new Menu("Options");
		// add menu item(s) to menu
		menu.getItems().addAll(addEdgeMI, addVertexMI, displayMI, eulerMI, exitMI); 
		// create menu bar
		menuBar = new MenuBar();
		// add menu to menu bar
		menuBar.getMenus().addAll(menu);
		// create menu listener
		MenuItemListener menuListener = new MenuItemListener();
		// add the menu listener to the menu item(s)
		addEdgeMI.setOnAction(menuListener);
		addVertexMI.setOnAction(menuListener);
		displayMI.setOnAction(menuListener);
		eulerMI.setOnAction(menuListener);
		exitMI.setOnAction(menuListener);
	}
	
	private void setViewTo(Observer newView) {
		// clear the window
		window.setCenter(null);
		// set current view to new view
		currentView = newView;
		// set the window to the new view
		window.setCenter((Node) currentView);
	}
	
	private class MenuItemListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			// create text for menu item text
			String text = ((MenuItem) event.getSource()).getText();
			// check which menu item was selected
			if (text.equals("Exit"))
				System.exit(0);
			else if (text.equals("Add Edge"))
				setViewTo(addEdgeView);
			else if (text.equals("Add Vertex"))
				setViewTo(addVertexView);
			else if (text.equals("Display Graph"))
				setViewTo(displayView);
			else if (text.equals("Find Euler Tour"))
				setViewTo(eulerView);
		}
		
	}
}
