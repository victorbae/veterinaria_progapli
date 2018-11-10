package br.com.unoesc.veterinaria.controller.relatorios;

import java.net.URL;
import java.util.List;

import org.controlsfx.control.textfield.TextFields;

import br.com.unoesc.veterinaria.banco.AnimaisBanco;
import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.RacaBanco;
import br.com.unoesc.veterinaria.banco.TipoAnimalBanco;
import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import br.com.unoesc.veterinaria.dao.AnimaisDao;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.dao.RacaDao;
import br.com.unoesc.veterinaria.dao.TipoAnimalDao;
import br.com.unoesc.veterinaria.model.Animais;
import br.com.unoesc.veterinaria.model.filtros.FiltrosAnimais;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaAnimal;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaCliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class FiltrosRelatorioAnimaisController {

	@FXML
	private TextField tfCliente;

	@FXML
	private TextField tfRaca;

	@FXML
	private TextField tfTipoAnimal;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnContinuar;

	@FXML
	private Button btnLimpar;

	private Stage dialogStage;

	private boolean clicadoSalvar;

	private AnimaisDao animalDao = new AnimaisBanco();

	private ClienteDao clienteDao = new ClienteBanco();

	private RacaDao racaDao = new RacaBanco();

	private TipoAnimalDao tipoAnimalDao = new TipoAnimalBanco();

	private FiltrosAnimais filtroAnimal;

	@FXML
	public void initialize() {
		TextFields.bindAutoCompletion(tfCliente, clienteDao.listar());
		TextFields.bindAutoCompletion(tfRaca, racaDao.listar());
		TextFields.bindAutoCompletion(tfTipoAnimal, tipoAnimalDao.listar());
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
		URL url = getClass().getResource("/br/com/unoesc/veterinaria/relatorios/RelatorioAnimais.jasper");
		JasperPrint jasperPrint;
		List<Animais> listaAnimais = animalDao.findByFiltros(filtroAnimal);
		try {
			if (listaAnimais != null) {
				JRBeanCollectionDataSource pegaLista = new JRBeanCollectionDataSource(listaAnimais);
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
		tfRaca.clear();
		tfTipoAnimal.clear();

	}

	private FiltrosAnimais validaFiltros() {
		filtroAnimal = new FiltrosAnimais();
		if (!tfCliente.getText().isEmpty() && tfCliente.getText() != null) {
			filtroAnimal.setCliente(EstaticosParaCliente.achaClienteByName(tfCliente.getText()));
		}
		if (!tfRaca.getText().isEmpty() && tfRaca.getText() != null) {
			filtroAnimal.setRaca(EstaticosParaAnimal.achaRacaByNome(tfRaca.getText()));
		}
		if (!tfTipoAnimal.getText().isEmpty() && tfTipoAnimal.getText() != null) {
			filtroAnimal.setTipoAnimal(EstaticosParaAnimal.achaTipoAnimalByNome(tfTipoAnimal.getText()));
		}

		return filtroAnimal;
	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}
}
