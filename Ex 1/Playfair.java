import java.io.*;
import java.util.*;

class Pair <T> {
	T x, y;
	boolean fresh;

	Pair(){
		this.fresh = true;
	}

	Pair(T x, T y){
		this.x = x;
		this.y = y;
	}

	T getX(){
		return this.x;
	}

	T getY(){
		return this.y;
	}

	boolean isFresh(){
		return this.fresh;
	}
}

public class Playfair{
	String text, result;
	String key;
	char skipKey = 'z'; //replace with X
	char replaceKey = 'x';

	char[][] block;
	HashMap < Character, Pair <Integer> > index;
	//Pair pair;

	HashMap <Character, Integer> getOccurenceMap(){
		HashMap <Character, Integer> hm = new HashMap <Character, Integer> ();
		for( char c = 'a'; (char)c != ('z' + 1); c++){
			if((char)c == skipKey)
				continue;
			hm.put((char)c, 0);
		}

		return hm;
	}
	
	String getResult(){
		return this.result;
	}

	Playfair(String text, String key){
		this.text = text.toLowerCase();
		this.result = "";
		this.key = key.toLowerCase();
		this.block = new char[5][5];
		this.index =  new HashMap < Character, Pair <Integer> >();

		HashMap <Character, Integer> occurenceMap = this.getOccurenceMap();
		int keyLength = this.key.length();
		int keyCount = 0, i, j;
		char current;
		i = j = keyCount;

		while(keyCount < keyLength){
			if(j != 0 && j % 5 == 0){
				j = 0;
				i += 1;
			}
			
			current = this.key.charAt(keyCount);
			if(current == this.skipKey){
				current = this.replaceKey;
			}
			else if(current == ' '){
				keyCount += 1;
				continue;
			}

			if( occurenceMap.get(current) == 0){
				occurenceMap.put(current, 1);
				this.block[i][j] = current;
				this.index.put(current, new Pair<Integer>(i, j));
				j++;
			}
			else{
				keyCount++;
				continue;
			}
		}

		Set <Map.Entry< Character,Integer>> remnants = occurenceMap.entrySet();
		for (Map.Entry< Character,Integer> remnant:remnants)
       	{
       		if(j != 0 && j % 5 == 0){
				j = 0;
				i += 1;
			}

          	if(remnant.getValue() == 0){
				current = remnant.getKey();
          		this.block[i][j] = current;
				this.index.put(current, new Pair<Integer>(i, j));
          		j += 1;
          	}
        }

        if( i == 4 && j == 5){
        	System.out.println("Square Key Successfully Generated:");
        }
        System.out.println("Provided Key: " + this.key);
        System.out.println("Skipped Letter: " + this.skipKey);
        System.out.println("Replace Letter: " + this.replaceKey + "\n");

        for(char[] row:this.block){
        	for(char letter:row){
        		System.out.print(letter + " ");
        	}
        	System.out.println("");
        }
		System.out.println("");
	}	
	
	public void encrypter(){
		System.out.println("Encrypting...\n");
		String result = "";
		char a, b;
		int nextx, nexty;
		
		//Padding replacement character
		this.text = this.text.toLowerCase();
		//if (this.text.length() % 2 == 1)
		//	this.text = this.text + Character.toString(this.replaceKey);
		
		for(int i = 0; i <= this.text.length() - 2; i+=2){
			a = this.text.charAt(i);
			if (i < this.text.length())
				b = this.text.charAt(i + 1);
			else	
				b = this.replaceKey;
						
			if (a == this.skipKey)
				a = this.replaceKey;
			if (b == this.skipKey)
				b = this.replaceKey;
			
			if (a == b){
				b = this.replaceKey;
				i -= 1;
			}
			
			Pair <Integer> posa = this.index.get(a);
			Pair <Integer> posb = this.index.get(b);
			
			if (posa.getX() == posb.getX()){
				nextx = posa.getX();
				nexty = posa.getY() + 1;
				
				if (nexty == 5)
					nexty = 0;	
					
				result += Character.toString(block[nextx][nexty]);
				
				nextx = posb.getX();
				nexty = posb.getY() + 1;
				
				if (nexty == 5)
					nexty = 0;	
					
				result += Character.toString(block[nextx][nexty]);
			}
			else if (posa.getY() == posb.getY()){
				nextx = posa.getX() + 1;
				nexty = posa.getY();
				
				if (nextx == 5)
					nextx = 0;	
					
				result += Character.toString(block[nextx][nexty]);
				
				nextx = posb.getX() + 1;
				nexty = posb.getY();
				
				if (nextx == 5)
					nextx = 0;	
					
				result += Character.toString(block[nextx][nexty]);
			}
			else{
				nextx = posa.getX();
				nexty = posb.getY();
				
				result += Character.toString(block[nextx][nexty]);
				
				nextx = posb.getX();
				nexty = posa.getY();
				
				result += Character.toString(block[nextx][nexty]);
			}
		
		this.result = result.toUpperCase();
		}
	}
	
	public void decrypter(){
		System.out.println("Decrypting...\n");
		String result = "";
		char a, b;
		int nextx, nexty;
		
		//Padding replacement character
		this.text = this.text.toLowerCase();
		if (this.text.length() % 2 == 1)
			this.text = this.text + Character.toString(this.replaceKey);
		
		for(int i = 0; i <= this.text.length() - 2; i+=2){
			a = this.text.charAt(i);
			b = this.text.charAt(i + 1);
						
			if (a == this.skipKey)
				a = this.replaceKey;
			if (b == this.skipKey)
				b = this.replaceKey;
			
			if (a == b){
				b = this.replaceKey;
			}
			
			Pair <Integer> posa = this.index.get(a);
			Pair <Integer> posb = this.index.get(b);
			
			if (posa.getX() == posb.getX()){
				nextx = posa.getX();
				nexty = posa.getY() - 1;
				
				if (nexty == -1)
					nexty = 4;	
					
				result += Character.toString(block[nextx][nexty]);
				
				nextx = posb.getX();
				nexty = posb.getY() - 1;
				
				if (nexty == -1)
					nexty = 4;	
					
				result += Character.toString(block[nextx][nexty]);
			}
			else if (posa.getY() == posb.getY()){
				nextx = posa.getX() - 1;
				nexty = posa.getY();
				
				if (nextx == -1)
					nextx = 4;	
					
				result += Character.toString(block[nextx][nexty]);
				
				nextx = posb.getX() - 1;
				nexty = posb.getY();
				
				if (nextx == -1)
					nextx = 4;	
					
				result += Character.toString(block[nextx][nexty]);
			}
			else{
				nextx = posa.getX();
				nexty = posb.getY();
				
				result += Character.toString(block[nextx][nexty]);
				
				nextx = posb.getX();
				nexty = posa.getY();
				
				result += Character.toString(block[nextx][nexty]);
			}
		
		this.result = result.toUpperCase();
		}
	}

	public static void main(String[] args) throws IOException {
 		//testing: //Playfair cipher = new Playfair("Hide the gold", "I am lord Voldemort");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Playfair handler;
		String key;
		int choice = 0;
		
		if (args.length == 1){
			key = args[0];
			System.out.println("Enter a message to be encrypted or decrypted:");
			String message = br.readLine();
			
			handler = new Playfair(message, key);
		}
		else if (args.length == 2){
			handler = new Playfair(args[1], args[0]); // 0 -> Key Phrase 1 -> Message
		}
		else if (args.length == 3){
			handler = new Playfair(args[1], args[0]);
			choice = Integer.parseInt(args[2]);
		}
		else{
			System.out.println("Enter a key-phrase to generate the square key:");
			key = br.readLine();
			System.out.println("Enter a message to be encrypted or decrypted:");
			String message = br.readLine();
			
			handler = new Playfair(message, key);
		}
		
		if(choice == 0){
			System.out.println("Select an option: \n1. Encrypt Text \n2. Decrypt Text");
			choice = Integer.parseInt(br.readLine());
		}
		
		if (choice == 1)
			handler.encrypter();
		else if (choice == 2)
			handler.decrypter();
		else 
			System.out.println("Fatal Error - Unknown Command");
		
		System.out.println("Result: " + handler.getResult());
	}
}