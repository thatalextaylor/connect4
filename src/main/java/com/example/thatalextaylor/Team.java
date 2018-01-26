package com.example.thatalextaylor;

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
