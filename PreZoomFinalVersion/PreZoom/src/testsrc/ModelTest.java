package testsrc;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import model.Line;
import model.Oval;
import model.Rectangle;

public class ModelTest {
	
// Oval Test case //
	@Test
	public void testSetY() {
		
		Oval o = new Oval();
		double yC = o.setY(150);
		org.junit.Assert.assertEquals(150, yC);
	}
		
	@Test
	public void testSetX() {
		Oval o = new Oval();
		double xC = 150;
		xC = o.getX();
		org.junit.Assert.assertEquals(150, xC);
		
	}
	
// Rectangle Test Case //	
	
	@Test
	public void testSetHeight() {
		Rectangle r = new Rectangle();
		double rH = r.setHeight(70);
		org.junit.Assert.assertEquals(70, rH);
	}
	
	@Test
	public void testSetWidth() {
		
		Rectangle r = new Rectangle();
		double rW = r.setLength(120);
		org.junit.Assert.assertEquals(120, rW);
	}
	
// Test Case Line //
	
	@Test
	public void testStartPointX() {
		
		Line l = new Line();
		double lsX = l.setStartPointX(50);
		org.junit.Assert.assertEquals(50, lsX);
	}
	
	@Test
	public void testStartPointY() {
		
		Line l = new Line();
		double lsY = l.setStartPointY(50);
		org.junit.Assert.assertEquals(50, lsY);
	
	}
	
	
}

