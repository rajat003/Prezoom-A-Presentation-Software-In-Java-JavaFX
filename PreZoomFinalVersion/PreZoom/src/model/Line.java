package model;

import javafx.scene.paint.Color;

public class Line {
	
	double startPointX = 50;
	double startPointY = 50;
	double endPointX = 200;
	double endPointY = 50;
	Color color = Color.BLUE;
	double stroke = 5;
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double getStroke() {
		return stroke;
	}
	public void setStroke(double stroke) {
		this.stroke = stroke;
	}
	
	
	public double getStartPointX() {
		return startPointX;
	}
	public double setStartPointX(double startPointX) {
		this.startPointX = startPointX;
		return startPointX;
	}
	public double getStartPointY() {
		return startPointY;
	}
	public double setStartPointY(double startPointY) {
		this.startPointY = startPointY;
		return startPointY;
	}
	public double getEndPointX() {
		return endPointX;
	}
	public void setEndPointX(double endPointX) {
		this.endPointX = endPointX;
	}
	public double getEndPointY() {
		return endPointY;
	}
	public void setEndPointY(double endPointY) {
		this.endPointY = endPointY;
	}
	
	
	
	

}
