package chemicalReaction;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * メインクラス.
 * ウィンドウの生成及びゲームシーンの管理を行う.
 */
public class Main extends BasicGame
{
	/** タイトル画面 */
	Title title;
	/** プレイ画面 */
	Play play;
	/** ゲームシーン */
	private State state;
	//
	Element element = Element.H;

	/**
	 * コンストラクタ
	 *
	 * @param name ゲーム名
	 * @throws SlickException
	 */
	public Main(String name)
	{
		super(name);
	}

	/**
	 * 初期化処理.
	 */
	public void init(GameContainer gc)
			throws SlickException
	{
		title = new Title();
		play = new Play();
		state = State.Play;
	}

	/**
	 * ステップごとの更新.
	 */
	public void update(GameContainer gc, int delta)
			throws SlickException
	{
		switch (state)
		{
			case Title:
				title.update(gc, delta);
				break;
			case Play:
				play.update(gc, delta);
				break;
		}

		//
		//System.out.println(element.getName());
	}

	/**
	 * ステップごとの描画処理.
	 */
	public void render(GameContainer gc, Graphics g)
			throws SlickException
	{
		// 背景を描画
		g.setBackground(Color.white);

		switch (state)
		{
			case Title:
				title.render(gc, g);
				break;
			case Play:
				play.render(gc, g);
				break;
		}
	}

	/**
	 * メインメソッド.
	 * ウィンドウの生成を行う.
	 */
	public static void main(String[] args)
	{
		Main main = new Main("Chemical Reaction");
		try
		{
			AppGameContainer agc = new AppGameContainer(main);
			agc.setDisplayMode(Play.DISPLAY_WIDTH, Play.DISPLAY_HEIGHT, false);
			agc.setTargetFrameRate(60);
			agc.setShowFPS(false);
			agc.setAlwaysRender(true);
			//agc.setIcon("res/img/bullet0.png");
			agc.start();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
}