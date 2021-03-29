package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Oval {
	
	double radius = 50;
	double x = 150;
	double y = 150;
	Color color = Color.GREEN;
	
	
	
	
	

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public double setY(double y) {
		this.y = y;
		return y;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

}
