import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String sql = "SELECT * FROM MORADORES";
        List moradores = new ArrayList<>();

        Morador m1 =  new Morador("José", "555-555-999", 12, "02639594494", "11962746533", "112211121", "Corsa", "UNP-3212", "Esposa");


        String insert = "INSERT INTO `MORADORES` VALUES(?, ?, ?, ? , ? , ? , ? , ?, ?, ?)";
        String url = "jdbc:sqlite:C:\\Users\\Julio\\Documents\\IntelliJ\\Condominio\\DB\\CondominioJava.db";
        try (Connection con = DriverManager.getConnection(url)){
            System.out.println("Deu certo, conectou !");
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setString(2, m1.getName());
            ps.setString(3, m1.getRG());
            ps.setInt(4, m1.getAP());
            ps.setString(5, m1.getCPF());
            ps.setString(6, m1.getTelefone());
            ps.setString(7, m1.getCelular());
            ps.setString(8, m1.getVeiculo());
            ps.setString(9, m1.getVeiculoPlaca());
            ps.setString(10, m1.getConjuge());
            ps.execute();

            // Fechar conexão
            ps.close();
            con.close();

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Não Deu");
        }





    }
}