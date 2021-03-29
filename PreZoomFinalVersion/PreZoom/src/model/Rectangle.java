package model;

import javafx.scene.paint.Color;

public class Rectangle {
	
	double x = 120;
	double y = 70;
	double length = 120;
	double height = 70;
	Color color = Color.RED;

	
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
	public void setY(double y) {
		this.y = y;
	}
	public double getLength() {
		return length;
	}
	public double setLength(double length) {
		this.length = length;
		return length;
	}
	public double getHeight() {
		return height;
	}
	public double setHeight(double height) {
		this.height = height;
		return height;
	}

}
