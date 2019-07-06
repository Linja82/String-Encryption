package encryptiondecryption2;

/**
 *
 * @author Alec Lin
 * @date June 12, 2019 1:24 A.M. 
 */
public class EncryptionDecryption2 {
public static void main(String[] args) {
        EncryptionDecryptionGUI a = new EncryptionDecryptionGUI();
        a.setVisible(true);
    }
    
    /** ENCRYPT
     * Encrypts the provided string and returns an encrypted 1D array
     * @param message String that is the message
     * @return 1D Array that includes the encrypted values 
     */
    public static int[] encrypt(String message){
        int inputLength = message.length(); //int that holds the length of the input message
        String [] messageCharacters = new String[inputLength];
        String messageShorten = message;
        for (int i = 0; i<inputLength;i++){
            messageCharacters[i] = messageShorten.substring(i, i+1); //Puts each individual character into 1 cell of the 1D string array
        }
        int[] encryptedMessage = new int[inputLength+3];
        for (int j = 0; j < inputLength; j++){
            encryptedMessage[j] = (int) messageCharacters[j].charAt(0); //Places the ASCII value of each character into its own cell in a 1D int array
        }
        EncryptionKey key = new EncryptionKey(); //Creates a new encryption key
        key.Generate(); //Generates random values for the encryption key
        for (int k = 0;k < inputLength; k++){ //Encrypts every ASCII value
            encryptedMessage[k] = encryptedMessage[k] * key.multiply; //Multiplies the ASCII value by the randomly generated multiply key
            encryptedMessage[k] = encryptedMessage[k] + key.add; //Adds the randomly generated addition key to the ASCII value
            encryptedMessage[k] = encryptedMessage[k] - key.subtract; //Subtracts the randomly generated subtract key from the ASCII value
        }
        //Writes encryption values into the encrypted message array
        encryptedMessage[inputLength] = key.multiply;
        encryptedMessage[inputLength + 1] = key.add;
        encryptedMessage[inputLength + 2] = key.subtract;
        
        return encryptedMessage;
    }
    
    /** PRINT ENCRYPT
     * Prints out the 1D array that contains the message in the format: [##][###][##][##][#][##][##][###]...
     * @param encryptedMessage a 1D array that contains the encrypted message
     */
    public static void printEncryptedMessage(int[] encryptedMessage){
        for (int i = 0; i< encryptedMessage.length; i++){
            System.out.print("[" + encryptedMessage[i] + "]" + " ");
        }
        System.out.println();
    }
    
    /** ENCRYPT TO STRING
     * Returns the encrypted message as a String
     * @param encryptedMessage 1D double array containing the encrypted characters
     * @return String of the encrypted characters
     */
    public static String encryptedMessageToString(int[] encryptedMessage){
        String encryptedMessageToString = "";
        for (int i = 0; i< encryptedMessage.length; i++){
            encryptedMessageToString += ("[" + encryptedMessage[i] + "]" + " ");
        }
        return encryptedMessageToString;
    }
    
    /**ENCRYPTED STRING TO ARRAY
     * Converts the string of encrypted values into a 1D array
     * @param encryptedMessage String containing the encrypted values
     * @return 1D array containing encrypted values
     */
    public static int[] encryptedStringToArray(String encryptedMessage){
        String encryptedMessageShorten = encryptedMessage;
        int bracketLocation = encryptedMessageShorten.indexOf("[");
        int numOfCells = 0;
        while (bracketLocation != -1){
            bracketLocation = encryptedMessageShorten.indexOf("[");
            if (bracketLocation != -1){
                numOfCells ++;
                encryptedMessageShorten = encryptedMessageShorten.substring(encryptedMessageShorten.indexOf("]")+1, encryptedMessageShorten.length());
            }
            else
                break;
        }
        int[] encryptedMessageArray = new int[numOfCells];
        encryptedMessageShorten = encryptedMessage;
        for (int i = 0; i < numOfCells; i++){
            encryptedMessageArray[i] = Integer.parseInt(encryptedMessageShorten.substring(encryptedMessageShorten.indexOf("[")+1, encryptedMessageShorten.indexOf("]")));
                encryptedMessageShorten = encryptedMessageShorten.substring((encryptedMessageShorten.indexOf("]")+1), (encryptedMessageShorten.length()));
        }
        return encryptedMessageArray;
    }
    
    /** DECRYPT
     * Decrypts the given encrypted array and returns a 1D array of characters
     * @param encryptedMessage encrypted 1D array
     * @return 1D array of characters
     */
    public static String[] decrypt(int[] encryptedMessage){
        EncryptionKey decryptKey = new EncryptionKey();
        int inputLength = encryptedMessage.length-3;
        decryptKey.multiply = (int)encryptedMessage[inputLength];
        decryptKey.add = (int)encryptedMessage[inputLength + 1];
        decryptKey.subtract = (int)encryptedMessage[inputLength + 2];
        for (int i = 0; i < inputLength; i++){
            encryptedMessage[i] = encryptedMessage[i] + decryptKey.subtract;
            encryptedMessage[i] = encryptedMessage[i] - decryptKey.add;
            encryptedMessage[i] = encryptedMessage[i] / decryptKey.multiply;
        }
        String[] decryptedMessage = new String[inputLength];
        for (int j = 0; j < inputLength; j++){
            decryptedMessage[j] = Character.toString((char) encryptedMessage[j]);
        }
        return decryptedMessage;
    }
  
    /**PRINT DECRYPT
     * Prints out the decrypted message
     * @param decryptedMessage 1D character array that contains the decrypted message
     */
    public static void printDecryptedMessage(String[] decryptedMessage){
        for (int i = 0; i < decryptedMessage.length; i++){
         System.out.print(decryptedMessage[i]);   
        }
        System.out.println();
    }
    
    /**DECRYPT TO STRING
     * Returns the decrypted values as a string
     * @param decryptedMessage 1D string array of decrypted values
     * @return String containing the message
     */
    public static String decryptedMessageToString(String[] decryptedMessage){
        String decryptedMessageToString = "";
        for (int i = 0; i<decryptedMessage.length; i++){
            decryptedMessageToString += decryptedMessage[i];   
        }
        return decryptedMessageToString;
    }
    
}
