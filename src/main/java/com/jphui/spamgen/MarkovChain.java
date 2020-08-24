package com.jphui.spamgen;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class MarkovChain 
{
	//INSTANCE VARIABLES
	/**
	 *	This holds all of the word relationships
	 */
	private MWordGraph wg;
	
	/**
	 *	This remembers the lastWord that this Markov Chain generated
	 */
	private String lastWord;

	protected String START;

	//CONSTRUCTOR
    public MarkovChain(String START, String END)
    {
    	//initialize instance variables
    	wg = new MWordGraph(START, END);

    	this.START = START;

    	lastWord = null;
    }

    public MarkovChain()
    {
        this("[START]", "[END]");
    }
    
    //METHODS
    public String getLastWord() { return lastWord; }

    /**
     *	Add word relationships from the specified file
     */
    public void train(String filename)
    {
    	//TODO: add data from filename to the com.jphui.spamgen.WordGraph
        wg.processFile(filename);
    }
    
    /**
     *	Get a list of words that follow lastWord.
	 *	words with more *weight* will appear more times in the list.
	 *  if lastWord is null, then return the words that are neighbors of [START]
     */
    public List<String> getNextWords()
    {
    	//TODO: return List<String> of words that are neighbors of lastWord, weighted appropriatly

        // Design decision: have it set lastWord *itself* to START if it's "null"
    	lastWord = lastWord == null ? START : lastWord;

    	List<String> ret = new LinkedList<>();
    	Map<String, Integer> data = wg.getGraph().getNeighborWeights(lastWord);
    	for (String word : data.keySet()) {
            for (int i = 0; i < data.get(word); i++) {
                ret.add(word);
            }
        }
    	return ret;
    }

    /**
     * Only relevant to children to update Last's
     */
    public void updateMemory() {  }
    
    /**
     *	Get a word that follows lastWord
     *	Use a weighted random number to pick the next word from the list of all of lastWord's neighbors in wordGraph
     *	If lastWord is null or [END], this should generate a word that *starts* a sentence
     *	Remember the word that is about to be returned in the appropriate instance variable
     */
    public String getNextWord()
    {
    	//TODO: return random word with an edge from lastWord

        /**
         * Comment AX-50:
         * I need to know which version of "getNextWords()" will run here... if it's always the "bottomMost", then
         * we can reuse this method for all levels!
         * RESOLVED: IT DOES CORRECTLY CALL THE DEEPEST LEVEL!!!
         */
        List<String> pickMe = this.getNextWords();

        // Design decision: have children update memory update lastWord when it's chosen
        this.updateMemory();
        lastWord = pickMe.get((int) (Math.random()*pickMe.size()));

        return lastWord;
    }
    
    /**
     *	Generate a sentence using the data in the wordGraph. 
     */
    public String generateSentence()
    {
    	//TODO: generate a sentence from [START] to [END]

        // Fresh start
        lastWord = null;

        StringBuilder ret = new StringBuilder();

        do
        {
            /**
             * RESOLVED: See Comment AX-50
             */
            ret.append(this.getNextWord());
            ret.append(" ");
        }
        while (!wg.isEndWord(lastWord));

        return ret.toString().trim();
    }
    
    
}