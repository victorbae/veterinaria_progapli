package br.com.unoesc.veterinaria.dialogs;

import java.io.IOException;

import br.com.unoesc.veterinaria.controller.ControllerErroNaoPreenchido;
import br.com.unoesc.veterinaria.controller.cadastro.ControllerCadastroFuncionario;
import br.com.unoesc.veterinaria.controller.cadastro.ControllerNaoExcluir;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NaoExcluiDialogFactory {
	private Stage stageDono;

	public NaoExcluiDialogFactory(Stage stage) {
		this.stageDono = stage;
	}

	public boolean showDialog() {
		boolean clicandoSalvar = false;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/NaoExcluir.fxml"));
		try {
			AnchorPane clienteDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			// dialogStage.getIcons().add(new
			// Image("/br/com/unoesc/veterinaria/img/erro.png"));
			dialogStage.setTitle("Aviso !!!");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(stageDono);
			Scene scene = new Scene(clienteDialog);
			dialogStage.setScene(scene);

			ControllerNaoExcluir controller = loader.getController();
			controller.setStageDialog(dialogStage);
			dialogStage.showAndWait();

			clicandoSalvar = controller.clicadoSalvar();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return clicandoSalvar;
	}

}
