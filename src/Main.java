import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List moradores = new ArrayList<>();

        int choice;

        String url = "jdbc:sqlite:C:\\Users\\Julio\\Documents\\IntelliJ\\Condominio\\DB\\CondominioJava.db";

        try (Connection con = DriverManager.getConnection(url)){
            System.out.println("Voce deseja adicionar ou visualizar cadastros ? ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    adicionarPessoa(scanner, con);
                    break;
                 case 2:
                     buscarResultado(scanner, con);
                     break;
                }
              //  con.close();
            }
            catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Não Deu");
            }

        }

        public static void buscarResultado(Scanner scanner, Connection con) throws SQLException {
           String query = "SELECT * FROM MORADORES WHERE NAME = ?";

           System.out.println("Quem voce deseja buscar ? ");
           String busca = scanner.nextLine();

           PreparedStatement stmt = con.prepareStatement(query);
           stmt.setString(1, busca);
           ResultSet retorno =  stmt.executeQuery();

           while (retorno.next()) {
               for (int i = 1; i <= 10; i++){
                   String x = retorno.getString(i);
                   System.out.println(x);
               }
           }

        }

        public static void adicionarPessoa(Scanner scanner, Connection con)   {

            String insert = "INSERT INTO `MORADORES` VALUES(?, ?, ?, ? , ? , ? , ? , ?, ?, ?)";


            System.out.println("Insira o name: ");
            String nome = scanner.nextLine();

            System.out.println("Insira o RG: ");
            String RG = scanner.nextLine();

            System.out.println("Insira o AP");
            int apartamento = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Insira o CPF");
            String cpf = scanner.nextLine();
            System.out.println("Insira o telefone");
            String telefone = scanner.nextLine();
            System.out.println("Insira o celular");
            String celular = scanner.nextLine();
            System.out.println("Insira o veiculo");
            String veiculo = scanner.nextLine();
            System.out.println("Insira a placa do veiculo");
            String veiculoplaca = scanner.nextLine();
            System.out.println("Insira o conjuge");
            String conjuge = scanner.nextLine();
            Morador m1 =  new Morador(nome, RG, apartamento, cpf, telefone, celular, veiculo, veiculoplaca, conjuge);
            try {
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
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }


}
