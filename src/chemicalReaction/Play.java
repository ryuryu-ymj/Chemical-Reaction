package chemicalReaction;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * プレイ画面の更新,描画を行うクラス.
 */
public class Play extends GameState
{
	/**
	 * 画面の横幅
	 */
	public static final int DISPLAY_WIDTH = 1400;
	/**
	 * 画面の縦幅
	 */
	public static final int DISPLAY_HEIGHT = 900;
	/** フレームカウンタ */
	public static int counter;

	ObjectPool objectPool;
	ImageManager gameImage;
	//Element element;
	//ChemicalEquation chemicalEquation;
	Molecular molecular;

	/**
	 * コンストラクタ
	 */
	Play()
	{
		super();
		objectPool = new ObjectPool();
		/*for (int i = 0; i < Table.HANDCARD_NUM; i++)
		{
			ObjectPool.newCard(Element.getRandomOne(), Table.CardPosition.DECKCARD);
		}*/
		gameImage = new ImageManager();
		//element = Element.Ag;
		molecular = Molecular.NH3;
	}

	/**
	 * 初期化処理.
	 */
	public void init(GameContainer gc)
			throws SlickException
	{
		objectPool.init();
		counter = 0;
		objectPool.startDealCards();
	}

	/**
	 * ステップごとの更新.
	 */
	public void update(GameContainer gc, int delta)
			throws SlickException
	{

		objectPool.moveCards(gc);
		if (objectPool.checkFieldCard() != null)
		{
			//System.out.println(objectPool.checkFieldCardToMolecular().getSymbol());
			if (!objectPool.isClearFieldCards())
			{
				if(objectPool.checkButtonPushed(gc, Table.Button.BUTTON_OK))
				{
					objectPool.startClearFieldCards();
				}
			}
		}
		if(objectPool.checkButtonPushed(gc, Table.Button.BUTTON_DRAW))
		{
			objectPool.startDrawDeckCard();;
		}
		objectPool.clearThrowCard();
		objectPool.update(gc);

		counter++;
		//System.out.println(Table.handCards.size() + " " + Table.fieldCards.size());
		//System.out.println(molecular.getSymbol());
		/*for (int i = 0; i < molecular.getElements().length; i++)
		{
			System.out.print(molecular.getElements()[i].getSymbol());
		}
		System.out.println();*/
	}

	/**
	 * ステップごとの描画処理.
	 */
	public void render(GameContainer gc, Graphics g)
			throws SlickException
	{
		objectPool.render(g);
	}
}