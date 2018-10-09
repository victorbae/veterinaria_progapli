package br.com.unoesc.veterinaria.controller.cadastro;

import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControllerCadastroVenda {

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnLimpar;

	@FXML
	private ComboBox<Cliente> cbxCliente;

	@FXML
	private DatePicker dtDataVenda;

	@FXML
	private TextField tfValorDesconto;

	@FXML
	private TextField tfValorTotal;

	@FXML
	private TableView<Produto> tvCarinho;

	@FXML
	private TableColumn<?, ?> tcNomeProduto;

	@FXML
	private TableColumn<?, ?> tcQuantidade;

	@FXML
	private TableColumn<?, ?> tcValorUnitario;

	@FXML
	private TableColumn<?, ?> tcValorTotal;

	@FXML
	private Button btnExcluirProduto;

	@FXML
	private Button btnAdicionarProduto;

	@FXML
	void AdicionarProduto(ActionEvent event) {

	}

	@FXML
	void Cancelar(ActionEvent event) {

	}

	@FXML
	void ExcluirProduto(ActionEvent event) {

	}

	@FXML
	void Limpar(ActionEvent event) {

	}

	@FXML
	void Salvar(ActionEvent event) {

	}

}
