package se.jroc.game_of_life.gui;

public interface GUICallback {
    void locationClicked(int x, int y);

    void playButtonClicked();

    void stopButtonClicked();

    void randomizeButtonClicked();

}
