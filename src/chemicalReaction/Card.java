package chemicalReaction;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import chemicalReaction.Table.CardPosition;
import chemicalReaction.Table.CardStatus;

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
	 * そのカードの元素
	 */
	private Element element;
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
	//private float putX, putY;
	/**
	 * そのカードが置かれているポジション
	 */
	private CardPosition position;
	/**
	 * そのカードをput座標まで自動で動かすかどうか
	 */
	//private boolean isMoveAuto;
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
		switch (position)
		{
			case HANDCARD:
				moveCardAuto(Table.getHandCardX(this), position.getPositionY(), 20);
				break;
			case FIELD:
				moveCardAuto(Table.getFieldCardX(this), position.getPositionY(), 20);
				break;
			default:
				moveCardAuto(position.getPositionX(), position.getPositionY(), 20);
				break;
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
			//System.out.println(x + " " + y);
		}
		else
		{
			ImageManager.getCard(element.getNum()).draw(x + WIDTH / 2 - (float) (WIDTH * Math.cos(angle)) / 2, y, (float) (WIDTH * Math.cos(angle)), HEIGHT);
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
		isHoldCard = false;
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
	}

	/**
	 * カードを置く
	 * @param position カードを置く位置
	 */
	public void putdownCard(CardPosition position)
	{
		this.position = position;
		isHoldCard = true;
		switch (position)
		{
			case HANDCARD:
				Table.addHandCard(this);
				break;
			case FIELD:
				Table.addFieldCard(this);
				break;
			default:
				break;
		}
		System.out.print(this);
	}

	/**
	 * 何もない場所にカードを置く
	 */
	public void putdownCard()
	{
		isHoldCard = true;
	}

	/*public void putdownCard(int putX, int putY)
	{
		isMoveAuto = true;
		isHoldCard = true;
	}*/

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
		if ((goalX - x) * (goalX - x - speedX) > 0 || (goalY - y) * (goalY - y - speedY) > 0)
		{
			x += speedX;
			y += speedY;
			isHoldCard = false;
		}
		else // 通り過ぎを防ぐ
		{
			//System.out.println(num + " " + (goalX) + " " + (x) + " " + speedX + " " + (goalY) + " " + (y) + " " + speedY);
			x = goalX;
			y = goalY;
			isHoldCard = true;
		}
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
			isHoldCard = true;
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
	 * @param element カードの種類
	 * @param position カードが置かれる場所
	 */
	public void activate(Element element, CardPosition position)
	{
		active = true;
		isRotationAuto = false;
		isHoldCard = false;
		angle = Math.PI;
		this.element = element;
		this.x = position.getPositionX();
		this.y = position.getPositionY();
		this.position = position;
	}

	public Element getElement()
	{
		return element;
	}

	/**
	 *
	 * @return そのカードが置かれている位置
	 */
	public CardPosition getPosition()
	{
		return position;
	}
}
