import java.io.*;

public class Vegenere{
  String text, key, result;

  String getResult(){
    return this.result;
  }

  public Vegenere(String text, String key){
    this.text = text.toLowerCase();
    this.key = key;
  }

  public void encrypt(){
    int klength = this.key.length();
    int tlength = this.text.length();
    char temp;
    String result = "";

    for(int i = 0; i < tlength; i++){
      temp = this.text.charAt(i);
      if(Character.isWhitespace(temp)){
        result += " ";
        continue;
      }
      result += String.valueOf((char)((((int) temp - 97) + ((int) this.key.charAt(i % klength) - 97)) % 26 + 97));
    }

    this.result = result;
  }

  public void decrypt(){
    int klength = this.key.length();
    int tlength = this.text.length();
    int ntemp;
    char temp;
    String result = "";

    for(int i = 0; i < tlength; i++){
      temp = this.text.charAt(i);
      if(Character.isWhitespace(temp)){
        result += " ";
        continue;
      }
      ntemp = (((int) temp - 97) - ((int) this.key.charAt(i % klength) - 97));
      if(ntemp < 0){
        ntemp += 25;
        ntemp = ntemp % 26;
      }

      result += String.valueOf((char)(ntemp % 26));
    }

    this.result = result;
  }

  public static void main(String args[]) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String key, text;
    int choice;

    System.out.println("Enter a Key");
    key = br.readLine();
    System.out.println("Enter the text");
    text = br.readLine();

    Vegenere obj = new Vegenere(text, key);
    System.out.println("Select an Option:\n1.Encrypt\n2.Decrypt\n");
    choice = Integer.parseInt(br.readLine());

    switch(choice){
      case 1: {
        System.out.println("Encrypting...");
        obj.encrypt();
        System.out.println("Encrypted Text: " + obj.getResult());
        break;
      }
      case 2:{
        System.out.println("Decrypting...");
        obj.decrypt();
        System.out.println("Decrypting Text: " + obj.getResult());
        break;
      }
    }
  }
}
