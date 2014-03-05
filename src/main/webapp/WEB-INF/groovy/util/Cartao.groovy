package util

import com.google.appengine.api.datastore.*
import java.text.SimpleDateFormat

public class Cartao {
  def pontos

	public Cartao(pontos) {
		this.pontos = pontos
	}

  public String getCategoria() {
    getCategoria(pontos)
  }
  
  public String getCategoria(pontos) {
    if (pontos < 50000) return "bronze"
    if (pontos < 150000) return "prata"
    return "ouro"
  }

  public String categoriaProximoBonus() {
    def pontosFuturos = pontos + faltaQuantoParaGanharBonus()
    return getCategoria(pontosFuturos)
  }
  
 
  public int faltaQuantoParaGanharBonus() {
    return 10000 - (pontos % 10000)
  }
  
  public int valorProximoBonus() {
    switch (categoriaProximoBonus()) {
      case "bronze": return 100
      case "prata": return 150      
      default: return 100
    }
  }
}

