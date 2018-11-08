package br.com.unoesc.veterinaria.controller.relatorios;

import java.util.HashMap;
import java.util.Map;

import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaGeral;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FiltrosRelatorioVendaController {

	private final String MAIOR_QUE = "Maior que ";
	private final String MENOR_QUE = "Menor que ";
	private final String IGUAL_A = "Igual a ";

	@FXML
	private TextField tfCliente;

	@FXML
	private DatePicker dpData;

	@FXML
	private ComboBox<String> cbxTipoRange;

	@FXML
	private TextField tfValorRange;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnContinuar;

	@FXML
	private Button btnLimpar;

	private Stage dialogStage;

	private boolean clicadoSalvar;

	private Map<String, Object> parametros = new HashMap<>();

	@FXML
	private void initialize() {
		populaCombo();
	}

	@FXML
	void Cancelar(ActionEvent event) {
		dialogStage.close();
	}

	@FXML
	void Continuar(ActionEvent event) {
		validaFiltros();
		EstaticosParaGeral.geraRelatorio("/relatorios/RelatorioAgrupado.jasper", FiltrosRelatorioVendaController.class,
				parametros);
		clicadoSalvar = true;
		if (dialogStage != null) {
			dialogStage.close();
		}

	}

	@FXML
	void Limpar(ActionEvent event) {
		tfCliente.clear();
		tfValorRange.clear();
		dpData.setValue(null);
	}

	private Map<String, Object> validaFiltros() {
		if (tfCliente.getText() != null) {
			parametros.put("nomeCliente", tfCliente.getText());
		}
		if (dpData.getValue() != null) {
			parametros.put("dataVenda", dpData.getValue());
		}

		if (tfValorRange.getText() != null && cbxTipoRange.getValue() != null) {
			String range = null;
			switch (cbxTipoRange.getValue()) {
			case MAIOR_QUE:
				range = ">" + tfValorRange.getText();
				break;
			case MENOR_QUE:
				range = "<" + tfValorRange.getText();
				break;
			case IGUAL_A:
				range = "=" + tfValorRange.getText();
				break;
			}
			parametros.put("rangeValor", range);
		}
		return parametros;
	}

	private void populaCombo() {
		cbxTipoRange.getItems().add(MAIOR_QUE);
		cbxTipoRange.getItems().add(MENOR_QUE);
		cbxTipoRange.getItems().add(IGUAL_A);
	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}

}
