package com.jphui.spamgen;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class MarkovChainGen2 extends MarkovChain
{
	//INSTANCE VARIABLES
	/**
	 *	This holds relationships between pairs-of-words -> next-word
	 */
	private MWordGraph wgPairs;

    /**
     * This holds the word generated 2 steps ago
     */
    private String lastlastWord;

	//CONSTRUCTOR
    public MarkovChainGen2(String START, String END)
    {
    	//Initialize Instance Variables
        super(START, END);
        wgPairs = new MWordGraph(START, END);

        lastlastWord = null;
    }

    public MarkovChainGen2()
    {
        this("[START]", "[END]");
    }
    
    //METHODS
    //TODO: override methods needed to generate 2nd Degree Markov Chains

    public String getLastlastWord() { return lastlastWord; }

    @Override
    public void train(String filename)
    {
    	super.train(filename); //tell the com.jphui.spamgen.MarkovChain super class to train() like it normally does
    	
    	//TODO: do some magic to store 2nd order markov chain data
    	wgPairs.processFileGen(filename, 2);
    }

    @Override
    /**
     * APPROACH: getNextWord() will operate the same at all levels!
     *      - It will be given a list from getNextWords and choose 1 randomly...
     *      - THUS, getNextWords() will change at the different orders!
     * Edge cases for orders 2+
     *      - "starting out"
     *      - "not seeing the current pair->single mapping
     *          --> Deprecated: "escape route" is shown in TODO but should never run in theory.
     */
    public List<String> getNextWords()
    {
        // First-order results
        List<String> firstOrder = super.getNextWords();

        // This is the "starting" case, in which the choices must be first-order!
        if (lastlastWord == null)
            return firstOrder;

        // At this point, we know that both lastlast and last are filled. This is because eventually, updateMemory()
        //      will run and assign lastlast.
        // TODO: This can easily be made more scalable with a few easy tweaks in the future!
        // This is in the format of Queue.toString()
        String findMe = "[" + lastlastWord + ", " + getLastWord() + "]";

        Map<String, Integer> data = wgPairs.getGraph().getNeighborWeights(findMe);

        List<String> ret = new LinkedList<>();
        if (!data.isEmpty())
        {
            for (String word : data.keySet())
            {
                for (int i = 0; i < data.get(word); i++)
                {
                    ret.add(word);
                }
            }
        }
        else // TODO: This is a backup in case no data is found, but this should in theory never run!
            ret = firstOrder;
        return ret;
    }

    @Override
    public void updateMemory()
    {
        lastlastWord = getLastWord();
        super.updateMemory();
    }

    @Override
    public String generateSentence()
    {
        // Fresh start
        lastlastWord = null;

        return super.generateSentence();
    }
}