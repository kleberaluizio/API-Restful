package com.kleberaluizio.coffee.model;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

public class Coffee {

    private Long id;

    @NotBlank(message = "O nome não pode estar em branco.")
    private String nome;

    @NotNull(message = "O preço deve ser informado.")
    @PositiveOrZero(message = "O preço deve ser maior ou igual a zero.")
    private Double preco;

    @PastOrPresent(message = "A data de fabricação deve ser menor ou igual a hoje.")
    private LocalDate dataDeFabricacao;

    @FutureOrPresent(message = "A data de validade não pode ser anterior a data de hoje.")
    private LocalDate dataDeValidade;

    public Coffee() {}

    public Coffee(Long id,
                  String nome,
                  Double preco,
                  LocalDate dataDeFabricacao,
                  LocalDate dataDeValidade) {

        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.dataDeFabricacao = dataDeFabricacao;
        this.dataDeValidade = dataDeValidade;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public LocalDate getDataDeFabricacao() {
        return dataDeFabricacao;
    }

    public void setDataDeFabricacao(LocalDate dataDeFabricacao) {
        this.dataDeFabricacao = dataDeFabricacao;
    }

    public LocalDate getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(LocalDate dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Coffee coffee = (Coffee) object;
        return Objects.equals(id, coffee.id) && Objects.equals(nome, coffee.nome) && Objects.equals(preco, coffee.preco) && Objects.equals(dataDeFabricacao, coffee.dataDeFabricacao) && Objects.equals(dataDeValidade, coffee.dataDeValidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, preco, dataDeFabricacao, dataDeValidade);
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", dataDeFabricacao=" + dataDeFabricacao +
                ", dataDeValidade=" + dataDeValidade +
                '}';
    }
}
