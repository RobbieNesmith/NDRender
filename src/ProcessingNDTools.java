import processing.core.PApplet;

public class ProcessingNDTools
{
	public static void drawShape2D(PApplet parent, ShapeND shape)
	{
		for(Edge edge : shape.getEdges())
		{
			VecND s = shape.getVertex(edge.getStart());
			VecND e = shape.getVertex(edge.getEnd());
			parent.line(s.getCoord(0),s.getCoord(1),e.getCoord(0),e.getCoord(1));
		}
	}
	
	public static void drawShape3D(PApplet parent,ShapeND shape)
	{
		for(Edge edge : shape.getEdges())
		{
			VecND s = shape.getVertex(edge.getStart());
			VecND e = shape.getVertex(edge.getEnd());
			parent.line(s.getCoord(0),s.getCoord(1),s.getCoord(2),e.getCoord(0),e.getCoord(1),e.getCoord(2));
		}
	}
	
	public static void drawVertices2D(PApplet parent, ShapeND shape, float size)
	{
		for(VecND vec : shape.getVertices())
		{
			parent.ellipse(vec.getCoord(0),vec.getCoord(1),size,size);
		}
	}
	
	public static void drawVertices3D(PApplet parent, ShapeND shape, float size)
	{
		for(VecND vec : shape.getVertices())
		{
			parent.pushMatrix();
			parent.translate(vec.getCoord(0), vec.getCoord(1), vec.getCoord(2));
			parent.ellipse(0,0,size,size);
			parent.popMatrix();
		}
	}
}
