package iut.gon.gribouille_tp1;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class Dialogues {

	public static boolean confirmation() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Voulez-vous vraiment quitter ?",ButtonType.YES, ButtonType.NO);
				alert.setTitle("Confirmation");

				Optional<ButtonType> result = alert.showAndWait();
				return result.orElse(ButtonType.NO) == ButtonType.YES;
	}
}
