import processing.core.PApplet;

public class TwoDimDemo extends PApplet
{
	float viewWidth = 4;
	float scale;
	ShapeND square;
	
	public static void main(String[] args)
	{
		PApplet.main("TwoDimDemo");
	}
	public void settings()
	{
		size(1280,720);
	}
	public void setup()
	{
		square = ShapeND.generateMeasure(2);
		VecND translation = VecND.generateOrigin(2);
		translation.setCoord(0, -.5f);
		translation.setCoord(1, -.5f);
		square.translate(translation);
		scale = width / viewWidth;
		strokeWeight(1/scale);
	}
	public void draw()
	{
		background(255);
		pushMatrix();
		translate(width/2,height/2);
		scale(scale,-scale);
		square.rotate(0, 1, (float) (Math.PI/240));
		ProcessingNDTools.drawShape2D(this, square);
		ProcessingNDTools.drawVertices2D(this, square, 32/scale);
		popMatrix();
	}
}
