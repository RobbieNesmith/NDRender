import java.util.ArrayList;

public class VecND
{
  private ArrayList<Float> coords;
  private int dim;
  
  public static VecND generateOrigin(int dimension)
  {
    ArrayList<Float> coords = new ArrayList<Float>();
    for(int i = 0; i < dimension; i++)
    {
      coords.add(0f);
    }
    return new VecND(coords);
  }
  
  public VecND(ArrayList<Float> coords)
  {
    this.dim = coords.size();
    this.coords = new ArrayList<Float>();
    for(int i = 0; i < dim; i++)
    {
      this.coords.add(coords.get(i));
    }
  }
  
  public int getDim()
  {
    return this.dim;
  }
  
  public ArrayList<Float> getCoords()
  {
    return this.coords;
  }
  
  public float getCoord(int num)
  {
    return this.coords.get(num);
  }
  
  public void setCoord(int num, float val)
  {
    this.coords.set(num,val);
  }
  
  public VecND copy()
  {
    return new VecND(this.coords);
  }
  
  public float getMagnitude()
  {
	  float r = 0;
	  for(int i = 0; i < this.getDim(); i++)
	  {
		  r += this.getCoord(i) * this.getCoord(i);
	  }
	  r = (float) Math.sqrt(r);
	  return r;
  }
  
  public float getMagnitude(int axis1, int axis2)
  {
	  return (float) Math.sqrt(Math.pow(this.getCoord(axis1), 2) + Math.pow(this.getCoord(axis2), 2));
  }
  
  public float getAngle(int axis1, int axis2) // on the axis1 axis2 plane
  {
	  return (float) Math.atan2(this.getCoord(axis2), this.getCoord(axis1));
  }
  
  public VecND add(VecND other)
  {
    int dimDiff = other.getDim() - this.getDim();
    if(dimDiff > 0)
    {
    	this.increaseDimension(dimDiff);
    }
    for(int i = 0; i < other.getDim(); i++)
    {
      this.setCoord(i,this.getCoord(i) + other.getCoord(i));
    }
    return this;
  }
  
  public VecND mult(float smult) // uniform scale
  {
    for(int i = 0; i < this.getDim(); i++)
    {
      this.setCoord(i,this.getCoord(i) * smult);
    }
    return this;
  }
  
  public VecND mult(VecND vmult) // non-uniform scale
  {
    for(int i = 0; i < this.getDim(); i++)
    {
      this.setCoord(i,this.getCoord(i) * vmult.getCoord(i));
    }
    return this;
  }
  
  public VecND rotate(int axis1, int axis2, float radians) // on the axis1 axis2 plane
  {
	  float r = this.getMagnitude(axis1,axis2);
	  float theta = this.getAngle(axis1, axis2);
	  theta += radians;
	  this.setCoord(axis1,(float)(r*Math.cos(theta)));
	  this.setCoord(axis2,(float)(r*Math.sin(theta)));
	  return this;
  }
  
  public VecND perspectiveDown(float focalLength)
  {
    ArrayList<Float> newCoords = new ArrayList<Float>();
    for(int i = 0; i < this.getDim() - 1; i++)
    {
      newCoords.add(this.getCoord(i) * focalLength / this.getCoord(this.getDim() - 1));
    }
    VecND other = new VecND(newCoords);
    return other;
  }
  
  public VecND stereographicDown()
  {
	  ArrayList<Float> newCoords = new ArrayList<Float>();
	    for(int i = 0; i < this.getDim() - 1; i++)
	    {
	      newCoords.add(this.getCoord(i)/ (1 - this.getCoord(this.getDim() - 1)));
	    }
	    VecND other = new VecND(newCoords);
	    return other;
  }
  
  public void increaseDimension(int numD)
  {
	for(int i = 0; i < numD; i++)
	{
	    this.coords.add(0f);
	}
  }
  
  public String toString()
  {
	  String res = "(";
	  for(int i = 0; i < this.getDim(); i++)
	  {
		  res += this.getCoord(i);
		  if(i < this.getDim() - 1)
		  {
			  res += ", ";
		  }
	  }
	  res += ")";
	  return res;
  }
}