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
	/** カードフレームのx座標 */
	public static final int[] FRAME_X = new int[FRAME_NUM];
	/** カードフレームのy座標 */
	public static final int[] FRAME_Y = new int[FRAME_NUM];

	/** テーブルの山札の数 */
	public static final int CARDBACK_NUM = 10;
	/** 山札のx座標 */
	public static final int CARDBACK_X = 50;
	/** 山札のy座標 */
	public static final int CARDBACK_Y = Play.DISPLAY_HEIGHT / 2 - Card.HEIGHT / 2;

	/**
	 * コンストラクタ
	 */
	Table()
	{
		active = true;
		for (int i = 0; i < FRAME_NUM; i++)
		{
			FRAME_X[i] = (Card.WIDTH) * i + 20;
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
		for (int i = 0; i < FRAME_NUM; i++)
		{
			ImageManager.renderCardFrame(FRAME_X[i], FRAME_Y[i]);
		}
		for (int i = 0; i < CARDBACK_NUM; i++)
		{
			ImageManager.renderCardBack(CARDBACK_X + (CARDBACK_NUM - 1 - i), CARDBACK_Y + (CARDBACK_NUM - 1 - i));
		}
		//System.out.println(CARDBACK_X + " " + CARDBACK_Y);
	}
}
