import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class SeguroPJ extends Seguro {

    private Frota frota;
    private ClientePJ cliente;

    public static Scanner read = new Scanner(System.in);

    public SeguroPJ(Frota frota, ClientePJ cliente, int id, Date dataInicio, Date dataFim, Seguradora seguradora){
        super(id, dataFim, dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
    }

    public Frota getFrota() {
        return frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public ClientePJ getCliente() {
        return cliente;
    }

    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }

    @Override
    protected boolean desautorizarCondutor(String condutor){
        boolean removeu = false;
        for(Condutor c : super.getListaCondutores()){
            if(condutor.equals(c.getCpf())){
                super.getListaCondutores().remove(c);
                removeu = true;
            }
        }
        for(Sinistro s : super.getListaSinistros()){
            if(s.getCondutor().getCpf().equals(condutor)){
                super.getListaSinistros().remove(s);
                removeu = true;
            }
        }
        return removeu;
    }

    @Override
    protected boolean autorizarCondutor(Condutor condutor){
        for(Condutor c : super.getListaCondutores()){
            if(condutor.getCpf().equals(c.getCpf())){
                return false;
            }
        }
        super.getListaCondutores().add(condutor);
        return true;
    }

    @Override
    protected double calcularValor(){
        int quantidadeFunc = cliente.getQuantidadeFunc();
        int qntVeiculos = 0;
        for(Frota frota : cliente.getListaFrota()){
            qntVeiculos += frota.getListaVeiculos().size();
        }

        int qntSinistrosCondutor = 0;

        int qntSinistrosCliente = 0;

        for(Sinistro s : super.getListaSinistros()){
            if(s.getSeguro() instanceof SeguroPJ){
                qntSinistrosCliente++;
            }
        }

        for(Condutor c : super.getListaCondutores()){
            qntSinistrosCondutor += c.getListaSinistros().size();
        }

        LocalDate d1 = Instant.ofEpochMilli(cliente.getDataFundacao().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate d2 = LocalDate.now();

		double anosPosFundacao = Period.between(d1, d2).getYears();

        return ( CalcValor.VALOR_BASE.getValor() * (10 + ( quantidadeFunc ) /10) * 
        (1 + 1/(qntVeiculos+2) ) * (1 + 1/(anosPosFundacao+2) ) * (2 + qntSinistrosCliente/10) * (5 + qntSinistrosCondutor/10));
    }

    @Override
    protected boolean gerarSinistro(Seguradora seguradora, Seguro seguro) throws ParseException{
        Random random = new Random();
        int id_gerado = random.nextInt();

        System.out.println("Entre com a data do sinsitro: ");
        String data_sinistro = read.nextLine();

        System.out.println("Entre com o endereco do sinistro: ");
        String end_sinistro = read.nextLine();

        System.out.println("Entre com o nome do condutor: ");
        String nome_cond = read.nextLine();

        System.out.println("Entre com o cpf do condutor: ");
        System.out.println("->> cuidado com o formato do cpf: ###.###.###-##");
        String cpf_cond = read.nextLine();

        while((Validacao.validarCPF(cpf_cond))==false){
            System.out.println("CPF do cliente_pf eh invalido! Digite outro cpf: ");
            cpf_cond = read.nextLine();
        }

        System.out.println("Entre com o endereco do condutor: ");
        String end_cond = read.nextLine();

        System.out.println("Entre com o email do condutor: ");
        String email_cond = read.nextLine();

        System.out.println("Entre com o telefone do condutor: ");
        String tel_cond = read.nextLine();

        System.out.println("Entre com a data de nascimento do condutor: ");
        String data_nascimento = read.nextLine();
        Date nascimento_cond = new SimpleDateFormat("dd/MM/yyyy").parse(data_nascimento);

        Condutor cond = new Condutor(cpf_cond, nome_cond, tel_cond, end_cond, email_cond, nascimento_cond, null);

        System.out.println("Placa do veiculo: ");
        String placa = read.nextLine();
        while((Validacao.validarPlaca(placa))==false){
            System.out.println("Placa eh invalida! Digite outra placa: ");
            placa = read.nextLine();
        }

        System.out.println("Modelo do veiculo: ");
        String modelo = read.nextLine();

        System.out.println("Marca do veiculo: ");
        String marca = read.nextLine();

        System.out.println("Ano do veiculo: ");
        int ano = read.nextInt();
        read.nextLine();

        Veiculo veiculo_condutor = new Veiculo(placa, marca, modelo, ano);

        Sinistro sinistro = new Sinistro(id_gerado, data_sinistro, end_sinistro, seguradora, veiculo_condutor, cond, seguro);
        super.getListaSinistros().add(sinistro);
        super.getListaCondutores().add(cond);
        return true;
    }

    // imprimir as informacoes do seguro pj
    @Override
    public String toString(){

        String str = "Frota do seguro: \n" + frota.toString() + "\n"
        + "Cliente do seguro: \n" + cliente.toString() + "\n";

        return str;
    }
}
