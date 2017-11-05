/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pantallas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import programa.Coordenada;

/**
 *
 * @author juanm
 */
public class Panel_4 extends JPanel implements MouseListener {

    int xref1,xref2,yref1,yref2,tam_cuadrado;
    Coordenada c1,c2;

    public Panel_4(int xref1, int xref2, int yref1, int yref2, int tam_cuadros) {
        this.xref1 = xref1;
        this.xref2 = xref2;
        this.yref1 = yref1;
        this.yref2 = yref2;
        this.tam_cuadrado = tam_cuadros;
        this.c1 = new Coordenada(xref1,yref1);
        this.c2 = new Coordenada(xref2,yref2);
    }
    
        @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarEscenario(g);   

    }

    private void dibujarEscenario(Graphics g) {
        Graphics2D c = (Graphics2D) g; 
        Image fondo = loadImage("fondo_partida.jpg");
        c.drawImage(fondo, 0, 0, null);
        c.setColor(Color.WHITE);
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                c.drawRect(xref1+(i*this.tam_cuadrado),yref1+(j*this.tam_cuadrado), this.tam_cuadrado, this.tam_cuadrado); 
                c.drawRect(xref2+(i*this.tam_cuadrado),yref2+(j*this.tam_cuadrado), this.tam_cuadrado, this.tam_cuadrado);
            }
        }
    }
       
    public Image loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }
    
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
     
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
     
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }
    
}
