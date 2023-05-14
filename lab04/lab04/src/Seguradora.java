import java.util.ArrayList;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList <Cliente> listaClientes;
    private ArrayList <Sinistro> listaSinistros;


    public Seguradora(String nome, String telefone, String email, String endereco){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;

        this.listaClientes = new ArrayList<Cliente>();
        this.listaSinistros = new ArrayList<Sinistro>();
    }

    // getters e setters

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEndereco(){
        return endereco;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
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

    protected boolean cadastrarVeiculo(String placa, String modelo, String marca, int ano, String cliente){
        Veiculo v = new Veiculo(placa, marca, modelo, ano);
        // pessoa juridica (cnpj)
        if(cliente.length()==18){
            for(Cliente c : listaClientes){
                if((c instanceof ClientePJ)){
                    ClientePJ clientePJ = (ClientePJ) c;
                    if(clientePJ.getCnpj().equals(cliente)){
                        clientePJ.getListaVeiculos().add(v);
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
                        clientePF.getListaVeiculos().add(v);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected Cliente buscarCliente(String cliente){
        // cliente eh pessoa juridica (cnpj)
        if(cliente.length()==18){
            for(Cliente c : listaClientes){
                if((c instanceof ClientePJ)){
                    ClientePJ clientePJ = (ClientePJ) c;
                    if(clientePJ.getCnpj().equals(cliente)){
                        return clientePJ;
                    }
                }
            }
        }

        // cliente eh pessoa fisica (cpf)
        else{
            for(Cliente c : listaClientes){
                if((c instanceof ClientePF)){
                    ClientePF clientePF = (ClientePF) c;
                    if(clientePF.getCpf().equals(cliente)){
                        return clientePF;
                    }
                }
            }
        }
        return null;
    }

    protected void transferenciaVeiculos(Cliente c1, Cliente c2){

        for(Veiculo v : c1.getListaVeiculos()){
            c2.getListaVeiculos().add(v);
        }

        c1.getListaVeiculos().clear();

        // atualizando o valor do seguro de cada cliente 
        c1.setValorSeguro(c1.calculaScore());

        c2.setValorSeguro(c2.calculaScore());

        
    }

    protected boolean removerVeiculo(String placa){
        for(Cliente c : listaClientes){
            for(Veiculo v : c.getListaVeiculos()){
                if(v.getPlaca().equals(placa)){
                    c.getListaVeiculos().remove(v);
                    return true;
                }
            }
        }
        return false;
    }

    // lista todos os veiculos cadastrados naquela seguradora
    protected void listarVeiculos(){
        // se a lista esta vazia, nao temos o que imprimir
        if(listaClientes.isEmpty())
            return;
        for(Cliente c : listaClientes){
            for(Veiculo v : c.getListaVeiculos()){
                System.out.println("################");
                System.out.println(v.toString());
                System.out.println("################");
            }
        }
    }

    protected boolean gerarSinistro(Sinistro sinistro, String cliente_sinistro, String veiculo_sinistro){
        // pessoa juridica (cnpj)
        if(cliente_sinistro.length()==18){
            for(Cliente c : listaClientes){
                if((c instanceof ClientePJ)){
                    ClientePJ clientePJ = (ClientePJ) c;
                    if(clientePJ.getCnpj().equals(cliente_sinistro)){
                        for(Veiculo v : clientePJ.getListaVeiculos()){
                            if(v.getPlaca().equals(veiculo_sinistro)){
                                sinistro.setCliente(clientePJ);
                                sinistro.setVeiculo(v);
                                listaSinistros.add(sinistro);
                                System.out.println("################");
                                System.out.println(sinistro.toString());
                                System.out.println("################");
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
                    if(clientePF.getCpf().equals(cliente_sinistro)){
                        for(Veiculo v : clientePF.getListaVeiculos()){
                            if(v.getPlaca().equals(veiculo_sinistro)){
                                sinistro.setCliente(clientePF);
                                sinistro.setVeiculo(v);
                                listaSinistros.add(sinistro);
                                System.out.println("################");
                                System.out.println(sinistro.toString());
                                System.out.println("################");
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    protected boolean visualizarSinistro(String cliente){
        // se a lista esta vazia, nao temos o que imprimir
        if(listaSinistros.isEmpty())
            return false;

        // pessoa juridica (cnpj)
        if(cliente.length()==18){
            for(Sinistro s : listaSinistros){
                if((s.getCliente() instanceof ClientePJ)){
                    ClientePJ clientePJ = (ClientePJ) s.getCliente();
                    if(clientePJ.getCnpj().equals(cliente)){
                        System.out.println("################");
                        System.out.println(s.toString());
                        System.out.println("################");
                        return true;
                    }
                }
            }

        }
        // pessoa fisica (cpf)
        else{
            for(Sinistro s : listaSinistros){
                if((s.getCliente() instanceof ClientePF)){
                    ClientePF clientePF = (ClientePF) s.getCliente();
                    if(clientePF.getCpf().equals(cliente)){
                        System.out.println("################");
                        System.out.println(s.toString());
                        System.out.println("################");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected void listarSinistros(){
        // se a lista esta vazia, nao temos o que imprimir
        if(listaClientes.isEmpty())
            return;
        for(Sinistro s : listaSinistros){
            System.out.println("################");
            System.out.println(s.toString());
            System.out.println("################");
        }
    }

    protected boolean removerSinistro(int id){
        for(Sinistro s : listaSinistros){
            if(id==s.getId()){
                listaSinistros.remove(s);
                return true;
            }
        }
        return false;
    }

    // atualiza o atributo valorSeguro de cada cliente da seguradora
    protected void calcularPrecoSeguroCliente(){
        for(Cliente c : listaClientes){
            if(c instanceof ClientePF){
                ClientePF clientePF = (ClientePF) c;
                int temp1 = 0;
                for(Sinistro s : listaSinistros){
                    if(s.getCliente() instanceof ClientePF){
                        ClientePF clientePF2 = (ClientePF) s.getCliente();
                        if(clientePF.getCpf().equals(clientePF2.getCpf())){
                            temp1++;
                        }
                    }
                }
                c.setValorSeguro(c.calculaScore() * (1 + temp1));
            }
            else{
                ClientePJ clientePJ = (ClientePJ) c;
                int temp2 = 0;
                for(Sinistro s : listaSinistros){
                    if(s.getCliente() instanceof ClientePJ){
                        ClientePJ clientePJ2 = (ClientePJ) s.getCliente();
                        if(clientePJ.getCnpj().equals(clientePJ2.getCnpj())){
                            temp2++;
                        }
                    }
                }
                c.setValorSeguro(c.calculaScore() * (1 + temp2));
            }
        }
    }

    // calcula o balanco de seguros de todos os clientes da seguradora
    protected double calcularReceita(){
        double total = 0.0;
        for(Cliente c : listaClientes){
            total += c.valorSeguro;
        }
        return total;
    }

    // imprimir as informacoes da seguradora
    @Override
    public String toString(){

        String str = "Nome da seguradora: " + nome + "\n"
        + "Telefone da seguradora: " + telefone + "\n"
        + "Email da seguradora: " + email + "\n"
        + "Endereco da seguradora: " + endereco + "\n";

        return str;
    }

    

}