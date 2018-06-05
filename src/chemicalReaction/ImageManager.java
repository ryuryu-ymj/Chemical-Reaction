package chemicalReaction;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * ゲームで使う画像の管理、描画
 *
 * @author ryuryu
 */
public class ImageManager
{
    /**
     * カードの表面の画像<p>
     * 配列番号＝原子番号
     */
    private static Image[] card;
    /**
     * カードの裏面の画像
     */
    private static Image cardBack;
    /**
     * カードを置く枠の画像　左
     */
    private static Image cardFrameLeft;
    /**
     * カードを置く枠の画像　右
     */
    private static Image cardFrameRight;
    /**
     * カードを置く枠の画像　真ん中
     */
    private static Image cardFrameMiddle;
    /**
     * "OK"ボタン
     */
    private static Image buttonOfOk;
    /**
     * "一枚引く"ボタン
     */
    private static Image buttonOfDraw;

    /**
     * 画像の余白の幅
     */
    private final float MARGIN = 29.527559055118110236220472440945f;

    ImageManager()
    {
        try
        {
            card = new Image[21];
            SpriteSheet ss = new SpriteSheet("res/img/card.png", 267, 354);
            for (int i = 0; i < card.length; i++)
            {
                card[i] = ss.getSubImage(i % 7, i / 7);
            }
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }

        try
        {
            SpriteSheet ss = new SpriteSheet("res/img/card2.png", 244, 298);
            cardBack = ss.getSubImage(0, 0);
            cardFrameLeft = ss.getSubImage(1, 0);
            cardFrameRight = ss.getSubImage(2, 0);
            cardFrameMiddle = ss.getSubImage(3, 0);
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }

        try
        {
            SpriteSheet ss = new SpriteSheet("res/img/button.png", 642, 189);
            buttonOfOk = ss.getSubImage(0, 0);
            buttonOfDraw = ss.getSubImage(0, 1);
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * カードの表面の画像を描画する
     *
     * @param num    カードの配列番号＝原子番号 - 1
     * @param x      中心点のx座標
     * @param y      中心点のy座標
     * @param width  横幅
     * @param height 縦幅
     */
    public void renderCard(int num, float x, float y, float width, float height)
    {
        float margin = MARGIN * width / (card[num].getWidth() - MARGIN * 2);
        card[num].draw(x - width / 2 - margin, y - height / 2 - margin, width + margin * 2, height + margin * 2);
    }

    /**
     * カードの裏面の画像を描画する
     *
     * @param x      中心点のx座標
     * @param y      中心点のy座標
     * @param width  横幅
     * @param height 縦幅
     */
    public void renderCardBack(float x, float y, float width, float height)
    {
        float margin = MARGIN * width / (cardBack.getWidth() - MARGIN * 2);
        cardBack.draw(x - width / 2 - margin, y - height / 2 - margin, width + margin * 2, height + margin * 2);
    }

    /**
     * カードを置く枠の画像を描画する
     *
     * @param x      中心点のx座標
     * @param y      中心点のy座標
     * @param width  横幅
     * @param height 縦幅
     */
    public void renderCardFrame(float x, float y, float width, float height)
    {
        float margin = MARGIN * height / (cardFrameMiddle.getHeight() - MARGIN * 2);
        cardFrameLeft.draw(x - width / 2 - margin, y - height / 2 - margin, (cardFrameMiddle.getHeight() - MARGIN * 2) / height);
        cardFrameRight.draw(x - width / 2 - margin, y - height / 2 - margin, (cardFrameMiddle.getHeight() - MARGIN * 2) / height);
        cardFrameMiddle.draw(x - width / 2 - margin, y - height / 2 - margin, width + margin * 2, height + margin * 2);
        System.out.println(x + " " + y + " " +  width + " " +  height);
    }

    /**
     * 切り札を出す場の枠の画像を描画する
     *
     * @param x 画像を表示するx座標
     * @param y 画像を表示するy座標
     */
    /*public void renderField(float x, float y)
    {
        fieldFrame.draw(x, y);
    }*/

    /**
     * 手札を出す枠の画像を描画する
     *
     * @param x      中心点のx座標
     * @param y      中心点のy座標
     * @param width  横幅
     * @param height 縦幅
     */
    /*public void renderHandCardFrame(float x, float y, float width, float height)
    {
        float margin = MARGIN * width / (handCardFrame.getWidth() - MARGIN * 2);
        handCardFrame.draw(x - width / 2 - margin, y - height / 2 - margin, width + margin * 2, height + margin * 2);
    }*/

    /**
     * "一枚引く"ボタンの画像を描画する
     *
     * @param x 画像を表示するx座標
     * @param y 画像を表示するy座標
     */
    public void renderButtonOfDraw(float x, float y)
    {
        buttonOfDraw.draw(x, y, 200, 50);
    }

    /**
     * "OK"ボタンの画像 を描画する
     *
     * @param x 画像を表示するx座標
     * @param y 画像を表示するy座標
     */
    public void renderButtonOfOk(float x, float y)
    {
        buttonOfOk.draw(x, y, 200, 50);
    }
}
