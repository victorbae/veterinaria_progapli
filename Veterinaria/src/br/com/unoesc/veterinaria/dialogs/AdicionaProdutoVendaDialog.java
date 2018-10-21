package br.com.unoesc.veterinaria.dialogs;

import java.io.IOException;

import br.com.unoesc.veterinaria.controller.cadastro.ControllerCadastroVenda;
import br.com.unoesc.veterinaria.model.VendaProduto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdicionaProdutoVendaDialog {
	private Stage stageDono;

	private VendaProduto vendaProdutoDevolta;

	public AdicionaProdutoVendaDialog(Stage stage) {
		this.stageDono = stage;
	}

	public boolean showDialog() {
		boolean clicandoSalvar = false;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(
				getClass().getResource("/br/com/unoesc/veterinaria/fxml/cadastro/AdicionaProdutoVenda.fxml"));
		try {
			AnchorPane produtoDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Adicionar Produto");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(stageDono);
			Scene scene = new Scene(produtoDialog);
			dialogStage.setScene(scene);

			ControllerCadastroVenda controller = loader.getController();
			controller.setStageDialog(dialogStage);
			dialogStage.showAndWait();

			clicandoSalvar = controller.clicadoSalvar();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return clicandoSalvar;
	}

	public VendaProduto retornaProduto() {
		return vendaProdutoDevolta;
	}

}
