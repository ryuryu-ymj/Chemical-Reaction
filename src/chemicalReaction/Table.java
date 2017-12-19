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
		ImageManager.renderField(CardPosition.FIELD.positionX, CardPosition.FIELD.positionY);
		ImageManager.renderButton(BUTTON_X, BUTTON_Y);
		ImageManager.renderCardFrame(CardPosition.THROWCARD.positionX, CardPosition.THROWCARD.positionY);
		for (int i = 0; i < HANDCARD_NUM; i++)
		{
			ImageManager.renderCardFrame(CardPosition.getHandCardPosition(i).positionX, CardPosition.getHandCardPosition(i).positionY);
		}
		for (int i = 0; i < DECKCARD_NUM; i++)
		{
			ImageManager.renderCardBack(CardPosition.DECKCARD.positionX + (DECKCARD_NUM - 1 - i), CardPosition.DECKCARD.positionY + (DECKCARD_NUM - 1 - i));
		}
	}

	public static enum CardStatus
	{
		/** 手札 */
		HANDCARD,
		/** 山札 */
		DECKCARD,
		/** 場札 */
		FIELDCARD,
		/** 捨て札 */
		THROWCARD,
		;
	}

	/**
	 * カードを置く位置
	 * @author ryuryu
	 *
	 */
	public static enum CardPosition
	{
		HANDCARD1(20, 623, Card.HEIGHT, Card.WIDTH, CardStatus.HANDCARD),
		HANDCARD2(190, HANDCARD1.positionY, Card.HEIGHT, Card.WIDTH, CardStatus.HANDCARD),
		HANDCARD3(360, HANDCARD1.positionY, Card.HEIGHT, Card.WIDTH, CardStatus.HANDCARD),
		HANDCARD4(530, HANDCARD1.positionY, Card.HEIGHT, Card.WIDTH, CardStatus.HANDCARD),
		HANDCARD5(700, HANDCARD1.positionY, Card.HEIGHT, Card.WIDTH, CardStatus.HANDCARD),
		HANDCARD6(870, HANDCARD1.positionY, Card.HEIGHT, Card.WIDTH, CardStatus.HANDCARD),
		HANDCARD7(1040, HANDCARD1.positionY, Card.HEIGHT, Card.WIDTH, CardStatus.HANDCARD),
		HANDCARD8(1210, HANDCARD1.positionY, Card.HEIGHT, Card.WIDTH, CardStatus.HANDCARD),
		/** 山札 */
		DECKCARD(50, 337, Card.HEIGHT, Card.WIDTH, CardStatus.DECKCARD),
		/** カードを出す場 */
		FIELD(220, 337, 227, 698, CardStatus.FIELDCARD),
		/** 捨て札 */
		THROWCARD(1040, 337, Card.HEIGHT, Card.WIDTH, CardStatus.THROWCARD),
		;

		/** ポジションの左上の座標 */
		private int positionX, positionY;
		/** ポジションの縦幅 */
		private int height;
		/** ポジションの横幅 */
		private int width;
		/** そのポジションに置かれるカードの種類 */
		private CardStatus cardStatus;

		/**
		 * コンストラクタ
		 * @param positionX ポジションの左上の座標
		 * @param positionY ポジションの左上の座標
		 * @param height ポジションの縦幅
		 * @param width ポジションの横幅
		 */
		private CardPosition(int positionX, int positionY, int height, int width, CardStatus status)
		{
			this.positionX = positionX;
			this.positionY = positionY;
			this.height = height;
			this.width = width;
			this.cardStatus = status;
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
		public static CardPosition getHandCardPosition(int handCardNum)
		{
			if (handCardNum >= 0 && handCardNum <= HANDCARD_NUM)
			{
				return values()[handCardNum];
			}
			return null;
		}

		/**
		 *
		 * @return ポジションの縦幅
		 */
		public int getHeight()
		{
			return height;
		}

		/**
		 *
		 * @return ポジションの横幅
		 */
		public int getWidth()
		{
			return width;
		}

		public CardStatus getStatus()
		{
			return cardStatus;
		}
	}
}
