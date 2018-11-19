package br.com.unoesc.veterinaria.dialogs;

import java.io.IOException;

import br.com.unoesc.veterinaria.controller.cadastro.ControllerCadastroAnimais;
import br.com.unoesc.veterinaria.model.Animais;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AnimaisDialogFactory {

	private Stage stageDono;

	private Animais animaisDevolta;

	public AnimaisDialogFactory(Stage stage) {
		this.stageDono = stage;
	}

	public boolean showDialog() {
		boolean clicandoSalvar = false;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/cadastro/CadastroAnimais.fxml"));
		try {
			AnchorPane animaisDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image("/br/com/unoesc/veterinaria/img/dog.png"));
			dialogStage.setTitle("Animais");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(stageDono);
			Scene scene = new Scene(animaisDialog);
			dialogStage.setScene(scene);

			ControllerCadastroAnimais controller = loader.getController();
			controller.setStageDialog(dialogStage);
			dialogStage.showAndWait();

			clicandoSalvar = controller.clicadoSalvar();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return clicandoSalvar;
	}

	public Animais retornaAnimais() {
		return animaisDevolta;
	}
}
