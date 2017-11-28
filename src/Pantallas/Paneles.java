/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pantallas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import programa.Coordenada;
import programa.Tablero;
import programa.Maquina;
import programa.Jugador;
import programa.musica1;

/**
 *
 * @author juanm
 */
public class Paneles extends JPanel implements ActionListener {

    private int xref1, yref1, tam_cuadrado, tipo_juego;
    private boolean online, local;
    Panel_Inicial p1;
    Panel_Eleccion p2;
    Panel_Ubi_Barcos_Jug2 p31;
    Panel_Ubi_Barcos_Jug1 p32;
    Panel_Ubi_barcos_vsPc p3;
    Panel_Partida p4;
    Panel_Online p5;
    Panel_Informacion pi;
    panel_Configuracion pc;
    Tablero tablero_jugador2, tablero_jugador1, tablero_local;
    private Jugador jugador01, jugador02;
    private Maquina maquina;
    Color color = new Color(128, 128, 0);
//    private ServerSocket servidor;
//    private Socket cliente;
//    private DataOutputStream toServerlocal,toServervisitante;
//    private DataInputStream fromServerlocal,fromServervisitante;

    public Paneles() {
        super(new BorderLayout());
        String m="Feel Good Inc. [8 Bit Tribute to Gorillaz] - 8 Bit Universe (online-audio-converter.com).wav";
        musica1 fondo=new musica1(m);
        fondo.run();
        this.p1 = new Panel_Inicial();
        this.p2 = new Panel_Eleccion();
        this.p31 = new Panel_Ubi_Barcos_Jug2();
        this.p32 = new Panel_Ubi_Barcos_Jug1();
        this.p3 = new Panel_Ubi_barcos_vsPc();
        this.p5 = new Panel_Online();
        this.pi = new Panel_Informacion();
        this.pc = new panel_Configuracion();
        this.tablero_local = p3.tablero1;
        this.tablero_jugador1 = p32.tablero1;
        this.tablero_jugador2 = p31.tablero1;
        add(p1);

        p1.iniciar.addActionListener(this);
        p1.configuracion.addActionListener(this);
        p1.informacion.addActionListener(this);

        p2.ONLINE.addActionListener(this);
        p2.VSLOCAL.addActionListener(this);
        p2.VSCOM.addActionListener(this);
        p2.REGRESAR.addActionListener(this);

        p3.REGRESAR.addActionListener(this);

        p3.horizontal.addActionListener(this);
        p3.vertical.addActionListener(this);
        p3.poner.addActionListener(this);

        p3.CONTINUAR.addActionListener(this);

        p31.CONTINUAR.addActionListener(this);

        p31.poner.addActionListener(this);
        p31.horizontal.addActionListener(this);
        p31.vertical.addActionListener(this);

        p32.CONTINUAR.addActionListener(this);

        p32.poner.addActionListener(this);
        p32.horizontal.addActionListener(this);
        p32.vertical.addActionListener(this);

        pi.regresar.addActionListener(this);
        pc.regresar.addActionListener(this);

        p5.REGRESAR.addActionListener(this);
        p5.LOCAL.addActionListener(this);
        p5.INVITADO.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(p1.iniciar)) {
            remove(p1);
            add(p2);
        }
        if (e.getSource().equals(p1.configuracion)) {
            remove(p1);
            add(pc);
        }
        if (e.getSource().equals(pc.regresar)) {
            remove(pc);
            add(p1);
        }
        if (e.getSource().equals(p1.informacion)) {
            remove(p1);
            add(pi);
        }
        if (e.getSource().equals(pi.regresar)) {
            remove(pi);
            add(p1);
        }
        if (e.getSource().equals(p2.REGRESAR)) {
            remove(p2);
            add(p1);
        }
        if (e.getSource().equals(p2.ONLINE)) {
            remove(p2);
            add(p5);
            this.tipo_juego = 1;
            this.online = true;
        }
        if (e.getSource().equals(p5.REGRESAR)) {
            remove(p5);
            add(p2);
            this.online = false;
        }
        if (e.getSource().equals(p2.VSLOCAL)) {
            remove(p2);
            add(p32);
            this.tipo_juego = 2;
        }
        if (e.getSource().equals(p32.poner)) {
            p32.poner();
        }
        if (e.getSource().equals(p32.CONTINUAR)) {
            //    if (!online) {
            if (p32.tablero1.getBarcos_creados() == 5) {
                remove(p32);
                add(p31);
            }

        }
        if (e.getSource().equals(p31.CONTINUAR)) {
            //  if (!online) {
            if (p31.tablero1.getBarcos_creados() == 5) {
                remove(p31);
                this.jugador01 = new Jugador("jugador 1", this.tablero_jugador1);
                this.jugador02 = new Jugador("jugador 2", this.tablero_jugador2);
                p4 = new Panel_Partida(this.jugador01, this.jugador02);
                add(p4);
            }
        }
        if (e.getSource().equals(p2.VSCOM)) {
            remove(p2);
            add(p3);
            this.tipo_juego = 3;
        }
        if (e.getSource().equals(p3.REGRESAR)) {
            remove(p3);
            add(p2);
        }
        if (e.getSource().equals(p3.poner)) {
            p3.poner();
        }
        if (e.getSource().equals(p31.poner)) {
            p31.poner();
        }
        if (e.getSource().equals(p3.CONTINUAR)) {
            if (p3.tablero1.getbarcos_creados() == 5) {
                remove(p3);
                this.jugador01 = new Jugador("jugador 1", this.tablero_local);
                this.maquina = new Maquina(new Coordenada(xref1 + 350, yref1), this.tam_cuadrado);
                p4 = new Panel_Partida(this.jugador01, this.maquina);
                add(p4);
            }
            if (e.getSource().equals(p4.INICIO)) {
                remove(p4);
                add(p1);
            }
        }

        repaint();

        revalidate();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image fondo = loadImage("panel_online.png");
        g.drawImage(fondo, 0, 0, null);
    }

    public Image loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }
}
