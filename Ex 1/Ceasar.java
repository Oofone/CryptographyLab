import java.io.*;

public class Ceasar{
  String text, result;
  int key;

  public Ceasar(int key, String text){
    this.key = key % 26;
    this.text = text.toLowerCase();

    this.result = "";
  }

  public void encrypt(){
    System.out.println("Encrypting...");
    char current;
    for(int i = 0; i < this.text.length(); i++){
      current = this.text.charAt(i);

      if (!Character.isLetter(current)){
        if(Character.isWhitespace(current))
          this.result += " ";
        else
          this.result += current;
        continue;
      }
      else{
        this.result += String.valueOf((char)(97 + (((int)current - 97 + this.key) % 26)));
      }
    }
    this.result = this.result.toUpperCase();
  }

  public void decrypt(){
    System.out.println("Decrypting...");
    char current;
    int val;
    for(int i = 0; i < this.text.length(); i++){
      current = this.text.charAt(i);

      if (!Character.isLetter(current)){
        if(Character.isWhitespace(current))
          this.result += " ";
        else
          this.result += current;
        continue;
      }
      else{
        val = Math.abs(Math.floorMod((int)current - 97 - this.key, 26));
        this.result += String.valueOf((char)(97 + val));
      }
    }
    this.result = this.result.toUpperCase();
  }

  public void cryptAnalysis(){
    System.out.println("Performing Cryptanalysis...");
  }

  public String getResult(){
    return this.result;
  }

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Ceasar handler;
		int key, choice = 0;
    String message;

		if (args.length == 1){
			key = Integer.parseInt(args[0]);
			System.out.println("Enter a message to be encrypted or decrypted:");
	    message = br.readLine();

			handler = new Ceasar(key, message);
		}
		else if (args.length == 2){
			handler = new Ceasar(Integer.parseInt(args[0]), args[1]); // 0 -> Key | 1 -> Message
		}
		else if (args.length == 3){
			handler = new Ceasar(Integer.parseInt(args[0]), args[1]); // 0 -> Key | 1 -> Message
			choice = Integer.parseInt(args[2]); // 3 -> (1 Encrypt | 2 Decrypt)
		}
		else{
			System.out.println("Enter a key-number:");
			key = Integer.parseInt(br.readLine());
      System.out.println("Enter a message to be encrypted or decrypted:");
			message = br.readLine();

			handler = new Ceasar(key, message);
		}

		if(choice == 0){
			System.out.println("Select an option: \n1. Encrypt Text \n2. Decrypt Text");
			choice = Integer.parseInt(br.readLine());
		}

		if (choice == 1)
			handler.encrypt();
		else if (choice == 2)
			handler.decrypt();
		else
			System.out.println("Fatal Error - Unknown Command");

		System.out.println("Result: " + handler.getResult());
	}
}
