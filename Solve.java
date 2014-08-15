package firstCode;

public class Solve {

	int puz[][][];int input[][];int result[][];
	
	
	
	public Solve()
	{
		puz=new int[9][9][10];
		result=new int[9][9];
	}
	public void tempassign()
	{
		
	//	input = new int[][] {{0,3,6,0,4,9,0,1,0},{0,8,2,0,1,0,0,0,6},{1,9,0,0,6,2,0,0,0},{0,6,0,2,0,0,1,0,0},{0,0,9,0,7,0,4,0,0},{0,0,3,0,0,1,0,2,0},{0,0,0,7,3,0,0,6,4},{6,0,0,0,5,0,9,3,0},{0,4,0,9,2,0,7,8,0}};
	//	input = new int[][] {{4,7,0,0,0,0,0,0,3},{0,6,0,2,7,0,0,9,0},{0,0,0,0,0,9,5,0,0},{9,0,0,0,0,0,0,0,0},{3,0,0,4,0,5,0,0,1},{0,0,0,0,0,0,0,0,6},{0,0,4,5,0,0,0,0,0},{0,9,0,0,2,8,0,4,0},{8,0,0,0,0,0,0,1,5}};
		input = new int[][] {{0,0,7,9,0,0,0,1,0},{0,0,0,2,0,0,0,6,0},{2,6,3,0,4,7,0,0,0},{0,0,0,0,0,0,3,5,0},{5,0,0,0,0,0,0,0,1},{0,2,4,0,0,0,0,0,0},{0,0,0,3,7,0,1,9,5},{0,4,0,0,0,6,0,0,0},{0,7,0,0,0,2,6,0,0}};
		for(int i=0;i<9;i++)
			{
				for(int j=0;j<9;j++)
				{
					puz[i][j][0]=input[i][j];
				for(int k=1;k<10;k++)
				{
					puz[i][j][k]=0;
				}
			}
		
	}
	}
	public void displayResult()
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{ System.out.print(result[i][j]+"  ");
				
			}System.out.println();
		}
	}
	public void display(int  arr[][][])
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{ System.out.print(arr[i][j][0]+"  ");
				
			}System.out.println();
		}
	}
	//Function assigns possibilities for empty cells, returns true, if a minimum of one cell is resolved
	public boolean assignpos(int arr[][][])
	{
		boolean madechange=false;
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				if(arr[i][j][0]==0)
				{
				int count=0;int val=0;
				for(int k=1;k<10;k++)
					{
					arr[i][j][k]=0;
					if(checkrow(k,i,arr) && checkcol(k,j,arr) && checkbox(k,i,j,arr))
					{count++;val=k;
					arr[i][j][k]=k;
					}
					}
			
				if(count==1){arr[i][j][0]=val;madechange=true;
				
				}
				}
				}
			}
	
		return madechange;
	}
	//following three functions check row, col and 3x3 box for presences of numbers
	public boolean checkrow(int k,int i,int arr[][][])
	{	boolean notpresent=true;
		for(int x=0;x<9;x++)
		{
			if(arr[i][x][0]==k){notpresent=false; return notpresent;}
		}
		return notpresent;
	}
	
	public boolean checkcol(int k,int j,int arr[][][])
	{
		boolean notpresent=true;
		for(int x=0;x<9;x++)
		{
			if(arr[x][j][0]==k){notpresent=false;return notpresent;}
		}
		
		return notpresent;
	}
	public boolean checkbox(int k, int i, int j,int arr[][][])
	{
		int startx=i-(i%3);
		int starty=j-(j%3);
		boolean notpresent=true;
		int countx=0;int county=0;
		while(countx<3)
		{
			countx++;county=0;starty=j-(j%3);
			while(county<3)
			{	
				county++;
				
				if(arr[startx][starty][0]==k)
				{notpresent=false;return notpresent;}
				
				starty++;
			}
			startx++;
			}
	
		
		return notpresent;
		
	}
	//Checks if Sudoku has been fully solved
	public boolean check(int arr[][][])
	{ boolean flag=true;
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				if(arr[i][j][0]==0){flag=false;break;}
			}
		}
		if(flag)
		{// if solved saving result to result arraybu
			for(int i=0;i<9;i++)
			{
				for(int j=0;j<9;j++)
				{
					result[i][j]=arr[i][j][0];
				}
			}
		}
		return flag;
		
	}
	//function for Solving with out guessing.
	public void solver(int arr[][][])
	{
		
		while(assignpos(arr));
		
	}
	//REcursive function of guessing then solving,
	public boolean guess(Arraylist obj)
	{	
		int count;//count is used to find cells with lowest number of possibilities 
		
		solver(obj.arr);// Resolving puzzle
		
		if(arrayisgood(obj.arr))// checks if the puzzle is still resolvable or needs to be backtracked
		{
			
			if(!check(obj.arr))
			{
			for(int i=2;i<=9;i++)
			{//cycles from cell with 2 to all 9 possibilities to ensure, lower possibility cells are guessed first
				for(int j=0;j<9;j++)
				{
					for(int k=0;k<9;k++)
					{	
						if(obj.arr[j][k][0]==0)
						{	count=0;
							for(int z=1;z<=9;z++)
							{
								if(obj.arr[j][k][z]!=0)
								{
									count++;if(count>i){break;}
									
								}
							}	
							if(count<=i)// check if cell has current limit of possibilities
								{
								
								for(int a=1;a<=9;a++)
								{
										
										if(obj.arr[j][k][a]==a){
											
											obj.arr[j][k][0]=a;
											obj.setnext(obj.arr);
											
											if(guess(obj.getnext())){return true;}
											obj.arr[j][k][0]=0;//guess was wrong, reverting changes
											//loop will guess again
											
								}
											
							 }return false;// if it guessed all for one cell and none worked, previous guess was wrong, hence return.
								}
						}
					}	
				}
			}
			}
			return true;	//control will only get here if the puzzle is solved
		}
		else
		{
			//if puzzle is un-resolvable no point guessing, last guess must have been incorrect.
			
			return false;
		}
	}
	//Checks if puzzle in current state is solvable, no empty cell should have zero possibilities
	public boolean arrayisgood(int arr[][][])
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				if(arr[i][j][0]==0)
				{	int sum=0;
					for(int k=1;k<10;k++)
					{
						sum=sum+arr[i][j][k];
					}
					if(sum==0){return false;}
				}	
			}
		}
	return true;
	}
	
	public boolean checkduplicates(int arr[][][])
	{
		boolean flag=false;
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				if(arr[i][j][0]!=0)
				{
					int temp=arr[i][j][0];
					//checking row
					for(int col=(j+1);col<9;col++)
					{
						if(arr[i][col][0]==temp)
							{
								flag=true; break;
							}
					}
					if(flag==true){break;}
					//checking col
					for(int row=(i+1);row<9;row++)
					{
						if(arr[row][j][0]==temp){flag=true; break;}
					}if(flag==true){break;}
					//checking 3x3 box
					int endrow=i-(i%3)+2;
					int endcol=j-(j%3)+2;
					int startrow=endrow-2;
					int startcol=endcol-2;
					
					while(startrow<=endrow)
					{
						while(startcol<=endcol)
						{
							if(startrow!=i && startrow!=j && arr[startrow][startcol][0]==temp)
							{flag=true; break;}
							startcol++;
						}startcol-=2;startrow++;
					}
				}
			}if(flag==true) {break;}
		}
		return flag;
	}
	
	public static void main(String[] args) 
	{
	Solve ob=new Solve();
	ob.tempassign();

	if(!ob.checkduplicates(ob.puz))
	{
	Arraylist obj=new Arraylist(ob.puz);
	ob.guess(obj);
	
	
	ob.displayResult();
	
	}
	else{
		System.out.println("input has duplicates..");
	}
	}
	

}
