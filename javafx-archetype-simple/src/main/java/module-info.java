module org.openjfx.javafx_archetype_simple {
    requires javafx.controls;
    requires javafx.fxml;
	requires json.simple;

    opens org.openjfx.javafx_archetype_simple to javafx.fxml;
    exports org.openjfx.javafx_archetype_simple;

}
