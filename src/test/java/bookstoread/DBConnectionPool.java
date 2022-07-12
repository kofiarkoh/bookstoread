package bookstoread;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

abstract class DBConnectionPool {

   @BeforeAll
   static void connectDbPool(){
    System.out.println("connecting to DB");
   }

   @AfterAll
  static void disconnectFromDB(){
    System.out.println("disconnecting from DB");
   }

}
