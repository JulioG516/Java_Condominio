import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List moradores = new ArrayList<>();

        int choice;

        String url = "jdbc:sqlite:C:\\Users\\Julio\\Documents\\IntelliJ\\Condominio\\DB\\CondominioJava.db";

        try (Connection con = DriverManager.getConnection(url)){
            System.out.println("Voce deseja adicionar ou visualizar cadastros ? Digite 1 para adicionar / 2 Para Visualizar Pessoa especifica / 3 Para visualizar todos ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    adicionarPessoa(scanner, con);
                    break;
                 case 2:
                     buscarResultado(scanner, con);
                     break;
                case 3:
                    List retorno = retornarTodos(con);
                    int tamanho = retorno.size();
                    for (int i = 0; i < tamanho; i++){
                        System.out.print("Número " +  (i + 1) + ": ");
                        System.out.print(retorno.get(i));
                        System.out.println();
                    }
                    System.out.println("Deseja remover alguém ? S/N");
                    String resposta = scanner.nextLine();
                    if(resposta.toUpperCase().equals("S")){
                        System.out.println("Quem voce deseja remover ? digite o ID desejado "); // Tirar um se for tirar do Array ou deixar normal se for do banco de dados
                        String id = scanner.nextLine();
                        String query = "DELETE FROM MORADORES WHERE ID = ?";
                        PreparedStatement ps = con.prepareStatement(query);
                        ps.setString(1, id);
                        ps.execute();
                    }
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

        public static void adicionarPessoa(Scanner scanner, Connection con)  {

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

        public static List retornarTodos(Connection con) throws  SQLException{
            String query = "SELECT * FROM MORADORES";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet retorno = ps.executeQuery();

            List moradoresRetorno = new ArrayList<>();


            String[] moradoresArray = new String[10];

            while (retorno.next()){
                moradoresArray[0] = retorno.getString(1);
                moradoresArray[1] = retorno.getString(2);
                moradoresArray[2] = retorno.getString(3);
                moradoresArray[3] = retorno.getString(4);
                moradoresArray[4] = retorno.getString(5);
                moradoresArray[5] = retorno.getString(6);
                moradoresArray[6] = retorno.getString(7);
                moradoresArray[7] = retorno.getString(8);
                moradoresArray[8] = retorno.getString(9);
                moradoresArray[9] = retorno.getString(10);

                moradoresRetorno.add(Arrays.toString(moradoresArray));

            }
            return moradoresRetorno;
        }


}
