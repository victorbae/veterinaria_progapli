package br.com.unoesc.veterinaria.controller.relatorios;

import java.net.URL;
import java.util.List;

import br.com.unoesc.veterinaria.banco.ProdutoBanco;
import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import br.com.unoesc.veterinaria.dao.ProdutoDao;
import br.com.unoesc.veterinaria.model.Produto;
import br.com.unoesc.veterinaria.model.filtros.FiltrosProdutos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class FiltrosRelatorioProdutoController {

	private final String MAIOR_QUE = "Maior que ";
	private final String MENOR_QUE = "Menor que ";
	private final String IGUAL_A = "Igual a ";

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

	@FXML
	private ComboBox<String> cbxTipoRangeQnt;

	@FXML
	private TextField tfValorRangeQnt;

	private Stage dialogStage;

	private boolean clicadoSalvar;

	private FiltrosProdutos filtrosProduto;

	private ProdutoDao produtoDao = new ProdutoBanco();

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
		geraRelatorio();
		clicadoSalvar = true;
		if (dialogStage != null) {
			dialogStage.close();
		}

	}

	public void geraRelatorio() {
		URL url = getClass().getResource("/br/com/unoesc/veterinaria/relatorios/RelatorioProdutos.jasper");
		JasperPrint jasperPrint;
		List<Produto> listaVendas = produtoDao.findByFiltros(filtrosProduto);
		try {
			if (listaVendas != null) {
				JRBeanCollectionDataSource pegaLista = new JRBeanCollectionDataSource(listaVendas);
				jasperPrint = JasperFillManager.fillReport(url.getPath(), null, pegaLista);
			} else {
				jasperPrint = JasperFillManager.fillReport(url.getPath(), null, ConexaoPrincipal.retornaconecao());
			}
			JasperViewer.viewReport(jasperPrint, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void Limpar(ActionEvent event) {
		tfValorRange.clear();
		cbxTipoRange.setValue(null);

	}

	private FiltrosProdutos validaFiltros() {
		filtrosProduto = new FiltrosProdutos();
		if (tfValorRangeQnt.getText() != null && cbxTipoRangeQnt.getValue() != null) {
			String range = null;
			switch (cbxTipoRange.getValue()) {
			case MAIOR_QUE:
				range = ">" + tfValorRangeQnt.getText();
				break;
			case MENOR_QUE:
				range = "<" + tfValorRangeQnt.getText();
				break;
			case IGUAL_A:
				range = "=" + tfValorRangeQnt.getText();
				break;
			}
			filtrosProduto.setCondicaoQntEstoque(range);
		} else {
			filtrosProduto.setCondicaoQntEstoque(null);

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
			filtrosProduto.setCondicaoValor(range);
		} else {
			filtrosProduto.setCondicaoValor(null);

		}
		return filtrosProduto;
	}

	private void populaCombo() {
		cbxTipoRange.getItems().add(MAIOR_QUE);
		cbxTipoRange.getItems().add(MENOR_QUE);
		cbxTipoRange.getItems().add(IGUAL_A);

		cbxTipoRangeQnt.getItems().add(MAIOR_QUE);
		cbxTipoRangeQnt.getItems().add(MENOR_QUE);
		cbxTipoRangeQnt.getItems().add(IGUAL_A);
	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}

}
