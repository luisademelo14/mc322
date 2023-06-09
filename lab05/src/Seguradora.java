import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Seguradora {
    private final String cnpj;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Seguro> listaSeguros;
    public static Scanner read = new Scanner(System.in);

    public Seguradora(String cnpj, String nome, String telefone, String endereco, String email){
        this.cnpj = cnpj;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.listaClientes = new ArrayList<Cliente>();
        this.listaSeguros = new ArrayList<Seguro>();
    }

    public String getCnpj() {
        return cnpj;
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
    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }
    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
    public ArrayList<Seguro> getListaSeguros() {
        return listaSeguros;
    }
    public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
        this.listaSeguros = listaSeguros;
    }

    protected void listarClientes(String tipoCliente){
        // se a lista esta vazia, nao temos o que imprimir
        if(listaClientes.isEmpty())
            return;
        // se for pessoa fisica
        if(tipoCliente.equals("PF")){
            for(Cliente c : listaClientes){
                if(c instanceof ClientePF){
                    System.out.println("################");
                    System.out.println(c.toString());
                    System.out.println("################");
                }
            }
        }
        // se for pessoa juridica
        else{
            for(Cliente c : listaClientes){
                if(c instanceof ClientePJ){
                    System.out.println("################");
                    System.out.println(c.toString());
                    System.out.println("################");
                }
            }
        }
    }

    protected void listarVeiculos(String cliente){
        // pessoa juridica (cnpj)
        if(cliente.length()==18){
            for(Cliente c : listaClientes){
                if((c instanceof ClientePJ)){
                    ClientePJ clientePJ = (ClientePJ) c;
                    if(clientePJ.getCnpj().equals(cliente)){
                        for(Frota f : clientePJ.getListaFrota()){
                                boolean listar = clientePJ.getVeiculosPorFrota(f.getCode());
                                if(!listar)
                                    System.out.println("Nao foi possivel realizar essa operacao!");    
                        }
                    }
                        
                }
            }
        }
        // pessoa fisica (cpf)
        else{
            for(Cliente c : listaClientes){
                if((c instanceof ClientePF)){
                    ClientePF clientePF = (ClientePF) c;
                    if(clientePF.getCpf().equals(cliente)){
                        for(Veiculo v : clientePF.getListaVeiculos()){
                            System.out.println("################");
                            System.out.println(v.toString());
                            System.out.println("################");
                        }
                       
                    }
                }
            }
        }
    }

    protected boolean cadastrarVeiculo(String cliente, Veiculo veiculo){
        // pessoa juridica (cnpj)
        if(cliente.length()==18){
            System.out.println("Entre com o code da frota: ");
            String code = read.nextLine();
            for(Cliente c : listaClientes){
                if((c instanceof ClientePJ)){
                    ClientePJ clientePJ = (ClientePJ) c;
                    if(clientePJ.getCnpj().equals(cliente)){
                        for(Frota f : clientePJ.getListaFrota()){
                            if(code.equals(f.getCode())){
                                for(Veiculo v : f.getListaVeiculos()){
                                    if(v.getPlaca().equals(veiculo.getPlaca())){
                                        return false;
                                    }
                                }
                                f.getListaVeiculos().add(veiculo);
                                return true;
                            }
                        } 
                    }
                }
            }
        }
        // pessoa fisica (cpf)
        else{
            for(Cliente c : listaClientes){
                if((c instanceof ClientePF)){
                    ClientePF clientePF = (ClientePF) c;
                    if(clientePF.getCpf().equals(cliente)){
                        for(Veiculo v : clientePF.getListaVeiculos()){
                            if(v.getPlaca().equals(veiculo.getPlaca())){
                                return false;
                            }
                        }
                        clientePF.getListaVeiculos().add(veiculo);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected boolean removerVeiculo(String cliente, String veiculo){
        // pessoa juridica (cnpj)
        if(cliente.length()==18){
            for(Cliente c : listaClientes){
                if((c instanceof ClientePJ)){
                    ClientePJ clientePJ = (ClientePJ) c;
                    if(clientePJ.getCnpj().equals(cliente)){
                        for(Frota f : clientePJ.getListaFrota()){
                            for(Veiculo v : f.getListaVeiculos()){
                                if(v.getPlaca().equals(veiculo)){
                                    f.getListaVeiculos().remove(v);
                                    return true;
                                }
                            }
                        }
                        
                    }
                }
            }

        }
        // pessoa fisica (cpf)
        else{
            for(Cliente c : listaClientes){
                if((c instanceof ClientePF)){
                    ClientePF clientePF = (ClientePF) c;
                    if(clientePF.getCpf().equals(cliente)){
                        for(Veiculo v : clientePF.getListaVeiculos()){
                            clientePF.getListaVeiculos().remove(v);
                            return true;
                        }
                       
                    }
                }
            }
        }
        return false;
    }

    protected boolean removerFrota(String cnpj_cliente, String code){
        for(Cliente c : listaClientes){
            if(c instanceof ClientePJ){
                ClientePJ cliente_pj = (ClientePJ) c;
                if(cliente_pj.getCnpj().equals(cnpj_cliente)){
                    for(Frota f : cliente_pj.getListaFrota()){
                        if(f.getCode().equals(code)){
                            cliente_pj.getListaFrota().remove(f);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    protected void listarFrotas(String cnpj_cliente){
        boolean existe = false;
        for(Cliente c : listaClientes){
            if(c instanceof ClientePJ){
                ClientePJ cliente_pj = (ClientePJ) c;
                if(cliente_pj.getCnpj().equals(cnpj_cliente)){
                    for(Frota f : cliente_pj.getListaFrota()){
                        System.out.println("################");
                        System.out.println(f.toString());
                        System.out.println("################");
                    }
                }
            }
        }
        if(!existe)
            System.out.println("Tal cliente nao foi cadastrado nesta seguradora");
    }

    protected void atualizarSeguros(){
        for(Seguro seguro : listaSeguros){
            seguro.calcularValor();
        }
    }

    protected void addVeiculoFrota(String cnpj_cliente, String code, Veiculo veiculo){
        boolean existe_cliente = false;
        boolean existe_frota = false;
        for(Cliente c : listaClientes){
            if(c instanceof ClientePJ){
                ClientePJ cliente_pj = (ClientePJ) c;
                if(cliente_pj.getCnpj().equals(cnpj_cliente)){
                    existe_cliente = true;
                    for(Frota f : cliente_pj.getListaFrota()){
                        if(f.getCode().equals(code)){
                            existe_frota = true;
                            f.addVeiculo(veiculo);
                        }
                    }
                }
            }
        }

        if(!existe_cliente || !existe_frota)
            System.out.println("Nao foi possivel realizar tal operacao");
    }

    protected void removerVeiculoFrota(String cnpj_cliente, String code, String veiculo){
        boolean existe_cliente = false;
        boolean existe_frota = false;
        for(Cliente c : listaClientes){
            if(c instanceof ClientePJ){
                ClientePJ cliente_pj = (ClientePJ) c;
                if(cliente_pj.getCnpj().equals(cnpj_cliente)){
                    existe_cliente = true;
                    for(Frota f : cliente_pj.getListaFrota()){
                        if(f.getCode().equals(code)){
                            existe_frota = true;
                            f.removeVeiculo(veiculo);
                        }
                    }
                }
            }
        }

        if(!existe_cliente || !existe_frota)
            System.out.println("Nao foi possivel realizar tal operacao");
    }

    protected void listarCondutoresDeSeguro(int id){
        boolean existe = false;
        for(Seguro s : listaSeguros){
            if(id==s.getId()){
                s.listarCondutores();
                existe = true;
            }
        }
        if(!existe)
            System.out.println("Tal seguro nao foi cadastrado nesta seguradora");

    }

    protected void listarSinistrosDeSeguro(int id){
        boolean existe = false;
        for(Seguro s : listaSeguros){
            if(id==s.getId()){
                s.listarSinistros();
                existe = true;
            }
        }
        if(!existe)
            System.out.println("Tal seguro nao foi cadastrado nesta seguradora");

    }

    protected void listarSeguros(){
        for(Seguro s : listaSeguros){
            System.out.println("################");
            System.out.println(s.toString());
            System.out.println("################");
        }
    }

    protected boolean gerarSeguro(String tipo) throws ParseException{
        if(tipo.equals("PF")){
            System.out.println("Entre com o cpf do cliente: ");
            String cpf_cliente = read.nextLine();
            for(Cliente c : listaClientes){
                if(c instanceof ClientePF){
                    ClientePF cliente_pf = (ClientePF) c;
                    if(cliente_pf.getCpf().equals(cpf_cliente)){
                        Random random = new Random();
                        int id_gerado = random.nextInt();

                        SeguroPF seguro_pf = new SeguroPF(null, cliente_pf, id_gerado, null, null, null);
                        seguro_pf.setSeguradora(this);
                        System.out.println("Entre com a data de inicio: ");
			            String data_inicio = read.nextLine();
			            Date data_inicioDate = new SimpleDateFormat("dd/MM/yyyy").parse(data_inicio);
                        seguro_pf.setDataInicio(data_inicioDate);
                        System.out.println("Entre com a data de fim: ");
			            String data_fim = read.nextLine();
			            Date data_fimDate = new SimpleDateFormat("dd/MM/yyyy").parse(data_fim);
                        seguro_pf.setDataInicio(data_fimDate);
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

                        Veiculo veiculo_seguroPF = new Veiculo(placa, marca, modelo, ano);
                        seguro_pf.setVeiculo(veiculo_seguroPF);

                        System.out.println("################");
                        System.out.println(seguro_pf.toString());
                        System.out.println("################");

                        return true;
                        
                    }
                }
            }
        }
        else{
            System.out.println("Entre com o cnpj do cliente: ");
            String cnpj_cliente = read.nextLine();
            for(Cliente c : listaClientes){
                if(c instanceof ClientePJ){
                    ClientePJ cliente_pj = (ClientePJ) c;
                    if(cliente_pj.getCnpj().equals(cnpj_cliente)){
                        Random random = new Random();
                        int id_gerado = random.nextInt();

                        SeguroPJ seguro_pj = new SeguroPJ(null, cliente_pj, id_gerado, null, null, null);
                        seguro_pj.setSeguradora(this);

                        System.out.println("Entre com a data de inicio: ");
			            String data_inicio = read.nextLine();
			            Date data_inicioDate = new SimpleDateFormat("dd/MM/yyyy").parse(data_inicio);
                        seguro_pj.setDataInicio(data_inicioDate);

                        System.out.println("Entre com a data de fim: ");
			            String data_fim = read.nextLine();
			            Date data_fimDate = new SimpleDateFormat("dd/MM/yyyy").parse(data_fim);
                        seguro_pj.setDataInicio(data_fimDate);

                        System.out.println("################");
                        System.out.println(seguro_pj.toString());
                        System.out.println("################");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected boolean cancelarSeguro(int seguro){
        for(Seguro s : listaSeguros){
            if(seguro==s.getId()){
                listaSeguros.remove(s);
                return true;
            }
        }
        return false;
    }

    protected boolean cadastrarCliente(Cliente cliente){
        // esse cliente ja foi cadastrado
        if(cliente instanceof ClientePF){
            ClientePF cliente_cadastar = (ClientePF) cliente;
            for(Cliente c : listaClientes){
                if(c instanceof ClientePF){
                    ClientePF clientePF = (ClientePF) c;
                    if(clientePF.getCpf().equals(cliente_cadastar.getCpf())){
                        return false;
                    }
                }
            }
            listaClientes.add(cliente);
            return true;
        }
        else{
            ClientePJ cliente_cadastar = (ClientePJ) cliente;
            for(Cliente c : listaClientes){
                if(c instanceof ClientePJ){
                    ClientePJ clientePJ = (ClientePJ) c;
                    if(clientePJ.getCnpj().equals(cliente_cadastar.getCnpj())){
                        return false;
                    }
                }
            }
            listaClientes.add(cliente);
            return true;
        }
    }

    protected boolean removerCliente(String cliente){
        if(listaClientes.isEmpty())
            return false;
        else{
            // pessoa juridica (cnpj)
            if(cliente.length()==18){
                for(Cliente c : listaClientes){
                    if((c instanceof ClientePJ)){
                        ClientePJ clientePJ = (ClientePJ) c;
                        if(clientePJ.getCnpj().equals(cliente)){
                            listaClientes.remove(c);
                            return true;
                        }
                    }
                }

            }
            // pessoa fisica (cpf)
            else{
                for(Cliente c : listaClientes){
                    if((c instanceof ClientePF)){
                        ClientePF clientePF = (ClientePF) c;
                        if(clientePF.getCpf().equals(cliente)){
                            listaClientes.remove(c);
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }


    public ArrayList<Seguro> getSegurosPorCliente(String cliente){
        ArrayList<Seguro> segurosCliente = new ArrayList<Seguro>();
        for(Seguro seguro : listaSeguros){
            if(seguro instanceof SeguroPF){
                SeguroPF s1 = (SeguroPF) seguro;
                if(s1.getCliente().getCpf().equals(cliente)){
                    segurosCliente.add(seguro);
                }
            }
            else if(seguro instanceof SeguroPJ){
                SeguroPJ s2 = (SeguroPJ) seguro;
                if(s2.getCliente().getCnpj().equals(cliente)){
                    segurosCliente.add(seguro);
                }
            }
            
        }
        return segurosCliente;
    }

    protected ArrayList<Sinistro> getSinistrosPorCliente(String cliente){
        ArrayList<Sinistro> sinistrosCliente = new ArrayList<Sinistro>();

        for(Seguro seguro : listaSeguros){
            if(seguro instanceof SeguroPF){
                SeguroPF s1 = (SeguroPF) seguro;
                if(s1.getCliente().getCpf().equals(cliente)){
                    sinistrosCliente.addAll(seguro.getListaSinistros());
                }
            }
            else if(seguro instanceof SeguroPJ){
                SeguroPJ s2 = (SeguroPJ) seguro;
                if(s2.getCliente().getCnpj().equals(cliente)){
                    sinistrosCliente.addAll(seguro.getListaSinistros());
                }
            }
        }

        return sinistrosCliente;
    }

    // calcula o balanco de seguros de todos os clientes da seguradora
    protected double calcularReceita(){
        double total = 0.0;
        for(Cliente c : listaClientes){
            if(c instanceof ClientePF){
                ClientePF cliente_pf = (ClientePF) c;
                ArrayList<Seguro> listaClienteSeguros = getSegurosPorCliente(cliente_pf.getCpf());
                for(Seguro s : listaClienteSeguros){
                    total += s.getValorMensal();
                }
            }
            else{
                ClientePJ cliente_pj = (ClientePJ) c;
                ArrayList<Seguro> listaClienteSeguros = getSegurosPorCliente(cliente_pj.getCnpj());
                for(Seguro s : listaClienteSeguros){
                    total += s.getValorMensal();
                }
            }
            
        }
        return total;
    }

    // imprimir as informacoes da seguradora
    @Override
    public String toString(){

        String str = "Nome da seguradora: " + nome + "\n"
        + "Telefone da seguradora: " + telefone + "\n"
        + "Email da seguradora: " + email + "\n"
        + "Endereco da seguradora: " + endereco + "\n"
        + "CNPJ da seguradora: " + cnpj + "\n";

        return str;
    }
    
}
