package model;

public class Stock {
	
	   private double value;
	   
	   public Stock() {
	   }

	   public Stock(double value) {
	      this.value = value;
	   }

	   public static Stock of(Stock stock) {
		   return new Stock(stock.getValue());
	   }

	   public double getValue() {
	      return value;
	   }
	   
	   public void setValue(double value) {
		      this.value=value;
	   }
}
