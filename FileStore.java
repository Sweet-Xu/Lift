import java.io.*;
import  java.util.ArrayList;

public class FileStore {
        public FileStore(){}

        public  void FileInput(ArrayList<Customer> customer) {
                try  {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Custmoer.txt"));
                        for (Customer c : customer){
                                writer.write(c.getID() + " \t");
                                writer.write(c.getComingtime() + " \t");
                                writer.write(c.getAge() + " \t");
                                writer.write(c.getSourcefloor() + " \t");
                                writer.write(c.getDestinationfloor() + " \t");
                        }
                        writer.flush();
                        writer.close();
                }catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

        }

        public  void FileOutput(ArrayList<Customer> customer) {
                try  {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Custmoer.txt"));
                        for (Customer c : customer){
                                writer.write("\n\n");
                                writer.write(c.getID() + " \t");
                                writer.write(c.getComingtime() + " \t");
                                writer.write(c.getAge() + " \t");
                                writer.write(c.getSourcefloor() + " \t");
                                writer.write(c.getDestinationfloor() + " \t");
                                writer.write(c.getEndtime() + " \t");
                        }
                        writer.flush();
                        writer.close();
                }catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

        }
}

