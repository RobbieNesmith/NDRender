import processing.core.PApplet;

public class StereographicDemo extends PApplet
{
	float viewWidth = 2;
	float scale;
	ShapeND cube;
	
	public static void main(String[] args)
	{
		PApplet.main("StereographicDemo");
	}
	public void settings()
	{
		size(1280,720);
	}
	public void setup()
	{
		cube = ShapeND.generateMeasure(3);
		VecND translation = VecND.generateOrigin(3);
		translation.setCoord(0, -.5f);
		translation.setCoord(1, -.5f);
		translation.setCoord(2, -.5f);
		cube.translate(translation);
		scale = width / viewWidth;
		strokeWeight(1/scale);
	}
	public void draw()
	{
		background(255);
		pushMatrix();
		translate(width/2,height/2);
		scale(scale,-scale);
		//cube.rotate(0, 2, (float) (Math.PI/240));
		ShapeND cubeRot = cube.copy();
		cubeRot.rotate(1,2,-(float)Math.PI/6);
		VecND trans = VecND.generateOrigin(3);
		trans.setCoord(2,4);
		cubeRot.translate(trans);
		cubeRot = cubeRot.stereographicDown();
		ProcessingNDTools.drawShape2D(this,cubeRot);
		ProcessingNDTools.drawVertices2D(this, cubeRot, 32/scale);
		popMatrix();
	}
	public void mouseDragged()
	{
		float x = (float) (Math.PI / 480) * (mouseX - pmouseX);
		float y = -(float) (Math.PI / 480) * (mouseY - pmouseY);
		cube.rotate(0,2,x);
		cube.rotate(1,2,y);
	}
}
