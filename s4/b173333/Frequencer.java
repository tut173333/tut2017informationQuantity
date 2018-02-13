package s4.b173333; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
import java.lang.*;
import s4.specification.*;

/*
interface FrequencerInterface {     // This interface provides the design for frequency counter.
    void setTarget(byte[]  target); // set the data to search.
    void setSpace(byte[]  space);  // set the data to be searched target from.
    int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
                    //Otherwise, it return 0, when SPACE is not set or Space's length is zero
                    //Otherwise, get the frequency of TAGET in SPACE
    int subByteFrequency(int start, int end);
    // get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
    // For the incorrect value of START or END, the behavior is undefined.
*/



public class Frequencer implements FrequencerInterface{
	// Code to start with: This code is not working, but good start point to work.
	byte [] myTarget;
	byte [] mySpace;
	boolean targetReady = false;
	boolean spaceReady = false;

	int []  suffixArray;

	// The variable, "suffixArray" is the sorted array of all suffixes of mySpace.
	// Each suffix is expressed by a interger, which is the starting position in mySpace.
	// The following is the code to print the variable
	private void printSuffixArray() {
		System.out.println("==== debug ====");
		if(spaceReady) {
			for(int i=0; i< mySpace.length; i++) {
				int s = suffixArray[i];
				for(int j = s; j < mySpace.length; j++) {
					System.out.print((char)mySpace[j]);
				}
				System.out.print('\n');
			}
		}
	}

	private int suffixCompare(int i, int j) {
		// comparing two suffixes by dictionary order.
		// i and j denoetes suffix_i, and suffix_j
		// i と j の短い方を基準にする(配列エラーを回避するため)
		int b = j;
		if(i > j){
			b = i;
		}

		// ※ 文字の比較ではなく文字列(suffix)を比較
		// 短い方の文字列分繰り返す
		for(int idx = 0; idx < mySpace.length - b ; idx++){
			// 文字列の先頭から一文字ずつ比較し、違う文字が見つかった時点で戻り値を返す
			// if suffix_i > suffix_j, it returns 1
			if(mySpace[i + idx] > mySpace[j + idx]){
				return 1;
			}
			// if suffix_i < suffix_j, it returns -1
			if(mySpace[i + idx] < mySpace[j + idx]){
				return -1;
			}
		}
		// if suffix_i = suffix_j, it returns 0;
		if(i < j) return 1; 
		if(i > j) return -1;
		return 0;

		// It is not implemented yet,
		// It should be used to create suffix array.
		// Example of dictionary order
		// "i"	  <  "o"		: compare by code
		// "Hi"	 <  "Ho"	   ; if head is same, compare the next element
		// "Ho"	 <  "Ho "	  ; if the prefix is identical, longer string is big
	}

	void merge(int suffa[],int suffb[],int suff[]){
		int i=0;
		int j=0;

		while(i<suffa.length || j<suffb.length){
			if(j>=suffb.length || (i<suffa.length && suffixCompare(suffb[j],suffa[i]) == 1)){
		  suff[i+j]=suffa[i];
		  i++;
			}
			else{

		  suff[i+j]=suffb[j];
		  j++;
			}
		  }

	}

	void mergeSort(int[] suff){
		if(suff.length>1){
		  int m=suff.length/2;
		  int n=suff.length-m;
		  int[] suffa=new int[m];
		  int[] suffb=new int[n];
		  for(int i=0;i<m;i++) suffa[i]=suff[i];
		  for(int i=0;i<n;i++) suffb[i]=suff[m+i];
		  mergeSort(suffa);
		  mergeSort(suffb);
		  merge(suffa,suffb,suff);
		}
	  }

	public void setSpace(byte []space) {
		mySpace = space;
		if(mySpace.length>0) spaceReady = true;
		suffixArray = new int[space.length];
		// put all suffixes  in suffixArray. Each suffix is expressed by one interger.
		for(int i = 0; i < space.length; i++) {
			suffixArray[i] = i;
		}
		//printSuffixArray();
		// // Sorting is not implmented yet.
		mergeSort(suffixArray);
		// バブルソート
		// for(int i = 0; i < space.length; i++){
		// 	for(int j = i + 1; j < space.length; j++){
		// 		if(suffixCompare(suffixArray[i], suffixArray[j]) == 1){
		// 			int tmp = suffixArray[i];
		// 			suffixArray[i] = suffixArray[j];
		// 			suffixArray[j] = tmp;
		// 		}
		// 	}
		// 	//  printSuffixArray();
		// }

		/* Example from "Hi Ho Hi Ho"
		0: Hi Ho
		1: Ho
		2: Ho Hi Ho
		3:Hi Ho
		4:Hi Ho Hi Ho
		5:Ho
		6:Ho Hi Ho
		7:i Ho
		8:i Ho Hi Ho
		9:o
		A:o Hi Ho
		*/
		//
		// printSuffixArray();
	}

	private int targetCompare(int i, int start, int end) {
		//It is called from subBytesStarIndex, and subBytesEndIndex
		//"start" and "end" are same as in subByteStartIndex, and subByteEndIndex **
		// target_start_end is subBytes(start, end) of target **
		// comparing suffix_i and target_start_end by dictonary order with limitation of length; ***
		// if the beginning of suffix_i matches target_start_end, and suffix is longer than target  it returns 0;


		if (i < 0) {return -1; }
		else if (i > mySpace.length - 1) { return 1; }

		i = suffixArray[i];

		int lim = end -start;
		if(mySpace.length - i < end - start) lim = mySpace.length - i;
		for(int k = 0;k < lim;k++){
			if(mySpace[i+k] > myTarget[start+k]) return 1;
			if(mySpace[i+k] < myTarget[start+k]) return -1;
		}
		if(mySpace.length - i < end - start) return -1;
		// if suffix_i > target_start_end it return 1;
		// if suffix_i < target_start_end it return -1
		// It is not implemented yet.
		// It should be used to search the apropriate index of some suffix.
		// Example of search
		// suffix		  target
		// "o"	   >	 "i"
		// "o"	   <	 "z"
		// "o"	   =	 "o"
		// "o"	   <	 "oo"
		// "Ho"	  >	 "Hi"
		// "Ho"	  <	 "Hz"
		// "Ho"	  =	 "Ho"
		// "Ho"	  <	 "Ho "   : "Ho " is not in the head of suffix "Ho"
		// "Ho"	  =	 "H"	 : "H" is in the head of suffix "Ho"

		//if(mySpace.length - suffixArray[i] > end - start) return 0;
		
		return 0;
	}

	private int subByteStartIndex(int start, int end) {		
		// It returns the index of the first suffix which is equal or greater than subBytes;
		// subBytes以上の最初のsaffixを返す
		// not implemented yet;
		// For "Ho", it will return 5  for "Hi Ho Hi Ho".
		// For "Ho ", it will return 6 for "Hi Ho Hi Ho".
		// int num = suffixArray.length/2;
		// if(suffixArray.length%2 == 1) num = suffixArray.length/2 +1;

		// if(targetCompare(suffixArray[i], start, end))
		// 以下サーチ

		int pLeft = 0;
		int pRight = suffixArray.length - 1;
		

		do{
			int center = (pLeft + pRight) / 2;
			// System.out.println(center - 1); 
			if (targetCompare(center,start,end) == 0 && targetCompare(center-1,start,end) == -1 ) {
				return center;
			} else if (targetCompare(center,start,end) == -1){
				pLeft = center + 1; 
			} else {
				pRight = center - 1;
			}
		}while (pLeft <= pRight);;

		// for(int i = 0; i < mySpace.length;i++ ){
		// 	if(targetCompare(i, start, end) == 0) {
		// 	//  System.out.println(i + " start");
		// 	return i;
		// 	}
		// }
		return suffixArray.length;
	}

	private int subByteEndIndex(int start, int end) {
		// It returns the next index of the first suffix which is greater than subBytes;
		// not implemented yet
		// For "Ho", it will return 7  for "Hi Ho Hi Ho".
		// For "Ho ", it will return 7 for "Hi Ho Hi Ho".

		// 以下サーチ
		int pLeft = 0;
		int pRight = suffixArray.length - 1;
		
		do{
			int center = (pLeft + pRight) / 2;
			// System.out.println(center - 1); 
			if (targetCompare(center,start,end) == 0 && targetCompare(center+1,start,end) == 1 ) {
				return center+1;
			} else if (targetCompare(center,start,end) == 1){
				pRight = center - 1;
			} else {
				pLeft = center + 1;
			}
		}while (pLeft <= pRight);


		// for(int i = 0; i < suffixArray.length;i++ ){
		// 	// System.out.println("tgtCom " + targetCompare(suffixArray[i], start, end));
		// 	if(targetCompare(i, start, end) == 1) {
		// 		//  System.out.println(i + " end");
		// 		return i;
		// 	}
		// }
		return suffixArray.length;
	}



	public int subByteFrequency(int start, int end) {
		// // This method could be defined as follows though it is slow.
		// int spaceLength = mySpace.length;
		// int count = 0;
		// for(int offset = 0; offset< spaceLength - (end - start); offset++) {
		// 	boolean abort = false;
		// 	for(int i = 0; i< (end - start); i++) {
		// 		if(myTarget[start+i] != mySpace[offset+i]) { abort = true; break; }
		// 	}
		// 	if(abort == false) { count++; }
		// }
		int first = subByteStartIndex(start,end);
		int last1 = subByteEndIndex(start, end);
		//inspection code
		// for(int k=start;k<end;k++) {
		//  System.out.write(myTarget[k]); 
		// }
		//  System.out.printf(": first=%d last1=%d\n", first, last1);
		
		return last1 - first;
	}

	public void setTarget(byte [] target) {
		myTarget = target;
		if(myTarget.length > 0) targetReady = true;
	}

	public int frequency() {
		if(targetReady == false) return -1;
		if(spaceReady == false) return 0;
		return subByteFrequency(0, myTarget.length);
	}

	public static void main(String[] args) {
		Frequencer frequencerObject;
		try {
			frequencerObject = new Frequencer();
			frequencerObject.setSpace("Hi Ho Hi Ho".getBytes());
			frequencerObject.setTarget("H".getBytes());
			int result = frequencerObject.frequency();
			System.out.print("Freq = "+ result+" ");
			if(4 == result) { System.out.println("OK"); }
				else {System.out.println("WRONG"); }
				
		}
		catch(Exception e) {
			System.out.println("STOP");
		}
	}
}
/*
public class Frequencer implements FrequencerInterface{
	// Code to Test, *warning: This code  contains intentional problem*
	byte [] myTarget;
	byte [] mySpace;
	public void setTarget(byte [] target) { myTarget = target;}
	public void setSpace(byte []space) { mySpace = space; }
	public int frequency() {
		if(myTarget == null){
			return -1;
		}
		if(mySpace == null){
			return 0;
		}
        int targetLength = myTarget.length;
    	int spaceLength = mySpace.length;
    	int count = 0;
    	if(targetLength == 0){
    		return -1;
    	}
    	else if(spaceLength == 0){
    		return 0;
    	}
        else{
            for(int start = 0; start<spaceLength; start++) { // Is it OK?
                boolean abort = false;
        		for(int i = 0; i<targetLength; i++) {
        		if(myTarget[i] != mySpace[start+i]) { abort = true; break; }
        		}
        		if(abort == false) { count++; }
        	}
        	return count;
        }
	}

	// I know that here is a potential problem in the declaration.
	public int subByteFrequency(int start, int length) {
	// Not yet, but it is not currently used by anyone.
	return -1;
	}

	public static void main(String[] args) {
	Frequencer myObject;
	int freq;
	try {
		System.out.println("checking my Frequencer");
		myObject = new Frequencer();
		myObject.setSpace("Hi Ho Hi Ho".getBytes());
		myObject.setTarget("H".getBytes());
		freq = myObject.frequency();
		System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
		if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
		System.out.println("Exception occurred: STOP");
	}
	}
}
*/
