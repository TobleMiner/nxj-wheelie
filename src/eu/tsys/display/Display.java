package eu.tsys.display;

import java.awt.Point;
import java.awt.Rectangle;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import eu.tsys.util.Vector;

public class Display
{
	public static int WIDTH = 100;
	public static int HEIGHT = 64;
	
	private Image buffer;
	private Graphics graphics;
	private Rectangle indicatorArea;
	private Point indicatorStartpoint;
	private Rectangle infoArea;
	private Point infoStartpoint;
	private Graphics display;
	
	private int indicatorWidth = WIDTH / 2;
	private int indicatorHeight = HEIGHT;
	
	private int infoWidth = WIDTH / 2;
	private int infoHeight = HEIGHT;
	
	public Display()
	{
		this.display = new Graphics();
		this.buffer = Image.createImage(WIDTH, HEIGHT);
		this.graphics = buffer.getGraphics();
		this.indicatorArea = new Rectangle(WIDTH - this.indicatorWidth,
				HEIGHT - this.indicatorHeight,
				this.indicatorWidth,
				this.indicatorHeight);
		this.indicatorStartpoint = new Point(this.indicatorArea.x + this.indicatorArea.width / 2,
				this.indicatorArea.y + this.indicatorArea.height);
		this.infoArea = new Rectangle(0, 0, this.infoWidth, this.infoHeight);
		this.infoStartpoint = new Point(this.infoArea.x, this.infoArea.y);
	}
	
	public void updateRotationInfo(Vector vec)
	{
		this.fillRect(this.infoArea, Graphics.WHITE);
		char[] charsX = ("X: " + Double.toString(vec.getX())).toCharArray();
		char[] charsY = ("Y: " + Double.toString(vec.getY())).toCharArray();
		char[] charsZ = ("Z: " + Double.toString(vec.getZ())).toCharArray();
		this.graphics.setColor(Graphics.BLACK);
		this.graphics.setFont(Font.getDefaultFont());
		this.graphics.drawChars(charsX, 0, charsX.length, this.indicatorStartpoint.x,
				this.indicatorStartpoint.y, 0);
		this.graphics.drawChars(charsY, 0, charsY.length, this.indicatorStartpoint.x,
				this.indicatorStartpoint.y + 20, 0);
		this.graphics.drawChars(charsZ, 0, charsZ.length, this.indicatorStartpoint.x,
				this.indicatorStartpoint.y + 40, 0);
	}
	
	public void updateIndicator(Vector vec)
	{
		Vector direction = vec.clone();
		direction.normalize();
		this.fillRect(this.indicatorArea, Graphics.WHITE);
		Point p2 = new Point((int)(this.indicatorStartpoint.x + this.indicatorWidth * (-direction.getZ())),
				(int)(this.indicatorStartpoint.y + this.indicatorHeight * (-direction.getX())));
		this.drawLine(this.indicatorStartpoint, p2, Graphics.BLACK, Graphics.SOLID);
	}
	
	public void drawLine(Point p1, Point p2, int color, int style)
	{
		this.graphics.setStrokeStyle(style);
		this.graphics.setColor(color);
		this.graphics.drawLine((int)p1.getX(), (int)p1.getY(),
				(int)p2.getX(), (int)p2.getY());
	}
	
	public void fillRect(Rectangle rect, int color)
	{
		this.graphics.setColor(color);
		this.graphics.fillRect(rect.x, rect.y, rect.width, rect.height);
	}
	
	public void writeBuffer()
	{
		this.display.drawImage(this.buffer, 0, 0, 0);
	}
}
