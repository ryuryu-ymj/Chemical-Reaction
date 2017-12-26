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
	Element element;
	ChemicalEquation chemicalEquation;
	Molecular molecular;

	/**
	 * コンストラクタ
	 */
	Play()
	{
		super();
		objectPool = new ObjectPool();
		for (int i = 0; i < Table.HANDCARD_NUM; i++)
		{
			ObjectPool.newCard(i + 1, "Au", Table.CardPosition.DECKCARD.getPositionX(), Table.CardPosition.DECKCARD.getPositionY(), Table.CardPosition.getHandCardPosition(i));
		}
		gameImage = new ImageManager();
		element = Element.Ag;
		molecular = Molecular.C_O2;
	}

	/**
	 * 初期化処理.
	 */
	public void init(GameContainer gc)
			throws SlickException
	{
		objectPool.init();
		counter = 0;
	}

	/**
	 * ステップごとの更新.
	 */
	public void update(GameContainer gc, int delta)
			throws SlickException
	{

		objectPool.moveCards(gc);
		objectPool.dealCards();
		objectPool.update(gc);

		counter++;
		System.out.println(molecular.getNums()[0]);
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