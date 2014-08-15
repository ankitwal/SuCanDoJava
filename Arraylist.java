package firstCode;
/* We use class ArrayList, to be able to track changes over the course of recursive guessing
 * It is a list-type data structure, with each guess deep copying and preserving its own puzzle
 * If a perticular branch of recursive guessing failes, we can track changes all the way up.
 */
public class Arraylist 
{
public Arraylist next;
public int arr[][][];
	public Arraylist(int array[][][])
	{
		arr=new int[9][9][10];
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				for(int k=0;k<9;k++)
				{
					arr[i][j][k]=array[i][j][k];
				}
			}
		}
	}
	public Arraylist getnext()
	{
		return next;
	}
	public void setnext(int array[][][])
	{
		next=new Arraylist(array);
	}
	
}
