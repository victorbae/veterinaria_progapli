package br.com.unoesc.veterinaria.controller.cadastro;

import org.controlsfx.control.textfield.TextFields;

import br.com.unoesc.veterinaria.banco.ProdutoBanco;
import br.com.unoesc.veterinaria.banco.VendaProdutoBanco;
import br.com.unoesc.veterinaria.dao.ProdutoDao;
import br.com.unoesc.veterinaria.dao.VendaProdutoDao;
import br.com.unoesc.veterinaria.model.Produto;
import br.com.unoesc.veterinaria.model.VendaProduto;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaProduto;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaVenda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerAdicionaProdutoVenda {

	@FXML
	private TextField tfProduto;

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

	private Stage dialogStage;

	private boolean clicadoSalvar;

	private Produto produto;

	private VendaProduto vendaProduto;

	private ProdutoDao produtoDao = new ProdutoBanco();

	private VendaProdutoDao vendaProdutoDao = new VendaProdutoBanco();

	@FXML
	private void initialize() {
//		tfValorTotal.setDisable(true);
		TextFields.bindAutoCompletion(tfProduto, produtoDao.listar());

	}

	@FXML
	void Adicionar(ActionEvent event) {
		populaVendaProduto();

		EstaticosParaVenda.carrinhoAux.add(vendaProduto);
		ControllerCadastroVenda.atualizaListaCarinho();
		dialogStage.close();
	}

	@FXML
	void Cancelar(ActionEvent event) {
		dialogStage.close();
	}

	public void populaVendaProduto() {
		produto = EstaticosParaProduto.achaProdutoByNome(tfProduto.getText());

		vendaProduto = new VendaProduto();
		vendaProduto.setIdVendaProduto(666);
		vendaProduto.setVenda(EstaticosParaVenda.venda);
		vendaProduto.setProduto(EstaticosParaVenda.achaProduto(produto.getIdProduto()));
		vendaProduto.setQuantidade(Double.parseDouble(tfQuantidade.getText()));
		vendaProduto.setValorUnitario(produto.getValorUnitario());
		vendaProduto.setValorTotal(
				vendaProduto.calculaValorTotal(Double.parseDouble(tfQuantidade.getText()), produto.getValorUnitario()));

		EstaticosParaVenda.vendaProduto = vendaProduto;

	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}
}
