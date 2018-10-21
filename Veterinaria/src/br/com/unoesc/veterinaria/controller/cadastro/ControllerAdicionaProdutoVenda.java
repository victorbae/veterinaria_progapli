package br.com.unoesc.veterinaria.controller.cadastro;

import br.com.unoesc.veterinaria.banco.ProdutoBanco;
import br.com.unoesc.veterinaria.banco.VendaProdutoBanco;
import br.com.unoesc.veterinaria.dao.ProdutoDao;
import br.com.unoesc.veterinaria.dao.VendaProdutoDao;
import br.com.unoesc.veterinaria.model.Produto;
import br.com.unoesc.veterinaria.model.Venda;
import br.com.unoesc.veterinaria.model.VendaProduto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ControllerAdicionaProdutoVenda {

	@FXML
	private ComboBox<Produto> cbxProduto;

	@FXML
	private TextField tfQuantidade;

	@FXML
	private TextField tfValorUnitario;

	@FXML
	private TextField tfValorTotal;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnAdicionar;

	private Venda venda;

	private Produto produto;

	private ProdutoDao produtoDao = new ProdutoBanco();

	private VendaProduto vendaProduto;

	private VendaProdutoDao vendaProdutoDao = new VendaProdutoBanco();

	@FXML
	private void initialize() {
		populaCombo();
	}

	@FXML
	void Adicionar(ActionEvent event) {
		populaVendaProduto();
		vendaProdutoDao.inserir(vendaProdutoDao);
	}

	@FXML
	void Cancelar(ActionEvent event) {

	}

	public void populaVendaProduto() {
		vendaProduto = new VendaProduto();

		vendaProduto.setProduto(vendaProduto.achaProduto(cbxProduto.getValue().getIdProduto()));
		vendaProduto.setQuantidade(Double.parseDouble(tfQuantidade.getText()));
		vendaProduto.setValorUnitario(cbxProduto.getValue().getValorUnitario());
		vendaProduto.setValorTotal(vendaProduto.calculaValorTotal(Double.parseDouble(tfQuantidade.getText()),
				cbxProduto.getValue().getValorUnitario()));

	}

	private void populaCombo() {
		for (Produto produto : produtoDao.listar()) {
			cbxProduto.getItems().add(produto);
		}
	}
}
