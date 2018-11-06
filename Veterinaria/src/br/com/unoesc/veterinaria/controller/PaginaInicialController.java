package br.com.unoesc.veterinaria.controller;

import java.io.IOException;

import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeAcesso;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaGeral;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class PaginaInicialController {

	@FXML
	private BorderPane bpPrincipal;

	@FXML
	private Button btnCliente;

	@FXML
	private Button btnProduto;

	@FXML
	private Button btnAdicionarVenda;

	@FXML
	private Button btnAnimais;

	@FXML
	private Button btnRaca;

	@FXML
	private Button btnTipoAnimal;

	@FXML
	public void initialize() {
		EstaticosParaGeral.bpPrincipalAux = bpPrincipal;
	}

	@FXML
	void AdicionarVenda(ActionEvent event) {
		if (EstaticosDeAcesso.isLogado()) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/cadastro/CadastroVenda.fxml"));
			try {
				AnchorPane cursoView = (AnchorPane) loader.load();
				bpPrincipal.setCenter(cursoView);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@FXML
	void Cliente(ActionEvent event) {
		if (EstaticosDeAcesso.isLogado()) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/Cliente.fxml"));
			try {
				AnchorPane cursoView = (AnchorPane) loader.load();
				bpPrincipal.setCenter(cursoView);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@FXML
	void Produto(ActionEvent event) {
		if (EstaticosDeAcesso.isLogado()) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/Produto.fxml"));
			try {
				AnchorPane cursoView = (AnchorPane) loader.load();
				bpPrincipal.setCenter(cursoView);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@FXML
	void Venda(ActionEvent event) {
		if (EstaticosDeAcesso.isLogado()) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/Venda.fxml"));
			try {
				AnchorPane cursoView = (AnchorPane) loader.load();
				bpPrincipal.setCenter(cursoView);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@FXML
	void filial(ActionEvent event) {
		if (EstaticosDeAcesso.isLogado()) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/Filial.fxml"));
			try {
				AnchorPane cursoView = (AnchorPane) loader.load();
				bpPrincipal.setCenter(cursoView);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@FXML
	void funcionario(ActionEvent event) {
		if (EstaticosDeAcesso.isLogado()) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/Funcionario.fxml"));
			try {
				AnchorPane cursoView = (AnchorPane) loader.load();
				bpPrincipal.setCenter(cursoView);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@FXML
	void Animais(ActionEvent event) {
		if (EstaticosDeAcesso.isLogado()) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/Animais.fxml"));
			try {
				AnchorPane cursoView = (AnchorPane) loader.load();
				bpPrincipal.setCenter(cursoView);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@FXML
	void Raca(ActionEvent event) {

	}

	@FXML
	void TipoAnimal(ActionEvent event) {

	}

	public Button getBtnCliente() {
		return btnCliente;
	}

	public void setBtnCliente(Button btnCliente) {
		this.btnCliente = btnCliente;
	}

	public Button getBtnProduto() {
		return btnProduto;
	}

	public void setBtnProduto(Button btnProduto) {
		this.btnProduto = btnProduto;
	}

	public Button getBtnAdicionarVenda() {
		return btnAdicionarVenda;
	}

	public void setBtnAdicionarVenda(Button btnAdicionarVenda) {
		this.btnAdicionarVenda = btnAdicionarVenda;
	}

	public Button getBtnAnimais() {
		return btnAnimais;
	}

	public void setBtnAnimais(Button btnAnimais) {
		this.btnAnimais = btnAnimais;
	}

	public Button getBtnRaca() {
		return btnRaca;
	}

	public void setBtnRaca(Button btnRaca) {
		this.btnRaca = btnRaca;
	}

	public Button getBtnTipoAnimal() {
		return btnTipoAnimal;
	}

	public void setBtnTipoAnimal(Button btnTipoAnimal) {
		this.btnTipoAnimal = btnTipoAnimal;
	}
}
