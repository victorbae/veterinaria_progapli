package br.com.unoesc.veterinaria.controller.relatorios;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class FiltrosRelatorioProdutoController {

	private final String MAIOR_QUE = "Maior que ";
	private final String MENOR_QUE = "Menor que ";
	private final String IGUAL_A = "Igual a ";
	private String valorRadioButton;

	@FXML
	private ComboBox<String> cbxTipoRange;

	private ToggleGroup group = new ToggleGroup();

	@FXML
	private TextField tfValorRange;

	@FXML
	private RadioButton rbMaiorQnt;

	@FXML
	private RadioButton rbMenorQnt;

	@FXML
	private RadioButton rbMaisVendidos;

	@FXML
	private RadioButton rbMenosVendidos;

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
		rbMaiorQnt.setToggleGroup(group);
		rbMenorQnt.setToggleGroup(group);
		rbMaisVendidos.setToggleGroup(group);
		rbMenosVendidos.setToggleGroup(group);
	}

	@FXML
	void Cancelar(ActionEvent event) {
		dialogStage.close();
	}

	@FXML
	void Continuar(ActionEvent event) {
		validaFiltros();
		geraRelatorio(parametros);
		clicadoSalvar = true;
		if (dialogStage != null) {
			dialogStage.close();
		}

	}

	public void geraRelatorio(Map<String, Object> parametros) {
		URL url = getClass().getResource("/br/com/unoesc/veterinaria/relatorios/RelatorioAgrupado.jasper");
		JasperPrint jasperPrint;
		try {
			if (parametros != null) {
				jasperPrint = JasperFillManager.fillReport(url.getPath(), parametros,
						ConexaoPrincipal.retornaconecao());
			} else {
				jasperPrint = JasperFillManager.fillReport(url.getPath(), null, ConexaoPrincipal.retornaconecao());
			}
			JasperViewer.viewReport(jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void Limpar(ActionEvent event) {
		tfValorRange.clear();
		cbxTipoRange.setValue(null);

	}

	private Map<String, Object> validaFiltros() {
		if (group.getSelectedToggle() != null) {
			parametros.put("tipoQuery", valorRadioButton);
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

	@FXML
	void maiorQnt(ActionEvent event) {
		valorRadioButton = "produto com maior quantidade";
	}

	@FXML
	void maisVendidos(ActionEvent event) {
		valorRadioButton = "produto mais vendido";
	}

	@FXML
	void menorQnt(ActionEvent event) {
		valorRadioButton = "produto com menor quantidade";
	}

	@FXML
	void menosVendidos(ActionEvent event) {
		valorRadioButton = "produto menos vendido";
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
