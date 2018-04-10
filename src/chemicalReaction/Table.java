package chemicalReaction;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Table extends GameObject
{
	/** テーブルの手札の数 */
	public static final int HANDCARD_NUM = 10;
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

	/** "OK"ボタンのx座標 */
	//public static final int BUTTON_OK_X = (int)Table.CardPosition.FIELD.getPositionX() + Table.CardPosition.FIELD.getWidth() / 2 - 100;//1210
	/** "OK"ボタンのy座標 */
	//public static final int BUTTON_OK_Y = (int)Table.CardPosition.FIELD.getPositionY() + Table.CardPosition.FIELD.getHeight() / 2 - 25;
	/** "OK"ボタンの横幅 */
	//public static final int BUTTON_OK_WIDTH = 200;
	/** "OK"ボタンの縦幅 */
	//public static final int BOTTON_OK_HEIGHT = 50;
	//private boolean isRenderButtonOfOk;

	/** 場札 */
	private static ArrayList<Card> fieldCards = new ArrayList<>();

	/** 手札 */
	private static ArrayList<Card> handCards = new ArrayList<>();

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
		ImageManager.renderCardFrame(CardPosition.THROWCARD.positionX, CardPosition.THROWCARD.positionY);
		ImageManager.renderHandCardFrame(CardPosition.HANDCARD.positionX, CardPosition.HANDCARD.positionY);
	}

	/**
	 * 山札を描画する
	 * @param g
	 */
	public void renderDeckCard(Graphics g)
	{
		for (int i = 0; i < DECKCARD_NUM; i++)
		{
			ImageManager.renderCardBack(DECKCARD_X + (DECKCARD_NUM - 1 - i), DECKCARD_Y + (DECKCARD_NUM - 1 - i));
		}
	}

	public void renderButton(Graphics g)
	{
		if (Button.BUTTON_OK.isRender)
		{
			ImageManager.renderButtonOfOk(Button.BUTTON_OK.x, Button.BUTTON_OK.y);
			Button.BUTTON_OK.isRender = false;
		}
		if (Button.BUTTON_DRAW.isRender)
		{
			ImageManager.renderButtonOfDraw(Button.BUTTON_DRAW.x, Button.BUTTON_DRAW.y);
			Button.BUTTON_DRAW.isRender = false;
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

	public static void clearFieldCards()
	{
		fieldCards.clear();
	}

	public static int getFieldCardsSize()
	{
		return fieldCards.size();
	}

	static Card getOneOfFieldCards(int index)
	{
		try
		{
			return fieldCards.get(index);
		}
		catch (IndexOutOfBoundsException i)
		{
			System.out.println(i.getMessage());
			return null;
		}
	}

	public static ArrayList<Card> getFieldCards()
	{
		return fieldCards;
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

	/**
	 * ボタンの位置や幅
	 * @author ryuryu
	 *
	 */
	public enum Button
	{
		BUTTON_OK(535, 425, 200, 50),
		BUTTON_DRAW(35, 400, 200, 50),
		;

		/** ボタンの左上の頂点の座標 */
		private int x, y;
		/** ボタンの横幅 */
		private int width;
		/** ボタンの縦幅 */
		private int height;
		/** ボタンを表示するかどうか */
		public boolean isRender;

		/**
		 * コンストラクタ
		 * @param x ボタンの左上のx座標
		 * @param y ボタンの左上のy座標
		 * @param width ボタンの横幅
		 * @param height ボタンの縦幅
		 */
		Button(int x, int y, int width, int height)
		{
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			isRender = false;
		}

		/**
		 *
		 * @return ボタンの左上の頂点のx座標
		 */
		public int getX()
		{
			return x;
		}

		/**
		 *
		 * @return ボタンの左上の頂点のy座標
		 */
		public int getY()
		{
			return y;
		}

		/**
		 *
		 * @return ボタンの横幅
		 */
		public int getWidth()
		{
			return width;
		}

		/**
		 *
		 * @return ボタンの縦幅
		 */
		public int getHeight()
		{
			return height;
		}
	}

	/**
	 * カードの種類
	 * @author ryuryu
	 *
	 */
	public enum CardStatus
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
	public enum CardPosition
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
		CardPosition(int positionX, int positionY, int height, int width, CardStatus status)
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
