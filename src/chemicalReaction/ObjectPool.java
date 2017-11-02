package chemicalReaction;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 * ゲームオブジェクトの管理クラス.
 * オブジェクトのインスタンスを持ち,
 * オブジェクト同士の相互作用(衝突処理など)を一括管理する.
 */
public class ObjectPool
{
	private static Card[] card;
	private static Background[] background;

	/** 画面上のカードの最大値 */
	public static final int CARD_MAX = 15;
	/** バックグラウンドのカードポジションの最大値 */
	public static final int BACKGROUND_CARDPOSSESSION = 8;
	public static final int BACKGROUND_CARDBACK = 10;

	ObjectPool()
	{
		card = new Card[CARD_MAX];
		for (int i = 0; i < card.length; i++)
		{
			card[i] = new Card();
		}

		background = new Background[BACKGROUND_CARDPOSSESSION + BACKGROUND_CARDBACK];
		for (int i = 0; i < BACKGROUND_CARDPOSSESSION; i++)
		{
			background[i] = new Background(0, (Background.WIDTH + 20) * i + 50, Play.DISPLAY_HEIGHT - Background.HEIGHT - 50);
		}
		for (int i = 0; i < BACKGROUND_CARDBACK; i++)
		{
			background[BACKGROUND_CARDPOSSESSION + i] = new Background(1, 100 - i, Play.DISPLAY_HEIGHT / 2 - Background.HEIGHT / 2 - i);
		}
	}

	/**
	 * 初期化処理.
	 */
	public void init()
	{
	}

	/**
	 * ステップごとの更新.
	 */
	public void update(GameContainer gc)
	{
		updateObjects(card, gc);;
	}

	/**
	 * ステップごとの描画処理.
	 */
	public void render(Graphics g)
	{
		renderObjects(background, g);
		renderObjects(card, g);
	}

	/**
	 * 新しいカードを作る
	 * @param num カード番号
	 * @param symbol 元素記号および分子式、組成式
	 * @param x x成分
	 * @param y y成分
	 * @return cardの配列番号
	 */
	public static int newCard(int num, String symbol, int x, int y, int putX, int putY)
	{
		for (int i = 0; i < CARD_MAX; i++)
		{
			if (!card[i].active)
			{
				card[i].activate(num, symbol, x, y, putX, putY);
				return i;
			}
		}
		return -1;
	}

	public void moveCards(GameContainer gc)
	{
		// カードをつかむ、移動させる、置く
		for (int i = 0; i < CARD_MAX; i++)
		{
			if (gc.getInput().getMouseX() > card[i].x + Card.SPACE_BREADTH && gc.getInput().getMouseX() < card[i].x + Card.WIDTH - Card.SPACE_BREADTH)
			{
				if (gc.getInput().getMouseY() > card[i].y + Card.SPACE_BREADTH && gc.getInput().getMouseY() < card[i].y + Card.HEIGHT - Card.SPACE_BREADTH)
				{
					if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON))
					{
						card[i].holdCard(gc);
						Card.holdCardNum = i;
					}
				}
			}
			if (Card.holdCardNum != -1)
			{
				if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
				{
					card[Card.holdCardNum].shiftCard(gc);
				}
				else
				{
					card[i].putdownCard(gc);
					Card.holdCardNum = -1;
				}
			}
		}
	}

	public void dealCards()
	{
		for (int i = 0; i < card.length; i++)
		{
			if ((Play.counter - 20) / (i + 1) == 10)
			{
				card[i].moveCardAuto(10);
			}
		}
	}

	/**
	 * 配列内のすべてのインスタンスを無効にする.
	 *
	 * @param object ゲームオブジェクトの配列
	 */
	private void deactivateObjects(GameObject[] object)
	{
		for (GameObject obj : object)
		{
			obj.active = false;
		}
	}

	/**
	 * 配列内のインスタンスのうち,有効な物のみを更新する.
	 *
	 * @param object ゲームオブジェクトの配列
	 */
	private void updateObjects(GameObject[] object, GameContainer gc)
	{
		for (GameObject obj: object)
		{
			if (obj.active)
			{
				obj.update(gc);
			}
		}
	}

	/**
	 * 配列内のインスタンスのうち,有効な物のみを描画する.
	 *
	 * @param object ゲームオブジェクトの配列
	 */
	private void renderObjects(GameObject[] object, Graphics g)
	{
		for (GameObject obj : object)
		{
			if (obj.active)
			{
				obj.render(g);
			}
		}
	}
}
