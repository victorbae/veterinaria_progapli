package br.com.unoesc.veterinaria.dialogs;

import java.io.IOException;

import br.com.unoesc.veterinaria.controller.cadastro.ControllerAdicionaProdutoVenda;
import br.com.unoesc.veterinaria.model.VendaProduto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdicionaProdutoVendaDialogFactory {
	private Stage stageDono;

	private VendaProduto vendaProdutoDevolta;

	public AdicionaProdutoVendaDialogFactory(Stage stage) {
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

			ControllerAdicionaProdutoVenda controller = loader.getController();
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
