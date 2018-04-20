package chemicalReaction;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * ゲームオブジェクトの抽象クラス.
 */
public abstract class GameObject
{
	/**
	 * インスタンス有効フラグ(falseならインスタンスは処理されない)
	 */
	public boolean active;

	/**
	 * 中心点のx座標
	 */
	public float x;

	/**
	 * 中心点のy座標
	 */
	public float y;

	public boolean isActive()
	{
		return active;
	}

	/**
	 * ステップごとの更新.
	 */
	public abstract void update(GameContainer gc);

	/**
	 * ステップごとの描画処理.
	 */
	public abstract void render(Graphics g, ImageManager im);

	/**
	 * オブジェクトがプレイ領域内にいるかどうかを確認し,
	 * 領域外に出ている場合は,インスタンスを無効にする.
	 *
	 * @param margin
	 */
	void checkLeaving(int margin)
	{
	}
}