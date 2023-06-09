public abstract class Cliente {
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    

    public Cliente(String nome, String endereco, String telefone, String email){
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // imprimir as informacoes do cliente
    @Override
    public String toString(){

        String str = "Nome do cliente: " + nome + "\n"
        + "Endereco do cliente: " + endereco + "\n"
        + "Telefone do cliente: " + telefone + "\n"
        + "Email do cliente: " + email + "\n";

        return str;
    }
}
