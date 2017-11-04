package chemicalReaction;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Background extends GameObject
{
	/**
	 * 縦幅　余白も入れて
	 */
	public static final int HEIGHT = 227;
	/**
	 * 横幅　余白も入れて
	 */
	public static final int WIDTH = 170;
	/**
	 * 余白の枠の幅
	 */
	public static final int SPACE_BREADTH = 23;
	/**
	 * カード番号
	 */
	private final int num;

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
	}
}
