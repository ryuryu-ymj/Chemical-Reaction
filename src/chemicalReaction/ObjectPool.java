package chemicalReaction;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import chemicalReaction.Table.CardPosition;
import chemicalReaction.Table.CardStatus;

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
	public static final int CARD_MAX = Table.HANDCARD_NUM;
	private int[] numOfCardInFrame = new int[Table.HANDCARD_NUM];

	/** clearFieldCardsメソッドのスイッチ */
	private boolean isClearFieldCards = false;
	/** clearFieldCardsメソッドのカウンタ */
	private int counterOfClearFieldCards = 0;

	/** dealCardsメソッドのスイッチ */
	private boolean isDealCards = false;
	/** dealCardsメソッドのカウンタ */
	private int counterOfClearDealCards = 0;

	private boolean isCanPutCardToField;

	ObjectPool()
	{
		card = new Card[CARD_MAX];
		for (int i = 0; i < card.length; i++)
		{
			card[i] = new Card();
		}

		table = new Table();
		init();
	}

	/**
	 * 初期化処理.
	 */
	public void init()
	{
		isCanPutCardToField = true;
	}

	/**
	 * ステップごとの更新.
	 */
	public void update(GameContainer gc)
	{
		if (isClearFieldCards)
		{
			clearFieldCards();
		}
		if (isDealCards)
		{
			dealCards();
		}
		updateObjects(card, gc);
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
		table.renderDeckCard(g);
	}

	/**
	 * 新しいカードを作る
	 * @param element カードの種類
	 * @param position カードの位置
	 * @return cardの配列番号
	 */
	public static int newCard(Element element, CardPosition position)
	{
		for (int i = 0; i < CARD_MAX; i++)
		{
			if (!card[i].active)
			{
				card[i].activate(element, position);
				return i;
			}
		}
		return -1;
	}

	/**
	 * カードをつかむ，移動させる，置くなどの動作を管理
	 * @param gc
	 */
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
						if (card[i].isHoldCard && card[i].active)
						{
							card[i].holdCard(gc);
							Card.holdCardNum = i;
							switch (card[i].getPosition())
							{
								case HANDCARD:
									Table.removeHandCard(card[i]);
									break;
								case FIELD:
									Table.removeFieldCard(card[i]);
									break;
								default:
									break;
							}
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
				k:
				for (int i = 0; i < Table.CardPosition.values().length; i++)
				{
					if (gc.getInput().getMouseX() > CardPosition.values()[i].getPositionX() && gc.getInput().getMouseX() < CardPosition.values()[i].getPositionX() + CardPosition.values()[i].getWidth())
					{
						if (gc.getInput().getMouseY() > CardPosition.values()[i].getPositionY() && gc.getInput().getMouseY() < CardPosition.values()[i].getPositionY() + CardPosition.values()[i].getHeight())
						{
							if (CardPosition.values()[i] != CardPosition.DECKCARD)
							{
								j:
								if (CardPosition.values()[i] == CardPosition.THROWCARD)
								{
									for (int j = 0; j < Table.HANDCARD_NUM; j++)
									{
										if (card[j].getPosition() == CardPosition.values()[i] && j != Card.holdCardNum)
										{
											card[j].putdownCard(card[Card.holdCardNum].getPosition());
											break j;
										}
									}
								}
								if (isCanPutCardToField)
								{
									card[Card.holdCardNum].putdownCard(CardPosition.values()[i]);
									Card.holdCardNum = -1;
									break i;
								}
								break k;
							}
						}
					}
				}
				card[Card.holdCardNum].putdownCard(card[Card.holdCardNum].getPosition()); // 何もない場所にカードを置く
				Card.holdCardNum = -1;
			}
		}
	}

	/**
	 * 場札が役と一致しているかを調べ，一致していたらその分子を返す
	 * @return 場札に一致する役　なければnull
	 */
	public Molecular checkFieldCardToMolecular()
	{
		Element[] fieldCardElements = new Element[Table.getFieldCardsSize()];
		for (int i = 0; i < Table.getFieldCardsSize(); i++)
		{
			fieldCardElements[i] = Table.getOneOfFieldCards(i).getElement();
		}
		boolean[] isFieldCardElementsExist = new boolean[fieldCardElements.length];
		for (int i = 0; i < fieldCardElements.length; i++)
		{
			isFieldCardElementsExist[i] = true;
		}

		for (int m = 0; m < Molecular.values().length; m++)
		{
			Element[] molecularElements = Molecular.values()[m].getElements();
			boolean[] isMolecularElementsExist = new boolean[molecularElements.length];
			for (int i = 0; i < molecularElements.length; i++)
			{
				isMolecularElementsExist[i] = true;
			}
			//System.out.print("fieldCardElements ");
			for (int i = 0; i < fieldCardElements.length; i++)
			{
				//System.out.print(fieldCardElements[i].getSymbol() + " " + isFieldCardElementsExist[i] + " ");
			}
			//System.out.print("  molecularElements ");
			for (int i = 0; i < molecularElements.length; i++)
			{
				//System.out.print(molecularElements[i].getSymbol() + " " + isMolecularElementsExist[i] + " ");
			}
			//System.out.println();
			//System.out.println(fieldCardElements + " " + molecularElements);
			for (int fE = 0; fE < fieldCardElements.length; fE++)
			{
				for (int mE = 0; mE < molecularElements.length; mE++)
				{
					if (fieldCardElements[fE] == molecularElements[mE] && isFieldCardElementsExist[fE] && isMolecularElementsExist[mE])
					{
						//System.out.println(fieldCardElements.length + " " + molecularElements.length);
						isFieldCardElementsExist[fE] = false;
						isMolecularElementsExist[mE] = false;
						//System.out.println(fE + " " + mE);
					}
				}
			}
			check:
			if (true)
			{
				for (boolean isFieldCardElementExist : isFieldCardElementsExist)
				{
					if (isFieldCardElementExist)
					{
						break check;
					}
				}
				for (boolean isMolecularElementExist : isMolecularElementsExist)
				{
					if (isMolecularElementExist)
					{
						break check;
					}
				}
				return Molecular.values()[m];
			}
			for (int i = 0; i < fieldCardElements.length; i++)
			{
				isFieldCardElementsExist[i] = true;
			}
			for (int i = 0; i < fieldCardElements.length; i++)
			{
				isFieldCardElementsExist[i] = true;
			}
		}
		return null;
	}

	/**
	 * カードを配り始める<P>
	 * 呼び出しは一回でいい
	 */
	public void startDealCards()
	{
		isDealCards = true;
		counterOfClearDealCards = 0;
	}

	/**
	 * カードを配る
	 */
	private void dealCards()
	{
		for (int i = 0; i < Table.HANDCARD_NUM; i++)
		{
			if ((counterOfClearFieldCards - 20) / (i + 1) == 10)
			{
				if (card[i].getPosition() != CardPosition.HANDCARD)
				{
					card[i].putdownCard(CardPosition.HANDCARD);
				}
			}
			else if ((counterOfClearFieldCards - 150) / (i + 1) == 10)
			{
				card[i].startRotationAuto();
				if (i == card.length - 1)
				{
					isDealCards = false;
				}
			}
		}
		counterOfClearFieldCards++;
	}

	/**
	 * 場札を一括消去し始める<P>
	 * 呼び出しは一回でいい
	 */
	public void startClearFieldCards()
	{
		if (!isClearFieldCards)
		{
			counterOfClearFieldCards = 0;
		}
		isClearFieldCards = true;
	}

	/**
	 * 場札を一括消去する
	 */
	private void clearFieldCards()
	{
		switch (counterOfClearFieldCards)
		{
			case 0:
				for (int i = 0; i < Table.getFieldCardsSize(); i++)
				{
					Table.getOneOfFieldCards(i).startRotationAuto();
				}
				isCanPutCardToField = false;
				break;
			case 30:
				for (int i = 0; i < Table.getFieldCardsSize(); i++)
				{
					Table.getOneOfFieldCards(i).putdownCard(CardPosition.DECKCARD);
				}
				break;
			case 70:
				for (int i = 0; i < Table.getFieldCardsSize(); i++)
				{
					Table.getOneOfFieldCards(i).delete();
				}
				isCanPutCardToField = true;
				Table.clearFieldCards();
				isClearFieldCards = false;
				break;
		}

		counterOfClearFieldCards++;
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
