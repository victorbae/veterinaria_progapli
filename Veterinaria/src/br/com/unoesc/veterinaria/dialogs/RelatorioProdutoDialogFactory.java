package br.com.unoesc.veterinaria.dialogs;

import java.io.IOException;

import br.com.unoesc.veterinaria.controller.relatorios.FiltrosRelatorioProdutoController;
import br.com.unoesc.veterinaria.model.Produto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RelatorioProdutoDialogFactory {
	private Stage stageDono;

	private Produto produtoDevolta;

	public RelatorioProdutoDialogFactory(Stage stage) {
		this.stageDono = stage;
	}

	public boolean showDialog() {
		boolean clicandoSalvar = false;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(
				getClass().getResource("/br/com/unoesc/veterinaria/fxml/relatorios/FiltrosRelatorioProduto.fxml"));
		try {
			AnchorPane produtoDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Filtros Relat�rio");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(stageDono);
			Scene scene = new Scene(produtoDialog);
			dialogStage.setScene(scene);

			FiltrosRelatorioProdutoController controller = loader.getController();
			controller.setStageDialog(dialogStage);
			dialogStage.showAndWait();

			clicandoSalvar = controller.clicadoSalvar();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return clicandoSalvar;
	}

	public Produto retornaProduto() {
		return produtoDevolta;
	}
}
