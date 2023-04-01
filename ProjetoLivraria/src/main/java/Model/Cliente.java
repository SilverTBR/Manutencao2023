/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Cliente {
     /**
     * @param idCliente Id do cliente desta classe
     * @param Nome Nome do cliente
     * @param Sobrenome Sobrenome do cliente
     * @param CPF CPF do cliente
     * @param Estado Estado do cliente
     * @param Bairro Nome bairro do cliente
     * @param Endereço Endereço do cliente
     * @param Cidade Nome da cidade do cliente
     */
    protected int idCliente;
    protected String Nome;
    protected String Sobrenome;
    protected String CPF;
    protected String Estado;
    protected String Bairro;
    protected String Endereco;
    protected String Cidade;

    public Cliente(int idCliente, String Nome, String Sobrenome, String CPF, String Estado, String Cidade, String Bairro, String Endereco) {
        this.idCliente = idCliente;
        this.Nome = Nome;
        this.Sobrenome = Sobrenome;
        this.CPF = CPF;
        this.Estado = Estado;
        this.Bairro = Bairro;
        this.Endereco = Endereco;
        this.Cidade = Cidade;
    }
    public Cliente() {
        
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String Sobrenome) {
        this.Sobrenome = Sobrenome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }
    
    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

}
