package fiap.tds.models;

public class Usuario {
    private int id;
    private String nome;
    private String sobrenome;
    private String cargo;
    private String emailCorporativo;
    private String telefoneCorporativo;
    private String empresa;
    private int numDeFuncionarios;
    private String pais;
    private String idioma;


    public Usuario() {
    }

    public Usuario(int id, String nome, String sobrenome, String cargo, String emailCorporativo, String telefoneCorporativo, String empresa, int numDeFuncionarios, String pais, String idioma) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cargo = cargo;
        this.emailCorporativo = emailCorporativo;
        this.telefoneCorporativo = telefoneCorporativo;
        this.empresa = empresa;
        this.numDeFuncionarios = numDeFuncionarios;
        this.pais = pais;
        this.idioma = idioma;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getCargo() {
        return cargo;
    }

    public String getEmailCorporativo() {
        return emailCorporativo;
    }

    public String getTelefoneCorporativo() {
        return telefoneCorporativo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public int getNumDeFuncionarios() {
        return numDeFuncionarios;
    }

    public String getPais() {
        return pais;
    }

    public String getIdioma() {
        return idioma;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", cargo='" + cargo + '\'' +
                ", emailCorporativo='" + emailCorporativo + '\'' +
                ", telefoneCorporativo='" + telefoneCorporativo + '\'' +
                ", empresa='" + empresa + '\'' +
                ", numDeFuncionarios=" + numDeFuncionarios +
                ", pais='" + pais + '\'' +
                ", idioma='" + idioma + '\'' +
                '}';
    }
}
