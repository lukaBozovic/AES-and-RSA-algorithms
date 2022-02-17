import java.util.*;
public class Aes {
	public static String [][] key = null;
	public static String[][] subTableHex= {
			{"63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2b", "fe", "d7", "ab", "76"},
			{"ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0"},
			{"b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15"},
			{"04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75"},
			{"09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84"},
			{"53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf"},
			{"d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8"},
			{"51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2"},
			{"cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73"},
			{"60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db"},
			{"e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79"},
			{"e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08"},
			{"ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a"},
			{"70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e"},
			{"e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df"},
			{"8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"},
			
	};
	public static String[][] invSubTableHex= {
			{"52", "09", "6a", "d5", "30", "36", "a5", "38", "bf", "40", "a3", "9e", "81", "f3", "d7", "fb"},
			{"7c", "e3", "39", "82", "9b", "2f", "ff", "87", "34", "8e", "43", "44", "c4", "de", "e9", "cb"},
			{"54", "7b", "94", "32", "a6", "c2", "23", "3d", "ee", "4c", "95", "0b", "42", "fa", "c3", "4e"},
			{"08", "2e", "a1", "66", "28", "d9", "24", "b2", "76", "5b", "a2", "49", "6d", "8b", "d1", "25"},
			{"72", "f8", "f6", "64", "86", "68", "98", "16", "d4", "a4", "5c", "cc", "5d", "65", "b6", "92"},
			{"6c", "70", "48", "50", "fd", "ed", "b9", "da", "5e", "15", "46", "57", "a7", "8d", "9d", "84"},
			{"90", "d8", "ab", "00", "8c", "bc", "d3", "0a", "f7", "e4", "58", "05", "b8", "b3", "45", "06"},
			{"d0", "2c", "1e", "8f", "ca", "3f", "0f", "02", "c1", "af", "bd", "03", "01", "13", "8a", "6b"},
			{"3a", "91", "11", "41", "4f", "67", "dc", "ea", "97", "f2", "cf", "ce", "f0", "b4", "e6", "73"},
			{"96", "ac", "74", "22", "e7", "ad", "35", "85", "e2", "f9", "37", "e8", "1c", "75", "df", "6e"},
			{"47", "f1", "1a", "71", "1d", "29", "c5", "89", "6f", "b7", "62", "0e", "aa", "18", "be", "1b"},
			{"fc", "56", "3e", "4b", "c6", "d2", "79", "20", "9a", "db", "c0", "fe", "78", "cd", "5a", "f4"},
			{"1f", "dd", "a8", "33", "88", "07", "c7", "31", "b1", "12", "10", "59", "27", "80", "ec", "5f"},
			{"60", "51", "7f", "a9", "19", "b5", "4a", "0d", "2d", "e5", "7a", "9f", "93", "c9", "9c", "ef"},
			{"a0", "e0", "3b", "4d", "ae", "2a", "f5", "b0", "c8", "eb", "bb", "3c", "83", "53", "99", "61"},
			{"17", "2b", "04", "7e", "ba", "77", "d6", "26", "e1", "69", "14", "63", "55", "21", "0c", "7d"},
			
	};
	public static String[][] subTableBin = new String[16][16];
	public static String[][] invSubTableBin = new String[16][16];
	public static String[][] fixedTable = new String[4][4]; //matrica koja se koristi u mixColumns
	public static String[][][] subKeys = new String[11][4][4];
	public static String[][] rconTableHex = {
			{"01", "02", "04", "08", "10", "20", "40", "80", "1b", "36"},
			{"00", "00", "00", "00", "00", "00", "00", "00", "00", "00"},
			{"00", "00", "00", "00", "00", "00", "00", "00", "00", "00"},
			{"00", "00", "00", "00", "00", "00", "00", "00", "00", "00"},
			{"00", "00", "00", "00", "00", "00", "00", "00", "00", "00"},
			
	};
	public static String[][] rconTableBin = new String[4][10];
	
	public static String hexToBin(String hex) {
		    int i = Integer.parseInt(hex, 16);
		    String bin = Integer.toBinaryString(i);
		    return String.format("%8s", bin).replace(" ", "0");
		}
	
	public static String binToHex(String bin) {

		int decimal = Integer.parseInt(bin,2);
		String hex = Integer.toString(decimal,16);
		return String.format("%2s", hex).replace(" ", "0");
	}
	
	public static String[][] hexToBinMatrix(String[][] hex){
		String[][] pom = new String[hex.length][hex[0].length];
		for (int i = 0; i < hex.length; i++) {
			for (int j = 0; j < hex[0].length; j++)
				pom[i][j] = hexToBin(hex[i][j]);
		}
		return pom;
	}
	
	public static String[][] binToHexMatrix(String[][] bin){
		String[][] pom = new String[bin.length][bin[0].length];
		for (int i = 0; i < bin.length; i++) {
			for (int j = 0; j < bin[0].length; j++)
				pom[i][j] = binToHex(bin[i][j]);
		}
		return pom;
	}
	
	//U ovoj fji se vrsi ucitavanje kljuca, takodje, generisu se binarne sub inv sub tabele, kao i fixedTable 
	public static void loadData() { 
		
		String [][] pom = {
				{"54", "53", "50", "31"},
				{"45", "43", "09", "32"},
				{"41", "4f", "41", "33"},
				{"4d", "52", "4e", "34"},
		};
		String [][] fix = {
				{"24", "a3", "30", "fa"},
				{"45", "37", "19", "3e"},
				{"c1", "28", "a1", "74"},
				{"fb", "11", "bb", "06"},
		};
		key = new String[4][4];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				key [i][j] = hexToBin(pom[i][j]);
		
		copyValueOfMatrix(subTableBin, hexToBinMatrix(subTableHex));
		copyValueOfMatrix(invSubTableBin, hexToBinMatrix(invSubTableHex));
		copyValueOfMatrix(fixedTable, hexToBinMatrix(fix));
		copyValueOfMatrix(rconTableBin, hexToBinMatrix(rconTableHex));
		
	
	}

	//Pomocna fja
	public static void printMatrix(String [][] m) {	
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++)
				System.out.print(m[i][j] + " ");
			System.out.println();
		}	
	}
	
	public static String xorBytes(String byte1, String byte2) {
		//Primjenjuje operaciju xor nad dva byta koja se predaju
		String result = "";
		for (int i = 0; i < 8; i++) {
			String temp = "";
			if ((byte1.charAt(i) == '0' && byte2.charAt(i) == '1') || (byte1.charAt(i) == '1' && byte2.charAt(i) == '0'))
					temp = "1";
			else
				temp = "0";
			result+= temp;
		}
		return result;
	}
	
	public static int findIndex(String word) {
		return Integer.parseInt(word,2);
	}
	
	public static String[][] subBytes(String [][] state){
		String [][] temp = new String [4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				String leftHalf = state[i][j].substring(0, 4);
				String rightHalf = state[i][j].substring(4);
				int row = findIndex(leftHalf);
				int column = findIndex(rightHalf);
				temp[i][j] = subTableBin[row][column];
			}
		}
		return temp;
	}
	
	public static String[][] invSubBytes(String [][] state){
		String [][] temp = new String [4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				String leftHalf = state[i][j].substring(0, 4);
				String rightHalf = state[i][j].substring(4);
				int row = findIndex(leftHalf);
				int column = findIndex(rightHalf);
				temp[i][j] = invSubTableBin[row][column];
			}
		}
		return temp;
	}
	
	public static String[][] shiftRows(String [][] state){
		String [][] temp = new String [4][4];
		temp [0][0] = state[0][0]; temp [0][1] = state[0][1]; temp [0][2] = state[0][2]; temp [0][3] = state[0][3];
		temp [1][0] = state[1][1]; temp [1][1] = state[1][2]; temp [1][2] = state[1][3]; temp [1][3] = state[1][0];
		temp [2][0] = state[2][2]; temp [2][1] = state[2][3]; temp [2][2] = state[2][0]; temp [2][3] = state[2][1];
		temp [3][0] = state[3][3]; temp [3][1] = state[3][0]; temp [3][2] = state[3][1]; temp [3][3] = state[3][2];
		return temp;
	}
	
	public static String[][] invShiftRows(String [][] state){
		String [][] temp = new String [4][4];
		temp [0][0] = state[0][0]; temp [0][1] = state[0][1]; temp [0][2] = state[0][2]; temp [0][3] = state[0][3];
		temp [1][0] = state[1][3]; temp [1][1] = state[1][0]; temp [1][2] = state[1][1]; temp [1][3] = state[1][2];
		temp [2][0] = state[2][2]; temp [2][1] = state[2][3]; temp [2][2] = state[2][0]; temp [2][3] = state[2][1];
		temp [3][0] = state[3][1]; temp [3][1] = state[3][2]; temp [3][2] = state[3][3]; temp [3][3] = state[3][0];
		return temp;
	}
	
	public static String[][] shiftCols(String [][] state){
		String [][] temp = new String [4][4];
		temp [0][0] = state[0][0]; temp [0][1] = state[1][1]; temp [0][2] = state[2][2]; temp [0][3] = state[3][3];
		temp [1][0] = state[1][0]; temp [1][1] = state[2][1]; temp [1][2] = state[3][2]; temp [1][3] = state[0][3];
		temp [2][0] = state[2][0]; temp [2][1] = state[3][1]; temp [2][2] = state[0][2]; temp [2][3] = state[1][3];
		temp [3][0] = state[3][0]; temp [3][1] = state[0][1]; temp [3][2] = state[1][2]; temp [3][3] = state[2][3];
		return temp;
	}
	
	public static String[][] invShiftCols(String [][] state){
		String [][] temp = new String [4][4];
		temp [0][0] = state[0][0]; temp [0][1] = state[3][1]; temp [0][2] = state[2][2]; temp [0][3] = state[1][3];
		temp [1][0] = state[1][0]; temp [1][1] = state[0][1]; temp [1][2] = state[3][2]; temp [1][3] = state[2][3];
		temp [2][0] = state[2][0]; temp [2][1] = state[1][1]; temp [2][2] = state[0][2]; temp [2][3] = state[3][3];
		temp [3][0] = state[3][0]; temp [3][1] = state[2][1]; temp [3][2] = state[1][2]; temp [3][3] = state[0][3];
		return temp;
	}
	
	//Pomocna fja
	public static void copyValueOfMatrix(String [][] m1, String[][] m2){
		//Kopira vrijednost m2 u m1 posto u javi nemamo redefinisanje operatora =
		//ukoliko bismo samo koristili jednako to bi se samo preusmjeravale reference
		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m1[0].length; j++)
				m1[i][j] = m2[i][j];
		}
	}
	
	public static String[][] addRoundKey(String[][] currState, String[][] currKey){
		String [][] pom = new String[4][4];
		
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				pom[i][j] = xorBytes(currState[i][j], currKey[i][j]);
		
		return pom;
	}
	
	public static String[][] mixColumns(String[][] state){
		String [][] temp = new String [4][4];
		copyValueOfMatrix(temp, addRoundKey(state, fixedTable));
		copyValueOfMatrix(temp, shiftCols(temp));
		return temp;
	}
	
	public static String[][] invMixColumns(String[][] state){
		String [][] temp = new String [4][4];
		copyValueOfMatrix(temp, invShiftCols(state));
		copyValueOfMatrix(temp, addRoundKey(temp, fixedTable));
		return temp;
	}
	
	//Uzimamo poslednju kolonu iz kljuca i vrsimo pomjeranje bajta za po jedan "navise" zatim tako dobijenu kolonu
	//mijenjamo na osnovu sub tabele. Kada dobijemo tu kolonu, vrsimo operaciju xor sa prvom kolonom iz polaznog kljuca i xor sa
	//odgovarajucom kolonom iz fiksirane matrice rcon(koristi se indeks kolone zavisne od broja sub kljuca). Rezultat ove dvije xor
	//operacije daje nam prvu kolonu u subKey-u. sledece tri kolone se dobijaju tako sto se vrsi xor izmedju odgovarajuce kolone
	//iz polaznog kljuca i prethodnom kolonom subkey-a.
	//Postupak se ponavlja za ostatak sub kljuceva.
	public static String getLastColumn(int index) {
		String res = "";
		for (int i = 0; i < 4; i++) {
			res+= subKeys[index][i][3] + " ";
		}
		return res;
	}
	public static String[] getSubColumn(int index) {
		String lastCol = getLastColumn(index);
		String[] lastColSplited = lastCol.split(" ");
		String[] res = new String[4];
		
		for (int j = 0; j < 4; j++) {
			String leftHalf = lastColSplited[j].substring(0, 4);
			String rightHalf = lastColSplited[j].substring(4);
			int row = findIndex(leftHalf);
			int column = findIndex(rightHalf);
			res[j] = subTableBin[row][column];
		}
		String[] r = new String[4];
		r[0] = res[1]; r[1] = res[2]; r[2]= res[3]; r[3] = res[0]; 
		return r;
	
	}
	public static void generateKeys() {
		copyValueOfMatrix(subKeys[0], key);
		
		for (int i = 1; i < 11; i++) {
			String[] firstCol = new String[4]; String[] secondCol = new String[4]; String[] thirdCol = new String[4]; String[] fourthCol = new String[4];
			String [] subColumn = getSubColumn(i-1);
		
			firstCol[0] = xorBytes(xorBytes(subColumn[0],subKeys[i-1][0][0]), rconTableBin[0][i-1]);
			firstCol[1] = xorBytes(xorBytes(subColumn[1],subKeys[i-1][1][0]), rconTableBin[1][i-1]);
			firstCol[2] = xorBytes(xorBytes(subColumn[2],subKeys[i-1][2][0]), rconTableBin[2][i-1]);
			firstCol[3] = xorBytes(xorBytes(subColumn[3],subKeys[i-1][3][0]), rconTableBin[3][i-1]);
			
			secondCol[0] = xorBytes(firstCol[0],subKeys[i-1][0][1]);
			secondCol[1] = xorBytes(firstCol[1],subKeys[i-1][1][1]);
			secondCol[2] = xorBytes(firstCol[2],subKeys[i-1][2][1]);
			secondCol[3] = xorBytes(firstCol[3],subKeys[i-1][3][1]);
			
			thirdCol[0] = xorBytes(secondCol[0],subKeys[i-1][0][2]);
			thirdCol[1] = xorBytes(secondCol[1],subKeys[i-1][1][2]);
			thirdCol[2] = xorBytes(secondCol[2],subKeys[i-1][2][2]);
			thirdCol[3] = xorBytes(secondCol[3],subKeys[i-1][3][2]);
			
			fourthCol[0] = xorBytes(thirdCol[0],subKeys[i-1][0][3]);
			fourthCol[1] = xorBytes(thirdCol[1],subKeys[i-1][1][3]);
			fourthCol[2] = xorBytes(thirdCol[2],subKeys[i-1][2][3]);
			fourthCol[3] = xorBytes(thirdCol[3],subKeys[i-1][3][3]);
			
			subKeys[i][0][0] = firstCol[0]; subKeys[i][0][1] = secondCol[0]; subKeys[i][0][2] = thirdCol[0]; subKeys[i][0][3] = fourthCol[0];
			subKeys[i][1][0] = firstCol[1]; subKeys[i][1][1] = secondCol[1]; subKeys[i][1][2] = thirdCol[1]; subKeys[i][1][3] = fourthCol[1];
			subKeys[i][2][0] = firstCol[2]; subKeys[i][2][1] = secondCol[2]; subKeys[i][2][2] = thirdCol[2]; subKeys[i][2][3] = fourthCol[2];
			subKeys[i][3][0] = firstCol[3]; subKeys[i][3][1] = secondCol[3]; subKeys[i][3][2] = thirdCol[3]; subKeys[i][3][3] = fourthCol[3];
		}
	}
			
	
	public static void encrypt(String [][] state) {
		copyValueOfMatrix(state, addRoundKey(state, subKeys[0]));
		for (int i = 1; i < 10; i++) {
			copyValueOfMatrix(state, subBytes(state));
			copyValueOfMatrix(state, shiftRows(state));
			copyValueOfMatrix(state, mixColumns(state));
			copyValueOfMatrix(state, addRoundKey(state, subKeys[i]));
		}
		copyValueOfMatrix(state, subBytes(state));
		copyValueOfMatrix(state, shiftRows(state));
		copyValueOfMatrix(state, addRoundKey(state, subKeys[10]));

	}
	
	public static void decrypt(String [][] state) {
		copyValueOfMatrix(state, addRoundKey(state, subKeys[10]));
		
		for (int i = 9; i > 0; i--) {
			copyValueOfMatrix(state, invShiftRows(state));
			copyValueOfMatrix(state, invSubBytes(state));
			copyValueOfMatrix(state, addRoundKey(state, subKeys[i]));
			copyValueOfMatrix(state, invMixColumns(state));
			
		}
		copyValueOfMatrix(state, invShiftRows(state));
		copyValueOfMatrix(state, invSubBytes(state));
		copyValueOfMatrix(state, addRoundKey(state, subKeys[0]));
		
	}
	
	public static String convertCharToHex(char c) {
		int a = (int)c;
		return String.format("%1$02x",a);
	}
	
	public static String[][] convertStringToHex(String s){
		int br = 0;
		String [][]res = new String[4][4];
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				res[j][i] = convertCharToHex(s.charAt(br++));
			}
		}
		return res;
	}
	
	public static void convertHexToString (String[][] m) {
		String res = "";
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				res+= m[j][i];
			}
		}
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < res.length(); i+=2) {
		    String str = res.substring(i, i+2);
		    output.append((char)Integer.parseInt(str, 16));
		}
		System.out.println(output);

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		loadData();
	
		String [][] pom = new String[4][4];
		String [][] state = {
				{hexToBin("12"), hexToBin("05"), hexToBin("1e"), hexToBin("65")},
				{hexToBin("b0"), hexToBin("04"), hexToBin("0a"), hexToBin("7b")},
				{hexToBin("12"), hexToBin("99"), hexToBin("a3"), hexToBin("7c")},
				{hexToBin("1e"), hexToBin("17"), hexToBin("1e"), hexToBin("7a")}
		};
		// Primjer prevodjenja iz stringa u hexadecimalnu matricu na osnovu ASCII tabele
		copyValueOfMatrix(pom, convertStringToHex("Danas je nedelja"));
		//printMatrix(pom);
		
		long pocetak = new Date().getTime();
		
		System.out.println();
		
		//Primjer izvrsavanja funkcije SubBytes
		/*
		copyValueOfMatrix(pom, subBytes(hexToBinMatrix(pom)));
		printMatrix(binToHexMatrix(pom));
		//Inverzna operacija od subBytes
		System.out.println();
		copyValueOfMatrix(pom, invSubBytes(pom));
		printMatrix(binToHexMatrix(pom));
		System.out.println();
		
		*/
		//primjer za shift 
		/*
		copyValueOfMatrix(pom, shiftRows(hexToBinMatrix(pom)));
		printMatrix(binToHexMatrix(pom));
		System.out.println();
		//inverz
		copyValueOfMatrix(pom, invShiftRows(pom));
		printMatrix(binToHexMatrix(pom));
		System.out.println();
		*/
		
		/*
		copyValueOfMatrix(pom, mixColumns(hexToBinMatrix(pom)));
		printMatrix(binToHexMatrix(pom));
		System.out.println();
		copyValueOfMatrix(pom, invMixColumns(pom));
		printMatrix(binToHexMatrix(pom));
		System.out.println();
		*/
		
		//Pocetak glavnog primjera 
		generateKeys();
		
		copyValueOfMatrix(pom, hexToBinMatrix(pom));
		for (int i = 0; i < 1000; i++) {
		encrypt(pom);
		//printMatrix(binToHexMatrix(pom));
		//System.out.println();
		decrypt(pom);
		//printMatrix(binToHexMatrix(pom));
		//System.out.println();
		//convertHexToString(binToHexMatrix(pom));
		}
		
		long kraj = new Date().getTime();
        System.out.println("Potrebno vrijeme je bilo "+ (kraj - pocetak));
	
	}
	

	
}
