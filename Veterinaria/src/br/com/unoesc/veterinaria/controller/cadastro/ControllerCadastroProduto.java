package br.com.unoesc.veterinaria.controller.cadastro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ControllerCadastroProduto {

	@FXML
	private TextField tfNome;

	@FXML
	private TextField tfQntEstoque;

	@FXML
	private TextField tfValorEntrada;

	@FXML
	private TextField tfMargemLucro;

	@FXML
	private ComboBox<?> cbxEstoque;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnLimpar;

	@FXML
	void Cancelar(ActionEvent event) {

	}

	@FXML
	void Limpar(ActionEvent event) {

	}

	@FXML
	void Salvar(ActionEvent event) {

	}
}
