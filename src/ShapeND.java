import java.util.ArrayList;

public class ShapeND
{
  private ArrayList<VecND> vertices;
  private VecND origin;
  private ArrayList<Edge> edges;
  
  public static ShapeND generateMeasure(int dimension)
  {
	  ArrayList<VecND> vertex = new ArrayList<VecND>();
	  vertex.add(VecND.generateOrigin(dimension));
	  ShapeND measure = new ShapeND(vertex,new ArrayList<Edge>());
	  for(int i = 0; i < dimension; i++)
	  {
		  VecND translation = VecND.generateOrigin(i + 1);
		  translation.setCoord(i, 1);
		  measure.extrude(translation);
	  }
	  measure.calcOrigin();
	  return measure;
  }
  
  public ShapeND()
  {
	  this.vertices = new ArrayList<VecND>();
	  this.edges = new ArrayList<Edge>();
  }
  
  public ShapeND(ArrayList<VecND> vertices, ArrayList<Edge> edges)
  {
    this.vertices = new ArrayList<VecND>();
    this.edges = new ArrayList<Edge>();
    for(int i = 0; i < vertices.size(); i++)
    {
      this.vertices.add(vertices.get(i).copy());
    }
    for(int i = 0; i < edges.size(); i++)
    {
      this.edges.add(edges.get(i));
    }
    this.calcOrigin();
  }
  
  public ArrayList<VecND> getVertices()
  {
    return this.vertices;
  }
  
  public VecND getVertex(int id)
  {
    return this.vertices.get(id);
  }
  
  public void setVertex(int id, VecND vertex)
  {
    this.vertices.set(id,vertex);
  }
  
  public void addVertex(VecND vertex)
  {
	  this.vertices.add(vertex);
  }
  
  public int getNumVerts()
  {
    return this.vertices.size();
  }
  
  public ArrayList<Edge> getEdges()
  {
    return this.edges;
  }
  
  public Edge getEdge(int id)
  {
	  return this.edges.get(id);
  }
  
  public void setEdge(int id, Edge edge)
  {
	  this.edges.set(id, edge);
  }
  
  public void addEdge(Edge edge)
  {
	  this.edges.add(edge);
  }
  
  public void addEdge(int v1, int v2)
  {
	  this.edges.add(new Edge(v1, v2));
  }
  
  public int getNumEdges()
  {
    return this.edges.size();
  }
  
  public int getDim()
  {
    return this.getVertex(0).getDim();
  }
  
  public VecND getOrigin()
  {
    return this.origin;
  }
  
  public VecND calcOrigin()
  {
    this.origin = VecND.generateOrigin(this.getDim()); // set up origin with all 0s
    for(int i = 0; i < this.getNumVerts(); i++)
    {
      origin.add(this.getVertex(i));
    }
    if(this.getNumVerts() > 0)
    {
      origin.mult(1f/this.getNumVerts());
    }
    return this.getOrigin();
  }
  
  public ShapeND translate(VecND translation)
  {
    for(int i = 0; i < this.getNumVerts(); i++)
    {
      this.setVertex(i,this.getVertex(i).add(translation));
    }
    return this;
  }
  
  public ShapeND scale(float scale) // uniform
  {
	  for(int i = 0; i < this.getNumVerts(); i++)
	  {
		  this.setVertex(i,this.getVertex(i).mult(scale));
	  }
	  return this;
  }
  
  public ShapeND scale(VecND sVec) // non-uniform
  {
	  for(int i = 0; i < this.getNumVerts(); i++)
	  {
		  this.setVertex(i,this.getVertex(i).mult(sVec));
	  }
	  return this;
  }
  
  public ShapeND rotate(int axis1, int axis2, float radians)
  {
	  for(int i = 0; i < this.getNumVerts(); i++)
	  {
		  this.setVertex(i,this.getVertex(i).rotate(axis1,axis2,radians));
	  }
	  return this;
  }
  
  public ShapeND perspectiveDown(float focalLength)
  {
    ShapeND other = new ShapeND(this.getVertices(),this.getEdges());
    for(int i = 0; i < other.getNumVerts(); i++)
    {
      other.setVertex(i,this.getVertex(i).perspectiveDown(focalLength));
    }
    return other;
  }
  
  public ShapeND stereographicDown()
  {
	  ShapeND other = new ShapeND(this.getVertices(),this.getEdges());
	  for(int i = 0; i < other.getNumVerts(); i++)
	  {
	      other.setVertex(i,this.getVertex(i).stereographicDown());
      }
	  return other;
  }
  
  public void increaseDimension(int numD)
  {
	  for(VecND vertex: this.getVertices())
	  {
		  vertex.increaseDimension(numD);
	  }
  }
  
  public ShapeND join(ShapeND other)
  {
	  int numVerts = this.getNumVerts();
	  for(int i = 0; i < other.getNumVerts(); i++)
	  {
		  this.vertices.add(other.getVertex(i).copy());
	  }
	  for(Edge e : other.getEdges())
	  {
		  this.edges.add(new Edge(e.getStart() + numVerts,e.getEnd() + numVerts));
	  }
	  return this;
  }
  
  public ShapeND extrude(VecND translation)
  {
	  int numVerts = this.getNumVerts();
	  
	  ShapeND extruded = this.copy().translate(translation);
	  this.join(extruded);
	  for(int i = 0; i < numVerts; i++)
	  {
		  this.edges.add(new Edge(i, i+numVerts));
	  }
	  return this;
  }
  
  public ShapeND copy()
  {
    return new ShapeND(this.getVertices(), this.getEdges());
  }
  
  public String toString()
  {
	  String res = "Number of vertices: " + this.getNumVerts() + "\n";
	  
	  for(VecND vert : this.getVertices())
	  {
		  res += vert + "\n";
	  }
	  
	  res += "Number of edges: " + this.getNumEdges() + "\n";
	  
	  for(Edge edge : this.getEdges())
	  {
		  res += this.getVertex(edge.getStart()) + " --> " + this.getVertex(edge.getEnd()) + "\n";
	  }
	  
	  return res;
  }
}