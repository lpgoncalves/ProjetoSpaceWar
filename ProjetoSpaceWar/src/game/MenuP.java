package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;



public class MenuP {
	 int tela = -1;
	 int itemSelecionado = 0;
	 String itens[];   
	 Graphics bbg;   
	 boolean menuAtivo;
	 int x;      //coordenada x do menu
	 int y;      //coordenada y do menu
	 int tamanhoDaFonte = 20; //nem precisa explicar não é ?
	 int distanciaEntreItens = 15;//distância entre cada item do menu!
	 Font fonte = new Font("Arial", Font.BOLD, tamanhoDaFonte);//a fonte do nosso menu
	 Color corSelecionado = new Color(255, 0, 0); // COR VERMELHA para o item selecionado
	 Color corNaoSelecionado = new Color(0, 0, 0); // COR PRETA para o item que não está selecionado
	 
	
	 public MenuP (int numeroDeItens, int x, int y, boolean ativo) {
	  itens = new String[numeroDeItens];
	  this.x = x;
	  this.y = y;
	  this.menuAtivo = ativo;
	 }
	 
	 public void controlar(KeyEvent e) {
		  if (menuAtivo) {
		   controlarMenu(e);
		  }
     }
	 
	 public void voltarAoMenu(KeyEvent e){
	      if(e.getKeyCode() == e.VK_ESCAPE){
	       tela = -1; 
	       menuAtivo = true;
	     }
	 }

	 private void controlarMenu(KeyEvent e) {
		 
		  if (e.getKeyCode() == e.VK_UP) {
			   itemSelecionado -= 1;
			  }
		  
		  if (e.getKeyCode() == e.VK_DOWN) {
			   itemSelecionado += 1;
			  } 
		  
		  if (itemSelecionado >= itens.length) {
			   itemSelecionado = 0;
			  }
		  
		  if (itemSelecionado < 0) {
			   itemSelecionado = itens.length - 1;
			  }
		  
		  if(e.getKeyCode() == e.VK_ENTER){
			   tela = itemSelecionado;
			   menuAtivo = false;
			  }
	 }
	 
	 public void desenharMenu() {
	
		 bbg.setFont(fonte);//seta a fonte que definimos bem acima na declaração de variáveis
		  
		  for (int i = 0; i < itens.length; i++) {
		      if(itemSelecionado == i){//se ele estiver selecionado muda a cor para vermelho e desenha o item na tela
		        bbg.setColor(corSelecionado);
		        bbg.drawString(itens[i], x, y+(i*(tamanhoDaFonte+distanciaEntreItens)));
		      }
		      else {//se não estiver selecionado muda a cor para preto e desenha o item na tela
		        bbg.setColor(corNaoSelecionado);
		        bbg.drawString(itens[i], x, y+(i*(tamanhoDaFonte+distanciaEntreItens)));
	          }
	     }
		  
	 }

}


