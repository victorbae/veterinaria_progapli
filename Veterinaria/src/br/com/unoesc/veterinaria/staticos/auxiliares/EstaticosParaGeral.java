package br.com.unoesc.veterinaria.staticos.auxiliares;

import br.com.unoesc.veterinaria.dialogs.ErroNaoPreenchidoDialogFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EstaticosParaGeral {

	public static BorderPane bpPrincipalAux;

	public static final String MAIOR_QUE = "Maior que";
	public static final String MENOR_QUE = "Menor que";
	public static final String IGUAL_A = "Igual a";

	public static void chamaErroNaoPreenchido(Stage stage) {
		Stage stageDono = (Stage) stage.getScene().getWindow();
		ErroNaoPreenchidoDialogFactory nopeDialog = new ErroNaoPreenchidoDialogFactory(stageDono);

		nopeDialog.showDialog();
	}

}
