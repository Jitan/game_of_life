package se.jroc.game_of_life.gui;

public abstract class GUICallbackHandler implements GUICallback {
    public abstract void locationClicked(int x, int y);

    public abstract void playButtonClicked();


    public abstract void stopButtonClicked();

    public abstract void randomizeButtonClicked();
}
