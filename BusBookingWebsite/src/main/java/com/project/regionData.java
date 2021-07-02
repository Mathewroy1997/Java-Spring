package com.project;


	
	

	public class regionData {

		private String region;
		private long activeCases;
		private long newInfected;
		private long recovered;
		private long newRecovered;
		private long deceased;
		private long newDeceased;
		private long totalInfected;
		public String getRegion() {
			return region;
		}
		public void setRegion(String region) {
			this.region = region;
		}
		public long getActiveCases() {
			return activeCases;
		}
		public void setActiveCases(long activeCases) {
			this.activeCases = activeCases;
		}
		public long getNewInfected() {
			return newInfected;
		}
		public void setNewInfected(long newInfected) {
			this.newInfected = newInfected;
		}
		public long getRecovered() {
			return recovered;
		}
		public void setRecovered(long recovered) {
			this.recovered = recovered;
		}
		public long getNewRecovered() {
			return newRecovered;
		}
		public void setNewRecovered(long newRecovered) {
			this.newRecovered = newRecovered;
		}
		public long getDeceased() {
			return deceased;
		}
		public void setDeceased(long deceased) {
			this.deceased = deceased;
		}
		public long getNewDeceased() {
			return newDeceased;
		}
		public void setNewDeceased(long newDeceased) {
			this.newDeceased = newDeceased;
		}
		public long getTotalInfected() {
			return totalInfected;
		}
		public void setTotalInfected(long totalInfected) {
			this.totalInfected = totalInfected;
		}
		@Override
		public String toString() {
			return "regionData [region=" + region + ", activeCases=" + activeCases + ", newInfected=" + newInfected
					+ ", recovered=" + recovered + ", newRecovered=" + newRecovered + ", deceased=" + deceased
					+ ", newDeceased=" + newDeceased + ", totalInfected=" + totalInfected + "]";
		}
		
		
		
		
		
	
}
