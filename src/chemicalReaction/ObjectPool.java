package chemicalReaction;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import chemicalReaction.Table.CardPosition;

/**
 * ゲームオブジェクトの管理クラス.
 * オブジェクトのインスタンスを持ち,
 * オブジェクト同士の相互作用(衝突処理など)を一括管理する.
 */
public class ObjectPool
{
	private static Card[] card;
	private static Table table;

	/** 画面上のカードの最大値 */
	public static final int CARD_MAX = 8;
	private int[] numOfCardInFrame = new int[Table.HANDCARD_NUM];

	ObjectPool()
	{
		card = new Card[CARD_MAX];
		for (int i = 0; i < card.length; i++)
		{
			card[i] = new Card();
		}

		table = new Table();
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
		table.render(g);
		renderObjects(card, g);
		// 今つかんでいるカードを再描画
		if (Card.holdCardNum != -1)
		{
			card[Card.holdCardNum].render(g);
		}
	}

	/**
	 * 新しいカードを作る
	 * @param num カード番号
	 * @param symbol 元素記号および分子式、組成式
	 * @param x x成分
	 * @param y y成分
	 * @param position カードの位置
	 * @return cardの配列番号
	 */
	public static int newCard(int num, String symbol, int x, int y, CardPosition position)
	{
		for (int i = 0; i < CARD_MAX; i++)
		{
			if (!card[i].active)
			{
				card[i].activate(num, symbol, x, y, position);
				return i;
			}
		}
		return -1;
	}

	public void moveCards(GameContainer gc)
	{
		// カードをつかむ
		for (int i = 0; i < CARD_MAX; i++)
		{
			if (gc.getInput().getMouseX() > card[i].x + Card.SPACE_BREADTH && gc.getInput().getMouseX() < card[i].x + Card.WIDTH - Card.SPACE_BREADTH)
			{
				if (gc.getInput().getMouseY() > card[i].y + Card.SPACE_BREADTH && gc.getInput().getMouseY() < card[i].y + Card.HEIGHT - Card.SPACE_BREADTH)
				{
					if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON))
					{
						if (card[i].isHoldCard)
						{
							card[i].holdCard(gc);
							Card.holdCardNum = i;
						}
					}
				}
			}
		}
		if (Card.holdCardNum != -1)
		{
			i:
			if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) // つかんでいるカードをマウスに合わせて移動させる
			{
				card[Card.holdCardNum].shiftCard(gc);
			}
			else // カードを置く
			{
				for (int i = 0; i < Table.CardPosition.values().length; i++)
				{
					if (gc.getInput().getMouseX() > CardPosition.values()[i].getPositionX() && gc.getInput().getMouseX() < CardPosition.values()[i].getPositionX() + CardPosition.values()[i].getWidth())
					{
						if (gc.getInput().getMouseY() > CardPosition.values()[i].getPositionY() && gc.getInput().getMouseY() < CardPosition.values()[i].getPositionY() + CardPosition.values()[i].getHeight())
						{
							switch (CardPosition.values()[i].getStatus())
							{
								case HANDCARD:
									putdownHandCard(i);
									break;
								case DECKCARD:
									break;
								case FIELDCARD:
									break;
								case THROWCARD:
									break;
								default:
									break;
							}
							/*j:
							for (int j = 0; j < Table.HANDCARD_NUM; j++)
							{
								if (card[j].getPosition() == Position.values()[i] && j != Card.holdCardNum)
								{
									card[j].putdownCard(card[Card.holdCardNum].getPosition());
									break j;
								}
							}
							card[Card.holdCardNum].putdownCard(Position.values()[i]);*/
							Card.holdCardNum = -1;
							break i;
						}
					}
				}
				card[Card.holdCardNum].putdownCard(card[Card.holdCardNum].getPosition());
				Card.holdCardNum = -1;
			}
		}
	}

	private void putdownHandCard(int checkingCardNum)
	{
		j:
			for (int j = 0; j < Table.HANDCARD_NUM; j++)
			{
				if (card[j].getPosition() == CardPosition.values()[checkingCardNum] && j != Card.holdCardNum)
				{
					card[j].putdownCard(card[Card.holdCardNum].getPosition());
					break j;
				}
			}
			card[Card.holdCardNum].putdownCard(CardPosition.values()[checkingCardNum]);
	}

	/**
	 * カードを配る
	 */
	public void dealCards()
	{
		for (int i = 0; i < Table.HANDCARD_NUM; i++)
		{
			if ((Play.counter - 20) / (i + 1) == 10)
			{
				card[i].startMoveAuto();
				numOfCardInFrame[i] = i;
			}
			else if ((Play.counter - 150) / (i + 1) == 10)
			{
				card[i].startRotationAuto();
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
