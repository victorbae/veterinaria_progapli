package br.com.unoesc.veterinaria.controller;

import br.com.unoesc.veterinaria.banco.FilialBanco;
import br.com.unoesc.veterinaria.dao.FilialDao;
import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.model.Funcionario;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    }

    @FXML
    void Excluir(ActionEvent event) {

    }

    @FXML
    void Editar(ActionEvent event) {

    }

}
