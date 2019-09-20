/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservidor;

import Tratamento.WeChat;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aless
 */
public class Servidor {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.printf("Porta: ");
        int n = s.nextInt();
        try {
            new Servidor(n).executa();
        } catch (IOException ex) {
            System.out.println("Erro carteado: " + ex.getMessage());
        }
    }
    
    private int porta;
    private List<PrintStream> clientes;
    
    public Servidor (int porta){
        this.porta = porta;
        this.clientes = new ArrayList<PrintStream>();
    }
    
    public void executa() throws IOException {
        
        ServerSocket servidor = new ServerSocket(this.porta);
        System.out.println("Porta " + this.porta + " aberta!");            

        while(true){
            Socket cliente = servidor.accept();
            System.out.println("Nova conexão com o cliente" + cliente.getInetAddress().getHostAddress());              

            // adiciona saída do cliente na lista
            PrintStream ps = new PrintStream(cliente.getOutputStream());
            this.clientes.add(ps);

            // Cria tratador de cliente numa nova thread
            WeChat tc = new WeChat(cliente.getInputStream(), this);
            new Thread(tc).start();
        }  
    }
    
    public void distribuiMensagem(String msg){
        // Envia mensagem pra Galera
        for (PrintStream cliente : this.clientes){
            cliente.println(msg);
        }
    }
}
