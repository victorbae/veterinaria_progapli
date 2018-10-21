package br.com.unoesc.veterinaria.controller.cadastro;

import br.com.unoesc.veterinaria.banco.ProdutoBanco;
import br.com.unoesc.veterinaria.dao.ProdutoDao;
import br.com.unoesc.veterinaria.model.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

	@FXML
	private ComboBox<?> cbxEstoque;

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
	void Cancelar(ActionEvent event) {

	}

	@FXML
	void Limpar(ActionEvent event) {
		limpaTela();
	}

	@FXML
	void Salvar(ActionEvent event) {

		preencheProduto();
		produtoDao.inserir(produto);

		clicadoSalvar = true;
		if (dialogStage != null) {
			dialogStage.close();
		}

	}

	public void preencheProduto() {
		produto = new Produto();
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

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}
}
