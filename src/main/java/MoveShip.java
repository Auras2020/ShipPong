import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MoveShip extends JPanel implements Runnable, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static final int WIDTH = 600, HEIGHT = 600;
    
    private Thread thread;
    private boolean running;
    
    private int xCoorShip = 270;
    private int yCoorShip = 550;
    private int widthShip=100;
    private int heightShip=10;
    
    private int xCoorBall=293;
    private int yCoorBall=535;
    private int razaBall=15;
    
    private final static int xCoorBlock = 50;
    private final static int yCoorBlock = 50;
    private final static int widthBlock=45;
    private final static int heightBlock=30;
    
    private Rectangles r;
    private List<Rectangles> blocks=new ArrayList<>();
    
    private Ball ball;
    private List<Ball> balls=new ArrayList<>();
    
    private Ship s;
    private List<Ship> ship=new ArrayList<>();
 
    private boolean right = false, left = true;
    private boolean vest=true, est=false, nord=true, sud=false;
    
    private int ticks = 0;
    private int ticks1 = 0;
    private int start=0;

	public MoveShip(JFrame window) {
		addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        s=new Ship(xCoorShip, yCoorShip, widthShip, heightShip);
        blocks=new ArrayList<>();
        
        start();
	}
	
	public void tick() {
		
		if(start==0) {
		for(int j=0; j<5; j++) {
        	for(int i=0; i<10; i++) {
        	r=new Rectangles(xCoorBlock+i*50, yCoorBlock+j*35, widthBlock, heightBlock);
        	blocks.add(r);
       }
       }
		}
		start++;
		
		if(start>500000) {
			start=1;
		}
		
		for(int i=blocks.size()-1; i>=0; i--) {
			if(nord==true) {
				if((yCoorBall==blocks.get(i).getyCoor()+heightBlock 
						&& xCoorBall+razaBall/2>blocks.get(i).getxCoor() 
					&& xCoorBall<=blocks.get(i).getxCoor()+widthBlock-razaBall/2) ||
						(yCoorBall<=blocks.get(i).getyCoor()+heightBlock && 
						yCoorBall>=blocks.get(i).getyCoor() && 
						(xCoorBall+razaBall==blocks.get(i).getxCoor() ||
						xCoorBall==blocks.get(i).getxCoor()+widthBlock)))
						{
					
					if(vest==true) {
						sud=true;
				        est=false;
				        vest=true;
				        nord=false;
				        blocks.remove(i);
				        break;
					}
					else if(est==true) {
						sud=true;
				        est=true;
				        vest=false;
				        nord=false;
				        blocks.remove(i);
				        break;
					}
			    }
			
			}
			else if(sud==true) {
				if((yCoorBall==blocks.get(i).getyCoor()
						&& xCoorBall+razaBall/2>blocks.get(i).getxCoor() 
					&& xCoorBall<=blocks.get(i).getxCoor()+widthBlock-razaBall/2) ||
						(yCoorBall<=blocks.get(i).getyCoor()+heightBlock && 
						yCoorBall>=blocks.get(i).getyCoor() && 
						(xCoorBall+razaBall==blocks.get(i).getxCoor() ||
						xCoorBall==blocks.get(i).getxCoor()+widthBlock)))
						{
					
					if(vest==true) {
						sud=false;
				        est=false;
				        vest=true;
				        nord=true;
				        blocks.remove(i);
				        break;
					}
					else if(est==true) {
						sud=false;
				        est=true;
				        vest=false;
				        nord=true;
				        blocks.remove(i);
				        break;
					}
			    }
			
			}
			}
			
		
		if(yCoorBall+razaBall==yCoorShip && (xCoorBall+razaBall/2>=xCoorShip &&
				xCoorBall+razaBall/2<=xCoorShip+widthShip)) {
			if(sud==true && est==true) {
				nord=true;
				vest=false;
				est=true;
				sud=false;
			}
			else if(sud==true && vest==true) {
				nord=true;
				vest=true;
				est=false;
				sud=false;
			}
		}
		
		if(yCoorBall+razaBall==HEIGHT) {
			stop();
		}
		
		if(xCoorShip<=0) {
			xCoorShip=0;
		}
		if(xCoorShip>=WIDTH-widthShip) {
			xCoorShip=WIDTH-widthShip;
		}
		
		ticks++;
		
		if(xCoorBall<=0) {
			if(nord==true) {
				vest=false;
				est=true;
				sud=false;
			}
			else if(sud==true){
				vest=false;
				est=true;
				nord=false;
			}
		}
		if(xCoorBall>=WIDTH-razaBall) {
			if(nord==true) {
				vest=true;
			    est=false;
			    sud=false;
			}
			else if(sud==true) {
				vest=true;
			    est=false;
			    nord=false;
			}
		}
		if(yCoorBall<=0) {
			if(vest==true) {
				nord=false;
			    est=false;
			    sud=true;
			}
			else if(est==true) {
				nord=false;
			    vest=false;
			    sud=true;
			}
		}
		
		ticks1++;
		
		if(ticks>200000) {
			if(right) xCoorShip+=10;
            if(left) xCoorShip-=10;
			
            ticks=0;
            
            s=new Ship(xCoorShip, yCoorShip, widthShip, heightShip);
            
		}
		
	
		
		if(ticks1>50000) {
			if(nord==true) {
				if(vest==true) {
					xCoorBall-=2;
                    yCoorBall-=2;
				}
				else if(est==true) {
					xCoorBall+=2;
                    yCoorBall-=2;
				}
			}
			if(sud==true) {
				if(vest==true) {
					xCoorBall-=2;
                    yCoorBall+=2;
				}
				else if(est==true) {
					xCoorBall+=2;
                    yCoorBall+=2;
				}
			}
			
			ticks1=0;
			
            ball=new Ball(xCoorBall, yCoorBall, razaBall);
		}
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        ball=new Ball(xCoorBall, yCoorBall, razaBall);
        ball.draw(g);
      
        s=new Ship(xCoorShip, yCoorShip, widthShip, heightShip);
        s.draw(g);
        
       for(int i=0; i<blocks.size(); i++) {
    	   blocks.get(i).draw(g);
       }
       
       if(yCoorBall+razaBall==HEIGHT) {
    	   g.drawString("GAME OVER", 300, 300);
       }
       
       g.drawString("SCORE: " + (50-blocks.size())*10, 10, 20);
       if(blocks.size()==0) {
    	   g.drawString("CONGRATULATIONS", 300, 300);
    	   stop();
       }
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT ) {
            {
        		left=false;
                right = true;
        	}
        }
        if(key == KeyEvent.VK_LEFT) {
        	{
        		right=false;
                left = true;
        	}
        }
        if(key==KeyEvent.VK_ENTER) {
        	if(running==false) {
        		right=false;
        		left=true;
        		vest=false;
        		est=true;
        		nord=true;
        		sud=false;
        		xCoorShip = 270;
        	    yCoorShip = 550;
                blocks=new ArrayList<>();
                start=0;
                xCoorBall=293;
                yCoorBall=535;
                repaint();
                start();
        	}
        }
        
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (running) {
            tick();
            repaint();
        }
	}
	
	public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }
 
    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
