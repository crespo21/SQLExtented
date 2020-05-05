import java.sql.*;

public class UmuziJDBC {
    private static  PreparedStatement preparedStatement;
    public static void dataBaseConnection() throws  Exception{
        //get a Connection to a database
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/umuzi",
                "user", "pass");
        //create a statement
        Statement statement = connection.createStatement();

        //Execute sql Query
        ResultSet result =  statement.executeQuery("select  * from  Customers");
        while (result.next()) {
            System.out.println(
                    result.getString("customerid") + "\t"
                            + result.getString("firstname") + "\t"
                            + result.getString("lastname") + "\t"
                            + result.getString("gender") + "\t"
                            + result.getString("address") + "\t"
                            + result.getString("phone") + "\t"
                            + result.getString("email") + "\t"
                            + result.getString("city") + "\t"
                            + result.getString("country")
            );
        }
        // execute query
        System.out.println(" ");
        result=statement.executeQuery("select firstname , lastname from Customers;");
        while (result.next()){
            System.out.println(result.getString("firstname") + " " +
                    result.getString("lastname"));
        }

        System.out.println(" ");
        // execute query
        result=statement.executeQuery("select firstname , lastname from Customers where customerid =1");
        while (result.next()){
            System.out.println(result.getString("firstname") + " " +
                    result.getString("lastname"));
        }

        System.out.println(" ");
        // create the  firstQuery preparedStatement
        String firstQuery="update  Customers\n" +  "set FirstName=?, LastName=?\n" +
                "where CustomerID=?";
        preparedStatement = connection.prepareStatement(firstQuery);
        preparedStatement.setString(1,"Lerato");
        preparedStatement.setString(2,"Mabitso");
        preparedStatement.setInt(3,1);

        // execute the preparedStatement
        preparedStatement.executeUpdate();

       // create the  secondQuery preparedStatement
        String secondQuery = "delete from  customers where customerid = ?";
        preparedStatement = connection.prepareStatement(secondQuery);
        preparedStatement.setInt(1, 2);
        preparedStatement.execute();

        // create the  thirdQuery preparedStatement
        String thirdQuery = "select count(distinct  status) from  orders";
        result = statement.executeQuery(thirdQuery);
        result.next();
        int queryCounter = result.getInt(1);
        System.out.println("Count: " + queryCounter);

        // create the  fourthQuery preparedStatement
        String fourthQuery = "select  max  (amount) from  payments";
        result = statement.executeQuery(fourthQuery);
        result.next();
        double maxAmount = result.getDouble(1);
        System.out.println("max amount: " + maxAmount);

        connection.close();

    }

    public static void main(String[] args) throws Exception {
        dataBaseConnection();
    }
}

