import processing.core.PApplet;

public class P3DDemo extends PApplet
{
	float viewWidth = 4;
	float scale;
	ShapeND cube;
	
	public static void main(String[] args)
	{
		PApplet.main("P3DDemo");
	}
	public void settings()
	{
		size(1280,720,P3D);
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
		strokeWeight(4/scale);
		float fov = (float) (Math.PI/3.0);
		float cameraZ = (float) ((height/2.0) / Math.tan(fov/2.0));
		perspective(fov, (float)width/(float)height, 
		            cameraZ/10.0f, cameraZ*10.0f);
	}
	public void draw()
	{
		background(255);
		pushMatrix();
		translate(width/2,height/2);
		scale(scale,-scale);
		cube.rotate(0, 2, (float) (Math.PI/240));
		ShapeND cubeRot = cube.copy();
		cubeRot.rotate(1,2,-(float)Math.PI/6);
		VecND trans = VecND.generateOrigin(3);
		trans.setCoord(2,4);
		cubeRot.translate(trans);
		ProcessingNDTools.drawShape3D(this,cubeRot);
		ProcessingNDTools.drawVertices3D(this, cubeRot, 32/scale);
		popMatrix();
	}
}

