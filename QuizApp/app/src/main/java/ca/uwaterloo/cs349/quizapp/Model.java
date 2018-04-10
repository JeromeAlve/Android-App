package ca.uwaterloo.cs349.quizapp;

/**
 * Created by jeromealve on 2018-03-25.
 */

import java.util.Observable;
import java.util.Observer;


/**
 * Class Model
 * - Stores a persistent state for the application.
 */
public class Model extends Observable
{
    // Private Variables
    private String name;
    private int numQ=1;
    private int curQ=1;
    boolean [] wrong = new boolean [5];

    int score =0;
    int[][] answers = new int[][]{
    { 0, 0, 0, 0}, //q1
    { 0, 0, 0, 0}, //q2
    { 0, 0, 0, 0}, //q3
    { 0, 0, 0, 0}, //q4
    { 0, 0, 0, 0}  //q5
    };

    int[][] correct = new int[][]{
            { 1, 0, 0, 0}, //q1
            { 1, 0, 1, 0}, //q2
            { 0, 0, 1, 0}, //q3
            { 0, 0, 0, 1}, //q4
            { 0, 0, 1, 1}  //q5
    };


    /**
     * Model Constructor:
     * - Init member variables
     */
    private static final Model ourInstance = new Model();
    static Model getInstance()
    {
        return ourInstance;
    }

    public void setName(String i)
    {
        this.name = i;
        this.notifyObservers();
    }
    public String getName()
    {
        return this.name;
    }
    public void nextPage()
    {
        this.curQ++;
        this.notifyObservers();
    }
    public void prevPage()
    {
        this.curQ--;
        this.notifyObservers();
    }
    public int getCur()
    {
        return this.curQ;
    }

    public void setNum(int i){
        this.numQ = i;
        this.notifyObservers();
    }
    public int getNum(){
        return this.numQ;
    }
    public void initObservers()
    {
        setChanged();
        notifyObservers();
    }

    //reset values
    public void logout(){
        this.curQ = 1;
        this.score= 0;
        this.answers = new int[][]{
                { 0, 0, 0, 0}, //q1
                { 0, 0, 0, 0}, //q2
                { 0, 0, 0, 0}, //q3
                { 0, 0, 0, 0}, //q4
                { 0, 0, 0, 0}  //q5
        };
        for(int i = 0; i< 5; i++){
            this.wrong[i] = false;
        }
        this.notifyObservers();
    }

    @Override
    public synchronized void deleteObserver(Observer o)
    {
        super.deleteObserver(o);
    }

    @Override
    public synchronized void addObserver(Observer o)
    {
        super.addObserver(o);
    }

    /**
     * Clears the observer list so that this object no longer has any observers.
     */
    @Override
    public synchronized void deleteObservers()
    {
        super.deleteObservers();
    }


    @Override
    public void notifyObservers()
    {
        super.notifyObservers();
    }
}