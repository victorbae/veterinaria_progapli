package br.com.unoesc.veterinaria.controller.cadastro;

import br.com.unoesc.veterinaria.banco.ProdutoBanco;
import br.com.unoesc.veterinaria.dao.ProdutoDao;
import br.com.unoesc.veterinaria.model.Produto;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeAcesso;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaGeral;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaProduto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerCadastroProduto {

	@FXML
	private Label lblMaster;

	@FXML
	private TextField tfNome;

	@FXML
	private TextField tfQntEstoque;

	@FXML
	private TextField tfValorEntrada;

	@FXML
	private TextField tfMargemLucro;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnLimpar;

	private Stage dialogStage;

	private boolean clicadoSalvar;

	private Produto produto;

	private ProdutoDao produtoDao = new ProdutoBanco();

	@FXML
	private void initialize() {

		if (EstaticosParaProduto.isEditando) {
			this.produto = EstaticosParaProduto.produto;
			populaTela();
			lblMaster.setText("Editando Produto");
		}
	}

	@FXML
	void Cancelar(ActionEvent event) {
		limpaTela();
		dialogStage.close();
	}

	@FXML
	void Limpar(ActionEvent event) {
		limpaTela();
	}

	@FXML
	void Salvar(ActionEvent event) {
		try {
			preencheProduto();

			if (EstaticosParaProduto.isEditando) {
				produtoDao.alterar(produto);
				EstaticosParaProduto.isEditando = false;
			} else {
				produtoDao.inserir(produto);
			}
			clicadoSalvar = true;
			if (dialogStage != null) {
				dialogStage.close();
			}
		} catch (Exception e) {
			EstaticosParaGeral.chamaErroNaoPreenchido(dialogStage);
		}
	}

	private void populaTela() {
		tfNome.setText(produto.getNome());
		tfQntEstoque.setText(String.valueOf(produto.getQuantidadeEstoque()));
		tfValorEntrada.setText(String.valueOf(produto.getValorEntrada()));
		tfMargemLucro.setText(String.valueOf(produto.getMargemLucro()));

	}

	public void preencheProduto() {
		if (!EstaticosParaProduto.isEditando) {
			produto = new Produto();
		} else {
			produto.setIdProduto(EstaticosParaProduto.produto.getIdProduto());
		}
		produto.setNome(tfNome.getText());
		produto.setQuantidadeEstoque(Double.parseDouble(tfQntEstoque.getText()));
		produto.setValorEntrada(Double.parseDouble(tfValorEntrada.getText()));
		produto.setMargemLucro(Double.parseDouble(tfMargemLucro.getText()));
		produto.setFilial(EstaticosDeAcesso.getFilial());
	}

	public void limpaTela() {
		tfNome.clear();
		tfQntEstoque.clear();
		tfValorEntrada.clear();
		tfMargemLucro.clear();
	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}
}
