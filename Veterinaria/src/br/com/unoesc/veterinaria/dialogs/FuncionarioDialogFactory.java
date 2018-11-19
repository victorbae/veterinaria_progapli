package br.com.unoesc.veterinaria.dialogs;

import java.io.IOException;

import br.com.unoesc.veterinaria.controller.cadastro.ControllerCadastroFuncionario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FuncionarioDialogFactory {
	private Stage stageDono;

	public FuncionarioDialogFactory(Stage stage) {
		this.stageDono = stage;
	}

	public boolean showDialog() {
		boolean clicandoSalvar = false;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/cadastro/CadastroFuncionario.fxml"));
		try {
			AnchorPane clienteDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image("/br/com/unoesc/veterinaria/img/man.png"));
			dialogStage.setTitle("Novo Funcionario");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(stageDono);
			Scene scene = new Scene(clienteDialog);
			dialogStage.setScene(scene);

			ControllerCadastroFuncionario controller = loader.getController();
			controller.setStageDialog(dialogStage);
			dialogStage.showAndWait();

			clicandoSalvar = controller.clicadoSalvar();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return clicandoSalvar;
	}
}