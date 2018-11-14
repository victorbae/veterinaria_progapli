package br.com.unoesc.veterinaria.controller;

import br.com.unoesc.veterinaria.banco.FilialBanco;
import br.com.unoesc.veterinaria.dao.FilialDao;
import br.com.unoesc.veterinaria.dialogs.ClienteDialogFactory;
import br.com.unoesc.veterinaria.dialogs.FilialDialogFactory;
import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.model.Funcionario;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeFuncionario;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaFilial;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerFilial {

	@FXML
	private TableColumn<Filial, String> tcCNPJ;

	@FXML
	private TableView<Filial> tvFilial;

	@FXML
	private TableColumn<Filial, String> tcNome;

	@FXML
	private TableColumn<Filial, String> tcTelefone;

	@FXML
	private TableColumn<Filial, Integer> tcId;

	@FXML
	private Button btnExcluir;

	@FXML
	private TableColumn<Filial, String> tcEndereco;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnEditar;

	@FXML
	private TableColumn<Filial, Funcionario> tcGerente;

	FilialDao filialDao = new FilialBanco();

	Filial filial = new Filial();

	@FXML
	private void initialize() {
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		tcId.setCellValueFactory(new PropertyValueFactory<>("idFilial"));
		tcGerente.setCellValueFactory(new PropertyValueFactory<>("gerente"));
		tcCNPJ.setCellValueFactory(new PropertyValueFactory<>("Cnpj"));
		tvFilial.setItems(FXCollections.observableArrayList(filialDao.listar()));
	}

	@FXML
	void Novo(ActionEvent event) {
		Stage stageDono = (Stage) btnNovo.getScene().getWindow();
		FilialDialogFactory clienteDialog = new FilialDialogFactory(stageDono);

		boolean clicadoSalvar = clienteDialog.showDialog();

		if (clicadoSalvar) {
			atualizaLista();
		}
	}

	@FXML
	void Excluir(ActionEvent event) {
		populaFilialByOnCLick();
		filialDao.excluir(filial);
	}

	@FXML
	void Editar(ActionEvent event) {
		
		EstaticosParaFilial.filial = tvFilial.getSelectionModel().getSelectedItem();
		EstaticosParaFilial.editando = true;
		
		Stage stageDono = (Stage) btnNovo.getScene().getWindow();
		FilialDialogFactory clienteDialog = new FilialDialogFactory(stageDono);

		boolean clicadoSalvar = clienteDialog.showDialog();

		if (clicadoSalvar) {
			atualizaLista();
		}
	}

	public void atualizaLista() {
		tvFilial.setItems(FXCollections.observableArrayList(filialDao.listar()));
		tvFilial.refresh();
	}

	public void populaFilial() {
		filial = new Filial();
		filial.setCnpj(String.valueOf(tcCNPJ));
		filial.setEndereco(String.valueOf(tcEndereco));
		filial.setGerente(EstaticosDeFuncionario.buscaFuncionarioById(Integer.valueOf(tcGerente.getId())));
		filial.setNome(String.valueOf(tcNome));
		filial.setTelefone(String.valueOf(tcTelefone));
	}

	@FXML
	void filialClicked(ActionEvent event) {
		populaFilialByOnCLick();
	}

	public void populaFilialByOnCLick() {
		filial = new Filial();
		filial = tvFilial.getSelectionModel().getSelectedItem();
	}
}