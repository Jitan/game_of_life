module se.jroc.game_of_life {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens se.jroc.game_of_life to javafx.fxml;
    exports se.jroc.game_of_life;
    exports se.jroc.game_of_life.gui;
    opens se.jroc.game_of_life.gui to javafx.fxml;
}