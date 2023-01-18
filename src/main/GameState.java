package main;

public enum GameState {
	PLAYING, PAUSE, ENDING;

	public static GameState gameState = PLAYING;

	public static void setGameState(GameState state) {
		gameState = state;
	}

	public static GameState getGameState() {
		return gameState;
	}

}
