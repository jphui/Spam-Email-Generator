package com.jphui.spamgen;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MarkovChainGen3 extends MarkovChainGen2
{
	//INSTANCE VARIABLES
    /**
     *	This holds relationships between trios-of-words -> next-word
     */
    private MWordGraph wgTrios;
    /**
     *	This holds the word generated 3 steps ago
     */
    protected String lastlastlastWord;

    //CONSTRUCTOR
    public MarkovChainGen3(String START, String END)
    {
        //Initialize Instance Variables
        super(START, END);
        wgTrios = new MWordGraph(START, END);

        lastlastlastWord = null;
    }

    public MarkovChainGen3()
    {
        this("[START]", "[END]");
    }
    
    //METHODS
    //TODO: override appropriate methods to generate 3rd Degree Markov Chains

    public String getLastlastlastWord() { return lastlastlastWord; }

    @Override
    public void train(String filename)
    {
        super.train(filename);
        wgTrios.processFileGen(filename, 3);
    }

    @Override
    public List<String> getNextWords()
    {
        //TODO: This is written in such a way to be understand with scale in mind.

        // Previous-order results
        List<String> previousOrder = super.getNextWords();

        // This is the "starting" case, in which the choices must take on what lower orders generate!
        if (lastlastlastWord == null)
            return previousOrder;

        // TODO: This can easily be made more scalable with a few easy tweaks in the future!
        // This is in the format of Queue.toString()
        String findMe = "[" + lastlastlastWord + ", " + getLastlastWord() + ", " + getLastWord() + "]";

        Map<String, Integer> data = wgTrios.getGraph().getNeighborWeights(findMe);

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
            ret = previousOrder;
        return ret;
    }

    @Override
    public void updateMemory()
    {
        lastlastlastWord = getLastlastWord();
        super.updateMemory();
    }

    @Override
    public String generateSentence()
    {
        // Fresh start
        lastlastlastWord = null;

        return super.generateSentence();
    }
}