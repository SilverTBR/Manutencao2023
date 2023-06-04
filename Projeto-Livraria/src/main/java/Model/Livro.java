/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Livro {
    /**
    * @param idLivro id do Livro 
    * @param titulo Titulo do Livro
    * @param sobrenomeAutor Sobrenome do autor
    * @param qntPgns Quantidade de paginas do Livro
    * @param genero Genero do Livro
    * @param editora Editora do Livro
    */
      
    protected int idLivro;
    protected String titulo;
    protected String nomeAutor;
    protected String sobrenomeAutor;
    protected int qntPgns;
    protected String genero;
    protected String editora;
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public String getSobrenomeAutor() {
        return sobrenomeAutor;
    }

    public void setSobrenomeAutor(String sobrenomeAutor) {
        this.sobrenomeAutor = sobrenomeAutor;
    }

    public int getQntPgns() {
        return qntPgns;
    }

    public void setQntPgns(int qntPgns) {
        this.qntPgns = qntPgns;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }
}
