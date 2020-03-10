package database;

public interface Database {

    /**
     * This function is going to return an int that represents the score of the player
     * @return the score of the player
     */
    public int loadScore();

    /**
     *Add the new score to the database
     * @param newScore
     */
    public void saveScore(int newScore);
}
