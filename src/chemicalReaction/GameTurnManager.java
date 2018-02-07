package chemicalReaction;

public class GameTurnManager
{
	private enum GameTurn
	{
		PLAYER_TURN,
		NPC_TURN,
		;
	}

	private GameTurn gameTurn;

	/**
	 * 初期化処理
	 */
	public void init()
	{
		gameTurn = GameTurn.PLAYER_TURN;
	}

	/**
	 * ターンを進める
	 */
	public void advanceTurn()
	{
		switch (gameTurn)
		{
			case PLAYER_TURN:
				gameTurn = GameTurn.NPC_TURN;
				break;
			case NPC_TURN:
				gameTurn = GameTurn.PLAYER_TURN;
				break;
		}
	}

	/**
	 * 今のゲームターンを返す
	 * @return 今のゲームターン
	 */
	public GameTurn getGameTurn()
	{
		return gameTurn;
	}
}
