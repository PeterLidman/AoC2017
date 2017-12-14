package advent14;

public class advent14 {
	//static String defrag = "flqrgnkx";
	static String defrag="amgozmfv";
	static String puzzle = "106,118,236,1,130,0,235,254,59,205,2,87,129,25,255,118";
	static int puzlen = 0;
	static int[] suffix = { 17, 31, 73, 47, 23 };
	static int[] intpuz = new int[59];
	static int data[] = new int[256];
	static int tin[] = { 0, 1, 2, 3, 4 };
	static int cp = 0;
	static int skipsize = 0;
	static int regions = 0;
	static int usedSquares = 0;
	static int[] dense = new int[16];
	static String denseStr = "";
	static String hexStr = "";
	static String disk[] = new String[128];

	public static void main(String[] args) {	
		for (int k = 0; k < 128; k++) {
			puzzle = defrag + "-" + k;
			//System.out.println("beräknar för: " + puzzle);

			puzlen=0;
			denseStr="";
			for (int i = 0; i < puzzle.length(); i++) {
				puzlen++;
				intpuz[i] = puzzle.charAt(i);
			}
			intpuz[puzlen++] = suffix[0];
			intpuz[puzlen++] = suffix[1];
			intpuz[puzlen++] = suffix[2];
			intpuz[puzlen++] = suffix[3];
			intpuz[puzlen++] = suffix[4];

			for (int i = 0; i < 256; i++) {
				data[i] = i;
			}
			cp = 0;
			skipsize = 0;
			for (int i = 0; i < 16; i++) {
				dense[i]=0;
			}
			
			for (int j = 0; j < 64; j++) {
				for (int i = 0; i < puzlen; i++) {
					reverse(cp, intpuz[i]);
					cp = (cp + intpuz[i] + skipsize++) % 256;
				}
			}
			for (int i = 0; i < 16; i++) {
				dense[i] = data[i * 16];
				for (int j = 1; j < 16; j++) {
					dense[i] = dense[i] ^ data[i * 16 + j];
				}
//				System.out.println(" i=" + i + " dense=" + dense[i]);
				denseStr += (char) dense[i];
			}
			//System.out.println("densstring:" + denseStr);
			hexStr=toHex(denseStr);
			//System.out.println("hexdensstring:" + hexStr);
			usedSquares += countBitsFromHexString(hexStr);
			disk[k]=hexStringToBinString(hexStr);
			//System.out.println(disk[k]);
		}
		System.out.println("used squares= " + usedSquares);
		for(int i=0; i<128;i++) {
			for(int j=0; j<128;j++) {
				regions += wipeOut(i, j);	
			}			
		}
		System.out.println("regions= " + regions);
	}
	
	private static int wipeOut(int row, int place) {
		if (row < 0 || row > 127) {
			return 0;
		}
		if (place <0 || place > 127) {
			return 0;
		}
		if(disk[row].substring(place, place+1).equals("1")) {
			disk[row] = disk[row].substring(0, place) + "0" + disk[row].substring(place+1);
			// släck rekursivt upp ner framför och bakom
			wipeOut(row+1, place);
			wipeOut(row-1, place);
			wipeOut(row, place+1);
			wipeOut(row, place-1);
			return 1;
		} 
		return 0;
	}
	
	private static String hexStringToBinString(String in) {
		String ret="";
		for (int i=0;i<in.length();i++) {
			switch(in.charAt(i)) {
			case '0':
				ret+="0000";
			break;
			case '1':
				ret+="0001";
				break;
			case '2':
				ret+="0010";
				break;
			case '3':
				ret+="0011";
				break;
			case '4':
				ret+="0100";
				break;
			case '5':
				ret+="0101";
				break;
			case '6':
				ret+="0110";
				break;
			case '7':
				ret+="0111";
				break;
			case '8':
				ret+="1000";
				break;
			case '9':
				ret+="1001";
				break;
			case 'a':
				ret+="1010";
				break;
			case 'b':
				ret+="1011";
				break;
			case 'c':
				ret+="1100";
				break;
			case 'd':
				ret+="1101";
				break;
			case 'e':
				ret+="1110";
				break;
			case 'f':
				ret+="1111";
				break;
			default:
				System.out.println("åh nej");
			}
		}
		return ret;
	}

	static int countBitsFromHexString(String in) {
		int sum=0;
		for (int i=0;i<in.length();i++) {
			switch(in.charAt(i)) {
			case '0':
				sum+=0;
			break;
			case '1':
			case '2':
			case '4':
			case '8':
				sum+=1;				
				break;
			case '3':
			case '5':
			case '6':
			case '9':
			case 'a':
			case 'c':
				sum+=2;				
				break;
			case '7':
			case 'b':
			case 'd':
			case 'e':
				sum+=3;				
				break;
			case 'f':
				sum+=4;				
				break;
			default:
				System.out.println("åh nej");
			}
		}
		return sum;
	}

	public static String toHex(String arg) {
		String ut = "";
		for (int i = 0; i < arg.length(); i++) {
			ut += String.format("%02x", dense[i]);
		}
		return ut;
	}

	static void reverse(int start, int len) {
		int temp;
		if (len < 2) {
			return;
		}
		for (int i = 0; i < len / 2; i++) {
			// System.out.println("i" + i);
			temp = data[(i + start) % 256];
			data[(i + start) % 256] = data[(len - i + start - 1) % 256];
			data[(len - i + start - 1) % 256] = temp;
		}
	}

	static void print() {
		String a = "";
		for (int i = 0; i < 256; i++) {
			a += " " + i + ":" + data[i];
		}
		System.out.println("->" + a);
	}
}
