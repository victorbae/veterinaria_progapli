package br.com.unoesc.veterinaria.controller.relatorios;

import java.net.URL;
import java.util.List;

import org.controlsfx.control.textfield.TextFields;

import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.VendaBanco;
import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.dao.VendaDao;
import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Venda;
import br.com.unoesc.veterinaria.model.filtros.FiltrosVenda;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaCliente;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaGeral;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class FiltrosRelatorioVendaController {

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

	private FiltrosVenda filtrosVenda;

	private VendaDao vendaDao = new VendaBanco();

	private ClienteDao clienteDao = new ClienteBanco();

	@FXML
	private void initialize() {
		populaCombo();
		TextFields.bindAutoCompletion(tfCliente, clienteDao.listar());
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
		URL url = getClass().getResource("/br/com/unoesc/veterinaria/relatorios/RelatorioVendas.jasper");
		JasperPrint jasperPrint;
		List<Venda> listaVendas = vendaDao.findByFiltros(filtrosVenda);
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
		tfCliente.clear();
		tfValorRange.clear();
		cbxTipoRange.setValue(null);
		dpData.setValue(null);
	}

	private FiltrosVenda validaFiltros() {
		filtrosVenda = new FiltrosVenda();
		for (Cliente cliente : clienteDao.listar()) {
			if (tfCliente.getText() == cliente.getNomeCompleto()) {
				filtrosVenda.setCliente(EstaticosParaCliente.achaClienteByName(tfCliente.getText()));
			}
		}

		if (dpData.getValue() != null) {
			filtrosVenda.setDataVenda(dpData.getValue());
		}

		if (tfValorRange.getText() != null && cbxTipoRange.getValue() != null) {
			Double valor = null;
			String operacao = null;
			switch (cbxTipoRange.getValue()) {
			case EstaticosParaGeral.MAIOR_QUE:
				operacao = cbxTipoRange.getValue();
				valor = Double.valueOf(tfValorRange.getText());
				break;
			case EstaticosParaGeral.MENOR_QUE:
				operacao = cbxTipoRange.getValue();
				valor = Double.valueOf(tfValorRange.getText());
				break;
			case EstaticosParaGeral.IGUAL_A:
				operacao = cbxTipoRange.getValue();
				valor = Double.valueOf(tfValorRange.getText());
				break;
			}
			filtrosVenda.setOperacao(operacao);
			filtrosVenda.setCondicaoValor(valor);
		} else {
			filtrosVenda.setOperacao(null);
			filtrosVenda.setCondicaoValor(null);

		}
		return filtrosVenda;
	}

	private void populaCombo() {
		cbxTipoRange.getItems().add(EstaticosParaGeral.MAIOR_QUE);
		cbxTipoRange.getItems().add(EstaticosParaGeral.MENOR_QUE);
		cbxTipoRange.getItems().add(EstaticosParaGeral.IGUAL_A);
	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}

}
