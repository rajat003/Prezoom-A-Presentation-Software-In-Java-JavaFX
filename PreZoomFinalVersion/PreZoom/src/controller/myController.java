package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.*;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Camera;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Oval;

public class myController implements Initializable {

    //Group root = new Group();
    Parent root1;
    Stage stagePresentationWindow;
    Scene scenePresentation;
    public int slidePointer = 0;
    ArrayList<AnchorPane> slidesData = new ArrayList<AnchorPane>();
    ObservableList<javafx.scene.Node> temp;
    ;
    
    //  Model Declaration //
    
    model.Rectangle mR = new model.Rectangle();
    Oval mO = new Oval();
    model.Line mL = new model.Line();
    model.TextArea mT = new model.TextArea();
    
    
    // VBox for side time line //
    @FXML
    private VBox vBoxTimeline;


    // Controller code for main window //
    @FXML
    public Button createButton;


    @FXML
    public Button deleteButton;

    /*
     * It will exit from the system.
     */
    @FXML
    public void onExitButtonClicked(ActionEvent event) throws IOException {
        
         Stage stage = (Stage) deleteButton.getScene().getWindow(); 
         	stage.close();
    }

    @FXML
    public Button openButton;

    @FXML
    public void onOpenClicked(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage secondStage = new Stage();
        fileChooser.showOpenDialog(secondStage);

        

    }

    @FXML
    private AnchorPane rootPane;
/*
 * 
 */
    @FXML
    public void onclick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/presentation.fxml"));
        //rootPane.getChildren().setAll(fxmlLoader);
        root1 = (Parent) fxmlLoader.load();
        Stage stagePresentationWindow = new Stage();
        stagePresentationWindow.setTitle("Presentation Window");
        // Scene scenePresentation = new Scene(root1);
        stagePresentationWindow.setScene(new
                Scene(root1,800,500));
       // stagePresentationWindow.setFullScreen(true);
        stagePresentationWindow.show();
        
        this.stagePresentationWindow = stagePresentationWindow;

    }


    // Controller code for Presentation Window //

    // variables for drag and drop features for shape
    // which represents the coordinates of x,y axis
    // TranslateX,Y are new (x,y) of of moved shape
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    // Id for main window
    @FXML
    public BorderPane presentationWindow;

    // id for main drawing area
    @FXML
    public AnchorPane canvas;
  
    // Menu Items // 
    
    @FXML
    private MenuItem fileOpenButton;

    @FXML
    private MenuItem fileSaveButton;
    
    @FXML
    private MenuItem zoomInButton;

    @FXML
    private MenuItem zoomOutButton;



    @FXML
    public Button rectangleButton;

    @FXML
    public Button ovalButton;

    @FXML
    public Button lineButton;

    @FXML
    public Button textButton;

    @FXML
    public ColorPicker colorPicker;

    @FXML
    public Button addState;
    
    
    // Text Field to change the shape //
    @FXML
    private TextField xCordTf;

    @FXML
    private TextField yCordTf;

    @FXML
    private TextField heightTf;

    @FXML
    private TextField widthTf;
    
    @FXML
    private TextField radiusTf;
    
    Rectangle rectangle;
    Circle circle;
    Line line;

    @FXML
    public void onColorPickerClicked(ActionEvent event) {

    }

    @FXML
    public Slider slider;
    

    @FXML
    private Button state1Button;

    @FXML
    void state1Clicked(ActionEvent event) {
        loadSlideData(0);

    }

    // Menu item presentation button //

    @FXML
    public MenuItem presentationButton;
    
    @FXML
    public void zoomInClicked(ActionEvent ev) {
		
				System.out.println("Here in zoom in");
				
				canvas.setOnScroll(event -> {
			        double delta_y = event.getDeltaY();
			        double zoom_fac = 1.05;

			        if(delta_y < 0) {
			            zoom_fac = 2.0 - zoom_fac;
			        }

			        Scale newScale = new Scale();
			        newScale.setPivotX(event.getX());
			        newScale.setPivotY(event.getY());
			        newScale.setX( canvas.getScaleX() * zoom_fac );
			        newScale.setY( canvas.getScaleY() * zoom_fac );

			        canvas.getTransforms().add(newScale);

			        event.consume();
			    });
		       
			   
    }

    @FXML
    void zoomOutClicked(ActionEvent event) {

    }

    @FXML
    void doZoom(MouseEvent event) {
    	
    	slider.setMax(800);
    	slider.setMin(-400);
    	slider.setPrefWidth(300d);
    	slider.setLayoutX(-150);
    	slider.setLayoutY(200);
    	canvas.translateZProperty().bind(slider.valueProperty());
    	
    }
    

    // create line method //
    @FXML
    public void createLineClicked(ActionEvent event) {

        Line line = new Line();
        line.setStartX(mL.getStartPointX());
        line.setStartY(mL.getStartPointY());
        line.setEndX(mL.getEndPointX());
        line.setEndY(mL.getEndPointY());
        line.setStroke(mL.getColor());
        line.setStrokeWidth(mL.getStroke());
        line.setCursor(Cursor.HAND);

        // Method to change the color for rectangle on mouse click //
        // Right click open menu for change color / delete the shape //

        ContextMenu cm = new ContextMenu();
        cm.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mEvent) {
                // TODO Auto-generated method stub
                if (mEvent.getButton() == MouseButton.SECONDARY) {
                    //System.out.println("right click check");
                    event.consume();
                }
            }
        });

        cm.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                ((MenuItem) event.getTarget()).getText();
            }
        });

        MenuItem deleteLi = new MenuItem("Delete");
        MenuItem ccLi = new MenuItem("Change Color");
        //MenuItem menuItem3 = new MenuItem("");

        cm.getItems().addAll(deleteLi, ccLi);


        line.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                // TODO Auto-generated method stub
                if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    cm.show(line, mouseEvent.getScreenX(), mouseEvent.getScreenY());
                }
            }
        });

        deleteLi.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                //if(event.getButton() == MouseButton.PRIMARY) {
                canvas.getChildren().remove(line);
                //}
            }
        });
        
        ccLi.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				line.setStroke(Color.ORANGE);
			}
		});
        

        line.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            
            Line c = (Line) (t.getSource());
            c.toFront();
        });
        line.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;

            Line c = (Line) (t.getSource());

            c.setStartX(c.getStartX() + offsetX);
            c.setStartY(c.getStartY() + offsetY);
            //c.setEndX(c.getEndX() + );
            //c.setEndY();

            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
        });


        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.25), circle);
        transition.setOnFinished((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                
            	//line.setStartX(line.getTranslateX() + line.getStartX()());
                //line.setStartY(line.getTranslateY() + line.getStartY()());
                line.setEndX(line.getTranslateX() + line.getEndX());
                line.setEndY(line.getTranslateY() + line.getEndY());
                line.setTranslateX(0);
                line.setTranslateY(0);
            }
        });


        canvas.getChildren().add(line);
        this.line = line;
    }

    // method to create a oval //
    @FXML
    public void createOvalClicked(ActionEvent event) {

        Circle circle = new Circle();
        circle.setRadius(mO.getRadius());
        circle.setCenterX(mO.getX());
        circle.setCenterY(mO.getY());
        circle.setFill(mO.getColor());

        circle.setCursor(Cursor.HAND);

        // Method to change the color for circle on mouse click //
        
        // Right click open menu for change color / delete the shape //

        ContextMenu cm = new ContextMenu();
        cm.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mEvent) {
                // TODO Auto-generated method stub
                if (mEvent.getButton() == MouseButton.SECONDARY) {
                    //System.out.println("right click check");
                    event.consume();
                }
            }
        });

        cm.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                ((MenuItem) event.getTarget()).getText();
            }
        });

        MenuItem deleteCirc = new MenuItem("Delete");
        MenuItem ccCirc = new MenuItem("Change Color");
        //MenuItem menuItem3 = new MenuItem("");

        cm.getItems().addAll(deleteCirc, ccCirc);


        circle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                // TODO Auto-generated method stub
                if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    cm.show(circle, mouseEvent.getScreenX(), mouseEvent.getScreenY());
                }
            }
        });

        deleteCirc.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                canvas.getChildren().remove(circle);
               
            }
        });
        
        ccCirc.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				circle.setFill(Color.BROWN);
			}
		});

        circle.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();

            System.out.println("C X " +t.getSceneX());
            System.out.println("C Y " +t.getSceneY());
            System.out.println("C R " +circle.getRadius());
            
            xCordTf.setText(String.valueOf(t.getSceneX()));
            yCordTf.setText(String.valueOf(t.getSceneY()));
            
            heightTf.setText("");
            widthTf.setText("");
            
            radiusTf.setText(String.valueOf(circle.getRadius()));
            
            Circle c = (Circle) (t.getSource());
            c.toFront();
        });
        circle.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;

            Circle c = (Circle) (t.getSource());

            c.setCenterX(c.getCenterX() + offsetX);
            c.setCenterY(c.getCenterY() + offsetY);

            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
        });


        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.25), circle);
        transition.setOnFinished((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                circle.setCenterX(circle.getTranslateX() + circle.getCenterX());
                circle.setCenterY(circle.getTranslateY() + circle.getCenterY());
                circle.setTranslateX(0);
                circle.setTranslateY(0);
            }
        });
        
        canvas.getChildren().add(circle);
        this.circle = circle;

    }


    // Method to create a rectangle //
    @FXML
    public Rectangle createRectangleClicked(ActionEvent event) {

        Rectangle rectangle = new Rectangle();

        //Set attributes for rectangle

        rectangle.setX(mR.getX());
        rectangle.setY(mR.getY());
        rectangle.setWidth(mR.getLength());
        rectangle.setHeight(mR.getHeight());
        rectangle.setFill(mR.getColor());


        // Right click open menu for change color / delete the shape //

        ContextMenu cm = new ContextMenu();
        cm.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mEvent) {
                // TODO Auto-generated method stub
                if (mEvent.getButton() == MouseButton.SECONDARY) {
                    //System.out.println("right click check");
                    event.consume();
                }
            }
        });

        cm.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                ((MenuItem) event.getTarget()).getText();
            }
        });

        MenuItem deleteRect = new MenuItem("Delete");
        MenuItem ccRect = new MenuItem("Change Color");
        //MenuItem menuItem3 = new MenuItem("");

        cm.getItems().addAll(deleteRect, ccRect);


        rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                // TODO Auto-generated method stub
                if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    cm.show(rectangle, mouseEvent.getScreenX(), mouseEvent.getScreenY());
                }
            }
        });

        deleteRect.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                //if(event.getButton() == MouseButton.PRIMARY) {
                canvas.getChildren().remove(rectangle);
                //}
            }
        });
        
        ccRect.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rectangle.setFill(Color.YELLOW);
			}
		});


        rectangle.setCursor(Cursor.HAND);
        rectangle.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            
            System.out.println("R X " +t.getSceneX());
            System.out.println("R Y " +t.getSceneY());
            System.out.println("R W " +rectangle.getWidth());
            System.out.println("R L " +rectangle.getHeight());
            
            
            
            xCordTf.setText(String.valueOf(t.getSceneX()));
            yCordTf.setText(String.valueOf(t.getSceneY()));
            
            heightTf.setText(String.valueOf(rectangle.getHeight()));
            widthTf.setText(String.valueOf(rectangle.getWidth()));

            radiusTf.setText("");
            
            Rectangle c = (Rectangle) (t.getSource());
            c.toFront();
        });
        rectangle.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;

            Rectangle c = (Rectangle) (t.getSource());

            c.setX(c.getX() + offsetX);
            c.setY(c.getY() + offsetY);

            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
        });


        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.25), rectangle);
        transition.setOnFinished((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                rectangle.setX(rectangle.getTranslateX() + rectangle.getX());
                rectangle.setY(rectangle.getTranslateY() + rectangle.getY());
                rectangle.setTranslateX(0);
                rectangle.setTranslateY(0);
            }
        });

        rectangle.setOnZoom(new EventHandler<ZoomEvent>() {
            @Override public void handle(ZoomEvent event) {
            	rectangle.setScaleX(rectangle.getScaleX() * event.getZoomFactor());
            	rectangle.setScaleY(rectangle.getScaleY() * event.getZoomFactor());
                System.out.println("Rectangle: Zoom event" +
                    ", inertia: " + event.isInertia() + 
                    ", direct: " + event.isDirect());
     
                event.consume();
            }
    });

        rectangle.setOnZoomStarted(new EventHandler<ZoomEvent>() {
            @Override public void handle(ZoomEvent event) {
                //inc(rectangle);
                System.out.println("Rectangle: Zoom event started");
                event.consume();
            }
    });

        rectangle.setOnZoomFinished(new EventHandler<ZoomEvent>() {
            @Override public void handle(ZoomEvent event) {
                //dec(rectangle);
                System.out.println("Rectangle: Zoom event finished");
                event.consume();
            }
    });

        canvas.getChildren().addAll(rectangle);
        
        this.rectangle = rectangle;
        return rectangle;
    }

    // method to add text //

    @FXML
    public void createTextClicked(ActionEvent event) {
        TextArea textArea = new TextArea();
       // textArea.minHeight(12);
       // textArea.minWidth(12);

        canvas.getChildren().add(textArea);
    }
    
    @FXML
   public void resizeShapeClicked(ActionEvent event) {

    	double x =Double.parseDouble(xCordTf.getText()) ;
    	double y = Double.parseDouble(yCordTf.getText());
    	
    	
    	// resize rectangle condition
    	if(heightTf.getText() != null) {
    		double h = Double.parseDouble(heightTf.getText());
        	double w = Double.parseDouble(widthTf.getText());
	    	rectangle.setX(x);
	        rectangle.setY(y);
	        rectangle.setWidth(w);
	        rectangle.setHeight(h);
    	}
    	// resize oval condition
    	else{
    	double r = Double.parseDouble(radiusTf.getText());
    	circle.setCenterX(x);
    	circle.setCenterY(y);
    	circle.setRadius(r);
    	}
    	
    }


    @FXML
    public void addNewState(ActionEvent event) {

        AnchorPane addState = new AnchorPane();
        
        ObservableList<javafx.scene.Node> temp = FXCollections.observableArrayList();
        ;
        temp = canvas.getChildren();

        System.out.println("Slidedata sie ! " + slidesData.size());
        addState.getChildren().setAll(temp);
      //  addState.getChildren().addAll(slidePointer, temp);
        //slidesData.add(slidePointer, addState);
        if (slidePointer == slidesData.size()) 
            slidesData.add(slidePointer, addState);
       
        else
            slidesData.set(slidePointer, addState);

        System.out.println("data added at " + slidePointer + " size " + temp.size() + " addstate size " + addState.getChildren().size() + " Canvas Size " + canvas.getChildren().size());
        canvas.getChildren().removeAll();
       // canvas.getChildren().setAll(slidesData.get(slidePointer).getChildren());
        System.out.println("New Pane Addded!");
        slidePointer = slidesData.size();
       


        for (int i = 0; i < slidesData.size(); i++) {
            System.out.println("Data in slideData size at " + i + " - " + slidesData.get(i).getChildren().size());
        }

        Button stateBtn = new Button("State " + (slidePointer + 1));
        System.out.println("event index added in fution i = !" + slidePointer);
//        stateBtn.setOnAction((event1 -> loadSlideData((slidePointer))));
        stateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int i = Integer.parseInt(((Button) event.getSource()).getText().split(" ")[1]);
                i--;
                loadSlideData(i);
            }
        });
        Separator ns = new Separator(Orientation.HORIZONTAL);
        ns.setStyle("");
        vBoxTimeline.getChildren().addAll(stateBtn, ns);

        //stagePresentationWindow.show();
        // delete state on right click //
        ContextMenu contextMenuState = new ContextMenu();
        contextMenuState.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mEvent) {
                // TODO Auto-generated method stub
                if (mEvent.getButton() == MouseButton.SECONDARY) {
                    //System.out.println("right click check");
                    event.consume();
                }
            }
        });

        contextMenuState.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                ((MenuItem) event.getTarget()).getText();
            }
        });
        MenuItem deleteState = new MenuItem("Delete");
        contextMenuState.getItems().addAll(deleteState);

        deleteState.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                addState.getChildren().clear();
                vBoxTimeline.getChildren().removeAll(stateBtn, ns);

            }
        });

        stateBtn.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent m) {
                // TODO Auto-generated method stub
                if (m.isSecondaryButtonDown()) {
                    contextMenuState.show(stateBtn, m.getScreenX(), m.getScreenY());
                }

            }
        });
    }

    // temp methods  ex//
    // Method to store the data of slide 
    // When moving from 1 slide to another slide 
    void loadSlideData(int i) {
        System.out.println("In method " + i);

        AnchorPane addState = new AnchorPane();
        
 //		label added for testing purpose //
//        Label text = new Label("Test for new pane");
//        text.setTextFill(Color.GREEN);
//        text.setStyle("-fx-background-color: black;");
//        text.setAlignment(Pos.CENTER);
        
        ObservableList<javafx.scene.Node> temp = FXCollections.observableArrayList();
        ;
        temp = canvas.getChildren();

        System.out.println("Slidedata sie ! " + slidesData.size());
        addState.getChildren().setAll(temp);

        if (slidePointer == slidesData.size())
            slidesData.add(slidePointer, addState);
        else
            slidesData.set(slidePointer, addState);

        for (int j = 0; j < slidesData.size(); j++) {
            System.out.println("Data in slideData size at " + j + " - " + slidesData.get(j).getChildren().size());
        }

        canvas.getChildren().removeAll();
        slidePointer = i;
        canvas.getChildren().setAll(slidesData.get(slidePointer).getChildren());
        System.out.println("i =" + i);
        System.out.println("data added at " + slidePointer + " size " + temp.size() + " addstate size " + slidesData.get(slidePointer).getChildren() + " Canvas Size " + canvas.getChildren().size());

//        addState = slidesData.get(i);
        System.out.println("slide data siZe " + slidesData.get(i).getChildren().size());
        System.out.println("c sise " + canvas.getChildren().size());


    }

    // Show Presentation Method//
    // pointer variable for slide presentation //
    int pointer = 0;

    @FXML
    void startPresentationClicked(ActionEvent event) {

        Stage presentationStage = new Stage();

        AnchorPane anchorPresentation = new AnchorPane();

        anchorPresentation.setPrefWidth(500);
        anchorPresentation.setPrefHeight(580);
        anchorPresentation.getChildren().addAll(slidesData.get(pointer).getChildren());

        Scene presentationScene = new Scene(anchorPresentation);

        presentationScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent ke) {

                AnchorPane addState = new AnchorPane();
                ObservableList<javafx.scene.Node> temp = FXCollections.observableArrayList();
                temp = anchorPresentation.getChildren();
                slidesData.get(pointer).getChildren().setAll(temp);
                if(ke.getCode() == KeyCode.RIGHT) {
                    pointer++;
                    System.out.println("Current pointer right " +pointer);
                    if(pointer < slidesData.size()) {
                    	
                    	FadeTransition ft = new FadeTransition(Duration.millis(3000), anchorPresentation);
                    	ft.setFromValue(0.0);
                    	ft.setToValue(1.0);
                    	ft.play();
                        anchorPresentation.getChildren().removeAll();
                        anchorPresentation.getChildren().setAll(slidesData.get(pointer).getChildren());
                    }else {
                        pointer--;
                    }
                }
                else if(ke.getCode() == KeyCode.LEFT) {
                    pointer--;
                    System.out.println("Current pointer left " +pointer);
                    if(pointer >=0 ) {
                   	

                    	FadeTransition ft = new FadeTransition(Duration.millis(3000), anchorPresentation);
                    	ft.setFromValue(0.0);
                    	ft.setToValue(1.0);
                    	ft.play();
                        anchorPresentation.getChildren().removeAll();
                        anchorPresentation.getChildren().setAll(slidesData.get(pointer).getChildren());
                    }else{
                        pointer++;
                    }

                }
                System.out.println("Key code capture " +ke.getCode());
            }
        });

        presentationStage.setScene(presentationScene);
        
        presentationStage.setTitle("Presentation Mode");

       // presentationStage.setFullScreen(true);
        presentationStage.show();

    }
    
    // Menu Items Save / Open File methods //
    @FXML
    void fileOpenButtonClicked(ActionEvent event) {

    }

    @FXML
    void fileSaveButtonClicked(ActionEvent event) {
    	System.out.println("in save...");
    	// Serialize the array list and write into file // 
    	
    	try{
    	    FileOutputStream writeData = new FileOutputStream("SlideData.ser");
    	    ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

    	    writeStream.writeObject(slidesData);
    	    writeStream.flush();
    	    writeStream.close();

    	}catch (IOException e) {
    	    e.printStackTrace();
    	}    	
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        state1Button.setOnAction(event -> loadSlideData(0));
    }

}