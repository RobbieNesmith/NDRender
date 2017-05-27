import processing.core.PApplet;

public class VRDemo extends PApplet
{
	float viewWidth = 3;
	float eyeRot = PI/64;
	float scale;
	ShapeND cubeLeft;
	ShapeND cubeRight;
	
	public static void main(String[] args)
	{
		PApplet.main("VRDemo");
	}
	public void settings()
	{
		//size(1280,720);
		fullScreen();
	}
	public void setup()
	{
		cubeLeft = ShapeND.generateMeasure(4);
		cubeRight = ShapeND.generateMeasure(4);
		VecND translation = VecND.generateOrigin(4);
		translation.setCoord(0, -.5f);
		translation.setCoord(1, -.5f);
		translation.setCoord(2, -.5f);
		translation.setCoord(3, -.5f);
		cubeLeft.translate(translation);
		cubeRight.translate(translation);
		scale = width / viewWidth;
		strokeWeight(2/scale);
	}
	public void draw()
	{
		background(255);
		pushMatrix();
		translate(width/2,height/2);
		scale(scale,-scale);
		//cube.rotate(0, 3, (float) (Math.PI/480));
		ShapeND cubeLeftRot = cubeLeft.copy();
		ShapeND cubeRightRot = cubeRight.copy();
		cubeLeftRot.rotate(0, 2, (float)Math.PI/6);
		cubeLeftRot.rotate(1,2,-(float)Math.PI/6);
		cubeRightRot.rotate(0, 2, (float)Math.PI/6);
		cubeRightRot.rotate(1,2,-(float)Math.PI/6);
		cubeLeftRot.rotate(0,1,-eyeRot);
		cubeRightRot.rotate(0,1,eyeRot);
		VecND trans = VecND.generateOrigin(4);
		trans.setCoord(2,4);
		trans.setCoord(3,3);
		cubeLeftRot.translate(trans);
		cubeRightRot.translate(trans);
		cubeLeftRot = cubeLeftRot.stereographicDown();
		cubeLeftRot = cubeLeftRot.stereographicDown();
		cubeRightRot = cubeRightRot.stereographicDown();
		cubeRightRot = cubeRightRot.stereographicDown();
		trans = VecND.generateOrigin(2);
		trans.setCoord(0, viewWidth / 4);
		cubeRightRot.translate(trans);
		trans.mult(-1);
		cubeLeftRot.translate(trans);
		ProcessingNDTools.drawShape2D(this,cubeLeftRot);
		ProcessingNDTools.drawShape2D(this,cubeRightRot);
		//ProcessingNDTools.drawVertices2D(this, cubeLeftRot, 32/scale);
		popMatrix();
	}
	public void mouseDragged()
	{
		float x = (float) (Math.PI / 480) * (mouseX - pmouseX);
		float y = -(float) (Math.PI / 480) * (mouseY - pmouseY);
		cubeLeft.rotate(0,3,x);
		cubeLeft.rotate(1,3,y);
		cubeRight.rotate(0,3,x);
		cubeRight.rotate(1,3,y);
	}
}
