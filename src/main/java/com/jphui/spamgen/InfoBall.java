package com.jphui.spamgen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class InfoBall
{
    private String input;
    private String order;
    private String length;
    private String output;

    public InfoBall()
    {
        input = "";
        output = "";
    }

    public void generate()
    {
        try
        {
            File tempfile;
            tempfile = File.createTempFile("tmp", ".txt");

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempfile)))
            {
                    bw.write(input);
            }

            String path = tempfile.getPath();
            //System.out.println(path);
            MarkovChain mkc;

            if (order.equals("3")) { mkc = new MarkovChainGen3();}
            else if (order.equals("2")) { mkc = new MarkovChainGen2(); }
            else { mkc = new MarkovChain(); }

            mkc.train(path);

            for(int i = 0; i < Integer.parseInt(length); i++)
            {
                output += mkc.generateSentence() + " ";
            }

            //System.out.println(tempfile.delete());
            tempfile.delete();

            System.out.println(output);
        }
        catch(Exception e)
        {
            output = "Error creating or writing to tempfile, contact @jphui\n" + input;
            e.printStackTrace();
        }
    }

    /////

    public String getInput() {
        return input;
    }

    public String getOrder() {
        return order;
    }

    public String getLength() {
        return length;
    }

    public String getOutput() {
        return output;
    }

    /////

    public void setInput(String input) {
        this.input = input;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setOutput() {
        this.output = output;
    }

    /////

    public String toString()
    {
        return "TextLength: " + input.length() + ", " +
                "Order: " + order + ", " +
                "Length: " + length;
    }
}
