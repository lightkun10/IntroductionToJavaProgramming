public class Question5_35
{
	public static void main(String[] args)
	{
		double sum = 0.0;
		for (int i = 1; i < 625; i++)
		{
			sum += (1 / (Math.sqrt(i) + Math.sqrt(i + 1)));
		}

		System.out.println("Result: " + sum);
		// in this case, floating-point arithmetic comes quite close to the real result
	}
}