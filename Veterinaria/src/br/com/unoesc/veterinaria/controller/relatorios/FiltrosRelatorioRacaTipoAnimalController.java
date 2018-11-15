package br.com.unoesc.veterinaria.controller.relatorios;

import java.net.URL;
import java.util.List;

import br.com.unoesc.veterinaria.banco.AnimaisBanco;
import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import br.com.unoesc.veterinaria.dao.AnimaisDao;
import br.com.unoesc.veterinaria.model.Animais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class FiltrosRelatorioRacaTipoAnimalController {

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnContinuar;

	@FXML
	private Button btnLimpar;

	@FXML
	private RadioButton rbAnimaisRaca;

	@FXML
	private RadioButton rbAnimaisTipoAnimal;

	@FXML
	private ToggleGroup group = new ToggleGroup();

	private AnimaisDao animalDao = new AnimaisBanco();

	@FXML
	private void initialize() {
		rbAnimaisRaca.setToggleGroup(group);
		rbAnimaisTipoAnimal.setToggleGroup(group);
	}

	@FXML
	void Cancelar(ActionEvent event) {

	}

	@FXML
	void Continuar(ActionEvent event) {
		geraRelatorio();
	}

	@FXML
	void Limpar(ActionEvent event) {

	}

	public void geraRelatorio() {
		URL url = getClass().getResource("/br/com/unoesc/veterinaria/relatorios/RelatorioRaca.jasper");
		JasperPrint jasperPrint;
		List<Animais> listaAnimais = animalDao.listarPorRacaETipoAnimal();
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

}
