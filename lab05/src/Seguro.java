import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class Seguro {
    private final int id;
    private Date dataInicio;
    private Date dataFim;
    private Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Condutor> listaCondutores;
    private int valorMensal;

    public Seguro(int id, Date dataInicio, Date dataFim, Seguradora seguradora){
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.listaCondutores = new ArrayList<Condutor>();
        this.listaSinistros = new ArrayList<Sinistro>();
    }

    public int getId() {
        return id;
    }
    public Date getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    public Date getDataFim() {
        return dataFim;
    }
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    public Seguradora getSeguradora() {
        return seguradora;
    }
    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }
    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }
    public ArrayList<Condutor> getListaCondutores() {
        return listaCondutores;
    }
    public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
        this.listaCondutores = listaCondutores;
    }
    public int getValorMensal() {
        return valorMensal;
    }
    public void setValorMensal(int valorMensal) {
        this.valorMensal = valorMensal;
    }

    protected void listarCondutores(){
        for(Condutor c : listaCondutores){
            System.out.println("################");
            System.out.println(c.toString());
            System.out.println("################");
        }
    }

    protected void listarSinistros(){
        for(Sinistro s : listaSinistros){
            System.out.println("################");
            System.out.println(s.toString());
            System.out.println("################");
        }
    }

    protected boolean desautorizarCondutor(String condutor){
        for(Condutor c : listaCondutores){
            if(condutor.equals(c.getCpf())){
                listaCondutores.remove(c);
                return true;
            }
        }
        return false;
    }


    protected boolean autorizarCondutor(Condutor condutor){
        for(Condutor c : listaCondutores){
            if(condutor.getCpf().equals(c.getCpf())){
                return false;
            }
        }
        listaCondutores.add(condutor);
        return true;
    }

    protected double calcularValor(){
        return 0;
    }

    protected boolean gerarSinistro(Seguradora seguradora, Seguro seguro) throws ParseException{
        Sinistro sinistro = new Sinistro(id, null, null, seguradora, null, null, seguro);
        for(Sinistro s : listaSinistros){
            if(s.getId()==sinistro.getId()){
                return false;
            }
        }
        listaSinistros.add(sinistro);
        return true;
    }

    // imprimir as informacoes do seguro
    @Override
    public String toString(){

        Date date1 = dataInicio;
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
		String strDate1 = formatter1.format(date1);

        Date date2 = dataFim;
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
		String strDate2 = formatter2.format(date2);


        String str = "Id do seguro: " + id + "\n"
        + "Data de inicio: " + strDate1 + "\n"
        + "Data final: " + strDate2 + "\n"
        + "Seguradora do seguro: " + seguradora.toString() + "\n"
        + "Valor mensal do seguro: " + valorMensal + "\n";

        return str;
    }
}
