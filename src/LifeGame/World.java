package LifeGame;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;




public class World extends JPanel implements Runnable{

	private final int rows;
	private final int cols;
	private boolean NextCells[][];
	private boolean CurrentCells[][];
	private boolean isChanged=false;
	private int SurroundAliveCells;
	//��ʼ��
	public World(int rows,int cols)
	{
		this.rows=rows;
		this.cols=cols;
		NextCells=new boolean[rows][cols];
		CurrentCells=new boolean[rows][cols];
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				NextCells[i][j]=false;
				CurrentCells[i][j]=false;
				
			}
		}
	}
	//��ȡ����
	public boolean GetNextCells(int row,int col) {
		return NextCells[row][col];
	}
	public boolean GetCurrentCells(int row,int col) {
		return CurrentCells[row][col];
	}
	public int GetSurroundAliveCells() {
		return SurroundAliveCells;
	}
	
	@Override
	public void run() {
		while(true)
		{
			synchronized(this)
			{
				while(isChanged)
				{
					try 
					{
						this.wait();
					} catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
				repaint();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for(int i=0;i<20;i++)
				{
					for(int j=0;j<20;j++)
					{
						CellsCheck(i,j);
					}
				}
				//������������
				boolean[][] temp=new boolean[rows][cols];
				CurrentCells=NextCells;
				NextCells=temp;
			}
		}
	}

//���Ƶ�ͼ
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (int i = 0; i < rows; i++) 
        {
            for (int j = 0; j < cols; j++) 
            {
            	if(CurrentCells[i][j])
            	{
            		g.fillRect(j * 20, i * 20, 20, 20);
            	}
            	else
            	{
                    g.drawRect(j * 20, i * 20, 20, 20);            		
            	}
            }
        }
	}
	//
//�ж���һ��ϸ��״̬
	private void CellsCheck(int row,int col)
	{
		SurroundAliveCells=0;
		//�ж��ܱ�ϸ��
		for(int r=row-1;r<=row+1;r++)
		{
			for(int c=col-1;c<=col+1;c++)
			{
				if(r<0||r>19||c<0||c>19||(r==row&&c==col))
				{
					continue;
				}
				else if(CurrentCells[r][c])
				{
					SurroundAliveCells++;
				}
			}
		}
		if(SurroundAliveCells==3)
		{
			NextCells[row][col]=true;
		}
		else if(SurroundAliveCells!=2)
		{
			NextCells[row][col]=false;
		}
		else
		{
			NextCells[row][col]=CurrentCells[row][col];
		}
	}
	//�������ͼ��
	public void RandomInitialize(int n)
	{
		isChanged=true;
		Random rand=new Random();
		int a=0;
		int x=0,y=0;
			synchronized(this)
			{
				//��ʼ��ǰ���ϸ��
				for(int i=0;i<rows;i++)
				{
					for(int j=0;j<cols;j++)
					{
						CurrentCells[i][j]=false;
					}
				}
				while(a<n)
				{
					x=rand.nextInt(20);
					y=rand.nextInt(20);
					if(!CurrentCells[y][x])
					{
						CurrentCells[y][x]=true;
						a++;
					}
				}
				isChanged=false;
				this.notifyAll();
			}
	}
}
