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

    /**
     * 画面上のカードの最大値
     */
    public static final int CARD_MAX = 20;
    //private int[] numOfCardInFrame = new int[Table.HANDCARD_NUM];

    /**
     * clearFieldCardsメソッドのスイッチ
     */
    private boolean isClearFieldCards = false;
    /**
     * clearFieldCardsメソッドのカウンタ
     */
    private int counterOfClearFieldCards = 0;

    /**
     * dealCardsメソッドのスイッチ
     */
    private boolean isDealCards = false;
    /**
     * dealCardsメソッドのカウンタ
     */
    private int counterOfDealCards = 0;

    /**
     * drawDeckCardメソッドのスイッチ
     */
    private boolean isDrawDeckCard = false;
    /**
     * drawDeckCardメソッドのカウンタ
     */
    private int counterOfDrawDeckCard = 0;
    /**
     * drawDeckCardメソッドで引いたカードの配列番号
     */
    private int newCardNum = 0;

    /**
     * 場にカードを置けるかどうか
     */
    private boolean isCanPutCardToField;
    /**
     * 山札からカードを引けるかどうか
     */
    private boolean isCanDrawDeckCard;

    private GameTurnManager gameTurn;

    ObjectPool()
    {
        card = new Card[CARD_MAX];
        for (int i = 0; i < card.length; i++)
        {
            card[i] = new Card();
        }

        table = new Table();
        gameTurn = new GameTurnManager();
        init();
    }

    /**
     * 初期化処理.
     */
    public void init()
    {
        isCanPutCardToField = true;
        isCanDrawDeckCard = false;
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
        if (isDrawDeckCard)
        {
            drawDeckCard();
        }
        updateObjects(card, gc);
        //System.out.println(isDealCards + " " + isCanDrawDeckCard + " " + isDrawDeckCard);
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
        table.renderButton(g);
    }

    /**
     * 新しいカードを作る
     *
     * @param element  カードの種類
     * @param position カードの位置
     * @return cardの配列番号　なかったら-1
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
     *
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
     *
     * @return 場札に一致する役　なければnull
     */
    public Molecular checkFieldCard()
    {
        return Molecular.checkCard(Table.getFieldCards().toArray(new Card[Table.getFieldCardsSize()]));
    }

    /**
     * カードを配り始める<P>
     * 呼び出しは一回でいい
     */
    public void startDealCards()
    {
        isDealCards = true;
        counterOfDealCards = 0;
        for (int i = 0; i < Table.HANDCARD_NUM; i++)
        {
            newCard(Element.getRandomOne(), Table.CardPosition.DECKCARD);
        }
    }

    /**
     * カードを配る
     */
    private void dealCards()
    {
        for (int i = 0; i < Table.HANDCARD_NUM; i++)
        {
            if ((counterOfDealCards - 20) / (i + 1) == 10)
            {
                if (card[i].getPosition() != CardPosition.HANDCARD)
                {
                    card[i].putdownCard(CardPosition.HANDCARD);
                }
            }
            else if ((counterOfDealCards - 150) / (i + 1) == 10)
            {
                card[i].startRotationAuto();
                if (i == Table.HANDCARD_NUM - 1)
                {
                    isDealCards = false;
                    isCanDrawDeckCard = true;
                }
            }
        }
        counterOfDealCards++;
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
     * ボタンを表示し，押されたかどうかを判定する
     *
     * @param gc
     * @param button 表示するボタン
     * @return ボタンが押されたかどうか
     */
    public boolean checkButtonPushed(GameContainer gc, Table.Button button)
    {
        button.isRender = true;
        if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
        {
            if (gc.getInput().getMouseX() > button.getX() && gc.getInput().getMouseX() < button.getX() + button.getWidth())
            {
                if (gc.getInput().getMouseY() > button.getY() && gc.getInput().getMouseY() < button.getY() + button.getHeight())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * カードを山札から一枚手札に引き始める
     */
    public void startDrawDeckCard()
    {
        if (isCanDrawDeckCard)
        {
            newCardNum = newCard(Element.getRandomOne(), Table.CardPosition.DECKCARD);
            if (newCardNum != -1)
            {
                card[newCardNum].putdownCard(Table.CardPosition.HANDCARD);
            }
            isCanDrawDeckCard = false;
            isDrawDeckCard = true;
            counterOfDrawDeckCard = 0;
        }
    }

    /**
     * カードを山札から一枚手札に引く
     */
    private void drawDeckCard()
    {
        if (counterOfDrawDeckCard == 70)
        {
            card[newCardNum].startRotationAuto();
            isDrawDeckCard = false;
            isCanDrawDeckCard = true;
        }

        counterOfDrawDeckCard++;
    }

    /**
     * 捨て札を消去する
     */
    public void clearThrowCard()
    {
        for (int i = 0; i < card.length; i++)
        {
            if (card[i].active && card[i].getPosition() == CardPosition.THROWCARD)
            {
                card[i].delete();
            }
        }
    }

    /**
     * @return clearFieldCardsメソッドのスイッチ
     */
    public boolean isClearFieldCards()
    {
        return isClearFieldCards;
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
        for (GameObject obj : object)
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
