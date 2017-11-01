import java.util.concurrent.ThreadLocalRandom;

public class RunNets {

	public static void main(String[] args) {
	 for(int i = 0; i<10; i++){
		 run() ;
	 }
	 
	  

	}
	
	public static void run(){
		Net n = new Net(1,5,1) ;
		double input[]= new double[50] ;
		double desired[]= new double[50];
		for(int i=0;i<50;i++){
			double d = ThreadLocalRandom.current().nextDouble(0,1000) ;
			input[i] = d ;
			desired[i]= d ;
		}
		//double error = n.error(n.calculate(input), desired, 50) ;
		//System.out.print(error);
		double results[] = new double[50] ;
		for(int x = 0; x<50; x++){
			results[x] = n.calculate(input[x]) ;
		}
		
		
	    //System.out.print(results[0]);
	    
		System.out.println("START") ;
		double error = n.error(results, desired, 50) ;
		System.out.println(error);
		double[] weights = n.returnWeights() ;
		for(int x = 0 ; x<10; x++){
			System.out.println(weights[x]);
		}
		System.out.println("END") ;
		
		
		
	}
	

}
