package chemicalReaction;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Card extends GameObject
{
	/**
	 * 縦幅　余白も入れて
	 */
	public static final int HEIGHT = 227;
	/**
	 * 横幅　余白も入れて
	 */
	public static final int WIDTH = 170;
	/**
	 * 余白の枠の幅
	 */
	public static final int SPACE_BREADTH = 23;
	/**
	 * カード番号
	 */
	private int num;
	/**
	 * 元素記号および分子式、組成式
	 */
	public String symbol;
	/**
	 * カードをつかんだときのカードの左上からの座標
	 */
	private static float holdX, holdY;
	/**
	 * 今つかんでいるカード　つかんでいなっかたら-1
	 */
	static int holdCardNum;
	/**
	 * そのカードが置かれている座標
	 */
	private float putX, putY;
	/**
	 * そのカードをput座標まで自動で動かすかどうか
	 */
	private boolean isMoveAuto;
	/**
	 * そのカードを回転させるかどうか
	 */
	private boolean isRotationAuto;
	/**
	 * カードの角度　0 <= θ < 2π<p>
	 * 0　だったら表面　π　だったら裏面
	 */
	private double angle;
	/**
	 * そのカードをつかめるかどうか
	 */
	public boolean isHoldCard;

	/**
	 * コンストラクタ
	 */
	Card()
	{
		active = false;
		holdCardNum = -1;
		holdX = 0;
		holdY = 0;
	}

	@Override
	public void update(GameContainer gc)
	{
		if (isMoveAuto)
		{
			moveCardAuto(putX, putY, 20);
		}

		if (isRotationAuto)
		{
			rotationCardAuto();
		}
	}

	@Override
	public void render(Graphics g)
	{
		if (angle >= Math.PI / 2 && angle < Math.PI * 3 / 2)
		{
			ImageManager.getCardBack().draw(x + WIDTH / 2 - (float) (WIDTH * Math.cos(angle)) / 2, y, (float) (WIDTH * Math.cos(angle)), HEIGHT);
		}
		else
		{
			ImageManager.getCard(num).draw(x + WIDTH / 2 - (float) (WIDTH * Math.cos(angle)) / 2, y, (float) (WIDTH * Math.cos(angle)), HEIGHT);
		}
		//img[num].draw((float)(x - width / 2 / 3.5), y - height / 2 / 5, (float)(width * 4.5 / 3.5), height * 6 / 5);
		//System.out.println(CardSet.getSymbolFromNum(0));
		/*g.setColor(Color.blue);
		g.drawRoundRect(x, y, width, height, 7);
		g.drawString(symbol, x, y);*/
	}

	/**
	 * カードをつかむ
	 */
	public void holdCard(GameContainer gc)
	{
		holdX = gc.getInput().getMouseX() - x;
		holdY = gc.getInput().getMouseY() - y;
		isMoveAuto = false;
		//holdCardNum = num;
		//System.out.println(holdX);
	}

	/**
	 * カードを移動させる
	 */
	public void shiftCard(GameContainer gc)
	{
		x = gc.getInput().getMouseX() - holdX;
		y = gc.getInput().getMouseY() - holdY;
		isMoveAuto = false;
	}

	/**
	 * カードを置く
	 */
	public void putdownCard(GameContainer gc)
	{
		isMoveAuto = true;
		isHoldCard = false;
	}

	/**
	 * カードを自動で滑らかに移動させる
	 * @param goalX 目的地のx座標
	 * @param goalY 目的地のy座標
	 * @param moveSpeed 移動速度
	 */
	private void moveCardAuto(float goalX, float goalY, int moveSpeed)
	{
		double moveAngle = Math.atan2(goalY - y, goalX - x);
		float speedX = (float) (moveSpeed * Math.cos(moveAngle));
		float speedY = (float) (moveSpeed * Math.sin(moveAngle));
		if ((putX - x) * (putX - x - speedX) > 0 && (putY - y) * (putY - y - speedY) > 0)
		{
			x += speedX;
			y += speedY;
		}
		else // 通り過ぎを防ぐ
		{
			x = putX;
			y = putY;
			isMoveAuto = false;
			isHoldCard = true; 
			if (angle != 0)
			{
				startRotationAuto();
			}
		}
	}

	/**
	 * そのカードをput座標まで自動で動かし始める
	 */
	public void startMoveAuto()
	{
		isMoveAuto = true;
	}

	/**
	 * カードを自動で滑らかに1回転させる
	 */
	private void rotationCardAuto()
	{
		angle += 0.1;
		if (angle >= Math.PI * 2)
		{
			angle = 0;
			isRotationAuto = false;
		}
	}

	/**
	 * そのカードを自動で滑らかに回転させ始める
	 */
	public void startRotationAuto()
	{
		isRotationAuto = true;
	}

	/**
	 * 初期化処理
	 * @param num カード番号
	 * @param symbol 元素記号および分子式、組成式
	 * @param x x成分 最初に表示させるカードの左上の頂点
	 * @param y y成分 最初に表示させるカードの左上の頂点
	 * @param putX そのカードが置かれている座標
	 * @param putY そのカードが置かれている座標
	 */
	public void activate(int num, String symbol, int x, int y, int putX, int putY)
	{
		active = true;
		isMoveAuto = false;
		isRotationAuto = false;
		isHoldCard = false;
		angle = Math.PI;
		this.num = num;
		this.symbol = symbol;
		this.x = x;
		this.y = y;
		this.putX = putX;
		this.putY = putY;
	}

	/**
	 *
	 * @return カード番号
	 */
	public int getNum()
	{
		return num;
	}
}
