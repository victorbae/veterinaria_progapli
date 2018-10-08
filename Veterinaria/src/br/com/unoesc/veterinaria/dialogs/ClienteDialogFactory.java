package br.com.unoesc.veterinaria.dialogs;

import java.io.IOException;

import br.com.unoesc.veterinaria.controller.cadastro.ControllerCadastroCliente;
import br.com.unoesc.veterinaria.model.Cliente;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClienteDialogFactory {
	private Stage stageDono;

	private Cliente clienteDevolta;

	public ClienteDialogFactory(Stage stage) {
		this.stageDono = stage;
	}

	public boolean showDialog() {
		boolean clicandoSalvar = false;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/cadastro/CadastroCliente.fxml"));
		try {
			AnchorPane clienteDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Novo Cliente");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(stageDono);
			Scene scene = new Scene(clienteDialog);
			dialogStage.setScene(scene);

			ControllerCadastroCliente controller = loader.getController();
			controller.setStageDialog(dialogStage);
			dialogStage.showAndWait();

			clicandoSalvar = controller.clicadoSalvar();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return clicandoSalvar;
	}

	public Cliente retornaCliente() {
		return clienteDevolta;
	}

}
