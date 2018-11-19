package br.com.unoesc.veterinaria.dialogs;

import java.io.IOException;

import br.com.unoesc.veterinaria.controller.relatorios.FiltrosRelatorioVendaController;
import br.com.unoesc.veterinaria.model.Produto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RelatorioVendaDialogFactory {
	private Stage stageDono;

	private Produto produtoDevolta;

	public RelatorioVendaDialogFactory(Stage stage) {
		this.stageDono = stage;
	}

	public boolean showDialog() {
		boolean clicandoSalvar = false;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(
				getClass().getResource("/br/com/unoesc/veterinaria/fxml/relatorios/FiltrosRelatorioVenda.fxml"));
		try {
			AnchorPane produtoDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image("/br/com/unoesc/veterinaria/img/page.png"));
			dialogStage.setTitle("Filtros Relatório Venda");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(stageDono);
			Scene scene = new Scene(produtoDialog);
			dialogStage.setScene(scene);

			FiltrosRelatorioVendaController controller = loader.getController();
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
