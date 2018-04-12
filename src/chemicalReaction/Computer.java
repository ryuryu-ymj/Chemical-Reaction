package chemicalReaction;

import java.util.ArrayList;

public class Computer
{
    /**
     * 場札
     */
    private ArrayList<Card> fieldCards = new ArrayList<>();
    /**
     * 手札
     */
    private ArrayList<Card> handCards = new ArrayList<>();

    Computer()
    {
    }

    /**
     * 手札をComputerに渡す
     *
     * @param handCards 手札
     */
    public void setHandCards(ArrayList<Card> handCards)
    {
        this.handCards = handCards;
    }

    public void directAction()
    {

    }
}
