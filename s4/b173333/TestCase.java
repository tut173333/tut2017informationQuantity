package s4.b173333; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
import java.lang.*;
import s4.specification.*;

/*
interface FrequencerInterface {     // このインタフェースは、周波数カウンタの設計を提供します
    void setTarget(byte[]  target); // 検索するデータを設定します。
    void setSpace(byte[]  space);  // 検索対象となるデータを設定してください。
    int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
                    //Otherwise, it return 0, when SPACE is not set or Space's length is zero
					//Otherwise, get the frequency of TAGET in SPACE
					//TARGETが設定されていないか、TARGETの長さがゼロの場合は-1を返し,
					//それ以外の場合は0を返します。スペースが設定されていないかスペースの長さが0の場合は、
    int subByteFrequency(int start, int end);
	// get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
	//ターゲット[開始]、タグセット[開始+ 1]、...、ターゲット[終了-1]などのタグセットのサブバイトの頻度を取得します。
	// For the incorrect value of START or END, the behavior is undefined.
	//For the incorrect value of START or END, the behavior is undefined.
}
*/

/*
package s4.specification;
public interface InformationEstimatorInterface{
	void setTarget(byte target[]); // set the data for computing the information quantities
	//情報量を算出するためのデータを設定する
    void setSpace(byte space[]); // set data for sample space to computer probability
    double estimation(); // It returns 0.0 when the target is not set or Target's length is zero;
// It returns Double.MAX_VALUE, when the true value is infinite, or space is not set.
// The behavior is undefined, if the true value is finete but larger than Double.MAX_VALUE.
// Note that this happens only when the space is unreasonably large. We will encounter other problem anyway.
// Otherwise, estimation of information quantity, 
}                        
*/


public class TestCase {
    public static void main(String[] args) {
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("checking s4.b173333.Frequencer");
	    myObject = new s4.b173333.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
		if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
		//targetが"o"
		myObject.setTarget("o".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"o\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(2 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

	
	try {
	    InformationEstimatorInterface myObject;
	    double value;
	    System.out.println("checking s4.b173333.InformationEstimator");
	    myObject = new s4.b173333.InformationEstimator();
	    myObject.setSpace("3210321001230123".getBytes());
	    myObject.setTarget("0".getBytes());
	    value = myObject.estimation();
	    System.out.println(">0 "+value);
	    myObject.setTarget("01".getBytes());
	    value = myObject.estimation();
	    System.out.println(">01 "+value);
	    myObject.setTarget("0123".getBytes());
	    value = myObject.estimation();
	    System.out.println(">0123 "+value);
	    myObject.setTarget("00".getBytes());
	    value = myObject.estimation();
	    System.out.println(">00 "+value);
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    }
}	    
	    
