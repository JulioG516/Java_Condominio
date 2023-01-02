public class Morador {
    private String CPF;
    private String telefone;
    private String celular;
    private String veiculo;
    private String veiculoPlaca;
    private String conjuge;
    public String name;
    public String rg;
    public int ap;

    public Morador(String name, String rg, int ap, String cpf, String telefone, String celular, String veiculo, String veiculoPlaca, String conjuge) {
        this.name = name;
        this.rg = rg;
        this.ap = ap;
        this.CPF = cpf;
        this.telefone = telefone;
        this.celular = celular;
        this.veiculo = veiculo;
        this.veiculoPlaca = veiculoPlaca;
        this.conjuge = conjuge;

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRG() {
        return this.rg;
    }

    public void setRG(String rg) {
        this.rg = rg;
    }

    public int getAP() {
        return this.ap;
    }
    public void setAP(int ap) {
        this.ap = ap;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getVeiculoPlaca() {
        return veiculoPlaca;
    }

    public void setVeiculoPlaca(String veiculoPlaca) {
        this.veiculoPlaca = veiculoPlaca;
    }

    public String getConjuge() {
        return conjuge;
    }

    public void setConjuge(String conjuge) {
        this.conjuge = conjuge;
    }
}
