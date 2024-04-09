module hi.verkefni5.vidmot {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens hi.verkefni5.vidmot to javafx.fxml;
    exports hi.verkefni5.vidmot;
}