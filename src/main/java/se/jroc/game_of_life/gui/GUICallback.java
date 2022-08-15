package se.jroc.game_of_life.gui;

public interface GUICallback {
    public void locationClicked(int x, int y);

    void playButton();

    void stopButton();
}
