package chemicalReaction;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Table extends GameObject
{
	/** テーブルのカードフレームの数 */
	public static final int FRAME_NUM = 8;
	/** テーブルの山札の数 */
	public static final int CARDBACK_NUM = 10;
	public static final int[] FRAME_X;
	public static final int[] FRAME_Y;

	/**
	 * コンストラクタ
	 */
	Table()
	{
		active = true;
		for (int i = 0; i < FRAME_NUM; i++)
		{
			FRAME_X[i] = (Card.WIDTH) * i - 150;
			FRAME_Y[i] = Play.DISPLAY_HEIGHT - Card.HEIGHT - 50;
		}
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
