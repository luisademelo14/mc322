public enum CalcValor {
    VALOR_BASE (10.0),
    FATOR_MIN_30 (1.25),
    FATOR_30_60 (1.0),
    FATOR_60_MAX (1.5);

    private final double valor;

    CalcValor(double valor){
        this.valor = valor;
    }

    public double getValor() {
        return this.valor;
    }

}