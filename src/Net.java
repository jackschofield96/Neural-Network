import java.util.concurrent.ThreadLocalRandom;

public class Net {

	private int inputNeurons ;
	private int hiddenNeurons ;
	private int outputNeurons ;
	private int neurons ;
	private int weightNum ;
	private double intermediateOutputs[];
	private double weights[] ;
	private double activationThresholds[] ;

	
	public Net(int inputCount, int hiddenCount, int outputCount){
		this.inputNeurons = inputCount ;
		this.hiddenNeurons = hiddenCount ;
		this.outputNeurons = outputCount ;
		this.neurons = inputCount + hiddenCount + outputCount ;
		weightNum = (inputCount * hiddenCount) + (hiddenCount * outputCount);
		weights = new double[weightNum];
        intermediateOutputs = new double[neurons] ;
		
		activationThresholds = new double[neurons] ;
		set() ;
		
	}
	//randomise weights and activation thresholds.
	public void set() {
		  int i;

		  for (i = 0; i < neurons; i++) {
		   activationThresholds[i] = 0.5 - (Math.random()) ;
		  }
		  for (i = 0; i < weights.length; i++) {
		   weights[i] = ThreadLocalRandom.current().nextDouble(-5,5) ;
			
		  }
	}
	//sigmoid function
	 public double threshold(double sum) {
		  return 1.0 / (1 + Math.exp(-1.0 * sum));
		 }
	
	 //return weights
	 public double[] returnWeights() {
		 return weights ;
	 }
	
	
	public double calculate(double input) {
		int i, j;
		  final int hiddenIndex = inputNeurons;
		  final int outIndex = inputNeurons + hiddenNeurons;

		  //inputs
		  for (i = 0; i < inputNeurons; i++) {
		   intermediateOutputs[i] = input;
		  }
		  int inx = 0;
          //hidden layer
		  for (i = hiddenIndex; i < outIndex; i++) {
		   double sum = activationThresholds[i];

		   for (j = 0; j < inputNeurons; j++) {
		    sum += intermediateOutputs[j] * weights[inx++];
		   }
		   intermediateOutputs[i] = threshold(sum);
		  }

		  double result[] = new double[outputNeurons];
          //output
		  for (i = outIndex; i < neurons; i++) {
		   double sum = activationThresholds[i];

		   for (j = hiddenIndex; j < outIndex; j++) {
		    sum += intermediateOutputs[j] * weights[inx++];
		   }
		   intermediateOutputs[i] = threshold(sum);
		   result[i-outIndex] = intermediateOutputs[i];
		  }

		  return result[0];
	}
	
	//mean error
	public double error(double results[], double desired[], int trainingLength){
		double sum = 0 ;
		for(int i = 0 ; i < trainingLength ; i++  ){
			sum += desired[i] - results[i] ;
			//System.out.println(sum);
		}
		sum = sum / trainingLength ;
		//System.out.println(sum);
		return sum ;
	}
	
	
	
	
}
