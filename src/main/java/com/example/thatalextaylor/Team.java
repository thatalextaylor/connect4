package com.example.thatalextaylor;

//The players of the game
public enum Team {
    None(-1, ' '),
    Red(1, 'R'),
    Green(2, 'G');

    private final int playerNumber;
    private char teamCharacter;

    Team(int playerNumber, char teamCharacter) {
        this.playerNumber = playerNumber;
        this.teamCharacter = teamCharacter;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public char getTeamCharacter() {
        return teamCharacter;
    }

    //Which player has a turn after this player
    public Team getNextPlayer() {
        switch (this) {
            case Red:
                return Green;
            case Green:
                return Red;
            default:
                return None;
        }
    }
}
