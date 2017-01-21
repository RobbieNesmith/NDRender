import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class ObjLoader
{
	public static ShapeND readOBJ(String filename) throws FileNotFoundException
	{
		ShapeND theShape = new ShapeND();
		Scanner sc = new Scanner(new File(filename));
		while(sc.hasNextLine())
		{
			String theLine = sc.nextLine();
			if(theLine.startsWith("#") || theLine.trim().isEmpty())
			{
				continue;
			}
			else if(theLine.startsWith("v "))
			{
				String[] lineChunks = theLine.trim().split("\\s");
				VecND theVertex = VecND.generateOrigin(lineChunks.length - 1);
				for(int i = 1; i < lineChunks.length; i++)
				{
					theVertex.setCoord(i - 1,Float.parseFloat(lineChunks[i]));
				}
				theShape.addVertex(theVertex);
			}
			else if(theLine.startsWith("l "))
			{
				String[] lineChunks = theLine.trim().split("\\s");
				Edge theEdge = new Edge(Integer.parseInt(lineChunks[1]) - 1, Integer.parseInt(lineChunks[2]) - 1); // -1 because OBJ starts with vertex number 1
				theShape.addEdge(theEdge);
			}
			else if(theLine.startsWith("f "))
			{
				String[] lineChunks = theLine.substring(1).trim().split("\\s");
				for(int i = 0; i < lineChunks.length; i++)
				{
					int next = (i + 1) % lineChunks.length;
					String[] startChunks = lineChunks[i].split("/");
					String[] endChunks = lineChunks[next].split("/");
					Edge theEdge = new Edge(Integer.parseInt(startChunks[0]) - 1, Integer.parseInt(endChunks[0]) - 1);
					theShape.addEdge(theEdge);
				}
			}
		}
		sc.close();
		theShape.calcOrigin();
		return theShape;
	}
}
