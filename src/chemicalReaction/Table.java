package chemicalReaction;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Table extends GameObject
{
	/** テーブルの手札の数 */
	public static final int HANDCARD_NUM = 8;
	/** カードフレームのx座標 */
	//public static final int[] FRAME_X = new int[FRAME_NUM];
	/** カードフレームのy座標 */
	//public static final int[] FRAME_Y = new int[FRAME_NUM];

	/** テーブルの山札の数 */
	public static final int DECKCARD_NUM = 10;
	/** 山札のx座標 */
	//public static final int CARDBACK_X = 50;
	/** 山札のy座標 */
	//public static final int CARDBACK_Y = Play.DISPLAY_HEIGHT / 2 - Card.HEIGHT / 2;

	/** カードを出す場の縦幅（余白も入れて） */
	public static final int FIELD_HEIGHT = 227;
	/** カードを出す場の横幅（余白も入れて） */
	public static final int FIELD_WIDTH = 928;

	/** ボタンのx座標 */
	public static final int BUTTON_X = 1210;
	/** ボタンのy座標 */
	public static final int BUTTON_Y = 337;

	/**
	 * コンストラクタ
	 */
	Table()
	{
		active = true;
		/*for (int i = 0; i < FRAME_NUM; i++)
		{
			//FRAME_X[i] = (Card.WIDTH) * i + 20;
			FRAME_Y[i] = Play.DISPLAY_HEIGHT - Card.HEIGHT - 50;
		}*/
	}

	@Override
	public void update(GameContainer gc)
	{
	}

	@Override
	public void render(Graphics g)
	{
		ImageManager.renderField(Position.FIELD.positionX, Position.FIELD.positionY);
		ImageManager.renderButton(BUTTON_X, BUTTON_Y);
		ImageManager.renderCardFrame(Position.THROWCARD.positionX, Position.THROWCARD.positionY);
		for (int i = 0; i < HANDCARD_NUM; i++)
		{
			ImageManager.renderCardFrame(Position.getHandCardPosition(i).positionX, Position.getHandCardPosition(i).positionY);
		}
		for (int i = 0; i < DECKCARD_NUM; i++)
		{
			ImageManager.renderCardBack(Position.DECKCARD.positionX + (DECKCARD_NUM - 1 - i), Position.DECKCARD.positionY + (DECKCARD_NUM - 1 - i));
		}
	}

	/**
	 * カードを置く位置
	 * @author ryuryu
	 *
	 */
	public static enum Position
	{
		HANDCARD1(20, 623),
		HANDCARD2(190, HANDCARD1.positionY),
		HANDCARD3(360, HANDCARD1.positionY),
		HANDCARD4(530, HANDCARD1.positionY),
		HANDCARD5(700, HANDCARD1.positionY),
		HANDCARD6(870, HANDCARD1.positionY),
		HANDCARD7(1040, HANDCARD1.positionY),
		HANDCARD8(1210, HANDCARD1.positionY),
		/** 山札 */
		DECKCARD(50, 337),
		/** カードを出す場 */
		FIELD(220, 337),
		/** 捨て札 */
		THROWCARD(1040, 337),
		;

		/** ポジションの左上の座標 */
		private int positionX, positionY;

		private Position(int positionX, int positionY)
		{
			this.positionX = positionX;
			this.positionY = positionY;
		}

		/**
		 *
		 * @return ポジションの左上のx座標
		 */
		public int getPositionX()
		{
			return positionX;
		}

		/**
		 *
		 * @return ポジションの左上のy座標
		 */
		public int getPositionY()
		{
			return positionY;
		}

		/**
		 * 指定された手札の位置を返す
		 * @param handCardNum 手札の番号　左から0~
		 * @return 指定された手札の位置
		 */
		public static Position getHandCardPosition(int handCardNum)
		{
			if (handCardNum >= 0 && handCardNum <= HANDCARD_NUM)
			{
				return values()[handCardNum];
			}
			return null;
		}
	}
}
