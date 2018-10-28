package br.com.unoesc.veterinaria.controller.cadastro;

import br.com.unoesc.veterinaria.banco.ProdutoBanco;
import br.com.unoesc.veterinaria.dao.ProdutoDao;
import br.com.unoesc.veterinaria.model.Produto;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaProduto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerCadastroProduto {

	@FXML
	private TextField tfNome;

	@FXML
	private TextField tfQntEstoque;

	@FXML
	private TextField tfValorEntrada;

	@FXML
	private TextField tfMargemLucro;

//	@FXML
//	private ComboBox<Estoque> cbxEstoque;

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
		this.produto = EstaticosParaProduto.produto;
//		populaCombo();

		if (EstaticosParaProduto.isEditando) {
			populaTela();
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

	}

	private void populaTela() {
		tfNome.setText(produto.getNome());
		tfQntEstoque.setText(String.valueOf(produto.getQuantidadeEstoque()));
		tfValorEntrada.setText(String.valueOf(produto.getValorEntrada()));
		tfMargemLucro.setText(String.valueOf(produto.getMargemLucro()));
//		cbxEstoque.setValue(produto.getEstoque);

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
//		produto.setEstoque(produto.achaEstoque(cbxEstoque.getValue()));
	}

	public void limpaTela() {
		tfNome.clear();
		tfQntEstoque.clear();
		tfValorEntrada.clear();
		tfMargemLucro.clear();
	}

//	private void populaCombo() {
//		for (Estoque estoque : estoqueDao.listar()) {
//			cbxEstoque.getItems().add(estoque);
//		}
//	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}
}
