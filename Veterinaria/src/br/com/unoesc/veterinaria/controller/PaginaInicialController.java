package br.com.unoesc.veterinaria.controller;

import java.io.IOException;

import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeAcesso;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaGeral;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class PaginaInicialController {

	@FXML
	private BorderPane bpPrincipal;

	@FXML
	private Button btAnimais;

	@FXML
	private Button btCliente;

	@FXML
	private Button btVendas;

	@FXML
	private Button btnProduto;

	@FXML
	private Button btFuncionario;

	@FXML
	private Button btFilial;

	@FXML
	private ImageView ftLogout;

	@FXML
	private Label lblNomeLogado;

	@FXML
	private ImageView ftUser;

	@FXML
	public void initialize() {
		EstaticosParaGeral.bpPrincipalAux = bpPrincipal;
		ftUser.setVisible(false);
		ftLogout.setVisible(false);
		lblNomeLogado.setText("");
		EstaticosDeAcesso.lblNomeLogado = lblNomeLogado;
		EstaticosDeAcesso.ftLogout = ftLogout;
		EstaticosDeAcesso.ftUser = ftUser;
		EstaticosDeAcesso.btAnimais = btAnimais;
		EstaticosDeAcesso.btCliente = btCliente;
		EstaticosDeAcesso.btFilial = btFilial;
		EstaticosDeAcesso.btFuncionario = btFuncionario;
		EstaticosDeAcesso.btnProduto = btnProduto;
		EstaticosDeAcesso.btVendas = btVendas;
		EstaticosDeAcesso.bloqueiaTudo();
	}

	@FXML
	void logout(MouseEvent event) {
		EstaticosDeAcesso.setFuncionario(null);
		EstaticosDeAcesso.setFilial(null);
		EstaticosDeAcesso.setLogado(false);
		lblNomeLogado.setText(" ");
		EstaticosDeAcesso.bloqueiaTudo();
		ftUser.setVisible(false);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/login.fxml"));
		try {
			AnchorPane cursoView = (AnchorPane) loader.load();
			bpPrincipal.setCenter(cursoView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	void cliente(ActionEvent event) {
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
	void produto(ActionEvent event) {
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
	void vendas(ActionEvent event) {
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
	void animais(ActionEvent event) {
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
}