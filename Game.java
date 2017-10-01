
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.io.*;
import java.util.*;
import java.net.HttpURLConnection;
import java.net.URL;


class Chips
{
	int number[] = new int[]{9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1,2};
	int points[]=new int[]{1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10,0};
	int selected[]=new int[27];
	int pl1,pl2;
	char getchip()
	{
		int i,j,k=0;
		Random r = new Random();
		for(j=0;j<27;j++)
		{
			if(selected[j]!=number[j])
				k=-1;
		}
		if(k==0)
			return ('a');
		char c = (char)(r.nextInt(27) + 'A');
		i=(int)c;
		if(selected[i-65]==number[i-65])
		{
			while(selected[i-65]==number[i-65])
			{
				c = (char)(r.nextInt(26) + 'A');
				i=(int)c;	
			}
		}
		selected[i-65]++;
		return c;
	}
	String playerwins()
	{
		if(pl1>pl2)
			return("PLAYER 1 WINS...");
		else if(pl2>pl1)
			return("PLAYER 2 WINS...");
		else
			return("TIE GAME");
	}
	
	void sort(char[] string,int[] pos,int l)
	{
		for(int i=0;i<l-1;i++)
		{
			for(int j=0;j<l-i-1;j++)
			{
				if(pos[j+1]<pos[j])
				{
					int temp=pos[j+1];
					pos[j+1]=pos[j];
					pos[j]=temp;
					char ch =string[j+1];
					string[j+1]=string[j];
					string[j]=ch;
				}
			}
		}
	}

}


public class Game extends Chips
{
	JFrame f;
	JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11;
	JButton b11,b12,b13,b2,b41[],b42[],b43[],b44,b45,b46,b91,b92,b93,b=null;
	JTextArea textArea;
	JLabel l2,l,l91,l92,l8;
	int z,width,height,v=1;
	ImageIcon imagez,des;
	LinkedList<JButton> take=new LinkedList<JButton>();
	LinkedList<JButton> drop=new LinkedList<JButton>();
	LinkedList<String> let=new LinkedList<String>();
	
	Game(String s)
	{
		f=new JFrame(s);
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		p4=new JPanel();
		p5=new JPanel();
		p6=new JPanel();
		p7=new JPanel();
		p8=new JPanel();
		p9=new JPanel();
		p10=new JPanel();
		p11=new JPanel();
		f.setLayout(new CardLayout());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int)screenSize.getWidth();
		height = (int)screenSize.getHeight();
		f.setBounds(0,0,width,height);
		f.add(p1);
		f.add(p2);
		f.add(p3);
		f.add(p4); 
		
		l=new JLabel();
		l.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("Images/maini.jpg")).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		b11=new JButton("PLAY");
		b12=new JButton("INFORMATION");
		b13=new JButton("EXIT");
		b11.setFont(b11.getFont().deriveFont(Font.BOLD, 32));
		b12.setFont(b12.getFont().deriveFont(Font.BOLD, 32));
		b13.setFont(b13.getFont().deriveFont(Font.BOLD, 32));
		p1.setLayout(null);
		l.setBounds(0,0,width,height);
		b11.setBounds((width-400)/2,(height-300)/4,400,100);
		b12.setBounds((width-400)/2,2*(height-300)/4 +100,400,100);
		b13.setBounds((width-400)/2,3*(height-300)/4 +200,400,100);
		b11.setBackground(Color.yellow);
		b12.setBackground(Color.yellow);
		b13.setBackground(Color.yellow);
		b11.setForeground(Color.red);
		b12.setForeground(Color.red);
		b13.setForeground(Color.red);
		l.add(b11);
		l.add(b12);
		l.add(b13);
		p1.add(l);
		p1.setBackground(Color.green);
		
		p1.setVisible(true);
		
		b13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg){
				JOptionPane.showMessageDialog(f," THANKS FOR PLAYING... ");
				System.exit(0);	}
		});

		l2=new JLabel("<HTML><U>RULES OF SCRABBLE</U></HTML>",SwingConstants.CENTER);
		l2.setFont(new Font("ALGERIAN", Font.BOLD, 48));
		l2.setOpaque(true);
		l2.setBackground(Color.lightGray);
		l2.setForeground(Color.yellow);
		l2.setPreferredSize(new Dimension(100, 150));
		b2=new JButton("BACK TO MAIN MENU");
		b2.setFont(b2.getFont().deriveFont(Font.ITALIC, 32));
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg){
				p1.setVisible(true);
				p2.setVisible(false);	}
		});
		b2.setBackground(Color.lightGray);
		b2.setForeground(Color.red);
		b2.setBorder(null);
		textArea = new JTextArea();
		try
		{
		FileReader fr= new FileReader("Rules.txt");
		BufferedReader br=new BufferedReader(fr);
		String c="";
		while(c!=null)
		{
			c=br.readLine();
			if(c!=null)
				textArea.append(c+"\n");
		}
		br.close();
		fr.close();
		}catch(IOException e)
		{}
		textArea.setBackground(Color.cyan);
		//textArea.setBorder(BorderFactory.createMatteBorder(2, 5, 2, 2, Color.blue));
		textArea.setEditable(false);
		textArea.setFont(new Font("Serif", Font.ITALIC, 16));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane areaScrollPane = new JScrollPane(textArea);
		areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(400,500));
		areaScrollPane.setBorder(BorderFactory.createMatteBorder(2, 5, 2, 2, Color.blue));
		p3.setPreferredSize(new Dimension(700,height-100));
		b2.setPreferredSize(new Dimension(100,150));
		p3.setLayout(new BorderLayout());
		p3.add(l2,BorderLayout.NORTH);
		p3.add(areaScrollPane,BorderLayout.CENTER);
		p3.add(b2,BorderLayout.SOUTH);
		p2.add(p3);
		p2.setBackground(Color.lightGray);
		
		b12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg){
				p3.setVisible(true);
				p2.setVisible(true);
				p1.setVisible(false);
			}
		});
		Mouse mou = new Mouse();
		b41=new JButton[225];
		p4.setBackground(Color.yellow);
		p5.setPreferredSize(new Dimension(width-200,height-200));
		p5.setLayout(new GridLayout(15,15));
		for(int i=0;i<225;i++)
		{
			switch(i)
			{
				case 0:
				case 7:
				case 14:
				case 105:
				case 119:
				case 210:
				case 217:
				case 224:b41[i]=new JButton("3W");
					b41[i].setBackground(Color.green);
					break;
				case 3:
				case 11:
				case 36:
				case 38:
				case 45:
				case 52:
				case 59:
				case 92:
				case 96:
				case 98:
				case 102:
				case 108:
				case 116:
				case 122:
				case 126:
				case 128:
				case 132:
				case 165:
				case 172:
				case 179:
				case 186:
				case 188:
				case 213:
				case 221:b41[i]=new JButton("2L");
					b41[i].setBackground(Color.cyan);
					break;
				case 20:
				case 24:
				case 76:
				case 80:
				case 84:
				case 88:
				case 136:
				case 140:
				case 144:
				case 148:
				case 200:
				case 204:b41[i]=new JButton("3L");
					b41[i].setBackground(Color.orange);
					break;
				case 16:
				case 28:
				case 32:
				case 42:
				case 48:
				case 56:
				case 64:
				case 70:
				case 154:
				case 160:
				case 168:
				case 176:
				case 182:
				case 192:
				case 196:
				case 208:b41[i]=new JButton("2W");
					b41[i].setBackground(Color.pink);
					break;
				case 112:b41[i]=new JButton();
					b41[i].setBackground(Color.white);
					break;
				default:b41[i]=new JButton();
					b41[i].setBackground(Color.lightGray);
					
			}
			p5.add(b41[i]);
			b41[i].addMouseListener(mou);
			b41[i].addMouseMotionListener(mou);
		}
		p5.setBorder(BorderFactory.createLineBorder(Color.blue));
		p4.add(p5);
	
		b42=new JButton[7];
		b43=new JButton[7];
		for(int i=0;i<7;i++)
		{
			char ch;
			ch=getchip();
			b42[i]=new JButton();
			b42[i].setPreferredSize(new Dimension(75,75));
			des=new ImageIcon("Chips/" + ch + ".jpg");
			des.setDescription("Chips/" + ch + ".jpg");
			b42[i].setIcon(des);
			des=null;
			b42[i].setBorder(BorderFactory.createLineBorder(Color.blue));
			p6.add(b42[i]);
			b42[i].addMouseListener(mou);
			b42[i].addMouseMotionListener(mou);
			ch=getchip();
			b43[i]=new JButton();
			b43[i].setPreferredSize(new Dimension(75,75));
			des=new ImageIcon("Chips/" + ch + ".jpg");
			des.setDescription("Chips/" + ch + ".jpg");
			b43[i].setIcon(des);
			des=null;
			b43[i].setBorder(BorderFactory.createLineBorder(Color.blue));
			p7.add(b43[i]);
			b43[i].addMouseListener(mou);
			b43[i].addMouseMotionListener(mou);
		}
		p8.setLayout(new CardLayout());
		p8.setPreferredSize(new Dimension((width-225)/2,80));
		p8.add(p7);
		p8.add(p6);
		p6.setVisible(true);
		p7.setVisible(false);
		l8=new JLabel("PLAYER 1",SwingConstants.CENTER);
		l8.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
		l8.setForeground(Color.blue);
		l8.setOpaque(true);
		l8.setBackground(Color.yellow);
		l8.setPreferredSize(new Dimension((width-225)/2,20));
		p11.setPreferredSize(new Dimension((width-225)/2,100));
		p11.setLayout(new BorderLayout());
		p11.add(l8,BorderLayout.NORTH);
		p11.add(p8,BorderLayout.CENTER);
		p6.setBackground(Color.yellow);
		p7.setBackground(Color.yellow);

		l91=new JLabel();
		l91.setPreferredSize(new Dimension(25,100));
		int m=(((width-225)/2)-50)/4;
		l92=new JLabel("<HTML><strong>"+"PLAYER 1: "+pl1+"<br><br> PLAYER 2: "+pl2+"</strong></HTML>",SwingConstants.CENTER);
		l92.setFont(new Font("AERIAL",Font.ITALIC,16));
		l92.setBackground(Color.white);
		l92.setOpaque(true);
		l92.setForeground(Color.blue);
		l92.setBorder(BorderFactory.createLineBorder(Color.blue));
		l92.setPreferredSize(new Dimension(m,100));
		b91=new JButton();
		b91.setPreferredSize(new Dimension(m,100));
		b91.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("Images/undo.jpg")).getImage().getScaledInstance(m, 100, Image.SCALE_SMOOTH)));
		b91.setBorder(BorderFactory.createLineBorder(Color.blue));
		b92=new JButton();
		b92.setPreferredSize(new Dimension(m,100));
		b92.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("Images/play.jpg")).getImage().getScaledInstance(m, 100, Image.SCALE_SMOOTH)));
		b92.setBorder(BorderFactory.createLineBorder(Color.blue));
		b93=new JButton();
		b93.setPreferredSize(new Dimension(m,100));
		b93.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("Images/exit.png")).getImage().getScaledInstance(m, 100, Image.SCALE_SMOOTH)));
		b93.setBorder(BorderFactory.createLineBorder(Color.blue));
		p10.setPreferredSize(new Dimension(width-200,105));
		p10.setLayout(new FlowLayout(FlowLayout.LEFT));
		p10.setBackground(Color.yellow);
		p10.add(p11);
		p10.add(l91);
		p10.add(l92);
		p10.add(b91);
		p10.add(b92);
		p10.add(b93);
		p4.add(p10);
		
		b91.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg){
				if(!take.isEmpty())
				{	JButton put = (JButton) take.getFirst();
					JButton get = (JButton) drop.getFirst();
					String s=undo(put,get);
					let.remove(s);
					take.remove(put);
					drop.remove(get);
				}
			}
		});
		
		b93.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg){
			if(z%2!=0)
			{
				JOptionPane.showMessageDialog(f,playerwins());
				System.exit(0);
			}
			else
				JOptionPane.showMessageDialog(f,"YOU CANNOT END GAME LIKE THIS, GAME CAN ONLY END AT 1st PLAYER CHANCE");
		}
		});
		
		b92.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg){
				int g=word();
				switch(g)
				{
					case -1:JOptionPane.showMessageDialog(f,"VALID WORD NOT FORMED");
						Iterator<JButton> itr=drop.iterator(); 
						Iterator<JButton> itr1=take.iterator(); 
						while(itr1.hasNext())
						{	
							undo((JButton)itr1.next(),(JButton)itr.next());
						}
						take.clear();
						drop.clear();
						let.clear();
						break;
					case -2:z++;
						if(z%2==0)
						{
							p6.setVisible(false);
							p7.setVisible(true);
							l8.setText("PLAYER 2");
						}	
						else
						{
							p7.setVisible(false);
							p6.setVisible(true);
							l8.setText("PLAYER 1");
						}
						break;
					case -3:break;
					default:if(z%2!=0)
						{
							pl1+=g;
							p6.setVisible(false);
							p7.setVisible(true);
							l8.setText("PLAYER 2");
						}
						else
						{
							pl2+=g;
							p7.setVisible(false);
							p6.setVisible(true);
							l8.setText("PLAYER 1");
						}
						l92.setText("<HTML><strong>"+"PLAYER 1: "+pl1+"<br><br> PLAYER 2: "+pl2+"</strong></HTML>");
        					take.clear();
        					drop.clear();
        					let.clear();
						z++;
    				}
			}
		});

		b11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg){
				z=1;
				p10.setVisible(true);
				p5.setVisible(true);
				p4.setVisible(true);
				p1.setVisible(false);
			}
		});

		f.setVisible(true);

	}
	
class Mouse implements MouseListener,MouseMotionListener
{
	
	
	public void mouseDragged(MouseEvent e)
	{}

	public void mouseReleased(MouseEvent e)
	{}
		
	public void mouseMoved(MouseEvent e)
	{}

	public void mouseClicked(MouseEvent e)
	{
		JButton ob=(JButton)e.getSource();
		if(e.getButton()== MouseEvent.BUTTON1)
		{	
			imagez=(ImageIcon)ob.getIcon();
			b=ob;
		}
		else if(e.getButton()== MouseEvent.BUTTON3)
		{	if(check(ob) && imagez!=null )
			{
			if(!image(ob))
			{
				if(!check(b))
				{
				ob.setText(null);
				des=new ImageIcon(imagez.getImage().getScaledInstance((width-200)/15,(height-200)/15, Image.SCALE_SMOOTH));
				des.setDescription(imagez.getDescription());
				ob.setIcon(des);
				des=null;
				take.addFirst(b);
				drop.addFirst(ob);
				char ch=getchip();
				let.addFirst(String.valueOf(ch));
				if(ch!='a')
				{
					des=new ImageIcon("Chips/"+ch+".jpg");
					des.setDescription("Chips/"+ch+".jpg");
					b.setIcon(des);
					des=null;
				}
				else
					b.setIcon(null);
				b=null;
				imagez=null;
				}
				else if(!image(b) || (image(b) && present(b)))
				{
				switch(colour(b))
				{
					case 1:b.setText("2L");
						break;
					case 2:b.setText("3L");
						break;
					case 3:b.setText("2W");
						break;
					case 4:b.setText("3W");
				}
				ob.setText(null);
				des=new ImageIcon(imagez.getImage().getScaledInstance((width-200)/15,(height-200)/15, Image.SCALE_SMOOTH));
				des.setDescription(imagez.getDescription());
				ob.setIcon(des);
				des=null;
				take.addFirst(b);
				drop.addFirst(ob);
				b.setIcon(null);
				b=null;
				imagez=null;
				}
			} 
			}
		}
	}

	public void mousePressed(MouseEvent e)
	{}

	public void mouseEntered(MouseEvent e)
	{}
		
	public void mouseExited(MouseEvent e)
	{}


}
	
	public static void main(String... s)
	{
		new Game("SCRABBLE GAME");
	}

	boolean check(JButton ob)
	{
		for(int i=0;i<7;i++)
		{
			if(ob==b42[i])
					return false;
			if(ob==b43[i])
					return false;
		}
		return true;
	}
	
	boolean present(JButton ob)
	{
		Iterator<JButton> itr=drop.iterator();  
		while(itr.hasNext())
			if(itr.next()==ob)
				return true;
		return false;
	}
	
	boolean image(JButton ob)
	{
		if(ob.getIcon()==null)
			return false;
		return true;
	}
	
	int colour(JButton ob)
	{
		if(ob.getBackground()==Color.cyan)
			return 1;
		if(ob.getBackground()==Color.orange)
			return 2;
		if(ob.getBackground()==Color.pink)
			return 3;
		if(ob.getBackground()==Color.green)
			return 4;
		return 5;
	}

	String undo(JButton put,JButton get)
	{
		imagez=(ImageIcon)get.getIcon();
		get.setIcon(null);
		String s="";
		switch(colour(get))
		{
					case 1:get.setText("2L");
						break;
					case 2:get.setText("3L");
						break;
					case 3:get.setText("2W");
						break;
					case 4:get.setText("3W");
		}
		if(image(put))
		{
			 s=(String)let.getFirst();
			char c[]=s.toCharArray();
			char ch=c[0];
			if(ch!='a')
			{
				int k=((int)ch)-65;
				selected[k]--;
			}
			des=new ImageIcon(imagez.getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH));
			des.setDescription(imagez.getDescription());
			put.setIcon(des);
			des=null;
		}
		else
		{
			put.setText(null);
			des=new ImageIcon(imagez.getImage().getScaledInstance((width-200)/15,(height-200)/15, Image.SCALE_SMOOTH));
			des.setDescription(imagez.getDescription());
			put.setIcon(des);
			des=null;
		}
		imagez=null;
		return s;
	
	}


	int word()
	{	char wor[]=new char[15];
		int pos[]=new int[15];
		int l=0,b;
		char mn=' ';
		Iterator<JButton> itr=drop.iterator();  
		while(itr.hasNext())
		{	JButton ob=(JButton)itr.next();
			if(ob.getIcon()!=null)
			{	ImageIcon image=(ImageIcon)ob.getIcon();
				String s=image.getDescription();
				char c[]=s.toCharArray();
				char ch=c[6];
				wor[l]=ch;
				if(l<2)
				{
					for(int k=0;k<225;k++)
						if(ob==b41[k])
							pos[l]=k;
					if(l==1)
					{
						if((pos[1]-pos[0])%15==0)
							mn='v';
						else if(((pos[1]-pos[0])<15) && ((pos[1]-pos[0])>-15))
							mn='h';
						else
							return -1;
					}
				}
				else
				{	int h=0;
					if(mn=='v')
					{
						for(int k=pos[0];k<225;k=k+15)
						{	if(ob==b41[k])
							{	h=1;
								pos[l]=k;
								break;
							}
						}
						for(int k=pos[0];k>=0;k=k-15)
						{	if(ob==b41[k])
							{	h=1;
								pos[l]=k;
								break;
							}
						}
					}
					else
					{	b=(pos[0])/15;
						for(int k=pos[0];k<((b+1)*15);k++)
						{	if(ob==b41[k])
							{	h=1;
								pos[l]=k;
								break;
							}
						}
						for(int k=pos[0];k>=((b)*15);k--)
						{	if(ob==b41[k])
							{	h=1;
								pos[l]=k;
								break;
							}
						}
					}
					if(h==0)
						return -1;
				}
				l++;
			}
		}
		if(l==0)
		{
			String fa;
			fa = JOptionPane.showInputDialog("DO U WANT TO SKIP YOUR TURN (IF YES THEN TYPE OK)");
			if(fa.equals("ok") || fa.equals("OK"))
				return -2;
			return -3;
		}
		else{
		sort(wor,pos,l);
		if(l==1)
		{
			if(b41[pos[0]-15].getIcon()!=null || b41[pos[0]+15].getIcon()!=null)
				mn='v';
			else if(b41[pos[0]+1].getIcon()!=null || b41[pos[0]-1].getIcon()!=null)
				mn='h';
			else
				return -1;
		}
		if(mn=='v')
		{

			for(int p=pos[0]-15;p>=0;p=p-15)
			{
				if(b41[p].getIcon()==null)
						break;
					else
					{
						ImageIcon image=(ImageIcon)b41[p].getIcon();
						String s=image.getDescription();
						char c[]=s.toCharArray();
						char ch=c[6];
						for(int i=l-1;i>=0;i--)
						{
							pos[i+1]=pos[i];
							wor[i+1]=wor[i];
						}
						pos[0]=p;
						wor[0]=ch;
						l++;
					}
			}
			for(int p=1;p<l;p++)
			{
				int m=(pos[p]/15)-(pos[p-1]/15);
				for(int j=1;j<m;j++)
				{
					int n=pos[p]-15;
					if(b41[n].getIcon()==null)
						return -1;
					else
					{
						ImageIcon image=(ImageIcon)b41[n].getIcon();
						String s=image.getDescription();
						char c[]=s.toCharArray();
						char ch=c[6];
						for(int i=l-1;i>=p;i--)
						{
							pos[i+1]=pos[i];
							wor[i+1]=wor[i];
						}
						pos[p]=n;
						wor[p]=ch;
						l++;
					}
				}
			}

			for(int p=(pos[l-1]+15);p<225;p=p+15)
			{
				if(b41[p].getIcon()==null)
						break;
					else
					{
						ImageIcon image=(ImageIcon)b41[p].getIcon();
						String s=image.getDescription();
						char c[]=s.toCharArray();
						char ch=c[6];
						pos[l]=p;
						wor[l]=ch;
						l++;
					}
			}

		}
		else
		{
			
			b=pos[0]/15;
			for(int p=(pos[0]-1);p>=(b*15);p--)
			{
				if(b41[p].getIcon()==null)
						break;
					else
					{
						ImageIcon image=(ImageIcon)b41[p].getIcon();
						String s=image.getDescription();
						char c[]=s.toCharArray();
						char ch=c[6];
						for(int i=l-1;i>=0;i--)
						{
							pos[i+1]=pos[i];
							wor[i+1]=wor[i];
						}
						pos[0]=p;
						wor[0]=ch;
						l++;
					}
			}
			for(int p=1;p<l;p++)
			{
				int m=pos[p]-pos[p-1];
				for(int j=1;j<m;j++)
				{
					int n=pos[p]-1;
					if(b41[n].getIcon()==null)
						return -1;
					else
					{
						ImageIcon image=(ImageIcon)b41[n].getIcon();
						String s=image.getDescription();
						char c[]=s.toCharArray();
						char ch=c[6];
						for(int i=l-1;i>=p;i--)
						{
							pos[i+1]=pos[i];
							wor[i+1]=wor[i];
						}
						pos[p]=n;
						wor[p]=ch;
						l++;
					}
				}
			}
			b=pos[l-1]/15;
			for(int p=(pos[l-1]+1);p<(b+1)*15;p++)
			{
				if(b41[p].getIcon()==null)
						break;
					else
					{
						ImageIcon image=(ImageIcon)b41[p].getIcon();
						String s=image.getDescription();
						char c[]=s.toCharArray();
						char ch=c[6];
						pos[l]=p;
						wor[l]=ch;
						l++;
					}
			}
		}
		String fi=String.valueOf(wor);
		String fin=fi.trim();
		int res=checkWord(fin);
		if(res==-1)
			return -1;
		int value=0,w=1;
		for(int i=0;i<l;i++)
		{
			int x=((int)wor[i])-65;
			switch(colour(b41[pos[i]]))
			{
				case 1:value+=(2*points[x]);
					break;
				case 2:value+=(3*points[x]);
					break;
				case 3:if(w!=3)
							w=2;
					value+=points[x];
					break;
				case 4:w=3;
					value+=points[x];
					break;
				default:value+=points[x];
			}
		}
		value*=w;
		JOptionPane.showMessageDialog( f, "Word:- "+fin+"   Points:- "+value);
		return value;
		}
	}

	int checkWord(String str) 
	{
		/*String response= "";
		HttpURLConnection con=null;
		InputStream input =null;
		try{
		String url = "https://en.wiktionary.org/w/api.php?action=checktoken&type=csrf&format=json&token="+str.toLowerCase();

		URL obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		con.connect();
		input = con.getInputStream();
		 StringBuilder output = new StringBuilder();
            if (input != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
		response = output.toString(); 
	}catch(Exception e){
		System.out.println(e);
	}
	finally {
                if (con != null) {
                    con.disconnect();
                }
                if (input != null) {
                    // function must handle java.io.IOException here
                    try{
                    input.close();
					}catch(IOException e){}                
                }
            }
      System.out.println(response);
        JSONObject json = (JSONObject) JSONValue.parse(response);
		JSONObject check = (JSONObject)json.get("checktoken");
		String valid = (String) check.get("result");

	if(valid.equals("invalid"))
		return -1;
	else*/
		return 1;
	}

}
