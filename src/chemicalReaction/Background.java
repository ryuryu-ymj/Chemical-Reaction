package chemicalReaction;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Background extends GameObject
{
	/**
	 * 縦幅
	 */
	public static final int HEIGHT = 227 * 5 / 6;
	/**
	 * 横幅
	 */
	public static final int WIDTH = (int) (170 * 3.5 / 4.5);
	/**
	 * カード番号
	 */
	private final int num;

	/** 画像 */
	private static Image[] img;
	static
	{
		try
		{
			img = new Image[2];
			SpriteSheet ss = new SpriteSheet("res/img/background.png", (int) (WIDTH * 4.5 / 3.5), HEIGHT * 6 / 5);
			for (int i = 0; i < img.length; i++)
			{
				img[i] = ss.getSubImage(i % 2, i / 2);
			}
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * コンストラクタ
	 * @param num カード番号
	 * @param x x成分 左上の頂点
	 * @param y y成分 左上の頂点
	 */
	Background(int num, float x, float y)
	{
		active = true;
		this.num = num;
		this.x = x;
		this.y = y;
	}

	@Override
	public void update(GameContainer gc)
	{
	}

	@Override
	public void render(Graphics g)
	{
		img[num].draw(x, y);
	}
}
