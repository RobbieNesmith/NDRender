public class Edge
{
  int start,end;
  public Edge(int start, int end)
  {
    this.start = start;
    this.end = end;
  }
  
  public int getStart()
  {
    return this.start;
  }
  
  public void setStart(int start)
  {
	  this.start = start;
  }
  
  public int getEnd()
  {
    return this.end;
  }
  
  public void setEnd(int end)
  {
	  this.end = end;
  }
  
  public boolean isTrivial()
  {
	  return this.getStart() == this.getEnd();
  }
  
  public boolean equals(Edge other)
  {
	  return this.getStart() == other.getStart() && this.getEnd() == other.getEnd();
  }
}