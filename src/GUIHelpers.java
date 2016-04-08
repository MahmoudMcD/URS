import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GUIHelpers {

    // Some helpers
    public static  void setBox(HBox box)
    {
        box.setSpacing(10);
        box.setPadding(new Insets(10, 10, 10, 10));
    }

    public static void setBox(VBox box)
    {
        box.setSpacing(10);
        box.setPadding(new Insets(10, 10, 10, 10));
    }
}
