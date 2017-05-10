import processing.core.PApplet;

public class SimplexRender extends PApplet
{
	float viewWidth = 3;
	float scale;
	ShapeND baseSimplex;
	ShapeND sierpinskiSimplex;
	
	public static void main(String[] args)
	{
		PApplet.main("SimplexRender");
	}
	public void settings()
	{
		size(1280,720);
	}
	public void setup()
	{
		baseSimplex = ShapeND.generateSimplex(4);
		sierpinskiSimplex = ShapeND.sierpinskiSimplex(baseSimplex,3);
		scale = width / viewWidth;
		strokeWeight(1/scale);
	}
	public void draw()
	{
		background(255);
		pushMatrix();
		translate(width/2,height/2);
		scale(scale,-scale);
		//baseSimplex.rotate(0, 3, (float) (Math.PI/480));
		ShapeND sierpinskiSimplexRot = sierpinskiSimplex.copy();
//		sierpinskiSimplexRot.rotate(0, 2, (float)Math.PI/6);
//		sierpinskiSimplexRot.rotate(1,2,-(float)Math.PI/6);
		VecND trans = VecND.generateOrigin(4);
		trans.setCoord(2,4);
		trans.setCoord(3,4);
		sierpinskiSimplexRot.translate(trans);
		sierpinskiSimplexRot = sierpinskiSimplexRot.stereographicDown();
		sierpinskiSimplexRot = sierpinskiSimplexRot.stereographicDown();
		ProcessingNDTools.drawShape2D(this,sierpinskiSimplexRot);
		popMatrix();
	}
	public void mouseDragged()
	{
		float x = (float) (Math.PI / 480) * (mouseX - pmouseX);
		float y = -(float) (Math.PI / 480) * (mouseY - pmouseY);
		sierpinskiSimplex.rotate(0,2,x);
		sierpinskiSimplex.rotate(2,3,y);
	}
}
