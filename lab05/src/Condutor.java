import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Condutor {
    
    private final String cpf;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private Date dataNascimento;
    private ArrayList<Sinistro> listaSinistros;

    public Condutor(String cpf, String nome, String telefone, String endereco, 
    String email, Date dataNascimento, ArrayList<Sinistro> listaSinistros){
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.listaSinistros = new ArrayList<Sinistro>();
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    protected boolean adicionarSinistro(Sinistro sinistro){
        for(Sinistro s : listaSinistros){
            // nao queremos add o mesmo sinistro na lista
            if(s.getId()==sinistro.getId()){
                return false;
            }
        }
        listaSinistros.add(sinistro);
        return true;
    }


    // imprimir as informacoes do condutor
    @Override
    public String toString(){

        Date date = dataNascimento;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);
        
        String str = "Nome do condutor: " + nome + "\n"
        + "Cpf do condutor: " + cpf + "\n"
        + "Telefone do condutor: " + telefone + "\n"
        + "Endereco do condutor: " + endereco + "\n"
        + "Email do condutor: " + email + "\n"
        + "Data de nascimento do condutor: " + strDate + "\n";

        return str;
    }
}
