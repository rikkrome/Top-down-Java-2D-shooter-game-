//package Asteroids.AsteroidsVector;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;









public class finalG
{
	public static void main(String[] args) 
	{
		
		StartGame zombieGame = new StartGame();
		playIntroductionSound("introductionSound.wav");
		zombieGame.run();
	}
	public static void playIntroductionSound(String introductionSound)
	{
		try{
			File file = new File(introductionSound);
	        Clip clip = AudioSystem.getClip();
	        AudioInputStream ais = AudioSystem.
	            getAudioInputStream( file );
	        clip.open(ais);
	        clip.start();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
class StartGame 
{
	public StartGame()
	{
		
	}
	public void run()
	{
	GameFrame frame = new GameFrame();
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	frame.setSize(900,700);
	frame.setVisible(true);
	frame.setup();
//	frame.intro();
	playBackgroundSound("backgroundSound.wav");  //<-- this is the background sound for in the game
	frame.run();
	}
	public static void playBackgroundSound(String backgroundSound)
	{
		//plays the sound of the sting path that is provided to the file object
		//THE BOTTOM CODE WILL PLAY THE BACKGROUND SOUND
			try{
				File file = new File(backgroundSound);
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream ais = AudioSystem.
		            getAudioInputStream( file );
		        clip.open(ais);
		        clip.start();
		        FloatControl gainControl = 
		        	    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		        	gainControl.setValue(-5.0f);
		        clip.loop(-1);  //  <--- This code makes the sound loop forever, giving it more of a background sound track feeling to it.
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		// // <-----------
	}
	
}
@SuppressWarnings("serial")
class GameFrame extends JFrame
{
	Image raster;
	Graphics rasterGraphics;
	java.awt.Image Background,Dash,Walls;
	private Image introductionGIFimage, introductionCreditsGIFimage, introductionOne;
	public static final AffineTransform identity = new AffineTransform();
	public static ArrayList<ScreenObj> ScreenObjs = new ArrayList<ScreenObj>();
	public static Player myShip;
	public static int Level = 1;
	public static int NewBackGroundNum = 0;
	public int countdown = 30;
	public static boolean gameover = false;
	public static int screenX = -300;
	public static int screenY = -300;
	public static int Invincible = 30;
	public String LevelBG;
	public static String PlayerImg;
	Random random = new Random();
	int RamdomNum = random.nextInt(3) + 1;
	int newRandNum;



	public GameFrame()
	{
		Dash = new ImageIcon("Dash.png").getImage();
		PickPlayerImg();
		
		myShip = new Player();
		ScreenObjs.add(myShip);		
		this.addKeyListener((Player)ScreenObjs.get(0));//This line is not ideal
	}
	public void setup()
	{
		raster = this.createImage(900, 700);
		rasterGraphics = raster.getGraphics();
		introductionGIFimage = new ImageIcon("MosterRunGameIntroductionPartOne.gif").getImage();
		introductionCreditsGIFimage = new ImageIcon("MobsterIntroAnimationTwo.gif").getImage();
		introductionOne = new ImageIcon("introductionOne.png").getImage();
	}

	public void intro()
	{
		
		//This bottom code will load the introduction logo.
		for (int i = 0; i < 100 ; i++) //5710
		{
			try{
				System.out.println(i);
				playGIFimage(rasterGraphics);
				
				this.getGraphics().drawImage(raster,0,0,null);
			}catch(Exception e){}
		
		}
		//The bottom code will load the credits + game menu + count down.
		for (int i = 0; i < 300; i++) //17535
		{
			try{
				System.out.println(i);
				playSecondGIFimage(rasterGraphics);
				this.getGraphics().drawImage(raster, 0, 0, null);
			}catch(Exception e){}
		}
	}
	
	//Functions of introduction GIF / JPG images.
			public void playGIFimage(Graphics g)
			{
				g.drawImage(introductionGIFimage,0,0,900,700,null);
			}
			public void playSecondGIFimage(Graphics g)
			{
				g.drawImage(introductionCreditsGIFimage,5,20,900,700,null);
			}
			public void introductionOne(Graphics g)
			{
				g.drawImage(introductionOne,0,0,900,700,null);			
			}
			// <---------------------------------------------------- END OF INTRODUCTION ANIMATION.
			//
			//

	public void MakeAirDrop()
	{
		ScreenObjs.add(new AirDrop());
	}
	public void CreateMobs()
	{
		ScreenObjs.add(new Mobs((float)Math.random()*this.getHeight(),(float)Math.random()*this.getWidth(),0, 101.0f, 100.6f,0,ScreenObjs.get(0),4+Level/2,10+(int)Math.random(),1,"zombie.png"));
		ScreenObjs.add(new Mobs((float)Math.random()*this.getHeight(),(float)Math.random()*this.getWidth(),0, 1.0f, 4.6f,0,ScreenObjs.get(0),1+Level/2,20+(int)Math.random(),10,"zombieblue.png"));
		ScreenObjs.add(new Mobs((float)Math.random()*this.getHeight(),(float)Math.random()*this.getWidth(),0, 2.0f, 3.6f,0,ScreenObjs.get(0),1,50+(int)Math.random(),16,"zombieblue.png"));
//		ScreenObjs.add(new Mobs((float)Math.random()*this.getHeight(),(float)Math.random()*this.getWidth(),0, 6.0f, 1.6f,0,ScreenObjs.get(0),3+Level,100+(int)Math.random(),4,"zombieblue.png"));
//		ScreenObjs.add(new Mobs((float)Math.random()*this.getHeight(),(float)Math.random()*this.getWidth(),0, 30.0f, 30.6f,0,ScreenObjs.get(0),5+Level,200+(int)Math.random(),6,"zombieblue.png"));


	}
	public void run()
	{
		int FrameNumber=0;
//		int Level=1;
		
		CreateMobs();
		
		
		

		
		while (!gameover)
		{
			
			FrameNumber++;
			if (Invincible > 0)
				Invincible--;
			
			PickPlayerImg();
			updatePlayerIMG(PlayerImg);
			ChangeLevelImg();
			PickBackGround(LevelBG);
			DrawBackground(rasterGraphics);
			
			drawDash(rasterGraphics);
			drawHealth(rasterGraphics);
			drawWalls(rasterGraphics);
			DashBoard();
			
			
			boolean nextLevel = true;
			for (int i=ScreenObjs.size()-1;i>=0;i--)
			{
				ScreenObj so = ScreenObjs.get(i);
				//Act on the various objects
				so.Act(FrameNumber,Invincible);		
				
				//Draw various screen objects							
				so.Draw(rasterGraphics,Invincible);
				if (so instanceof Mobs)
				{
					nextLevel = false;
					
				}
			}
			if(nextLevel)
			{ 
				//ScreenObj.ChangeGuns(1);
				CountDownTimer();
			}


			//Draw final Image
			Graphics frameG = this.getGraphics();
			frameG.drawImage(raster,0,0,null);

			try{
				Thread.sleep(10);
			}catch(Exception e){}
		}
		
		
		
	}
	public void DashBoard()
	{
		Font stringFont = new Font( "SansSerif", Font.PLAIN, 17); 
		rasterGraphics.setFont( stringFont );
		rasterGraphics.setColor(Color.WHITE);
		rasterGraphics.drawString(""+(int)ScreenObj.score,670,43);
		rasterGraphics.drawString(""+(int)ScreenObj.Health,140,42);
		rasterGraphics.drawString("Lifes:"+(int)ScreenObj.life,340,43);
		rasterGraphics.drawString("Kills: "+(int)ScreenObj.Kills,750,42);
		rasterGraphics.drawString("ROUND: "+Level,430,43);
	}
	public void CountDownTimer()
	{
		Font stringFont1 = new Font( "SansSerif", Font.PLAIN, 30); 
		rasterGraphics.setFont( stringFont1 );
		rasterGraphics.setColor(Color.WHITE);
		rasterGraphics.drawString("Next Round in: "+countdown,600,670);
		//ScreenObj.playSound("zombieType1Sound.wav");


		if (--countdown <= 0)
		{

			ScreenObj.playSound("zombieType1Sound.wav");
			Level++;
			NewBackGroundNum++;
			System.out.println("Level: "+Level);
			for (int i=0;i<Level;i++)
			CreateMobs();
			Invincible = 30;
			countdown =30;
			MakeAirDrop();
		}
	}

	public void ChangeLevelImg()
	{
		int i = 0;
		
		
		if(NewBackGroundNum == 0)
			i = 0;
		else if(NewBackGroundNum == 1)
			i = 1;
		else if(NewBackGroundNum == 2)
			i = 2;
		else if(NewBackGroundNum == 3)
			i = 3;
		else
			NewBackGroundNum = 0;
			System.out.println(NewBackGroundNum);
		switch(i)
		{
			default:
			{
				LevelBG = "BigRoad.png";
				System.out.println("1"+LevelBG);
				break;
			}
			case 0:
			{
				LevelBG = "BigRoad.png";
				System.out.println("1"+LevelBG);
				break;
			}
			case 1:
			{
				LevelBG = "backgroundNewLevel.jpg";
				System.out.println("2"+LevelBG);
				break;
			}
			case 2:
			{
				LevelBG = "SoccerStadium.png";
				System.out.println("3"+LevelBG);
				break;
			}
			case 3:
				LevelBG = "TreeBackG.png";
				System.out.println("3"+LevelBG);
				break;
			
		}
		
	}
	public void DrawBackground(Graphics g)
	{
		//int screenX = -300;
		screenX = (int)(myShip.Location.getX() - getWidth() / 2);
		screenY = (int)(myShip.Location.getY() - getWidth() / 2);

		g.drawImage(Walls, (int)-myShip.Location.getX()-screenX,(int)-myShip.Location.getY()-screenY, 2300,2000,null);
	}
	public void PickBackGround(String img)
	{
			Walls = new ImageIcon(img).getImage();

	}


	public void PickPlayerImg()
	{
		
		if(ScreenObj.Gun == 1)
			PlayerImg = "ShotGunShooter.png";
		else if(ScreenObj.Gun == 2)
			PlayerImg = "PlayerFire.png";
		else if(ScreenObj.Gun == 3)
		PlayerImg = "mech.png";
		else
			PlayerImg = "shooter.png";
	}
	public void updatePlayerIMG(String Img)
	{
		Player.PlayerImg(Img);
	}
	
	
	
	public void endCard()
	{
		rasterGraphics.drawString("GAMEOVER: "+countdown,600,800);

	}
	public void drawHealth(Graphics g)
	{
		g.setColor(Color.PINK);
		g.fillRect(130, 28,(int)ScreenObj.Health*2, 16);
		
	}
	public void drawDash(Graphics g)
	{
		g.drawImage(Dash, 0,20, 900,620,null);
		
	}
	
	public void drawWalls(Graphics g)
	{
		//g.drawImage(Walls, 0,0, 2300,620,null);
	}
	


}
abstract class ScreenObj
{
	public Vector2D Velocity;
	public  Vector2D Location;
	public Vector2D Direction;
	public java.awt.Image image;
	public static java.awt.Image Playerimage;
	public int Radius;
	public boolean UP, DOWN, RIGHT, LEFT, RL, RR, SPACE,
	W,D,S,A,Q,E;
	public float speed;
	public float Angle, DeltaAngle;
	public boolean isAlive;
	static float score = 0;
	static float life = 3;
	static float Health = 100;
	static float Kills = 0;
	public static int Gun = 0;
	public abstract void Act(int frame, int invincible);
	public abstract void Draw(Graphics g,int Invinsible);
	public static String KillSound;
	
	public void Die()
	{
		isAlive = false;
		GameFrame.ScreenObjs.remove(this);
	}
	
	/**
	 * @returns the distance between this ScreenObj and the one passed in
	 */
	public float Distance(ScreenObj so)
	{
		double XDelta = Location.getX() - so.Location.getX();
		double YDelta = Location.getY() - so.Location.getY();
		return (float)Math.sqrt(XDelta * XDelta + YDelta*YDelta);
	}

	public float Distance(Vector2D so)
	{
		double XDelta = Location.getX() - so.getX();
		double YDelta = Location.getY() - so.getY();
		return (float)Math.sqrt(XDelta * XDelta + YDelta*YDelta);
	}
	public static void ChangeGuns(int i)
	{
		if(i == 0)
		{
			Gun =0;
		}
		else if(i == 1)
		{
			Gun = 1;
		}
	}
	public void Score(float point)
	{
		score += point;
	}
	public void reset()
	{
		score = 0;
		Kills = 0;
		Health =100;
		 if(Health >=100)
			Health = 100;
		 
		 
	}
	public static void playSound(String backgroundSound)
	{
		KillSound = backgroundSound;
		//plays the sound of the sting path that is provided to the file object
		//THE BOTTOM CODE WILL PLAY THE BACKGROUND SOUND
			try{
				File file = new File(backgroundSound);
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream ais = AudioSystem.
		            getAudioInputStream( file );
		        clip.open(ais);
		        clip.start();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		// // <-----------
	}
	public static void playSoundtwo(String backgroundSound)
	{
		//plays the sound of the sting path that is provided to the file object
		//THE BOTTOM CODE WILL PLAY THE BACKGROUND SOUND
			try{
				File file = new File(backgroundSound);
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream ais = AudioSystem.
		            getAudioInputStream( file );
		        clip.open(ais);
		        FloatControl gainControl = 
		        	    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		        	gainControl.setValue(+0.0f);
		        clip.start();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		// // <-----------
	}
}
class Mobs extends ScreenObj
{
	ScreenObj Target;
	public Color C;
	java.awt.Image Flash;
	public int spawn;
	int armor;
	int TotalDamage;
	//float speed = 3f;
	public Mobs(float x, float y, float Angle, float xv, float yv,int score,ScreenObj b,int Armor,int time,int Speed,String img)
	{
		//MaxSpeed = 7f;
		Location = new Vector2D();
		Velocity = new Vector2D();
		Direction = new Vector2D();
		
		Location.set(x,y);
		Velocity.set(xv, yv);
		this.Angle = Angle;
		speed = (float)(4.0+Speed);
		Radius = 15;
		Target = b;
	
		armor = Armor;
		spawn = time;
		image = new ImageIcon(img).getImage();

	}					
	public void Act(int frame,int Invinsible)
	{		
		if (--spawn <= 0)
		{
		Vector2D PlayerTargetLocation = new Vector2D(Location);
		PlayerTargetLocation.setX(PlayerTargetLocation.getX()-GameFrame.screenX);
		PlayerTargetLocation.setY(PlayerTargetLocation.getY()-GameFrame.screenY);
		
		//Vector2D TargetLocation = new Vector2D(Target.Location);
		Direction = Target.Location.subtract(PlayerTargetLocation);
		Direction.normalize();
		Location = Location.add(Direction.multiply(speed));
		//Angle = (float) GameFrame.myShip.Location.dot(this.Location);
		Angle = GameFrame.myShip.Location.dot(this.Location);
//

			for (ScreenObj so : GameFrame.ScreenObjs)
			{
				Vector2D TargetLo = new Vector2D(so.Location);
				TargetLo.setX(TargetLo.getX()+GameFrame.screenX);
				TargetLo.setY(TargetLo.getY()+GameFrame.screenY);
				
				if (so instanceof Shot)
				if (so == this)
					continue;
				 if (so instanceof Shot && Distance(TargetLo) < 50+Radius) //17
				{
					 float xH = (float)so.Location.getX();
				     float yH = (float)so.Location.getY();
					GameFrame.ScreenObjs.add(new HitImg(1,xH,yH,"blood.png"));
					playSound("Impact.wav");
					int i = 15;
					so.Score(i);
					 Health =(float) (Health+ 0.2) ;
					 if(Health >=100)
						 Health = 100;
					// System.out.println(TotalDamage+" <Total: Damage>  "+Shot.Damage);
					 TotalDamage= TotalDamage + Shot.Damage;
					 if(armor < TotalDamage)
					 {
						this.Die();
						Kills++;
						so.Score(i+100);
						//import sound mob killed
					 }
					break;
				}
				
			}	
		}
	}
	public void Draw(Graphics g,int Invinsible)
	{
		if (--spawn <= 0)
		{
			AffineTransform at = new AffineTransform();
			at.setTransform(GameFrame.identity);
			at.translate((int)Location.getX()-GameFrame.screenX,(int)Location.getY()-GameFrame.screenY);
			//at.rotate(Math.toRadians(Angle),32,32);
			at.rotate(Math.atan2(Direction.getX(),-Direction.getY()),32,32);

			Graphics2D g2d = (Graphics2D)g;
			g2d.drawImage(image,at,null);	

		}
	}
	
	


}

class Player extends ScreenObj implements KeyListener
{
	public final int MOVE_AMOUNT = 7, ANGLE_AMOUNT = 5;	
	public final float MoveDeltaScale = 0.1f, DirectionDelta = 3;
	public Player()
	{
		System.out.println(GameFrame.PlayerImg);
		System.out.println("Locating Player Image");
		PlayerImg(GameFrame.PlayerImg);

		
		
		Direction = new Vector2D(0,1);
		Velocity = new Vector2D(0,0);
		Location = new Vector2D(300,300);
		DeltaAngle = 15;
	}
	public static void PlayerImg(String img)
	{

		Playerimage = new ImageIcon(img).getImage();
		if (Playerimage.getHeight(null) < 8)
			System.out.println("Could not find ship");
	}
	public void Draw(Graphics g,int Invinsible) 
	{			
		
		AffineTransform at = new AffineTransform();
		at.setTransform(GameFrame.identity);
		at.translate(Location.getX()-32,Location.getY()-32);
		at.rotate(Math.toRadians(Angle),32,32);
		//at.rotate(Math.atan2(Direction.getX(),Direction.getY()),32,32);

		
		Graphics2D g2d = (Graphics2D)g;
		if (Invinsible == 0 || Invinsible % 3 == 0)
		g2d.drawImage(Playerimage,at,null);		
	}
	public void Act(int frame,int Invinsible)
	{
		
		
		for (ScreenObj so : GameFrame.ScreenObjs)
		{
			Vector2D TargetLo = new Vector2D(so.Location);
			TargetLo.setX(TargetLo.getX()-GameFrame.screenX);
			TargetLo.setY(TargetLo.getY()-GameFrame.screenY);

			if (so == this)
				continue;
			 if (so instanceof Mobs && Distance(TargetLo) < 50+Radius && Invinsible == 0)
			{
				 Health = Health - 3 ;
				 float xH = (float)Location.getX();
			     float yH = (float)Location.getY();
				 GameFrame.ScreenObjs.add(new HitImg(1,xH-60,yH-20,"blood.png"));
				 if(Health <= 0)
				 {
					playSound("Pain.wav");
					 ChangeGuns(0);
					 life--;
				   Health =100;
				   GameFrame.Invincible = 30;
				 }
				if(life <= 0)
				{
					this.Die();
					
					Health = 0;
					life = 0;
					//reset();				
				}
				break;
			}
		}
		//boundaries
		if (Location.getX() > 860)
			Location.set(859, Location.getY());
		else if (Location.getX() <239)
			Location.set(240,Location.getY());
		if (Location.getY() > 650)
			Location.set(Location.getX(),651);
		else if (Location.getY() < 220)
			Location.set(Location.getX(),221);
		
		Location = Location.add( Velocity );
		
		if (W)
		{
			//Velocity = Velocity.add(Direction.multiply(MoveDeltaScale));
			Location.set(Location.getX(), Location.getY()-(MOVE_AMOUNT+(GameFrame.Level/2)));
		}
		if (S)
		{
			//Velocity = Velocity.add(Direction.multiply(-MoveDeltaScale));
			Location.set(Location.getX(), Location.getY()+(MOVE_AMOUNT+(GameFrame.Level/2)));
		}
		if (D)
		{
			//Direction.rotate(DirectionDelta);
			//Angle+=DeltaAngle;
			Location.set(Location.getX()+(MOVE_AMOUNT+(GameFrame.Level/2)), Location.getY());


		}
		if (A)
		{
			Location.set(Location.getX()-(MOVE_AMOUNT+(GameFrame.Level/2)), Location.getY());
			//Direction.rotate(-DirectionDelta);
			//Angle-=DeltaAngle;

		}
		if (RIGHT)
		{
			//Location.set(Location.getX()-MOVE_AMOUNT, Location.getY());
			//Direction.rotate(-DirectionDelta);
			Angle+=DeltaAngle;
		}
		if (LEFT)
		{
			//Location.set(Location.getX()-MOVE_AMOUNT, Location.getY());
			//Direction.rotate(-DirectionDelta);
			Angle-=DeltaAngle;
		}
		if(E)
		{
			
			
			
		}
			
//		if (Velocity.getLength() > 5)
//			Velocity = Velocity.normalize().multiply(5);
		if (SPACE && frame % 8 == 0)
		{	//import gun sound 
			 int k = Gun;
			switch(k)
			{
				case 0:
				{
					//Pistol
				float x = (float)Location.getX();
				float y = (float)Location.getY();
				GameFrame.ScreenObjs.add(new Shot(30,x-15,y+5,Angle,"shot.png",10,10,1));
				System.out.println("Gun0: "+Gun);
				playSound("PistolSound.wav");
				break;
				}
				case 1:
				{
					//ShotGun
				playSound("shotgunsound.wav");

				float x = (float)Location.getX();
				float y = (float)Location.getY();
				GameFrame.ScreenObjs.add(new Shot(10,x,y,Angle,"ShotGunBullets.png",30,30,3));
				GameFrame.ScreenObjs.add(new Shot(10,x,y,Angle+30,"ShotGunBullets.png",30,30,3));
				GameFrame.ScreenObjs.add(new Shot(10,x,y,Angle-30,"ShotGunBullets.png",30,20,3));
				System.out.println("Gun1: "+Gun);

				break;
				}
				case 2:
				{
					//fire
				playSound("Fire.wav");
				System.out.println("Gun1: "+Gun);
				
				float x = (float)Location.getX();
				float y = (float)Location.getY();
				GameFrame.ScreenObjs.add(new Shot(10,x,y,Angle,"FireBall.png",60,60,3));
				GameFrame.ScreenObjs.add(new Shot(10,x,y,Angle+30,"FireBall.png",60,60,3));
				GameFrame.ScreenObjs.add(new Shot(10,x,y,Angle+15,"FireBall.png",60,60,3));
				GameFrame.ScreenObjs.add(new Shot(10,x,y,Angle-30,"FireBall.png",60,60,3));
				GameFrame.ScreenObjs.add(new Shot(10,x,y,Angle-15,"FireBall.png",60,60,3));
				break;
				}
				case 3:
				{
					//mech suit
				playSound("RayGunSound.wav");
				System.out.println("Gun1: "+Gun);
				
				float x = (float)Location.getX();
				float y = (float)Location.getY();
				GameFrame.ScreenObjs.add(new Shot(10,x,y,Angle,"RayShot.png",60,60,5));
				GameFrame.ScreenObjs.add(new Shot(10,x*3/4,y,Angle+20,"RayShot.png",60,60,5));
				GameFrame.ScreenObjs.add(new Shot(10,x*3/4,y*3/4,Angle+20,"RayShot.png",60,60,5));
			
				break;
				}
			
			}
		}
		

	}
	//KeyListener
	public void keyTyped(KeyEvent e) { /*do nothing*/ }

    /** Handle the key-pressed event from the text field. */
    public void keyPressed(KeyEvent e) 
    { 
    	
    	if (e.getKeyCode() == KeyEvent.VK_W)
    	{
    		W = true;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_S)
    	{
    		S = true;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_D)
    	{
    		D = true;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_A)
    	{
    		A = true;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_SPACE)
    	{
    		SPACE = true;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_UP)
    	{
    		UP=true;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_DOWN)
    	{
    		DOWN=true;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    	{
    		RIGHT=true;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_LEFT)
    	{
    		LEFT=true;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_E)
    	{
    		E=true;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_Q)
    	{
    		RL=true;
    	}
    }
    /** Handle the key-released event from the text field. */
    public void keyReleased(KeyEvent e) 
    {	
    	if (e.getKeyCode() == KeyEvent.VK_W)
    	{
    		W = false;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_S)
    	{
    		S = false;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_D)
    	{
    		D = false;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_A)
    	{
    		A = false;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_SPACE)
    	{
    		SPACE = false;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_UP)
    	{
    		UP=false;
     	}
    	if (e.getKeyCode() == KeyEvent.VK_DOWN)
    	{
    		DOWN=false;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    	{
    		RIGHT=false;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_LEFT)
    	{
    		LEFT=false;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_E)
    	{
    		E=false;
    	}
    	if (e.getKeyCode() == KeyEvent.VK_Q)
    	{
    		RL=false;
    	}              
    }
}
class Shot extends ScreenObj
{
	public int TimeToLive;
	int shotX;
	int shotY;
	public static int Damage;

	public Shot(int time, float x, float y, float Angle,String img,int ShotX,int ShotY,int damage)
	{
		Location = new Vector2D();
		Location.set(x,y);
		this.Angle = Angle;
		speed = 15;
		TimeToLive = time;
		image = new ImageIcon(img).getImage();
		shotX = ShotX;
		shotY = ShotY;
		Damage = damage;
	}	
	public void Act(int frame,int Invinsible)
	{
		Location.set(Location.getX()+(float)Math.sin(Math.toRadians(Angle))*speed*2,
				Location.getY()-(float)Math.cos(Math.toRadians(Angle)) * speed*2);
		if (--TimeToLive <= 0)
			Die();
		if(KillSound == "Impact.wav")
			Die();
	}
	public void Draw(Graphics g,int Invinsible)
	{
		g.drawImage(image,(int)Location.getX(),(int)Location.getY(),shotX,shotY,null);			
	}
}
class HitImg extends ScreenObj
{
	public int TimeToLive;
	
	public HitImg(int time, float x, float y,String Img)
	{
		Location = new Vector2D();
		Location.set(x,y);
		speed = 15;
		TimeToLive = time;
		image = new ImageIcon(Img).getImage();

	}	
	public void Act(int frame,int Invinsible)
	{
		Location.set(Location.getX(),Location.getY());

		if (--TimeToLive <= 0)
			Die();
	}
	public void Draw(Graphics g,int Invinsible)
	{

		AffineTransform at = new AffineTransform();
		at.setTransform(GameFrame.identity);
		at.translate(Location.getX(),Location.getY());
		//at.rotate(Math.atan2(Direction.getX(),Direction.getY()),32,32);

		
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(image,at,null);	
		
		//g.drawImage(image,(int)Location.getX(),(int)Location.getY(),50,50,null);			

	}
}

class AirDrop extends ScreenObj
{
	Random random = new Random();
	int RamdomNum = random.nextInt(3) + 1; //Random number is only between 1-3 for testing,change 3 to 5 for full game
	public String BoxDropIMG;

	public AirDrop()
	{
		Location = new Vector2D(-200,-280);
		image = new ImageIcon("DropBox.png").getImage();

	}
	@Override
	public void Act(int frame, int invincible) 
	{
		Vector2D PlayerTargetLocation = new Vector2D(Location);
		PlayerTargetLocation.setX(PlayerTargetLocation.getX()-GameFrame.screenX);
		PlayerTargetLocation.setY(PlayerTargetLocation.getY()-GameFrame.screenY);
		
		for (ScreenObj so : GameFrame.ScreenObjs)
		{
			Vector2D TargetLo = new Vector2D(so.Location);
			TargetLo.setX(TargetLo.getX()+GameFrame.screenX);
			TargetLo.setY(TargetLo.getY()+GameFrame.screenY);

			if (so == this)
				continue;
			 if (so instanceof Player && Distance(TargetLo) < 400+Radius)
			{
				 
				 System.out.println("Door HIT!!!!!!");
				 if(score > 320)
				 {
					 BoxPick();
					 System.out.println(BoxDropIMG);
					 float xH = (float)so.Location.getX()+50;
					 float yH = (float)so.Location.getY()+10;
					GameFrame.ScreenObjs.add(new HitImg(10,xH,yH,BoxDropIMG));
					GameFrame.ScreenObjs.add(new HitImg(10,xH-30,yH-30,"20.png"));
					playSound("codzombielaugh.wav");
					score = score - 320;
					this.Die();
					//Gun = 1;
					break;
				 }
			}
			
		}
		
	}
	public void BoxPick()
	{
		
		//Health
		switch(RamdomNum)
		{
			case 1:
			{
				Gun = 1;
				BoxDropIMG = "ShotGun.png";
				System.out.println("Gun UpGrade");

				break;
			}
			case 2:
			{
				Gun = 2;
				BoxDropIMG = "ShotGun.png";
				System.out.println("Fire UpGrade");

				break;
			}
			case 3:
			{
				Gun = 3;
				BoxDropIMG = "ShotGun.png";
				System.out.println("Fire UpGrade");

				break;
			}
			case 4:
			{
				BoxDropIMG = "HPup.png";

				Health =  Health+50;
				if(Health >=100)
					 Health = 100;
				System.out.println("HP");
				break;
			}
			case 5:
			{
				BoxDropIMG = "Lifeup.png";
				life++;
				if(life >=5)
					life = 5;
				System.out.println("Life+1");

				break;
			}
			
		}

		
		
	}

	@Override
	public void Draw(Graphics g, int Invinsible) {
		
		AffineTransform at = new AffineTransform();
		at.setTransform(GameFrame.identity);
		at.translate((int)Location.getX()-GameFrame.screenX*2,(int)Location.getY()-GameFrame.screenY*2);
		//at.rotate(Math.toRadians(Angle),32,32);
		//at.rotate(Math.atan2(Direction.getX(),-Direction.getY()),32,32);

		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(image,at,null);	
		

	}
	
}
class Vector2D 
{
    private float x;
    private float y;

    public Vector2D() 
    {
        this.setX(0);
        this.setY(0);
    }
    
    public Vector2D(float x, float y) 
    {
        this.setX(x);
        this.setY(y);
    }
    public Vector2D(Vector2D v) 
    {
        this.setX(v.getX());
        this.setY(v.getY());
    }
    public void set(float x, float y) 
    {
        this.setX(x);
        this.setY(y);
    }

    public void setX(float x) 
    {
        this.x = x;
    }

    public void setY(float y) 
    {
        this.y = y;
    }

    public float getX() 
    {
        return x;
    }

    public float getY() 
    {    	
        return y;
    }
    public void rotate(double angle) 
    {
    	Vector2D newVect = new Vector2D(this);
		newVect.setX(getX() * (float)Math.cos(Math.toRadians(angle)) + 
				getY() * (float)Math.sin(Math.toRadians(angle)));
		newVect.setY(-getX() * (float)Math.sin(Math.toRadians(angle)) + 
				getY() * (float)Math.cos(Math.toRadians(angle)));
		this.set(newVect.getX(),newVect.getY());
    }
    //Specialty method used during calculations of ball to ball collisions.
    public float dot(Vector2D v2) 
    {
    	float result = 0.0f;
        result = this.getX() * v2.getX() + this.getY() * v2.getY();
        return result;
    }

    public float getLength() 
    {
        return (float) Math.sqrt(getX() * getX() + getY() * getY());
    }

    public Vector2D add(Vector2D v2) 
    {
        Vector2D result = new Vector2D();
        result.setX(getX() + v2.getX());
        result.setY(getY() + v2.getY());
        return result;
    }

    public Vector2D subtract(Vector2D v2) 
    {
        Vector2D result = new Vector2D();
        result.setX(this.getX() - v2.getX());
        result.setY(this.getY() - v2.getY());
        return result;
    }

    public Vector2D multiply(float scaleFactor) 
    {
        Vector2D result = new Vector2D();
        result.setX(this.getX() * scaleFactor);
        result.setY(this.getY() * scaleFactor);
        return result;
    }

    //Specialty method used during calculations of ball to ball collisions.
    public Vector2D normalize() 
    {
    	float length = getLength();
        if (length != 0.0f) 
        {
            this.setX(this.getX() / length);
            this.setY(this.getY() / length);
        } 
        else 
        {
            this.setX(0.0f);
            this.setY(0.0f);
        }
        return this;
    }
    public String toString()
    {
    	return "("+x+", "+y+")";
    }
}