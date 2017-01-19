import processing.core.PApplet;

public class FiveDimDemo extends PApplet
{
	float viewWidth = 4;
	float scale;
	ShapeND cube;
	
	public static void main(String[] args)
	{
		PApplet.main("FiveDimDemo");
	}
	public void settings()
	{
		size(1280,720);
	}
	public void setup()
	{
		cube = ShapeND.generateMeasure(5);
		VecND translation = VecND.generateOrigin(5);
		translation.setCoord(0, -.5f);
		translation.setCoord(1, -.5f);
		translation.setCoord(2, -.5f);
		translation.setCoord(3, -.5f);
		translation.setCoord(4, -.5f);
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
		cube.rotate(0, 4, (float) (Math.PI/240));
		ShapeND cubeRot = cube.copy();
		cubeRot.rotate(0, 2, (float)Math.PI/6);
		cubeRot.rotate(1,2,-(float)Math.PI/6);
		cubeRot.rotate(0, 4, -(float)Math.PI/6);
		VecND trans = VecND.generateOrigin(5);
		trans.setCoord(2,3);
		trans.setCoord(3,2);
		trans.setCoord(4, 2);
		cubeRot.translate(trans);
		cubeRot = cubeRot.stereographicDown();
		cubeRot = cubeRot.stereographicDown();
		cubeRot = cubeRot.stereographicDown();
		ProcessingNDTools.drawShape2D(this,cubeRot);
		ProcessingNDTools.drawVertices2D(this, cubeRot, 16/scale);
		popMatrix();
	}
}
