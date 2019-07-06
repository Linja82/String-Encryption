package encryptiondecryption2;

/**
 *
 * @author Alec Lin
 * @date June 12, 2019 1:24 A.M.
 */
import java.util.*;
public class EncryptionKey {
    int multiply;
    int add;
    int subtract;
    
    public EncryptionKey(){
        this.multiply = 1;
        this.add = 1;
        this.subtract = 1;
    }
    
    public EncryptionKey(int multiply, int add, int subtract){
        this.multiply = multiply;
        this.add = add;
        this.subtract = subtract;
    }
    
    public void Generate(){
        Random random = new Random();
        multiply = random.nextInt(25)+1;
        add = random.nextInt(25)+1;
        subtract = random.nextInt(25)+1;
    }
}