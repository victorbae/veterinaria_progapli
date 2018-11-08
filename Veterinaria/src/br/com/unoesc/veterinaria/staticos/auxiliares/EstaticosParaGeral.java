package br.com.unoesc.veterinaria.staticos.auxiliares;

import java.net.URL;
import java.util.Map;

import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import javafx.scene.layout.BorderPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class EstaticosParaGeral {

	public static BorderPane bpPrincipalAux;

	public static void geraRelatorio(String path, Class<?> classe, Map<String, Object> parametros) {
		URL url = classe.getClass().getResource(path);
		JasperPrint jasperPrint;
		try {
			if (parametros != null) {
				jasperPrint = JasperFillManager.fillReport(url.getPath(), parametros,
						ConexaoPrincipal.retornaconecao());
			} else {
				jasperPrint = JasperFillManager.fillReport(url.getPath(), null, ConexaoPrincipal.retornaconecao());
			}
			JasperViewer.viewReport(jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

}
