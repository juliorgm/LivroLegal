package br.com.juliorgm.livrolegal.model;

import java.io.Serializable;

public class Livro implements Serializable {

    private String mId;

    public void setmId(String mId) {
        this.mId = mId;
    }

    private String mTitulo;
    private String mAutor;
    private String mEditora;
    private String mDescricao;

    public Livro() {}

    public Livro(String mTitulo, String mAutor, String mEditora, String mDescricao) {
        this.mTitulo = mTitulo;
        this.mAutor = mAutor;
        this.mEditora = mEditora;
        this.mDescricao = mDescricao;
    }

    public String getmTitulo() {
        return mTitulo;
    }

    public String getmAutor() {
        return mAutor;
    }

    public String getmEditora() {
        return mEditora;
    }

    public String getmDescricao() {
        return mDescricao;
    }

    public String getmId() {
        return mId;
    }
}
