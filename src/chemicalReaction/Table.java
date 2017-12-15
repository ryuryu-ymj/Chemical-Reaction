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
	public static final int CARDBACK_NUM = 10;
	/** 山札のx座標 */
	//public static final int CARDBACK_X = 50;
	/** 山札のy座標 */
	//public static final int CARDBACK_Y = Play.DISPLAY_HEIGHT / 2 - Card.HEIGHT / 2;

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
		for (int i = 0; i < HANDCARD_NUM; i++)
		{
			ImageManager.renderCardFrame(CardPosition.getHandCardPosition(i).positionX, CardPosition.getHandCardPosition(i).positionY);
		}
		for (int i = 0; i < CARDBACK_NUM; i++)
		{
			ImageManager.renderCardBack(CardPosition.deckCard.positionX + (CARDBACK_NUM - 1 - i), CardPosition.deckCard.positionY + (CARDBACK_NUM - 1 - i));
		}
	}

	/**
	 * カードの位置
	 * @author ryuryu
	 *
	 */
	public static enum CardPosition
	{
		handCard1(20, 623),
		handCard2(190, 623),
		handCard3(360, 623),
		handCard4(530, 623),
		handCard5(700, 623),
		handCard6(870, 623),
		handCard7(1040, 623),
		handCard8(1210, 623),
		/** 山札 */
		deckCard(50, 337),
		;

		/** ポジションの左上の座標 */
		private int positionX, positionY;
		/** ポジションに置かれているカードの番号 */
		private int numOfHavingCard;

		private CardPosition(int positionX, int positionY)
		{
			this.positionX = positionX;
			this.positionY = positionY;
			numOfHavingCard = 0;
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
		 *
		 * @return ポジションに置かれているカードの番号
		 */
		public int getNumOfHavingCard()
		{
			return numOfHavingCard;
		}

		/**
		 * 指定された手札の位置を返す
		 * @param handCardNum 手札の番号　左から0~
		 * @return 指定された手札の位置
		 */
		public static CardPosition getHandCardPosition(int handCardNum)
		{
			if (handCardNum >= 0 && handCardNum <= HANDCARD_NUM)
			{
				return values()[handCardNum];
			}
			return null;
		}

		/**
		 * 山札の位置を返す
		 * @return 山札の位置
		 */
		public static CardPosition getDeckCardPosition()
		{
			return deckCard;
		}
	}
}
