import processing.core.PApplet;

public class MultiDimensionalRenderer extends PApplet
{
	float focalLength = 4;
	float focal4d = 1f;
	float tDist = 4;
	float viewWidth = 3;
	float scale;
	ShapeND theShape;
	ShapeND otherShape;
	ShapeND proj;
	ShapeND proj2;
	public static void main(String[] args)
	{
		PApplet.main("MultiDimensionalRenderer");
	}
	public void settings()
	{
		size(1280,720);
	}
	public void setup()
	{
		scale = width / viewWidth;
		strokeWeight(1/scale);
		theShape = ShapeND.generateMeasure(4);
		
		VecND translation = VecND.generateOrigin(4);
		for(int i = 0; i < translation.getDim(); i++)
		{
			translation.setCoord(i, -.5f);
		}
		theShape.translate(translation);
		theShape.scale(2);
	}
	public void draw()
	{
		background(255);
		pushMatrix();
		translate(width/2,height/2);
		scale(scale,-scale);
		VecND trans = VecND.generateOrigin(4);
		trans.setCoord(2, 4);
		trans.setCoord(3, 1);
		theShape.rotate(0, 3, (float) (Math.PI/960));
		//theShape.rotate(1, 2, (float) (Math.PI/960));
		otherShape = theShape.copy();

		otherShape.rotate(0, 2, (float)(Math.PI/6));
		otherShape.rotate(1, 2, (float)(Math.PI/12));
		otherShape.translate(trans);
		proj = otherShape.stereographicDown().stereographicDown();
		renderShape(proj);
		popMatrix();
	}
	public void renderShape(ShapeND shape)
	{
		for(Edge edge : shape.getEdges())
		{
			VecND s = shape.getVertex(edge.getStart());
			VecND e = shape.getVertex(edge.getEnd());
			line(s.getCoord(0),s.getCoord(1),e.getCoord(0),e.getCoord(1));
		}
	}
}
