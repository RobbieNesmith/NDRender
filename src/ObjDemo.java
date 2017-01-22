import java.io.FileNotFoundException;

import processing.core.PApplet;

public class ObjDemo extends PApplet
{
	float viewWidth = 3;
	float scale;
	ShapeND suzeanne;
	
	public static void main(String[] args)
	{
		PApplet.main("ObjDemo");
	}
	
	public void settings()
	{
		size(1280,720);
		noSmooth();
	}
	public void setup()
	{
		suzeanne = ShapeND.generateMeasure(3);
		try
		{
			suzeanne = ObjLoader.readOBJ("data/Suzeanne.obj");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		scale = width / viewWidth;
		strokeWeight(1/scale);
	}
	public void draw()
	{
		background(255);
		pushMatrix();
		translate(width/2,height/2);
		scale(scale,-scale);
		ShapeND suzRot = suzeanne.copy();
		suzRot.rotate(1,2,-(float)Math.PI/6);
		VecND trans = VecND.generateOrigin(3);
		trans.setCoord(2,3);
		suzRot.translate(trans);
		suzRot = suzRot.stereographicDown();
		ProcessingNDTools.drawShape2D(this,suzRot);
		popMatrix();
	}
	public void mouseDragged()
	{
		float x = (float) (Math.PI / 480) * (mouseX - pmouseX);
		float y = (float) -(Math.PI / 480) * (mouseY - pmouseY);
		suzeanne.rotate(0,2,x);
		suzeanne.rotate(1,2,y);
	}
}
