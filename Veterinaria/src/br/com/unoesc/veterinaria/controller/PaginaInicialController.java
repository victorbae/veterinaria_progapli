package br.com.unoesc.veterinaria.controller;

import java.io.IOException;

import br.com.unoesc.veterinaria.banco.PermissoesBanco;
import br.com.unoesc.veterinaria.dialogs.FuncionarioDialogFactory;
import br.com.unoesc.veterinaria.dialogs.TrocaFiliaisDialogFactory;
import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeAcesso;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaFilial;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaGeral;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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