package chemicalReaction;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Table extends GameObject
{
	/** テーブルの手札の数 */
	public static final int HANDCARD_NUM = 20;
	/** カードフレームのx座標 */
	//public static final int[] FRAME_X = new int[FRAME_NUM];
	/** カードフレームのy座標 */
	//public static final int[] FRAME_Y = new int[FRAME_NUM];

	/** テーブルの山札の数 */
	public static final int DECKCARD_NUM = 10;
	/** 山札のx座標 */
	public static final int DECKCARD_X = 50;
	/** 山札のy座標 */
	public static final int DECKCARD_Y = Play.DISPLAY_HEIGHT / 2 - Card.HEIGHT / 2;

	/** カードを出す場の縦幅（余白も入れて） */
	//public static final int FIELD_HEIGHT = 227;
	/** カードを出す場の横幅（余白も入れて） */
	//public static final int FIELD_WIDTH = 831;

	/** 手札を出す場の縦幅（余白も入れて） */
	//public static final int HANDCARD_HEIGHT = 227;
	/** 手札を出す場の横幅（余白も入れて） */
	//public static final int HANDCARD_WIDTH = 1247;

	/** ボタンのx座標 */
	public static final int BUTTON_X = 1210;
	/** ボタンのy座標 */
	public static final int BUTTON_Y = 337;

	/** 場札 */
	public static ArrayList<Card> fieldCards = new ArrayList<>();

	/** 手札 */
	public static ArrayList<Card> handCards = new ArrayList<>();

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
		ImageManager.renderHandCardFrame(CardPosition.HANDCARD.positionX, CardPosition.HANDCARD.positionY);
		for (int i = 0; i < DECKCARD_NUM; i++)
		{
			ImageManager.renderCardBack(DECKCARD_X + (DECKCARD_NUM - 1 - i), DECKCARD_Y + (DECKCARD_NUM - 1 - i));
		}
	}

	public static void addFieldCard(Card addFieldCard)
	{
		fieldCards.add(addFieldCard);
	}

	public static void removeFieldCard(Card removeFieldCard)
	{
		fieldCards.remove(removeFieldCard);
	}

	public static int getFieldCardX(Card fieldCard)
	{
		//System.out.println(fieldCards.indexOf(fieldCard) + " " + fieldCards.size());
		if (fieldCards.size() != 0)
		{
			return CardPosition.FIELD.positionX + CardPosition.FIELD.width * fieldCards.indexOf(fieldCard) / fieldCards.size();
		}
		return CardPosition.FIELD.positionX;
	}

	public static void addHandCard(Card addHandCard)
	{
		handCards.add(addHandCard);
	}

	public static void removeHandCard(Card removeHandCard)
	{
		handCards.remove(removeHandCard);
	}

	public static int getHandCardX(Card handCard)
	{
		if (handCards.size() != 0)
		{
			return CardPosition.HANDCARD.positionX + CardPosition.HANDCARD.width * handCards.indexOf(handCard) / handCards.size();
		}
		return CardPosition.HANDCARD.positionX;
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
		HANDCARD(50, 623, Card.HEIGHT, 1247, CardStatus.HANDCARD),
		/** 山札 */
		DECKCARD(50, 337, Card.HEIGHT, Card.WIDTH, CardStatus.DECKCARD),
		/** カードを出す場 */
		FIELD(220, 337, Card.HEIGHT, 831, CardStatus.FIELDCARD),
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
