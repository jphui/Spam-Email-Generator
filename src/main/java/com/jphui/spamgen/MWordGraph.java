package com.jphui.spamgen;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class MWordGraph extends WordGraph
{
    private String START;
    private String END;

    /**
     * Holds the most recently-added words for 2nd+ MC Orders
     */
    private Queue<String> lastWords;

    public MWordGraph(String START, String END)
    {
        this.START = START;
        this.END = END;
        graph.add(START);
        graph.add(END);

        lastWords = new LinkedList<>();
    }

    /**
     * This method should return true if the last character of word is one of the following characters
     *  Period (.)
     *  Question mark (?)
     *  Exclamation mark (!)
     *  Single quote (‘)
     *  Double quote (“)
     */
    public boolean isEndWord(String word)
    {
        return word.matches(".*[.?!'\"]$");
    }

    @Override
    public void addWord(String newWord)
    {
        if (lastWord == null)
            lastWord = START;

        super.addWord(newWord);

        if (isEndWord(newWord.trim()))
        {
            super.addWord(END);
            lastWord = null;
        }
    }

    /**
     *  I have made the decision to only process sentences that have at least 'order' words between START and END.
     *      NOTE how 'super.addWord()' is never called until the lastWord blob is big enough!!!
     *  However, in generation, it will use lower-order MC, and if that instantly produces an end word, then it can end
     * @param newWord is the word to be added
     * @param order is the Markov Chain order: * MUST be >0 *
     *              --> I believe that if {order = 1}, then it will operate identically to addWord() but will not make
     *              adjustments in com.jphui.spamgen.MarkovChain.java
     */
    private void addWordGen(String newWord, int order)
    {
        // Ensure that it's always trimmed!
        newWord = newWord.trim();

        // We will ensure this is always a sign of starting a new sentence
        if (lastWords.isEmpty())
            lastWords.add(START);

        // If the queue size isn't of size 'order', then we need to "prep" more by enqueuing more words!
        if (lastWords.size() < order)
        {
            if (!newWord.equals(""))
            {
                lastWords.add(newWord);
            }
        }
        else
        {
            /*
             * A big realization is that lastWord can be formatted however we want because the only thing that
             * "matters" is what is CHOSEN, which is what word it has an edge to (that's what's printed).
             */
            // As per addWord() implementation, we don't want edges TO blobs, so we set lastWord to null
            lastWord = null;
            // After this next operation, lastWord will FAVORABLY be set to lastWords.toString()
            super.addWord(lastWords.toString());

            // Many->newWord mapping ==> note that after this call, lastWord is set to newWord as per the super's def
            super.addWord(newWord);

            // Remove the oldest word and move-in newWord
            if (!newWord.equals(""))
            {
                lastWords.remove();
                lastWords.add(newWord);
            }

            // EndWord procedure
            if (isEndWord(newWord))
            {
                lastWord = lastWords.toString();
                super.addWord(END);

                // RESET!!!
                lastWords.clear();  // We're done with this sentence: clear the queue!
                lastWord = null;    // This doesn't affect our implementation but conventionally we'll do this.
            }
        }
    }

    /**
     *  Scalable, higher-order version of the processString() method
     * @param order is the Markov Chain order: MUST be >0
     */
    public void processStringGen(String str, int order)
    {
        for (String word : str.split(" "))
            addWordGen(word, order);
    }

    /**
     *	Scalable, higher-order version of the processFile() method
     * @param order is the Markov Chain order: MUST be >0
     */
    public void processFileGen(String filename, int order)
    {
        try
        {
            //open the specified file
            File file = new File(filename);
            Scanner in = new Scanner(file);

            //loop through each line of the file and process it
            while(in.hasNextLine())
                processStringGen(in.nextLine(), order);

            //TODO: Different from the lab repository! NEED to close all streams to delete the tempfile!
            in.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
