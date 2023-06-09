import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClientePF extends Cliente {
    private final String cpf;
    private String genero;
    private String educacao;
    private Date dataNascimento;
    private ArrayList <Veiculo> listaVeiculos;

    public ClientePF(String nome, String endereco, String telefone, String email, String educacao,
    String genero, String cpf, Date dataNascimento){
        super(nome, endereco, telefone, email);
        this.educacao = educacao;
        this.genero = genero;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public String getCpf() {
        return cpf;
    }

    
    public boolean equals(ClientePF c){
        if(this.cpf.equals(c.getCpf())){
            return true;
        }
        return false;
    }

    protected boolean cadastrarVeiculo(String placa, String modelo, String marca, int ano){
        Veiculo veiculo = new Veiculo(placa, marca, modelo, ano);
        for(Veiculo v : listaVeiculos){
            if(v.getPlaca().equals(veiculo.getPlaca())){
                return false;
            }
        }
        listaVeiculos.add(veiculo);
        return true;
    }

    protected boolean removerVeiculo(String veiculo){
        for(Veiculo v : listaVeiculos){
            if(v.getPlaca().equals(veiculo)){
                listaVeiculos.remove(v);
                return true;
            }
        }
        return false;
    }

    // imprimir as informacoes do cliente PF
    @Override
    public String toString(){

		Date date = dataNascimento;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);

        String str = super.toString() + "CPF do cliente: " + cpf + "\n"
        + "Data de nascimento do cliente: " + strDate + "\n"
        + "Genero do cliente: " + genero + "\n"
        + "Educacao do cliente: " + educacao + "\n";

        return str;
    }
    
}
